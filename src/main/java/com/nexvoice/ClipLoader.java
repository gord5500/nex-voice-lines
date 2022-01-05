package com.nexvoice;


import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

@Singleton
@Slf4j
public class ClipLoader {

    private static final long CLIP_MTIME_UNLOADED = -2;

    private long lastClipMTime = CLIP_MTIME_UNLOADED;
    private Clip clip = null;

    @Inject
    private NexVoiceConfig config;

    @Setter
    private int soundVolume = 100;

    private boolean loadClip(Sound sound) {

        try (InputStream resourceStream = ClipLoader.class.getResourceAsStream("/" + sound.getResourceName())) {
            if (resourceStream == null) {
                log.warn("Failed to load sound " + sound.getResourceName() + " as resource stream was null!");
            } else {
                try (InputStream fileStream = new BufferedInputStream(resourceStream);
                     AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(fileStream)) {
                    clip.open(audioInputStream); // liable to error with pulseaudio, works on windows, no clue about mac
                }
                return true;
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            log.warn("Failed to load sound " + sound, e);
        }
        return false;
    }

    public void playClip(Sound sound) {
        long currentMTime = System.currentTimeMillis();
        if (clip == null || currentMTime != lastClipMTime || !clip.isOpen()) {
            if (clip != null && clip.isOpen()) {
                clip.close();
            }

            try {
                clip = AudioSystem.getClip();
            } catch (LineUnavailableException e) {
                lastClipMTime = CLIP_MTIME_UNLOADED;
                log.warn("Failed to get clip for sound " + sound, e);
                return;
            }

            lastClipMTime = currentMTime;
            if (!loadClip(sound)) {
                return;
            }
        }

        // User configurable volume
        FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        float gain = 20f * (float) Math.log10(config.volume() / 100f);
        gain = Math.min(gain, volume.getMaximum());
        gain = Math.max(gain, volume.getMinimum());
        volume.setValue(gain);

        // From RuneLite base client Notifier class:
        // Using loop instead of start + setFramePosition prevents the clip
        // from not being played sometimes, presumably a race condition in the
        // underlying line driver
        clip.loop(0);
    }
}

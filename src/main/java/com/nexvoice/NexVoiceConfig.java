package com.nexvoice;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("NexVoice")
public interface NexVoiceConfig extends Config
{

	@ConfigItem(
			keyName = "Volume",
			name = "Volume",
			description = "The volume of the sound effects"
	)
	default int volume() { return 100;}
}

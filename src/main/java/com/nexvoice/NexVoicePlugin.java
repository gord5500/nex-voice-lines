package com.nexvoice;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Actor;
import net.runelite.api.Client;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.NpcDespawned;
import net.runelite.api.events.NpcSpawned;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
	name = "Nex Voice Lines"
)
public class NexVoicePlugin extends Plugin
{
	private Actor nex;
	private String lastText = "";

	@Inject
	private Client client;

	@Inject
	private ClipLoader clipLoader;

	@Subscribe
	public void onGameTick(GameTick event) {

		nex = client.getLocalPlayer();

		if (nex != null && nex.getOverheadText() != null) {

			String text = nex.getOverheadText();
			System.out.println("Overhead -> " + text);
			Sound line = null;
			if (text != null && !text.equals(lastText)) {

				switch (text.toLowerCase()) {
					case "at last!":
						line = Sound.AT_LAST;
						break;
					case "fumus!":
						line = Sound.FUMUS;
						break;
					case "umbra!":
						line = Sound.UMBRA;
						break;
					case "cruor!":
						line = Sound.CRUOR;
						break;
					case "glacies!":
						line = Sound.GLACIES;
						break;
					case "fill my soul with smoke!":
						line = Sound.FILL_MY_SOUL;
						break;
					case "no escape!":
						line = Sound.NO_ESCAPE;
						break;
					case "let the virus flow through you!":
						line = Sound.LET_THE_VIRUS;
						break;
					case "there is...":
						line = Sound.THERE_IS;
						break;
					case "fumus, don't fail me!":
						line = Sound.FUMUS_DONT_FAIL_ME;
						break;
					case "embrace darkness!":
						line = Sound.EMBRACE_DARKNESS;
						break;
					case "darken my shadow!":
						line = Sound.DARKEN_MY_SHADOW;
						break;
					case "fear the shadow!":
						line = Sound.FEAR_THE_SHADOW;
						break;
					case "umbra, don't fail me!":
						line = Sound.UMBRA_DONT_FAIL_ME;
						break;
					case "flood my lungs with blood!":
						line = Sound.FLOOD_MY_LUNGS;
						break;
					case "a siphon will solve this!":
						line = Sound.SIPHON;
						break;
					case "i demand a blood sacrifice!":
						line = Sound.I_DEMAND_A_BLOOD;
						break;
					case "cruor, don't fail me!":
						line = Sound.CRUOR_DONT_FAIL_ME;
						break;
					case "infuse me with the power of ice!":
						line = Sound.POWER_OF_ICE;
						break;
					case "contain this!":
						line = Sound.CONTAIN_THIS;
						break;
					case "die now, in a prison of ice!" :
						line = Sound.DIE_NOW;
						break;
					case "glacies, don't fail me!":
						line = Sound.GLACIES_DONT_FAIL_ME;
						break;
					case "now, the power of zaros!":
						line = Sound.POWER_OF_ZAROS;
						break;
					case "taste my wrath!":
						line = Sound.WRATH;
						break;
				}

				if (line != null) {

					System.out.println("Playing sound " + line.toString());
					Sound finalLine = line;
					new Thread(() -> {

						clipLoader.playClip(finalLine);
					}).start();
				}

				lastText = text;
			}
		} else {
			lastText = "";
		}
	}

	@Subscribe
	public void onNpcSpawned(NpcSpawned event) {

		if (event.getNpc() != null && event.getNpc().getName() != null && event.getNpc().getName().contains("Nex")) {

			System.out.println("Nex spawned!");
			nex = event.getNpc();
		}
	}


	@Subscribe
	public void onNpcDespawned(NpcDespawned event) {

		if (event.getNpc() != null && event.getNpc().getName() != null && event.getNpc().getName().contains("Nex")) {

			System.out.println("Nex despawned!");
			nex = null;
		}
	}

	@Provides
	NexVoiceConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(NexVoiceConfig.class);
	}
}

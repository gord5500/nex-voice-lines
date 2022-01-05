package com.nexvoice;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class NexVoiceTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(NexVoicePlugin.class);
		RuneLite.main(args);
	}
}
package de.plugdev.bungee.listener;

import de.plugdev.bungee.CloudBridge;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerPing implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerPingEvent(ProxyPingEvent event) {
		ServerPing ping = event.getResponse();
		ping.setDescription(CloudBridge.getCloudBridge().getTempInformations().getMotd());
		event.setResponse(ping);
	}

}

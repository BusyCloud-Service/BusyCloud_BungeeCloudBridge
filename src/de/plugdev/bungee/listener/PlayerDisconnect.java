package de.plugdev.bungee.listener;

import de.plugdev.bungee.CloudBridge;
import de.terrarier.netlistening.api.DataContainer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerDisconnect implements Listener {
	
	@EventHandler
	public void onPlayerDisconnect(PlayerDisconnectEvent event) {
		DataContainer container2 = new DataContainer();
		container2.add("Proxy");
		container2.add("playerdisconnect");
		container2.add(CloudBridge.getCloudBridge().getCloudKey());
		container2.add(event.getPlayer().getUniqueId());
		CloudBridge.getCloudBridge().getClient().sendData(container2);
		
		if(CloudBridge.getCloudBridge().getTempInformations().getPlayerList().contains(event.getPlayer())) {
			CloudBridge.getCloudBridge().getTempInformations().getPlayerList().remove(event.getPlayer());
		}
	}
	
}

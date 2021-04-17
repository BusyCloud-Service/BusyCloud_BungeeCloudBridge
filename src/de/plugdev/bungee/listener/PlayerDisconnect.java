package de.plugdev.bungee.listener;

import de.plugdev.bungee.CloudBridge;
import de.terrarier.netlistening.api.DataContainer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerDisconnect implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerDisconnect(PlayerDisconnectEvent event) {
		DataContainer container = new DataContainer();
		container.add("Proxy");
		container.add("playerconnect");
		container.add(CloudBridge.getCloudBridge().getCloudKey());
		container.add(event.getPlayer().getUniqueId().toString());
		container.add(event.getPlayer().getUniqueId());
		container.add(event.getPlayer().getServer().getInfo().getName());
		container.add(event.getPlayer().getAddress().getAddress());
		
		DataContainer container2 = new DataContainer();
		container.add("Proxy");
		container.add("playerdisconnect");
		container.add(CloudBridge.getCloudBridge().getCloudKey());
		container.add(event.getPlayer().getUniqueId());
		CloudBridge.getCloudBridge().getClient().sendData(container2);
		
		if(CloudBridge.playerList.contains(event.getPlayer())) {
			CloudBridge.playerList.remove(event.getPlayer());
		}
	}
	
}

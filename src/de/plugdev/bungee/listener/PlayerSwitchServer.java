package de.plugdev.bungee.listener;

import de.plugdev.bungee.CloudBridge;
import de.terrarier.netlistening.api.DataContainer;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerSwitchServer implements Listener {
	
	@EventHandler
	public void onPlayerSwitchServer(ServerSwitchEvent event) {
		DataContainer container = new DataContainer();
		container.add("Proxy");
		container.add("playerswitchserver");
		container.add(CloudBridge.getCloudBridge().getCloudKey());
		container.add(event.getPlayer().getUniqueId());
		container.add(event.getFrom().getName());
		container.add(event.getPlayer().getServer().getInfo().getName());
		
		CloudBridge.getCloudBridge().getClient().sendData(container);
	}
	
}

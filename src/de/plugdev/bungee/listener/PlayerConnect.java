package de.plugdev.bungee.listener;

import de.plugdev.bungee.CloudBridge;
import de.terrarier.netlistening.api.DataContainer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerConnect implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerJoin(PostLoginEvent event) {
		DataContainer container = new DataContainer();
		container.add("Proxy");
		container.add("playerconnect");
		container.add(CloudBridge.getCloudBridge().getCloudKey());
		container.add(event.getPlayer().getName());
		container.add(event.getPlayer().getUniqueId().toString());
		container.add("None");
		container.add(event.getPlayer().getAddress().getAddress().getHostAddress());

		if (CloudBridge.getCloudBridge().getClient() != null) {
			CloudBridge.getCloudBridge().getClient().sendData(container);
		}

	}

	@EventHandler
	public void onPlayerTarget(ServerConnectEvent event) {
		if (!CloudBridge.getCloudBridge().getTempInformations().getMainList().isEmpty()) {
			if(!CloudBridge.getCloudBridge().getTempInformations().getPlayerList().contains(event.getPlayer())) {
				
				CloudBridge.getCloudBridge().getTempInformations().getPlayerList().add(event.getPlayer());
				ServerInfo prefferedInfo = null;
				for(ServerInfo info : CloudBridge.getCloudBridge().getTempInformations().getMainList()) {
					if(prefferedInfo == null) {
						prefferedInfo = info;
					}
					if(info.getPlayers().size() <= prefferedInfo.getPlayers().size()) {
						prefferedInfo = info;
					}
				}
				event.setTarget(prefferedInfo);
			}
		}
	}

}

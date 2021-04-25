package de.plugdev.bungee.networking;

import java.net.InetSocketAddress;

import de.plugdev.bungee.CloudBridge;
import de.terrarier.netlistening.api.event.DecodeEvent;
import de.terrarier.netlistening.api.event.DecodeListener;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;

public class RconServerRegistry implements DecodeListener {

	@Override
	public void trigger(DecodeEvent event) {
		final String key = event.getData().read();
		if (key.equalsIgnoreCase("registerserver")) {
			String serverName = event.getData().read();
			InetSocketAddress address = new InetSocketAddress("localhost", event.getData().read());
			String serverMotd = "BusyCloud-Server | " + serverName;
			boolean isMain = event.getData().read();

			ServerInfo info = ProxyServer.getInstance().constructServerInfo(serverName, address, serverMotd, false);
			ProxyServer.getInstance().getServers().put(info.getName(), info);

			if(isMain) {
				CloudBridge.getCloudBridge().getTempInformations().getMainList().add(info);
			}
		} else if (key.equalsIgnoreCase("unregisterserver")) {

			String serverName = event.getData().read();

			ServerInfo info = ProxyServer.getInstance().getServerInfo(serverName);
			ProxyServer.getInstance().getServers().remove(info.getName());

			if (CloudBridge.getCloudBridge().getTempInformations().getMainList().contains(info)) {
				CloudBridge.getCloudBridge().getTempInformations().getMainList().remove(info);
			}
		}
	}

}

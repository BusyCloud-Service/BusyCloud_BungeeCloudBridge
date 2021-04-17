package de.plugdev.bungee.networking;

import de.terrarier.netlistening.api.event.DecodeEvent;
import de.terrarier.netlistening.api.event.DecodeListener;
import net.md_5.bungee.api.ProxyServer;

public class RconDecoder implements DecodeListener {

	@Override
	public void trigger(DecodeEvent event) {
		final String key = event.getData().read();
		if(key.equalsIgnoreCase("rcon")) {
			String nextMessage;
			while((nextMessage = event.getData().read()) != null) {
				ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), nextMessage);
			}
		}
	}

}

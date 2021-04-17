package de.plugdev.bungee.networking;

import de.plugdev.bungee.CloudBridge;
import de.terrarier.netlistening.api.DataContainer;
import de.terrarier.netlistening.api.event.DecodeEvent;
import de.terrarier.netlistening.api.event.DecodeListener;

public class DecodePingListener implements DecodeListener {
	
	@Override
	public void trigger(DecodeEvent event) {
		final String key = event.getData().read();
		if(key.equalsIgnoreCase("ping")) {
			
			long got = event.getData().read();
			DataContainer container = new DataContainer();
			container.add("returnping");
			container.add(got);
			container.add(System.currentTimeMillis());
			CloudBridge.getCloudBridge().getClient().sendData(container);
		}
	}
	
}

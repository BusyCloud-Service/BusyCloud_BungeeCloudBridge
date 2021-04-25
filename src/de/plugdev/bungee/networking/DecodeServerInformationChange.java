package de.plugdev.bungee.networking;

import de.plugdev.bungee.CloudBridge;
import de.terrarier.netlistening.api.DataContainer;
import de.terrarier.netlistening.api.event.DecodeEvent;
import de.terrarier.netlistening.api.event.DecodeListener;

public class DecodeServerInformationChange implements DecodeListener {
	
	@Override
	public void trigger(DecodeEvent event) {
		final String key = event.getData().read();
		if(key.equalsIgnoreCase("changebungeeinfo")) {
			DataContainer container = event.getData();
			String targetSetting = container.read();
			switch (targetSetting.toLowerCase()) {
			case "change#motd":
				CloudBridge.getCloudBridge().getTempInformations().setMotd(container.read());
				break;
			case "change#maxplayers":
				CloudBridge.getCloudBridge().getTempInformations().setMaxPlayers(container.read());
				break;
			case "change#maintenance":
				boolean maintenanceMode = false;
				CloudBridge.getCloudBridge().getTempInformations().setMaintenance((maintenanceMode = container.read()));
				if(maintenanceMode)
					CloudBridge.getCloudBridge().getTempInformations().setMaintenanceMessage(container.read());
				break;
			default:
				break;
			}
		}
	}
	
}

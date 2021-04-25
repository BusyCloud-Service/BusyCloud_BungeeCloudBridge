package de.plugdev.bungee.utils;

import java.util.LinkedList;
import java.util.List;

import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class TempInformations {
	
	private String motd = "§9BusyCloud §c| §dCloud is loading...";
	private int maxPlayers;
	
	private boolean maintenance;
	private String maintenanceString = "§9BusyCloud §c| §dThis instance is in maintenance-mode.";

	public List<ServerInfo> mainList = new LinkedList<>();
	public List<ProxiedPlayer> playerList = new LinkedList<>();
	
	public List<ServerInfo> getMainList() {
		return mainList;
	}
	
	public List<ProxiedPlayer> getPlayerList() {
		return playerList;
	}
	
	public String getMotd() {
		return motd;
	}
	
	public int getMaxPlayers() {
		return maxPlayers;
	}
	
	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}
	
	public void setMotd(String motd) {
		this.motd = motd;
	}

	public void setMaintenance(boolean maintenanceMode) {
		this.maintenance = maintenanceMode;
	}
	
	public void setMaintenanceMessage(String message) {
		this.maintenanceString = message;
	}
	
	public boolean isMaintenance() {
		return maintenance;
	}
	
	public String getMaintenanceString() {
		return maintenanceString;
	}
	
}

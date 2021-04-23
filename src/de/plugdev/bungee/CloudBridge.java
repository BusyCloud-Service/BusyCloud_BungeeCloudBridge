package de.plugdev.bungee;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import de.plugdev.bungee.commands.CommandHub;
import de.plugdev.bungee.listener.PlayerConnect;
import de.plugdev.bungee.listener.PlayerDisconnect;
import de.plugdev.bungee.listener.PlayerSwitchServer;
import de.plugdev.bungee.networking.DecodePingListener;
import de.plugdev.bungee.networking.RconDecoder;
import de.plugdev.bungee.networking.RconServerRegistry;
import de.terrarier.netlistening.Client;
import de.terrarier.netlistening.api.DataContainer;
import de.terrarier.netlistening.api.event.ConnectionTimeoutEvent;
import de.terrarier.netlistening.api.event.ConnectionTimeoutListener;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;

public class CloudBridge extends Plugin {

	private Client client;
	private static CloudBridge cloudBridge;
	private String cloudKey;
	
	public static List<ServerInfo> mainList = new LinkedList<>();
	public static List<ProxiedPlayer> playerList = new LinkedList<>();

	public static void main(String[] args) {
	}
	
	@Override
	public void onEnable() {

		cloudBridge = this;

		client = new Client.Builder("localhost", 1130).timeout(15000).build();
		
		File con = null;
		for (File file : new File(".").listFiles()) {
			if (file.getName().startsWith("KEY_") && file.isFile()) {
				cloudKey = file.getName();
				con = file;
			}
		}
		ProxyServer.getInstance().getConsole().sendMessage(new TextComponent(cloudKey));
		con.delete();
		
		DataContainer dataContainer = new DataContainer();
		dataContainer.add("Proxy");
		dataContainer.add("linkproxy");
		dataContainer.add(cloudKey);
		client.sendData(dataContainer);

		client.registerListener(new RconDecoder());
		client.registerListener(new RconServerRegistry());
		client.registerListener(new DecodePingListener());
		client.registerListener(new ConnectionTimeoutListener() {
			
			@Override
			public void trigger(ConnectionTimeoutEvent event) {
				ProxyServer.getInstance().getConsole().sendMessage(new TextComponent("§cConnection to Cloud timed out. Stopping Cloud."));
				ProxyServer.getInstance().stop("§5[BusyCloud-Service] §cConnection to Cloud timed out. Stopping Cloud.");
			}
		});

		ProxyServer.getInstance().getPluginManager().registerListener(this, new PlayerConnect());
		ProxyServer.getInstance().getPluginManager().registerListener(this, new PlayerDisconnect());
		ProxyServer.getInstance().getPluginManager().registerListener(this, new PlayerSwitchServer());
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new CommandHub("hub"));

	}

	@Override
	public void onDisable() {
		client.stop();
	}

	public Client getClient() {
		return client;
	}

	public String getCloudKey() {
		return cloudKey;
	}

	public static CloudBridge getCloudBridge() {
		return cloudBridge;
	}

}

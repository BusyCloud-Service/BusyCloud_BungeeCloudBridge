package de.plugdev.bungee.commands;

import de.plugdev.bungee.CloudBridge;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class CommandHub extends Command {

	public CommandHub(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(args.length == 0) {
			ProxiedPlayer player = (ProxiedPlayer) sender;
			ServerInfo prefferedInfo = null;
			for(ServerInfo info : CloudBridge.mainList) {
				if(prefferedInfo == null) {
					prefferedInfo = info;
				}
				if(info.getPlayers().size() <= prefferedInfo.getPlayers().size()) {
					prefferedInfo = info;
				}
			}
			player.connect(prefferedInfo);
		}
	}

}

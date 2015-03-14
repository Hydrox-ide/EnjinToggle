package me.Hydroxide.EnjinToggle;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHandler implements CommandExecutor {
	public Main plugin;
	
	ArrayList<Player>toggled = new ArrayList<Player>();
	
	public CommandHandler(Main plugin){
		this.plugin = plugin;
		}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Console commands are not supported by this plugin.");
			return false;
		}
		
		Player player = (Player) sender;
		
		if(label.equalsIgnoreCase("admin") && player.hasPermission("admin.toggle")) {
			if(args.length == 0) {
				player.sendMessage(ChatColor.RED + "Please specify args.");
				return false;
			}	
			if(args.length == 1) {
				if(args[0].equalsIgnoreCase("on")) {
					if(!(toggled.contains(player))) {
					String coloredOn = plugin.getConfig().getString("toggleOnMessage").replaceAll("(&([a-f0-9]))", "\u00A7$2");
					player.sendMessage(coloredOn);
					Main.perms.playerAdd(player, plugin.getConfig().getString("adminPermission"));
					toggled.add(player);
					return false;
					} else {
						String alreadyOn = plugin.getConfig().getString("alreadyToggledMessage").replaceAll("(&([a-f0-9]))", "\u00A7$2");
						player.sendMessage(alreadyOn);
						return false;
					}
				} else if(args[0].equalsIgnoreCase("off")) { 
					if(toggled.contains(player)) {
					String coloredOff = plugin.getConfig().getString("toggleOffMessage").replaceAll("(&([a-f0-9]))", "\u00A7$2");
					player.sendMessage(coloredOff);
					Main.perms.playerRemove(player, plugin.getConfig().getString("adminPermission"));
					toggled.remove(player);
					return false;
					} else if(!(toggled.contains(player))) {
						String alreadyOff = plugin.getConfig().getString("toggleOffMessage").replaceAll("(&([a-f0-9]))", "\u00A7$2");
						player.sendMessage(alreadyOff);
						return false;
					}
				}
			}
	  }
		else if(!(player.hasPermission("admin.toggle"))) {
		  player.sendMessage(ChatColor.RED + "No permission.");
		  return false;
	  }
		return false;
      }	
}

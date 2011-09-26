package me.techykid3.SuperPath;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

public class SuperPath extends JavaPlugin {
Logger log = Logger.getLogger("Minecraft");
public static ArrayList<Player> using = new ArrayList<Player>();
public static ArrayList<Material> blacklist = new ArrayList<Material>();
private SuperPathPlayerListener pListener = new SuperPathPlayerListener(this);
public static Configuration config;
public static Material type;

	public void onEnable(){
		PluginManager pm =  this.getServer().getPluginManager();
			log.info("[SuperPath] has been enabled!");
			log.info("[SuperPath] is coded by Techykid3");
			log.info("[SuperPath] is on version *v0.0.0.1*, for *CraftBukkit Recommended Build #1185*!");
			System.out.println("[SuperPath] programmed by InfinityCraft (infmc.co.cc) developers!");
			pm.registerEvent(Event.Type.PLAYER_MOVE, pListener, Event.Priority.High, this); //Can't be normal priority...
			config = getConfiguration();
			type =  Material.getMaterial(config.getInt("BlockType", 1));	
			String input= config.getString("BlacklistedBlocks", "46");
			String[] items = input.split(";");
			 for(String item : items){
				 blacklist.add(Material.getMaterial(item));
			 }
			config.save();		
	}
	
	public void onDisable(){
		
			log.info("[SuperPath] has been disabled!");
			
	}
	public boolean onCommand(CommandSender sender, Command cmd, String CommandLabel,
			String[] args) {
		if(CommandLabel.equalsIgnoreCase("track")){ //Checking if the player typed /track
		if(sender instanceof Player){ //Checking if the sender is player
			if(sender.isOp()||sender.hasPermission("SuperPath.use")){
			Player player = (Player) sender;
			if(using.contains(player)){
				using.remove(player);
				sender.sendMessage("Finished making track!");
			}else{
				using.add(player);
				sender.sendMessage("Check it out you are now making a path!");
				this.getServer().broadcastMessage(ChatColor.RED + "[SuperPath] is being used by: " + ChatColor.AQUA + ((Player) sender).getDisplayName());
			}
			}
		}else{
			sender.sendMessage("Only ingame players can use this command!");
		}
		}else if(CommandLabel.equalsIgnoreCase("tracktype")){
			if(sender instanceof Player){
				if(sender.isOp()||sender.hasPermission("SuperPath.settype")){
					if(args.length>0){
					if(blacklist.contains(args[0])){
						sender.sendMessage("That block is on the blacklist! Try something else!");
					}else{
						config.setProperty("BlockType", args[0]);	    
				    config.save();
				    //Reload
				    config = new Configuration(new File("plugins/SuperPath/config.yml"));
				    config.load();
					}
				}
			}
		}
		}
		return true;
	}
	
}
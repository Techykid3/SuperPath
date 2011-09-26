package me.techykid3.SuperPath;


import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;

public class SuperPathPlayerListener extends PlayerListener{
	@SuppressWarnings("unused")
	private SuperPath plugin;

	public SuperPathPlayerListener(SuperPath plugin) {
		this.plugin = plugin;
	}
	
	public void onPlayerMove(PlayerMoveEvent event){
		Player player = event.getPlayer();
		if(SuperPath.using.contains(player)){
			//System.out.println("Yes contains!"); //debug line xD
			Block block = player.getLocation().getBlock().getRelative(BlockFace.DOWN);
			if((!block.isEmpty())&& (!block.isLiquid())){
				block.setType(SuperPath.type);
				player.getLocation();
				player.sendMessage(ChatColor.GOLD + "Current Coordinates: " + ChatColor.YELLOW + "X: " + ChatColor.AQUA + player.getLocation().getBlockX() + " " + ChatColor.YELLOW + "Y: " + ChatColor.AQUA + player.getLocation().getBlockY() + " " + ChatColor.YELLOW + "Z: " + ChatColor.AQUA + player.getLocation().getBlockZ()); //WTF? Display the message ONLY every 30 blocks the person is placing.
				player.sendMessage(ChatColor.GOLD + "Current Block ID: " + ChatColor.RED + SuperPath.type);
				
			}
		}
	}
}

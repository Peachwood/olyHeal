package com.olympuspvp.heal;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageListener implements Listener{

	List<String> damage = new ArrayList<String>();
	olyHeal olyheal;
	
	public DamageListener(olyHeal oly){
		olyheal = oly;
	}
	
	
	@EventHandler
	public void onPlayerDamage(EntityDamageEvent e){
		
		Entity ent = e.getEntity();
		if(!(ent instanceof Player)) return;
		final Player p = (Player) ent;
		damage.add(p.getName());
		Bukkit.getScheduler().scheduleSyncDelayedTask(olyheal, new Runnable(){
			
			@Override
			public void run(){
				if(damage.contains(p.getName())) damage.remove(p.getName());
			}
			
		}, 200L);
		
	}
	
}

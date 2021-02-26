package pl.inder00.lobbycore.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamageListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent e){
        Entity ent = e.getEntity();
        if(ent instanceof Player){

            e.setCancelled(true);
            return;

        }
    }

}

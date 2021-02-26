package pl.inder00.lobbycore.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import pl.inder00.lobbycore.baisc.GUI;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e){
        if(e.getInventory() == null) return;
        if(e.getCurrentItem() == null) return;
        if(e.getCurrentItem().getType().equals(Material.AIR)) return;
        if(e.getInventory().getTitle() !=null && e.getInventory().getTitle().equals("§6Wybor serwera")){
            GUI g = GUI.get(e.getSlot());
            if(g.getCommand() != null){
                e.getWhoClicked().closeInventory();
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), g.getCommand().replace("{PLAYER}", e.getWhoClicked().getName()));
            }
            e.setCancelled(true);
            return;
        }
    }

}

package pl.inder00.lobbycore.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.inder00.lobbycore.Core;
import pl.inder00.lobbycore.baisc.BungeeServer;
import pl.inder00.lobbycore.storage.Config;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        Config cfg = Core.getConfiguration();

        e.setJoinMessage(null);

        p.teleport(p.getWorld().getSpawnLocation());

        ItemStack server = new ItemStack(Material.CHEST, 1);
        ItemMeta meta  = server.getItemMeta();
        meta.setDisplayName("§6Wybor serwera");
        server.setItemMeta(meta);

        p.getInventory().setItem(4,server);

        for(int i=0;i<100;i++){
            p.sendMessage(" ");
        }

        int online = Bukkit.getOnlinePlayers().size();
        for(BungeeServer servers : BungeeServer.getServers()){
            online += servers.getPlayers();
        }

        p.sendTitle(cfg.welcome$title.replace("{PLAYER}", p.getName()),cfg.welcome$subtitle.replace("{PLAYER}", p.getName()));
        for(String s : cfg.welcome$chat){
            p.sendMessage(s.replace("{ONLINE}", ""+online).replace("{REGISTRED}", ""+(Bukkit.getOfflinePlayers().length)).replace("{PLAYER}", p.getName()));
        }
    }

}

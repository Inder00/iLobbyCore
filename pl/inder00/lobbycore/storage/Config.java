package pl.inder00.lobbycore.storage;

import com.google.common.collect.Lists;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import pl.inder00.lobbycore.Core;
import pl.inder00.lobbycore.baisc.BungeeServer;
import pl.inder00.lobbycore.baisc.GUI;
import pl.inder00.lobbycore.baisc.ScoreboardSlot;

import java.util.HashMap;
import java.util.List;

public class Config {

    //=========================================================================
    private static Config inst;
    public FileConfiguration cfg = Core.getInstance().getConfig();

    public String welcome$title;
    public String welcome$subtitle;
    public List<String> welcome$chat;

    public String cannot$build;
    public String cannot$chat;

    public String scoreboard$name;
    public HashMap<Integer, String> scoreboard$list = new HashMap<>();

    public String connecting$title;
    public String connecting$subtitle;
    public String connecting$chat;

    //=========================================================================

    //Object
    public Config() {
        inst = this;
    }

    //Load
    public void load(){

        this.welcome$title = fixColor(cfg.getString("welcome.title"));
        this.welcome$subtitle = fixColor(cfg.getString("welcome.subtitle"));
        this.welcome$chat = fixColor(cfg.getStringList("welcome.chat"));

        this.connecting$title = fixColor(cfg.getString("connecting.title"));
        this.connecting$subtitle = fixColor(cfg.getString("connecting.subtitle"));
        this.connecting$chat = fixColor(cfg.getString("connecting.chat"));

        this.cannot$build = fixColor(cfg.getString("cannot-build"));
        this.cannot$chat = fixColor(cfg.getString("cannot-chat"));

        this.scoreboard$name = fixColor(cfg.getString("scoreboard.name"));


        for(String s : cfg.getConfigurationSection("scoreboard.list").getKeys(false)){
            try {

                new ScoreboardSlot(
                        s,
                        fixColor(cfg.getString("scoreboard.list."+s+".value")),
                        cfg.getInt("scoreboard.list."+s+".id"),
                        cfg.getBoolean("scoreboard.list."+s+".update")
                );

            } catch (Throwable e){
                e.printStackTrace();
            }
        }
        for(String s : cfg.getConfigurationSection("gui").getKeys(false)){
            //int slot, String name, Material material, short data, String command
            try {

                new GUI(cfg.getInt("gui."+s+".slot"),
                        fixColor(cfg.getString("gui."+s+".name")),
                        Material.getMaterial(cfg.getString("gui."+s+".item").toUpperCase()),
                        ((short) cfg.getInt("gui."+s+".data")),
                        cfg.getString("gui."+s+".command")
                );

            } catch(Throwable e){
                e.printStackTrace();
            }
        }
        for(String server : cfg.getStringList("bungeecord-servers")){
            new BungeeServer(server);
        }
    }

    private String fixColor(String input){
        return ChatColor.translateAlternateColorCodes('&',input
                .replace("{>}", "»")
                .replace("{.}", "•")
        );
    }
    private List<String> fixColor(List<String> input){
        List<String> out = Lists.newArrayList();
        for(String s : input){
            out.add(fixColor(s));
        }
        return out;
    }

}

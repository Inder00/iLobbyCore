package pl.inder00.lobbycore.baisc;

import java.util.ArrayList;
import java.util.List;

public class BungeeServer {

    private static List<BungeeServer> servers = new ArrayList<>();

    private String name;
    private int players;

    public BungeeServer(String name){
        this.name = name;
        this.players = players;
        servers.add(this);
    }

    public String getName() {
        return name;
    }

    public int getPlayers() {
        return players;
    }

    public void setPlayers(int players) {
        this.players = players;
    }

    public static List<BungeeServer> getServers() {
        return servers;
    }
    public static BungeeServer get(String name){
        for(BungeeServer server : servers){
            if(server.getName().equalsIgnoreCase(name)){
                return server;
            }
        }
        return null;
    }
}

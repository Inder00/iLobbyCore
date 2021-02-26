package pl.inder00.lobbycore.baisc;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class GUI {

    private static List<GUI> guis = new ArrayList<>();

    private int slot;
    private String command;
    private ItemStack itemStack;

    public GUI(int slot, String name, Material material, short data, String command){
        this.slot = slot-1;
        this.command = command;
        this.itemStack = new ItemStack(material,1,data);
        ItemMeta meta = this.itemStack.getItemMeta();
        meta.setDisplayName(name);
        this.itemStack.setItemMeta(meta);
        guis.add(this);
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public static List<GUI> getGuiList() {
        return guis;
    }
    public static GUI get(int slot){
        for(GUI g : guis){
            if(g.slot == slot){
                return g;
            }
        }
        return null;
    }

    public static Inventory init(){

        Inventory inv = Bukkit.createInventory(null,27,"§6Wybor serwera");

        for(GUI g : guis){
            inv.setItem(g.getSlot(), g.getItemStack());
        }

        return inv;

    }
}

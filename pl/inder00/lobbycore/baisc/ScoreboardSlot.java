package pl.inder00.lobbycore.baisc;

import java.util.ArrayList;
import java.util.List;

public class ScoreboardSlot {

    private static List<ScoreboardSlot> slots = new ArrayList<>();
    private static List<ScoreboardSlot> updating = new ArrayList<>();

    private String key, value;
    private int id;
    private boolean update;

    public ScoreboardSlot(String key, String value, int id, boolean update){
        this.key = key;
        this.value = value;
        this.id = id;
        this.update = update;
        slots.add(this);
        if(this.update == true) updating.add(this);
    }

    public static List<ScoreboardSlot> getSlots() {
        return slots;
    }

    public static List<ScoreboardSlot> getUpdating() {
        return updating;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public int getId() {
        return id;
    }

    public boolean isUpdate() {
        return update;
    }
}

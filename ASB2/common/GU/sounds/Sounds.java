package GU.sounds;

import net.minecraft.client.Minecraft;
import GU.info.Reference;

public enum Sounds {

    TEST("test", ".ogg"),
    POTION_HIT("potion_hit", ".ogg");
    
    private String name;
    private String format;

    Sounds(String name, String format) {

        this.name = name;
        this.format = format;
    }

    public String getName() {

        return name;
    }

    public String getFormat() {

        return format;
    }

    public void play(double x, double y, double z, float volume, float pitch) {

        Minecraft.getMinecraft().sndManager.playSound(Reference.MODDID + ":" + name, (float)x, (float)y, (float)z, volume, pitch);
    }
}

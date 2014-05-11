package GUOLD.sounds;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.util.ResourceLocation;
import GUOLD.info.Reference;

public enum Sounds implements ISound {
    
    TEST("test", ".ogg"), POTION_HIT("potion_hit", ".ogg");
    
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
        
        Minecraft.getMinecraft().mcSoundHandler.playSound(Reference.MODDID + ":" + name, (float) x, (float) y, (float) z, volume, pitch);
    }

    @Override
    public ResourceLocation getPositionedSoundLocation() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean canRepeat() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int getRepeatDelay() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getVolume() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getPitch() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getXPosF() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getYPosF() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getZPosF() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public AttenuationType getAttenuationType() {
        // TODO Auto-generated method stub
        return null;
    }
}

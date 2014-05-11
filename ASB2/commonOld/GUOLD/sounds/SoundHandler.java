package GUOLD.sounds;

import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import GUOLD.info.Reference;

public class SoundHandler {

    public SoundHandler() {        
    }

    public static void init() {
        
        MinecraftForge.EVENT_BUS.register(new SoundHandler());
    }
    
    @ForgeSubscribe
    public void doSoundsLoad(SoundLoadEvent event) {
        
        for(Sounds sound : Sounds.values()) {
            
            addSound(event, sound);
        }
    }
    
    private void addSound(SoundLoadEvent event, Sounds sound) {
        
        event.manager.soundPoolSounds.addSound(Reference.MODDID + ":" + sound.getName() + sound.getFormat());
    }
}

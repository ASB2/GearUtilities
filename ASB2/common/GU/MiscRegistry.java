package GU;

import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.event.ForgeSubscribe;
import GU.entity.EntityTest.EntityTestEntity;
import cpw.mods.fml.common.registry.EntityRegistry;

public class MiscRegistry {

    public static void init(Configuration config) {

        initEntitys();
    }

    public static void initEntitys() {

        EntityRegistry.registerModEntity(EntityTestEntity.class, "Test Entity",
                0, GearUtilities.instance, 80, 3, true);
    }

    @ForgeSubscribe
    public void initSounds(SoundLoadEvent event) {

        // event.manager.addSound("mod_id:hit.ogg");

        // Args: entity, sound, volume (relative to 1.0), and frequency (or
        // pitch, also relative to 1.0)
        // WorldObject.playSoundAtEntity(EntityPlayerObject, "mod_id:hit", 1.0F,
        // 1.0F);
        // if you have file names which ends with an number, exclude the number
        // when using playSound!

        // Seen as variations of the same sound
        // event.manager.addSound("mod_id:hit1.ogg");
        // event.manager.addSound("mod_id:hit2.ogg");
    }
}

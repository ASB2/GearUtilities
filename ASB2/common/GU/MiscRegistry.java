package GU;

import net.minecraftforge.common.Configuration;
import GU.entity.EntityCluster.EntityInfoCluster;
import GU.entity.EntityPotion.EntityModularPotion;
import GU.entity.EntityTest.EntityTestEntity;
import cpw.mods.fml.common.registry.EntityRegistry;

public class MiscRegistry {

    public static void init(Configuration config) {

        initEntitys();
    }

    public static void initEntitys() {

        EntityRegistry.registerModEntity(EntityTestEntity.class, "Test Entity", 0, GearUtilities.instance, 80, 3, true);
        EntityRegistry.registerModEntity(EntityModularPotion.class, "Modular Potion", 1, GearUtilities.instance, 80, 3, true);
        EntityRegistry.registerModEntity(EntityInfoCluster.class, "Info Clustor", 2, GearUtilities.instance, 80, 3, true);
        
    }
}

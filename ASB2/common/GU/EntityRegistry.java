package GU;

import GU.entity.EntityPotion.EntityModularPotion;
import GU.entity.EntityTest.EntityTestEntity;
import GU.entity.EntityTest.TestEntityRenderer;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class EntityRegistry {
    
    public static void init() {
        
        cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityTestEntity.class, "Test Entity", 0, GearUtilities.instance, 80, 3, true);
        cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityModularPotion.class, "Modular Potion", 1, GearUtilities.instance, 80, 3, true);
    }
    
    public static void initClient() {
        
        RenderingRegistry.registerEntityRenderingHandler(EntityTestEntity.class, new TestEntityRenderer());
    }
}

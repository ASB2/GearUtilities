package GUOLD;

import GUOLD.entity.EntityPotion.EntityModularPotion;
import GUOLD.entity.EntityQuanta.EntityQuanta;
import GUOLD.entity.EntityQuanta.QuantaRenderer;
import GUOLD.entity.EntityTest.EntityTestEntity;
import GUOLD.entity.EntityTest.TestEntityRenderer;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class EntityRegistry {
    
    public static void init() {
        
        cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityTestEntity.class, "Test Entity", 0, GearUtilities.instance, 80, 3, true);
        cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityModularPotion.class, "Modular Potion", 1, GearUtilities.instance, 80, 3, true);
        cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityQuanta.class, "Quanta", 2, GearUtilities.instance, 80, 3, true);
    }
    
    public static void initClient() {
        
        RenderingRegistry.registerEntityRenderingHandler(EntityTestEntity.class, new TestEntityRenderer());
        RenderingRegistry.registerEntityRenderingHandler(EntityQuanta.class, new QuantaRenderer());
    }
}
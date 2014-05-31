package GU;

import GU.entities.EntityBlockDestoryer;
import GU.entities.EntityPhoton;
import GU.entities.EntityPhoton.PhotonRenderer;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class EntityRegistry {
    
    public static void init() {
        
        cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityPhoton.class, "Entity Photon", 0, GearUtilities.instance, 800, 3, true);
        RenderingRegistry.registerEntityRenderingHandler(EntityPhoton.class, PhotonRenderer.instance);
        cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityBlockDestoryer.class, "Entity Block Destroyer", 0, GearUtilities.instance, 800, 3, true);
    }
}

package GU;

import GU.entities.EntityPhoton;
import GU.entities.EntityPhoton.PhotonRenderer;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class EntityRegistry {
    
    public static void init() {
        
        cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityPhoton.class, "Entity Photon", 0, GearUtilities.instance, 80, 3, true);
        RenderingRegistry.registerEntityRenderingHandler(EntityPhoton.class, PhotonRenderer.instance);
    }
}

package GU;

import GU.entity.EntityFamilar.EntityFamilars;
import GU.entity.EntityFamilar.FamilarsRenderer;
import GU.entity.EntityPotion.EntityModularPotion;
import GU.entity.EntityTest.EntityTestEntity;
import GU.entity.EntityTest.TestEntityRenderer;
import GU.entity.EntityTileFinder.EntityTileFinder;
import GU.entity.EntityTileFinder.TileFinderRenderer;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class EntityRegistry {

    public static void init() {

        cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityTestEntity.class, "Test Entity", 0, GearUtilities.instance, 80, 3, true);
        cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityModularPotion.class, "Modular Potion", 1, GearUtilities.instance, 80, 3, true);
        cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityTileFinder.class, "TileFinder", 2, GearUtilities.instance, 80, 3, true);
        cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityFamilars.class, "Familar Base", 3, GearUtilities.instance, 80, 3, true);
    }

    public static void initClient() {

        RenderingRegistry.registerEntityRenderingHandler(EntityTestEntity.class, new TestEntityRenderer());
        RenderingRegistry.registerEntityRenderingHandler(EntityTileFinder.class, new TileFinderRenderer());
        RenderingRegistry.registerEntityRenderingHandler(EntityFamilars.class, new FamilarsRenderer());
    }
}

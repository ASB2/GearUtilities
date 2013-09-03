package GU.entity.EntityCluster;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import GU.info.Textures;

public class InfoClusterRenderer extends Render {

    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTick) {

    }

    @Override
    protected ResourceLocation func_110775_a(Entity entity) {

        return Textures.BLANK;
    }
}
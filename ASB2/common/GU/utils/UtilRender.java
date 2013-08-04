package GU.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.client.FMLClientHandler;

public final class UtilRender {

    public static void renderTexture(ResourceLocation texture) {

        if(FMLClientHandler.instance().getClient() != null)
            FMLClientHandler.instance().getClient().renderEngine.func_110577_a(texture);
    }
    public static void renderFX(EntityFX fx) {

        if(FMLClientHandler.instance().getClient() != null) {

            Minecraft.getMinecraft().effectRenderer.addEffect(fx); 
        }
    }
}

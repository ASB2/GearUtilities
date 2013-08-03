package GU.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.client.FMLClientHandler;
import GU.fx.*;
import GU.api.power.*;
import GU.vector.*;

public final class UtilRender {

    public static void renderTexture(ResourceLocation texture) {

        if(FMLClientHandler.instance().getClient() != null)
            FMLClientHandler.instance().getClient().renderEngine.func_110577_a(texture);
    }

    public static void renderBeam(TileEntity source, IPowerMisc tile, int renderTime) {

        FXBeam beam;

        int coords[] = tile.getPosition();
        if(FMLClientHandler.instance().getClient() != null) {
            
            beam = new FXBeam(source.worldObj, new Vector3(source.xCoord + .5, source.yCoord + .5, source.zCoord + .5), new Vector3(coords[0] + .5, coords[1] + .5, coords[2] + .5), 1, 1, 1, renderTime);
            Minecraft.getMinecraft().effectRenderer.addEffect(beam);        
        }
    }
}

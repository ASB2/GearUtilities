package GU.color;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ColorableRenderer implements ISimpleBlockRenderingHandler {

    public static int colorableTile = RenderingRegistry.getNextAvailableRenderId();

    @Override
    public void renderInventoryBlock(Block block, int meta, int modelID, RenderBlocks renderer) {


        if(block instanceof IBlockColorable) {

            ((IBlockColorable)block).renderInventoryBlock(block, meta, modelID, renderer);
        }
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {

        if(block instanceof IBlockColorable) {

            return ((IBlockColorable)block).renderWorldBlock(world, x, y, z, block, modelId, renderer);
        }
        return false;
    }

    @Override
    public boolean shouldRender3DInInventory() {

        return true;
    }

    @Override
    public int getRenderId() {

        return colorableTile;
    }
}
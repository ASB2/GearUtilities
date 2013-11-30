package GU.blocks.BlockTestRender;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import ASB2.utils.UtilRender;
import GU.BlockRegistry;
import GU.info.Variables;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class TestRenderRenderer implements ISimpleBlockRenderingHandler {

    public static int testRenderID = RenderingRegistry.getNextAvailableRenderId();

    Random random = new Random();

    @Override
    public void renderInventoryBlock(Block block, int meta, int modelID, RenderBlocks renderer) {

        renderer.setRenderBounds(0, 0, 0, 1, 1, 1);
        UtilRender.renderStandardInvBlock(renderer, block, meta);
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {

        renderer.setRenderBounds(0.001, 0.001, 0.001, .999, .999, .999);
        UtilRender.renderFakeBlock(renderer, block, x, y, z, ((BlockTestRender) BlockRegistry.BlockTestRender).inner, 255, 255, 255, 255, Variables.BRIGHT_BLOCK);

        renderer.setRenderBounds(0, 0, 0, 1, 1, 1);
        renderer.renderStandardBlock(block, x, y, z);
        return true;
    }

    @Override
    public boolean shouldRender3DInInventory() {

        return true;
    }

    @Override
    public int getRenderId() {

        return testRenderID;
    }
}

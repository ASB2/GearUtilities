package GU.blocks.containers.BlockEnhancedBricks;

import java.awt.Color;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import GU.utils.UtilRender;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class EnchancedBricksRenderer implements ISimpleBlockRenderingHandler {

    public static int enchancedBricksID = RenderingRegistry.getNextAvailableRenderId();

    @Override
    public void renderInventoryBlock(Block block, int meta, int modelID, RenderBlocks renderer) {

        UtilRender.renderStandardInvBlock(renderer, block, meta);
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {

//        TileEnchancedBricks tile = (TileEnchancedBricks)world.getBlockTileEntity(x, y, z);

        Color color = new Color(block.colorMultiplier(world, x, y, z));
        
        UtilRender.renderFakeBlock(renderer, block, x, y, z, block.getIcon(0, 0), color.getRed(), color.getGreen(), color.getBlue(), 255, block.getLightValue(world, x, y, z));
        return true;
    }

    @Override
    public boolean shouldRender3DInInventory() {

        return true;
    }

    @Override
    public int getRenderId() {

        return enchancedBricksID;
    }

}

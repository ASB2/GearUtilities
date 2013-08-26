package GU.blocks.containers.BlockPlaceHolder;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import GU.utils.UtilRender;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class PlaceHolderRenderer implements ISimpleBlockRenderingHandler {

    public static int placeHolderID = RenderingRegistry .getNextAvailableRenderId();

    @Override
    public void renderInventoryBlock(Block block, int meta, int modelID, RenderBlocks renderer) {

        UtilRender.renderStandardInvBlock(renderer, block, meta);
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {

        if (block.blockMaterial == Material.air) {
        
            return false;
        }
        
        TilePlaceHolder tile = (TilePlaceHolder)world.getBlockTileEntity(x, y, z);
        
        renderer.renderBlockByRenderType(tile.getBlock(), x, y, z);
        return true;
    }

    @Override
    public boolean shouldRender3DInInventory() {

        return true;
    }

    @Override
    public int getRenderId() {

        return placeHolderID;
    }

}

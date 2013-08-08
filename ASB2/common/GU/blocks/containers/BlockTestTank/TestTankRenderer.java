package GU.blocks.containers.BlockTestTank;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import GU.utils.UtilMisc;
import GU.utils.UtilRender;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class TestTankRenderer implements ISimpleBlockRenderingHandler  {

    public static int tankModelID = RenderingRegistry.getNextAvailableRenderId();

    @Override
    public void renderInventoryBlock(Block block, int meta, int modelID, RenderBlocks renderer) {

        UtilRender.renderStandardInvBlock(renderer, block, meta);
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {

        UtilRender.renderMetadataBlock(block, 0, x, y, z, renderer, world);

        TileTestTank tile = (TileTestTank)world.getBlockTileEntity(x, y, z);

        if (tile.getFluid() != null) {

            if(!(tile.getCapacity() == tile.getFluidAmount())) { 
                
                renderer.setRenderBounds(0.01F, 0.01F, 0.01F, 0.99F, UtilMisc.getAmountScaled(0.99, tile.getFluidAmount(), tile.getCapacity()), 0.99F);
            }
            else {

                renderer.setRenderBounds(0.01F, 0.01F, 0.01F, 0.99F, 0.99, 0.99F);
            }
            
            UtilRender.renderFakeBlock(tile.getFluid().getFluid().getIcon(), x, y, z, renderer, world);
        }
        return true;
    }

    @Override
    public boolean shouldRender3DInInventory() {

        return true;
    }

    @Override
    public int getRenderId() {

        return tankModelID;
    }


}

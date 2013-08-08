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

        if (tile.fluidTank.getFluid() != null) {

            if(!(tile.fluidTank.getCapacity() == tile.fluidTank.getFluidAmount())) { 
                
                renderer.setRenderBounds(0.0001F, 0.001F, 0.0001F, 0.9999F, UtilMisc.getAmountScaled(0.9999, tile.fluidTank.getFluidAmount(), tile.fluidTank.getCapacity()), 0.9999F);
            }
            else {

                renderer.setRenderBounds(0.0001F, 0.0001F, 0.0001F, 0.9999F, 0.9999, 0.9999F);
            }
            
            UtilRender.renderFakeBlock(tile.fluidTank.getFluid().getFluid().getIcon(), x, y, z, renderer, world);
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

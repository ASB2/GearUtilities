package GU.blocks.containers.BlockConnectableTank;

import java.awt.Color;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import ASB2.utils.UtilMisc;
import ASB2.utils.UtilRender;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ConnectableTankRenderer implements ISimpleBlockRenderingHandler {

    public static int tankModelID = RenderingRegistry .getNextAvailableRenderId();

    @Override
    public void renderInventoryBlock(Block block, int meta, int modelID, RenderBlocks renderer) {

        UtilRender.renderStandardInvBlock(renderer, block, meta);
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {

        TileConnectableTank tile = (TileConnectableTank) world.getBlockTileEntity(x, y, z);

        renderer.setRenderBounds(0, 0, 0, 1, 1, 1);
        UtilRender.renderMetadataBlock(block, 0, x, y, z, renderer, world);
        
        if (tile.fluidTank.getFluid() != null) {

            double min = .001, max = .9999;

            renderer.setRenderBounds(min, min, min, max, UtilMisc.getAmountScaled(max, tile.fluidTank.getFluidAmount(), tile.fluidTank.getCapacity()),max);

            Fluid fluid = tile.fluidTank.getFluid().getFluid();
            Color color = new Color(fluid.getColor());
            
            for(ForgeDirection direction: ForgeDirection.VALID_DIRECTIONS) {

                if(direction == ForgeDirection.UP) {

                    UtilRender.renderFakeSide(renderer, block, direction, x, y, z, fluid.getStillIcon(), color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha(), block.getMixedBrightnessForBlock(world, x, y, z));
                }   
                else {

                    UtilRender.renderFakeSide(renderer, block, direction, x, y, z, fluid.getFlowingIcon(), color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha(), block.getMixedBrightnessForBlock(world, x, y, z));
                }
            }
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

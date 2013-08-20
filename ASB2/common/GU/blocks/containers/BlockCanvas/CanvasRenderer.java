package GU.blocks.containers.BlockCanvas;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.ForgeDirection;
import GU.BlockRegistry;
import GU.color.Color;
import GU.color.IColorable;
import GU.utils.UtilRender;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class CanvasRenderer implements ISimpleBlockRenderingHandler {

    public static int canvasRenderID = RenderingRegistry
            .getNextAvailableRenderId();

    Random random = new Random();

    @Override
    public void renderInventoryBlock(Block block, int meta, int modelID,
            RenderBlocks renderer) {

        renderer.setRenderBounds(0.01, 0.01, 0.01, .99, .99, .99);
        UtilRender.renderStandardInvBlock(renderer, block, meta);

        renderer.setRenderBounds(0, 0, 0, 1, 1, 1);
        UtilRender.renderStandardInvBlock(renderer, block,
                ((BlockCanvas) BlockRegistry.BlockCanvas).inner, 1, 1, 1, 1);

    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
            Block block, int modelId, RenderBlocks renderer) {

        TileEntity tile = world.getBlockTileEntity(x, y, z);

        if (tile != null && tile instanceof IColorable) {

            Color color = ((IColorable) tile).getColor(ForgeDirection.UNKNOWN);

            renderer.setRenderBounds(0.01, 0.01, 0.01, .99, .99, .99);
            UtilRender.renderFakeBlock(renderer, block, x, y, z,
                    block.getIcon(0, 0), color.getRed(), color.getGreen(),
                    color.getBlue(), color.getAlpha(),
                    block.getMixedBrightnessForBlock(world, x, y, z));

            renderer.setRenderBounds(0, 0, 0, 1, 1, 1);
            UtilRender.renderFakeBlock(renderer, block, x, y, z,
                    ((BlockCanvas) BlockRegistry.BlockCanvas).inner, 1, 1, 1,
                    1, block.getMixedBrightnessForBlock(world, x, y, z));
        } else {

            renderer.setRenderBounds(0.001, 0.001, 0.001, .999, .999, .999);
            UtilRender.renderFakeBlock(renderer, block, x, y, z,
                    block.getIcon(0, 0), 1, 1, 1, 1,
                    block.getMixedBrightnessForBlock(world, x, y, z));

            renderer.setRenderBounds(0, 0, 0, 1, 1, 1);
            UtilRender.renderFakeBlock(renderer, block, x, y, z,
                    ((BlockCanvas) BlockRegistry.BlockCanvas).inner, 1F, 1, 1,
                    1, block.getMixedBrightnessForBlock(world, x, y, z));
        }
        return true;
    }

    @Override
    public boolean shouldRender3DInInventory() {

        return true;
    }

    @Override
    public int getRenderId() {

        return canvasRenderID;
    }
}
package GU.blocks.containers.BlockCanvas;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.ForgeDirection;
import GU.BlockRegistry;
import GU.color.Color;
import GU.color.IColorable;
import GU.utils.UtilRender;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class CanvasRenderer implements ISimpleBlockRenderingHandler {

    public static int canvasRenderID = RenderingRegistry.getNextAvailableRenderId();

    Random random = new Random();

    @Override
    public void renderInventoryBlock(Block block, int meta, int modelID, RenderBlocks renderer) {

        renderer.setRenderBounds(0.01, 0.01, 0.01, .99, .99, .99);
        UtilRender.renderStandardInvBlock(renderer, block, meta);

        renderer.setRenderBounds(0, 0, 0, 1, 1, 1);
        UtilRender.renderStandardInvBlock(renderer, block, ((BlockCanvas)BlockRegistry.BlockCanvas).inner, 255, 255, 255, 255);
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {

        TileEntity tile = world.getBlockTileEntity(x, y, z);

        if (tile != null && tile instanceof IColorable) {

            renderer.setRenderBounds(0.0001, 0.0001, 0.0001, .9999, .9999, .9999);

            for(ForgeDirection direction: ForgeDirection.VALID_DIRECTIONS) {

                if(block.shouldSideBeRendered(world, x, y, z, direction.ordinal())) {

                    Color color = ((IColorable) tile).getColor(direction);

                    UtilRender.renderFakeSide(renderer, block, direction, x, y, z, block.getIcon(0, 0), color.getRed(), color.getGreen(), color.getBlue(), 50, block.getMixedBrightnessForBlock(world, x, y, z));
                }
            }

            renderer.setRenderBounds(0, 0, 0, 1, 1, 1);
            this.renderFakeBlock(world, renderer, block, x, y, z, ((BlockCanvas)BlockRegistry.BlockCanvas).inner, 255, 255, 255, 255, 255);
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

    public void renderFakeBlock(IBlockAccess world, RenderBlocks renderer, Block block, int x, int y, int z, Icon icon, float red, float green, float blue, float alfa, int brightness) {

        Tessellator tess = Tessellator.instance;

        tess.setBrightness(brightness);
        tess.setColorRGBA_F(red, green, blue, alfa);

        renderer.renderFaceXNeg(block, x, y, z, UtilRender.renderConnectedTexture(world, ((BlockCanvas)BlockRegistry.BlockCanvas).icons, block.blockID, x, y, z, 4));
        renderer.renderFaceXPos(block, x, y, z, UtilRender.renderConnectedTexture(world, ((BlockCanvas)BlockRegistry.BlockCanvas).icons, block.blockID, x, y, z, 5));

        renderer.renderFaceYNeg(block, x, y, z, UtilRender.renderConnectedTexture(world, ((BlockCanvas)BlockRegistry.BlockCanvas).icons, block.blockID, x, y, z, 0));
        renderer.renderFaceYPos(block, x, y, z, UtilRender.renderConnectedTexture(world, ((BlockCanvas)BlockRegistry.BlockCanvas).icons, block.blockID, x, y, z, 1));

        renderer.renderFaceZNeg(block, x, y, z, UtilRender.renderConnectedTexture(world, ((BlockCanvas)BlockRegistry.BlockCanvas).icons, block.blockID, x, y, z, 2));
        renderer.renderFaceZPos(block, x, y, z, UtilRender.renderConnectedTexture(world, ((BlockCanvas)BlockRegistry.BlockCanvas).icons, block.blockID, x, y, z, 3));
    }
}
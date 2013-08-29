package GU.blocks.containers.BlockCanvas;

import java.awt.Color;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import GU.BlockRegistry;
import GU.api.color.IColorable;
import GU.color.BlockColorable;
import GU.info.Reference;
import GU.utils.UtilDirection;
import GU.utils.UtilRender;
import GU.models.*;

public class BlockCanvas extends BlockColorable implements IBlockRender {

    public Icon inner;
    public Icon[] icons = new Icon[16];
    private String folder = ":canvasConnected";

    public BlockCanvas(int id, Material material) {
        super(id, material);
        this.useStandardRendering = false;
        this.registerTile(TileCanvas.class);
    }

    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {

        int id = UtilDirection.translateDirectionToBlockId(world, ForgeDirection.getOrientation(side), x, y, z);

        if(id == 0 || (!Block.blocksList[id].isOpaqueCube() && id != this.blockID)) {

            return true;
        }
        return false;
    }

    @Override
    public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side) {

        return true;
    }

    @Override
    public boolean isBlockNormalCube(World world, int x, int y, int z) {

        return true;
    }

    public boolean isOpaqueCube() {

        return false;
    }

    @Override
    public void registerIcons(IconRegister iconRegistry) {
        super.registerIcons(iconRegistry);

        inner = iconRegistry.registerIcon(Reference.MODDID + ":BlockCanvasOutline");

        icons[0] = iconRegistry.registerIcon(Reference.MODDID + folder + "/connectedRegular");
        icons[1] = iconRegistry.registerIcon(Reference.MODDID + folder + "/connected_1_d");
        icons[2] = iconRegistry.registerIcon(Reference.MODDID + folder + "/connected_1_u");
        icons[3] = iconRegistry.registerIcon(Reference.MODDID + folder + "/connected_1_l");
        icons[4] = iconRegistry.registerIcon(Reference.MODDID + folder + "/connected_1_r");
        icons[5] = iconRegistry.registerIcon(Reference.MODDID + folder + "/connected_2_h");
        icons[6] = iconRegistry.registerIcon(Reference.MODDID + folder + "/connected_2_v");
        icons[7] = iconRegistry.registerIcon(Reference.MODDID + folder + "/connected_2_dl");
        icons[8] = iconRegistry.registerIcon(Reference.MODDID + folder + "/connected_2_dr");
        icons[9] = iconRegistry.registerIcon(Reference.MODDID + folder + "/connected_2_ul");
        icons[10] = iconRegistry.registerIcon(Reference.MODDID + folder + "/connected_2_ur");
        icons[11] = iconRegistry.registerIcon(Reference.MODDID + folder + "/connected_3_d");
        icons[12] = iconRegistry.registerIcon(Reference.MODDID + folder + "/connected_3_u");
        icons[13] = iconRegistry.registerIcon(Reference.MODDID + folder + "/connected_3_l");
        icons[14] = iconRegistry.registerIcon(Reference.MODDID + folder + "/connected_3_r");
        icons[15] = iconRegistry.registerIcon(Reference.MODDID + folder + "/blank");
    }

    @Override
    public int getRenderType() {

        return BlockSimpleRenderer.renderID;
    }

    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TileCanvas();
    }

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

                    UtilRender.renderFakeSide(renderer, block, direction, x, y, z, block.getIcon(0, 0), color.getRed(), color.getGreen(), color.getBlue(), 255, 15728864);
                }
            }

            renderer.setRenderBounds(0 - .001, 0 - .001, 0 - .001, 1 + .001, 1 + .001, 1 + .001);
            this.renderFakeBlock(world, renderer, block, x, y, z, ((BlockCanvas)BlockRegistry.BlockCanvas).inner, 255, 255, 255, 255, block.getMixedBrightnessForBlock(world, x, y, z));
        }
        return true;
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

package GU.blocks.containers.BlockDissolver;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import ASB2.utils.UtilRender;
import GU.GearUtilities;
import GU.blocks.containers.ContainerBase;
import GU.info.Gui;
import GU.info.Reference;
import GU.info.Variables;
import GU.models.BlockSimpleRenderer;
import GU.models.IBlockRender;

public class BlockDissolver extends ContainerBase implements IBlockRender {

    Icon[] overlay = new Icon[7];
    Icon[] underlay = new Icon[7];

    public BlockDissolver(int id, Material material) {
        super(id, material);

        this.useStandardRendering = false;
        this.registerTile(TileDissolver.class);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int hitX, float hitY, float hitZ, float par9) {

        if(!player.isSneaking()) {

            player.openGui(GearUtilities.instance, Gui.DISSOLVER, world, x, y, z);
            return true;
        }
        return false;
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {

        overlay[0] = iconRegister.registerIcon(Reference.MODDID + ":BlockDissolverBottom");
        overlay[1] = iconRegister.registerIcon(Reference.MODDID + ":BlockDissolverTop");
        overlay[2] = iconRegister.registerIcon(Reference.MODDID + ":BlockDissolverSide");
        overlay[3] = overlay[2];
        overlay[4] = overlay[2];
        overlay[5] = overlay[2];

        underlay[0] = iconRegister.registerIcon(Reference.MODDID + ":BlockDissolverBottomUnderlay");
        underlay[1] = iconRegister.registerIcon(Reference.MODDID + ":BlockDissolverTopUnderlay");
        underlay[2] = iconRegister.registerIcon(Reference.MODDID + ":BlockDissolverEarthUnderlay");
        underlay[3] = iconRegister.registerIcon(Reference.MODDID + ":BlockDissolverFireUnderlay");
        underlay[4] = iconRegister.registerIcon(Reference.MODDID + ":BlockDissolverWaterUnderlay");
        underlay[5] = iconRegister.registerIcon(Reference.MODDID + ":BlockDissolverAirUnderlay");
    }

    @Override
    public Icon getIcon(int side, int metadata) {

        if(metadata == 1) {

            return overlay[side];
        }
        else {

            return underlay[side];
        }
    }

    @Override
    public int getRenderType() {

        return BlockSimpleRenderer.renderID;
    }

    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TileDissolver();
    }

    @Override
    public void renderInventoryBlock(Block block, int meta, int modelID, RenderBlocks renderer) {

        renderer.setRenderBounds(.01, .01, .01, .99, .99, .99);
        UtilRender.renderStandardInvBlock(renderer, block, meta);
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int metadata, RenderBlocks renderer) {

        renderer.setRenderBounds(.01, .01, .01, .99, .99, .99);

        for(ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {

            UtilRender.renderFakeSide(renderer, block, direction, x, y, z, underlay[direction.ordinal()], 255, 255, 255, 255, Variables.BRIGHT_BLOCK);
        }

        renderer.setRenderBounds(0, 0, 0, 1, 1, 1);

        for(ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {

            UtilRender.renderFakeSide(renderer, block, direction, x, y, z, overlay[direction.ordinal()], 255, 255, 255, 255, block.getMixedBrightnessForBlock(world, x, y, z));
        }
        return true;
    }
}

package GU.blocks.containers.BlockEnhancedBricks;

import java.awt.Color;

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
import GU.blocks.containers.ContainerBase;
import GU.color.ColorableRenderer;
import GU.color.EnumVinillaColor;
import GU.color.IBlockColorable;
import GU.color.IColorable;
import GU.info.Reference;
import GU.utils.UtilDirection;
import GU.utils.UtilRender;

public class BlockEnhancedBricks extends ContainerBase implements IBlockColorable {

    Icon overlay;
    
    public BlockEnhancedBricks(int id, Material material) {
        super(id, material);
        this.useStandardRendering = false;
        this.registerTile(TileEnchancedBricks.class);
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {
        super.registerIcons(iconRegister);
        
        overlay = iconRegister.registerIcon(Reference.MODDID + ":BlockEnhancedBricksOverlay");
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {

        TileEntity tile = world.getBlockTileEntity(x, y, z);

        if(tile != null && tile instanceof IColorable) {

            if(player.getHeldItem() != null && EnumVinillaColor.isItemDye(player.getHeldItem())) {

                ((IColorable)tile).setColor(EnumVinillaColor.getRGBValue(EnumVinillaColor.getItemColorValue(player.getHeldItem())), ForgeDirection.getOrientation(side));
            }
        }
        return false;
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
    
    public int getRenderType() {

        return ColorableRenderer.colorableTile;
    }

    @Override
    public void renderInventoryBlock(Block block, int meta, int modelID, RenderBlocks renderer) {
        
        renderer.setRenderBounds(0.0001, 0.0001, 0.0001, .9999, .9999, .9999);
        UtilRender.renderStandardInvBlock(renderer, block, block.getIcon(0, 0), 255, 255, 255, 255);
        renderer.setRenderBounds(0, 0, 0, 1, 1, 1);
        UtilRender.renderStandardInvBlock(renderer, block, overlay, 255, 255, 255, 255);
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        
        TileEntity tile = world.getBlockTileEntity(x, y, z);

        if (tile != null && tile instanceof IColorable) {

            renderer.setRenderBounds(0.0001, 0.0001, 0.0001, .9999, .9999, .9999);

            for(ForgeDirection direction: ForgeDirection.VALID_DIRECTIONS) {

                if(block.shouldSideBeRendered(world, x, y, z, direction.ordinal())) {

                    Color color = ((IColorable) tile).getColor(direction);

                    UtilRender.renderFakeSide(renderer, block, direction, x, y, z, this.getIcon(0, 0), color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha(), 15728864);
                }
            }

            renderer.setRenderBounds(0, 0, 0, 1, 1, 1);
            UtilRender.renderFakeBlock(renderer, block, x, y, z, overlay, 255, 255, 255, 255, block.getMixedBrightnessForBlock(world, x, y, z));
        }
        return true;
    }
    
    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TileEnchancedBricks();
    }
}

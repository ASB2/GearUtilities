package GU.blocks.containers.BlockCamoBlock;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import GU.GearUtilities;
import GU.blocks.containers.ContainerBase;
import GU.info.Gui;

public class BlockCamoBlock extends ContainerBase {

    public BlockCamoBlock(int id, Material material) {
        super(id, material);
        this.registerTile(TileCamoBlock.class);
    }

    @Override
    public boolean isOpaqueCube() {

        return false;
    }
    
    @Override
    public Icon getBlockTexture(IBlockAccess world, int x, int y, int z, int side) {

        TileEntity tile = world.getBlockTileEntity(x, y, z);

        if(tile != null) {

            ItemStack stack = ((IInventory)tile).getStackInSlot(0);

            if(stack != null && stack.getItem() instanceof ItemBlock) {

                return Block.blocksList[((ItemBlock)stack.getItem()).getBlockID()].getIcon(side, stack.getItemDamage());
            }
        }
        return super.getBlockTexture(world, x, y, z, side);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int hitX, float hitY, float hitZ, float par9) {

        if (!player.isSneaking()) {

            player.openGui(GearUtilities.instance, Gui.CAMO_BLOCK, world, x, y, z);
            return true;
        }
        world.markBlockForRenderUpdate(x, y, z);
        return false;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {

        if (world.isBlockIndirectlyGettingPowered(x, y, z))
            return null;

        return super.getCollisionBoundingBoxFromPool(world, x, y, z);
    }
    
    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TileCamoBlock();
    }
}

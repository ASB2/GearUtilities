package GU.blocks.containers.BlockCreationTable;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import GU.blocks.containers.TileBase;

public class TileCreationTable extends TileBase {

    public TileCreationTable() {

    }

    @Override
    public void updateEntity() {

        if(!worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {
            
        }
    }

    @Override
    public void trigger(int id) {

    }

    @Override
    public void triggerBlock(World world, EntityLivingBase player, ItemStack itemStack, int x, int y, int z, int side) {

        super.triggerBlock(world, player, itemStack, side, side, side, side);     
    }

    @Override
    public void triggerBlock(World world, boolean isSneaking, ItemStack itemStack, int x, int y, int z, int side) {

        super.triggerBlock(world, isSneaking, itemStack, side, side, side, side);            
    }
}

package GU.blocks.containers.BlockCreationTable;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import GU.blocks.containers.TileBase;
import GU.api.power.*;
import GU.api.wait.Wait;
import GU.power.*;

public class TileCreationTable extends TileBase implements IPowerMisc {

    public TileCreationTable() {

        this.waitTimer = new Wait(10, this, 0);
        powerProvider = new GUPowerProvider(0, PowerClass.LOW, State.SINK);
    }

    @Override
    public void updateEntity() {

        if(!worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {


        }
    }

    @Override
    public void trigger(int id) {

        ((GUPowerProvider)this.getPowerProvider()).movePower(worldObj, xCoord, yCoord, zCoord);
    }

    @Override
    public void triggerBlock(World world, EntityLivingBase player, ItemStack itemStack, int x, int y, int z, int side) {

        super.triggerBlock(world, player, itemStack, side, side, side, side);     
    }

    @Override
    public void triggerBlock(World world, boolean isSneaking, ItemStack itemStack, int x, int y, int z, int side) {

        super.triggerBlock(world, isSneaking, itemStack, side, side, side, side);            
    }

    @Override
    public String getName() {

        return "Creation Table";
    }

    @Override
    public PowerProvider getPowerProvider() {

        return null;
    }
}

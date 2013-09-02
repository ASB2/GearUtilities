package GU.api.conduit.packet;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.IFluidHandler;
import ASB2.utils.UtilDirection;
import ASB2.utils.UtilFluid;
import ASB2.utils.UtilInventory;
import GU.api.power.IPowerMisc;
import GU.api.power.PowerHelper;

public class ConduitPacket implements IConduitPacket {

    PacketType type;
    ForgeDirection direction;

    FluidTank tank;
    ItemStack heldItem;
    float heldEnergy;

    private ConduitPacket(ForgeDirection direction, PacketType type) {

        this.direction = direction;
        this.type = type;
    }

    public ConduitPacket(ForgeDirection direction, FluidStack stack) {
        this(direction, PacketType.FLUID);

        tank = new FluidTank(stack.amount);
        tank.setFluid(stack);
    }

    public ConduitPacket(ForgeDirection direction, ItemStack stack) {
        this(direction, PacketType.ITEM);

        heldItem = stack;
    }

    public ConduitPacket(ForgeDirection direction, PacketType type, float energy) {
        this(direction, type);
        heldEnergy = energy;
    }

    public void updatePacket(World world, int x, int y, int z) {

        for(ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {

            TileEntity tile = UtilDirection.translateDirectionToTile(world, direction, x, y, z);

            if(tile != null) {

                switch(this.getPacketType()) {

                    case FLUID: {

                        if(tank.getFluid() != null) {
                            
                            if(tile instanceof IFluidHandler) {

                                UtilFluid.moveFluid(tank, direction, (IFluidHandler)tile, 1000, true);
                            }
                        }
                        break;
                    }  
                    
                    case ITEM: {

                        if(heldItem != null) {
                            
                            if(tile instanceof IInventory) {

                                if(UtilInventory.addItemStackToInventory((IInventory)tile, heldItem, true)) {
                                  
                                    heldItem = null;
                                }
                            }
                        }
                        break;
                    }  
                    
                    case GUU: {

                        if(heldEnergy > 0) {
                            
                            if(tile instanceof IPowerMisc) {

                                if(PowerHelper.addEnergyToProvider((IPowerMisc)tile, direction, heldEnergy)) {
                                   
                                    heldEnergy = 0;
                                }
                            }
                        }
                        break;
                    } 
                    default : {
                        
                        break;
                    }
                }
            }
        }
    }

    public void savePacket(NBTTagCompound tag) {

    }

    public void loadPacket(NBTTagCompound tag) {

    }

    public float getHeldEnergy() {

        return heldEnergy;
    }

    public ItemStack getHeldItem() {

        return heldItem;
    }

    public FluidStack getHeldFluid() {

        return tank.getFluid();
    }

    public PacketType getPacketType() {

        return type;
    }

    public ForgeDirection getDirection() {

        return direction;
    }
}

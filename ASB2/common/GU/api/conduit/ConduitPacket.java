package GU.api.conduit;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.IFluidHandler;
import ASB2.utils.UtilDirection;
import ASB2.utils.UtilFluid;

public class ConduitPacket {

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
//        = stack;
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

                        if(heldFluid != null) {
                            
                            if(tile instanceof IFluidHandler) {

                                if(UtilFluid.addFluidToTank((IFluidHandler)tile, direction, heldFluid, true)) {

                                    this.heldFluid = null;
                                }
                            }
                        }
                        break;
                    }   
                }
            }
        }
    }

    public void savePacket() {

    }

    public void loadPacket() {

    }

    public float getHeldEnergy() {

        return heldEnergy;
    }

    public ItemStack getHeldItem() {

        return heldItem;
    }

    public FluidStack getHeldFluid() {

        return heldFluid;
    }

    public PacketType getPacketType() {

        return type;
    }

    public ForgeDirection getDirection() {

        return direction;
    }
}

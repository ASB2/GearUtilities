package GU.api.conduit;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;


public class ConduitPacket {

    PacketType type;
    ForgeDirection direction;

    FluidStack heldFluid;
    ItemStack heldItem;
    float heldEnergy;

    private ConduitPacket(ForgeDirection direction, PacketType type) {

        this.direction = direction;
        this.type = type;
    }

    public ConduitPacket(ForgeDirection direction, FluidStack stack) {
        this(direction, PacketType.FLUID);

        heldFluid = stack;
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

        switch(this.getPacketType()) {
            
            case FLUID: {
                
                if() {
                    
                }
                break;
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

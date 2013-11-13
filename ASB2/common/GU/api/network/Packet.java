package GU.api.network;

import java.util.EnumSet;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;

public class Packet implements IPacket {

    ForgeDirection direction = ForgeDirection.UNKNOWN;
    ItemStack storedItemStack;
    FluidStack storedFluid;

    public Packet(ItemStack stack, FluidStack fStack, ForgeDirection direction) {

        storedItemStack = stack;
        storedFluid = fStack;
        this.direction = direction;
    }

    public Packet(ItemStack stack, ForgeDirection direction) {

        storedItemStack = stack;
        this.direction = direction;
    }

    public ForgeDirection getDirection() {

        return direction;
    }

    public ItemStack getStoredItemStacks() {

        return storedItemStack;
    }

    public FluidStack getStoredFluidStacks() {

        return storedFluid;
    }

    public void load(NBTTagCompound tag) {

    }

    public void save(NBTTagCompound tag) {

        if(storedItemStack != null) {

            NBTTagList tagList = new NBTTagList();
            NBTTagCompound tempCompound = new NBTTagCompound();
            storedItemStack.writeToNBT(tempCompound);
            tagList.appendTag(tempCompound);
            tag.setTag("savePacket", tagList);
        }
    }

    @Override
    public EnumSet<PacketType> getPacketType() {
        // TODO Auto-generated method stub
        return null;
    }
}

package GU.api.conduit;

import java.util.EnumSet;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidTank;
import GU.color.EnumVinillaColor;
import GU.api.power.PowerProvider;

public abstract class ConduitLogic {

    TileEntity tile;
    EnumVinillaColor color;    

    ItemStack[] tileItemStacks;    
    FluidTank fluidTank;
    PowerProvider powerProvider;

    public ConduitLogic(TileEntity t, EnumVinillaColor color, ItemStack[] tileItemStacks, FluidTank fluidTank, PowerProvider powerProvider) {

        tile = t;
        this.color = color;

        this.tileItemStacks = tileItemStacks;
        this.fluidTank = fluidTank;
        this.powerProvider = powerProvider;        
    }

    public EnumSet<EnumConduitType> getConductorType() {

        return EnumSet.of(EnumConduitType.OTHER);
    }

    EnumVinillaColor getColorEnum() {

        return color;
    }

    public void updateConduit() {

    }

    public void readFromNBT(NBTTagCompound tag) {

        if(fluidTank != null)
            fluidTank.readFromNBT(tag);

        NBTTagList nbttaglist = tag.getTagList("Items");

        for (int i = 0; i < nbttaglist.tagCount(); i++) {

            NBTTagCompound nbttagcompound = (NBTTagCompound)nbttaglist.tagAt(i);
            byte byte0 = nbttagcompound.getByte("Slot");

            if (byte0 >= 0 && byte0 < tileItemStacks.length) {

                tileItemStacks[byte0] = ItemStack.loadItemStackFromNBT(nbttagcompound);
            }
        }
    }

    public void writeToNBT(NBTTagCompound tag) {

        if(fluidTank != null)
            fluidTank.writeToNBT(tag);

        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < tileItemStacks.length; i++) {

            if (tileItemStacks[i] != null) {

                NBTTagCompound nbttagcompound = new NBTTagCompound();
                nbttagcompound.setByte("Slot", (byte)i);
                tileItemStacks[i].writeToNBT(nbttagcompound);
                nbttaglist.appendTag(nbttagcompound);
            }
        }

        tag.setTag("Items", nbttaglist);
    }
}

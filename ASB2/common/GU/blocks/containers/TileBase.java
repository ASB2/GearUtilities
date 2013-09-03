package GU.blocks.containers;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.FluidTank;
import ASB2.utils.UtilBlock;
import GU.api.IWrenchable;
import GU.api.color.IVanillaColorable;
import GU.api.color.VanillaColor;
import GU.api.power.PowerProvider;
import GU.api.wait.IWaitTrigger;
import GU.api.wait.Wait;

public abstract class TileBase extends TileEntity implements IVanillaColorable, IWaitTrigger, IWrenchable {

    protected PowerProvider powerProvider;
    protected ForgeDirection orientation;
    protected VanillaColor color;
    protected ItemStack[] tileItemStacks = new ItemStack[0];
    public FluidTank fluidTank;
    protected Wait waitTimer;

    public TileBase() {
        
        if (color == null)
            color = VanillaColor.NONE;

        if (orientation == null)
            orientation = ForgeDirection.DOWN;

        fluidTank = new FluidTank(0);
    }
    
    public void onButtonEvent(int buttonID) {
        
    }

    public ForgeDirection getOrientation() {

        return ForgeDirection.getOrientation(getBlockMetadata());
    }

    @Override
    public VanillaColor getColorEnum() {

        return color;
    }

    @Override
    public void setColor(VanillaColor color) {

        this.color = color;
    }

    @Override
    public void trigger(int id) {

    }

    @Override
    public boolean shouldTick(int id) {

        return !worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);
    }

    @Override
    public void triggerBlock(World world, boolean isSneaking, ItemStack itemStack, int x, int y, int z, int side) {

        if (isSneaking) {

            UtilBlock.breakAndAddToInventory(null, worldObj, x, y, z, 1, true);
        }

        switch (getOrientation()) {

            case DOWN:
                worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, ForgeDirection.UP.ordinal(), 3);
                return;
            case UP:
                worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, ForgeDirection.SOUTH.ordinal(), 3);
                return;
            case SOUTH:
                worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, ForgeDirection.WEST.ordinal(), 3);
                return;
            case WEST:
                worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, ForgeDirection.NORTH.ordinal(), 3);
                return;
            case NORTH:
                worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, ForgeDirection.EAST.ordinal(), 3);
                return;
            case EAST:
                worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, ForgeDirection.DOWN.ordinal(), 3);
                return;

            default:
                worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 3);
                return;
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);

        fluidTank.readFromNBT(tag);

        if (color == VanillaColor.NONE || color == null)
            color = VanillaColor.translateNumberToColor(tag
                    .getInteger("color"));

        if (this.powerProvider != null)
            this.powerProvider.readFromNBT(tag);

        NBTTagList nbttaglist = tag.getTagList("Items");

        tileItemStacks = new ItemStack[tileItemStacks.length];

        for (int i = 0; i < nbttaglist.tagCount(); i++) {
            NBTTagCompound nbttagcompound = (NBTTagCompound) nbttaglist
                    .tagAt(i);
            byte byte0 = nbttagcompound.getByte("Slot");

            if (byte0 >= 0 && byte0 < tileItemStacks.length) {
                tileItemStacks[byte0] = ItemStack.loadItemStackFromNBT(nbttagcompound);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);

        fluidTank.writeToNBT(tag);

        if (this.getColorEnum() != VanillaColor.NONE)
            tag.setInteger("color", VanillaColor.translateColorToNumber(this.getColorEnum()));

        if (this.powerProvider != null)
            this.powerProvider.writeToNBT(tag);

        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < tileItemStacks.length; i++) {
            if (tileItemStacks[i] != null) {
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                nbttagcompound.setByte("Slot", (byte) i);
                tileItemStacks[i].writeToNBT(nbttagcompound);
                nbttaglist.appendTag(nbttagcompound);
            }
        }

        tag.setTag("Items", nbttaglist);
    }
}

package GU.blocks.containers;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.FluidTank;
import GU.api.IWrenchable;
import GU.api.color.EnumVinillaColor;
import GU.api.color.IVinillaColorable;
import GU.api.power.PowerProvider;
import GU.api.wait.IWaitTrigger;
import GU.api.wait.Wait;
import GU.utils.UtilBlock;
import GU.utils.UtilDirection;

public abstract class TileBase extends TileEntity implements IVinillaColorable,
IWaitTrigger, IWrenchable {

    protected PowerProvider powerProvider;
    protected ForgeDirection orientation;
    protected EnumVinillaColor color;
    protected ItemStack[] tileItemStacks = new ItemStack[0];
    public FluidTank fluidTank;
    protected Wait waitTimer;

    public TileBase() {
        
        if (color == null)
            color = EnumVinillaColor.NONE;

        if (orientation == null)
            orientation = ForgeDirection.DOWN;

        fluidTank = new FluidTank(0);
    }

    public ForgeDirection getOrientation() {

        return ForgeDirection.getOrientation(getBlockMetadata());
    }

    @Override
    public EnumVinillaColor getColorEnum() {

        return color;
    }

    @Override
    public void setColor(EnumVinillaColor color) {

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
                worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord,
                        UtilDirection.translateDirectionToNumber(ForgeDirection.UP), 3);
                return;
            case UP:
                worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, UtilDirection.translateDirectionToNumber(ForgeDirection.SOUTH), 3);
                return;
            case SOUTH:
                worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, UtilDirection.translateDirectionToNumber(ForgeDirection.WEST), 3);
                return;
            case WEST:
                worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, UtilDirection .translateDirectionToNumber(ForgeDirection.NORTH), 3);
                return;
            case NORTH:
                worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, UtilDirection.translateDirectionToNumber(ForgeDirection.EAST), 3);
                return;
            case EAST:
                worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, UtilDirection.translateDirectionToNumber(ForgeDirection.DOWN), 3);
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

        if (color == EnumVinillaColor.NONE || color == null)
            color = EnumVinillaColor.translateNumberToColor(tag
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

        if (this.getColorEnum() != EnumVinillaColor.NONE)
            tag.setInteger("color", EnumVinillaColor.translateColorToNumber(this.getColorEnum()));

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

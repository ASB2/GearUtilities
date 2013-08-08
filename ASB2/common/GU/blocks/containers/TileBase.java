package GU.blocks.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;
import GU.api.IDirectionSpecific;
import GU.api.color.EnumColor;
import GU.api.color.IColorable;
import GU.api.power.IPowerMisc;
import GU.api.power.PowerProvider;
import GU.api.wait.IWaitTrigger;
import GU.api.wait.Wait;
import GU.utils.UtilDirection;

public abstract class TileBase extends TileEntity implements IPowerMisc, IColorable, IDirectionSpecific, IInventory, IWaitTrigger, IFluidTank, IFluidHandler {

    protected PowerProvider powerProvider;
    protected ForgeDirection orientation;    
    protected EnumColor color;
    protected ItemStack[] tileItemStacks = new ItemStack[0];
    protected FluidTank fluidTank;
    protected Wait waitTimer;

    public TileBase() {

        if(color == null)
            color = EnumColor.NONE;

        if(orientation == null)
            orientation = ForgeDirection.DOWN;

        fluidTank = new FluidTank(0);
    }

    @Override
    public PowerProvider getPowerProvider() {

        return powerProvider;
    }

    @Override
    public ForgeDirection getOrientation() {

        if(!(orientation == UtilDirection.translateNumberToDirection(getBlockMetadata()))) {

            this.orientation = UtilDirection.translateNumberToDirection(getBlockMetadata());
        }

        if(orientation == ForgeDirection.SOUTH) {
            return UtilDirection.translateDirectionToOpposite(orientation);
        }
        if(orientation == ForgeDirection.NORTH) {
            return UtilDirection.translateDirectionToOpposite(orientation);
        }
        if(orientation == ForgeDirection.UP) {
            return UtilDirection.translateDirectionToOpposite(orientation);
        }
        if(orientation == ForgeDirection.DOWN) {
            return UtilDirection.translateDirectionToOpposite(orientation);
        }
        return orientation;
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return "Not Set";
    }


    @Override
    public EnumColor getColorEnum() {

        return color;
    }

    @Override
    public void setColor(EnumColor color) {

        this.color = color;
    }

    @Override
    public int getSizeInventory() {
        // TODO Auto-generated method stub
        return tileItemStacks.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {

        if(slot <= tileItemStacks.length) {

            return tileItemStacks[slot];
        }
        return null;

    }

    @Override
    public ItemStack decrStackSize(int slot, int amt) {

        ItemStack stack = getStackInSlot(slot);

        if (stack != null) {

            if (stack.stackSize <= amt) {

                setInventorySlotContents(slot, null);
            } 
            else {

                stack = stack.splitStack(amt);
                if (stack.stackSize == 0) {

                    setInventorySlotContents(slot, null);
                }
            }
        }
        return stack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {

        ItemStack stack = getStackInSlot(slot);

        if (stack != null) {

            setInventorySlotContents(slot, null);
        }
        return stack;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack) {

        tileItemStacks[slot] = itemStack;
    }

    @Override
    public String getInvName() {

        return this.getName();
    }

    @Override
    public boolean isInvNameLocalized() {

        return true;
    }

    @Override
    public int getInventoryStackLimit() {

        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {

        return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) == this && player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;
    }

    @Override
    public void openChest() {
        // TODO Auto-generated method stub

    }

    @Override
    public void closeChest() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemstack) {

        return true;
    } 

    public void trigger(int id) {

    }

    @Override
    public boolean shouldTick(int id) {

        return !worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);
    }

    @Override
    public FluidStack getFluid() {

        return fluidTank.getFluid();
    }

    @Override
    public int getFluidAmount() {

        return fluidTank.getFluidAmount();
    }

    @Override
    public int getCapacity() {

        return fluidTank.getCapacity();
    }

    @Override
    public FluidTankInfo getInfo() {

        return fluidTank.getInfo();
    }

    @Override
    public int fill(FluidStack resource, boolean doFill) {

        if (resource.isFluidEqual(fluidTank.getFluid()) || fluidTank.getFluid() == null)
            return fluidTank.fill(resource, doFill);

        return 0;
    }

    @Override
    public FluidStack drain(int maxDrain, boolean doDrain) {

        return fluidTank.drain(maxDrain, doDrain);
    }

    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {

        return this.fill(resource, doFill);
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {

        if (resource == null || !resource.isFluidEqual(fluidTank.getFluid())) {

            return null;
        }
        return fluidTank.drain(resource.amount, doDrain);
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {

        return this.drain(maxDrain, doDrain);
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {

        return this.fluidTank.getFluid().isFluidEqual(new FluidStack(fluid, 1));
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {

        return fluidTank.getFluidAmount() > 0;
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        // TODO Auto-generated method stub
        return new FluidTankInfo[] {this.getInfo()};
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);

        fluidTank.readFromNBT(tag);

        if(color == EnumColor.NONE || color == null)
            color = EnumColor.translateNumberToColor(tag.getInteger("color"));

        if(this.getPowerProvider() != null)
            this.getPowerProvider().readFromNBT(tag);

        NBTTagList nbttaglist = tag.getTagList("Items");

        tileItemStacks = new ItemStack[getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); i++)
        {
            NBTTagCompound nbttagcompound = (NBTTagCompound)nbttaglist.tagAt(i);
            byte byte0 = nbttagcompound.getByte("Slot");

            if (byte0 >= 0 && byte0 < tileItemStacks.length)
            {
                tileItemStacks[byte0] = ItemStack.loadItemStackFromNBT(nbttagcompound);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tag){
        super.writeToNBT(tag); 

        fluidTank.writeToNBT(tag);
        
        if(this.getColorEnum() != EnumColor.NONE)
            tag.setInteger("color", EnumColor.translateColorToNumber(this.getColorEnum()));

        if(this.getPowerProvider() != null)
            this.getPowerProvider().writeToNBT(tag);

        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < tileItemStacks.length; i++)
        {
            if (tileItemStacks[i] != null)
            {
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                nbttagcompound.setByte("Slot", (byte)i);
                tileItemStacks[i].writeToNBT(nbttagcompound);
                nbttaglist.appendTag(nbttagcompound);
            }
        }

        tag.setTag("Items", nbttaglist);
    }
}

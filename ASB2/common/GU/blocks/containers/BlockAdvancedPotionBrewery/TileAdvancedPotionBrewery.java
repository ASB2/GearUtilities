package GU.blocks.containers.BlockAdvancedPotionBrewery;

import java.util.ArrayList;

import cpw.mods.fml.common.network.PacketDispatcher;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import GU.api.module.IModuleProvider;
import GU.api.module.IModuleUser;
import GU.blocks.containers.TileBase;
import GU.utils.UtilInventory;
import GU.*;
import GU.api.potion.*;
import GU.api.power.*;
import GU.api.wait.Wait;
import GU.packets.TankPacket;
import GU.power.*;
import GU.info.*;
import GU.utils.*;

public class TileAdvancedPotionBrewery extends TileBase implements IInventory, IFluidHandler, IModuleUser, IPowerMisc {

    ArrayList<IModuleProvider> moduleList = new ArrayList<IModuleProvider>();

    boolean shouldCraft = false;;

    public TileAdvancedPotionBrewery() {

        this.waitTimer = new Wait(20 * 5, this, 1);
        tileItemStacks = new ItemStack[8];
        fluidTank = new FluidTank(1000 * 10);
        powerProvider = new GUPowerProvider(PowerClass.LOW, State.SINK);
    }

    @Override
    public void updateEntity() {

        waitTimer.update();
        
        if((shouldCraft || worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord))) {

            if(hasRequired()) {

                this.craftPotion();
            }
        }
        else {
            
            shouldCraft = false;
        }
    }

    public void onButtonEvent(int buttonID) {

        if(buttonID == 0)
            this.shouldCraft = true;
    }

    private void craftPotion() {

        shouldCraft = false;

        ItemStack potion = new ItemStack(ItemRegistry.ItemBrewedPotion, 1, 0);

        IPotion potionInterface = (IPotion)potion.getItem();

        potionInterface.setDuration(potion, getCombinedDuration());
        potionInterface.setIngredients(potion, getIngredients());
        potionInterface.setStrength(potion, getCombinedStrength());

        if(hasRequired()) {

            UtilFluid.removeFluidFromTank(this, ForgeDirection.UNKNOWN, new FluidStack(FluidRegistry.WATER, 1000), true);
            this.getPowerProvider().usePower(Variables.POTION_BASE_COST * this.getIngredients().size() + getCombinedPower(), ForgeDirection.UNKNOWN, true);

            UtilInventory.decreaseSlotContents(this, 0, 1);
            UtilInventory.decreaseSlotContents(this, 1, 1);
            UtilInventory.decreaseSlotContents(this, 2, 1);
            UtilInventory.decreaseSlotContents(this, 3, 1);
            UtilInventory.decreaseSlotContents(this, 4, 1);
            UtilInventory.decreaseSlotContents(this, 5, 1);
            UtilInventory.decreaseSlotContents(this, 6, 1);

            if(!UtilInventory.addItemStackToSlot(this, potion, 7, true)) {

                UtilBlock.spawnItemStackEntity(worldObj, xCoord, yCoord, zCoord, potion, 1);
            }
        }
    }

    public int getCombinedDuration() {

        int change = 0;

        for(ItemStack stack : getIngredients()) {

            if(stack != null) {

                if(stack.getItem() instanceof IPotionIngredient) {

                    IPotionIngredient potionInterface = (IPotionIngredient)stack.getItem();
                    change += potionInterface.getDurationChange(stack);
                }            
            }
        }
        if(change < 0) {
            return 0;
        }
        return change;
    }

    public int getCombinedStrength() {

        int change = 0;

        for(ItemStack stack : getIngredients()) {

            if(stack != null) {

                if(stack.getItem() instanceof IPotionIngredient) {

                    IPotionIngredient potionInterface = (IPotionIngredient)stack.getItem();
                    change += potionInterface.getStrengthChange(stack);
                }
            }
        }
        if(change < 0) {
            return 0;
        }
        return change;
    }

    public int getCombinedPower() {

        int change = 0;

        for(ItemStack stack : getIngredients()) {

            if(stack != null) {

                if(stack.getItem() instanceof IPotionIngredient) {

                    IPotionIngredient potionInterface = (IPotionIngredient)stack.getItem();
                    change += potionInterface.getPowerChange(stack);
                }
            }
        }
        if(change < 0) {
            return 0;
        }
        return change;
    }

    public ArrayList<ItemStack> getIngredients() {

        ArrayList<ItemStack> itemList = new ArrayList<ItemStack>();

        itemList.add(tileItemStacks[0]);
        itemList.add(tileItemStacks[1]);
        itemList.add(tileItemStacks[2]);
        itemList.add(tileItemStacks[3]);
        itemList.add(tileItemStacks[4]);
        itemList.add(tileItemStacks[5]);
        itemList.add(tileItemStacks[6]);
        return itemList;
    }

    public boolean hasRequired() {

        return (this.fluidTank.getFluidAmount() >= 1000) && (this.getPowerProvider().usePower(Variables.POTION_BASE_COST * this.getIngredients().size() + getCombinedPower(), ForgeDirection.UNKNOWN, false));
    }

    @Override
    public boolean addModule(IModuleProvider module) {

        if(!moduleList.contains(module)) {

            return moduleList.add(module);         
        }
        return false;
    }

    @Override
    public boolean removeModule(IModuleProvider module) {

        if(moduleList.contains(module)) {

            return moduleList.remove(module);         
        }
        return false;
    }

    @Override
    public ArrayList<IModuleProvider> getModules() {

        return moduleList;
    }

    @Override
    public void onInventoryChanged() {
        super.onInventoryChanged();

    }

    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {

        if(resource.getFluid() == FluidRegistry.WATER) {

            return fluidTank.fill(resource, doFill);
        }
        return 0;   
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {

        if(fluid == FluidRegistry.WATER) {

            if(fluidTank != null) {

                if(fluid != null) {

                    if(fluidTank.getFluid() != null) {

                        if(this.fluidTank.getFluid().isFluidEqual(new FluidStack(fluid, 0))) {

                            return true;
                        }
                    } 
                    else {

                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource,
            boolean doDrain) {

        if(resource == null || !resource.isFluidEqual(fluidTank.getFluid())) {

            return null;
        }

        worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
        return fluidTank.drain(resource.amount, doDrain);
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {

        return fluidTank.drain(maxDrain, doDrain);
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {

        if(this.fluidTank.getFluid() != null) {

            if(fluidTank.getFluidAmount() > 0) {

                if(this.fluidTank.getFluid().isFluidEqual(new FluidStack(fluid, 1))) {

                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {

        return new FluidTankInfo[] { fluidTank.getInfo() };
    }

    @Override
    public int getSizeInventory() {

        return tileItemStacks.length;
    }

    @Override
    public ItemStack getStackInSlot(int i) {

        return tileItemStacks[i];
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {


        return UtilInventory.decreaseSlotContents(this, slot, amount);
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {

        return tileItemStacks[i];
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemStack) {

        tileItemStacks[i] = itemStack;
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
    public boolean isUseableByPlayer(EntityPlayer entityplayer) {
        // TODO Auto-generated method stub
        return true;
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
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public String getInvName() {

        return "Advanced Potion Brewery";
    }

    @Override
    public PowerProvider getPowerProvider() {

        return powerProvider;
    }
    
    @Override
    public void trigger(int id) {

        if(fluidTank.getFluid() != null) {

            PacketDispatcher.sendPacketToAllAround(xCoord, yCoord, zCoord, 20, worldObj.provider.dimensionId, new TankPacket(xCoord, yCoord, zCoord, fluidTank.getFluid().getFluid().getID(), fluidTank.getFluid().amount).makePacket());
        } 
        else {

            PacketDispatcher.sendPacketToAllAround(xCoord, yCoord, zCoord, 20, worldObj.provider.dimensionId, new TankPacket(xCoord, yCoord, zCoord, 0, 0).makePacket());
        }
    }
}

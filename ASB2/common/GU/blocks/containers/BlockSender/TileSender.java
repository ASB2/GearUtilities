package GU.blocks.containers.BlockSender;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.IFluidHandler;
import ASB2.utils.UtilBlock;
import ASB2.utils.UtilDirection;
import ASB2.utils.UtilFluid;
import ASB2.utils.UtilInventory;
import ASB2.vector.Vector3;
import GU.api.cluster.IClusterTrigger;
import GU.api.cluster.IClustor;
import GU.api.power.IPowerMisc;
import GU.api.power.PowerClass;
import GU.api.power.PowerHelper;
import GU.api.power.PowerProvider;
import GU.api.wait.Wait;
import GU.blocks.containers.TileBase;
import GU.entity.EntityCluster.EntityInfoCluster;
import GU.info.Variables;
import GU.power.GUPowerProvider;

public class TileSender extends TileBase implements IClusterTrigger, IInventory, IPowerMisc {

    public static final int EXTRACTING_MODE = 1;
    public static final int SMELTING_MODE = 2;
    public static final int POWER_MODE = 3;
    public static final int LIQUID_MODE = 4;
    public static final int VORTEX_STABILIZE_MODE = 5;
    public static final int BLOCK_BREAK_MODE = 6;

    float animationPosition;
    int currentMode;

    public TileSender() {

        waitTimer = new Wait(5, this, 0);
        this.tileItemStacks = new ItemStack[9];    
        this.powerProvider = new GUPowerProvider(PowerClass.LOW);
    }

    public void updateEntity() {

        animationPosition++;

        if(!worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {

            this.waitTimer.update();
        }
        if(Minecraft.getMinecraft().thePlayer != null && Minecraft.getMinecraft().thePlayer.openContainer != null && Minecraft.getMinecraft().thePlayer.openContainer instanceof ContainerSender)
            this.sendReqularPowerPackets(10);
    }

    public void setMode(int mode) {

        this.currentMode = mode;
    }

    public int getMode() {

        return currentMode;
    }

    @Override
    public void onSentClustorCollosion(IClusterTrigger sender, ForgeDirection side, Vector3 position, IClustor clustor, int id) {

        TileEntity sink = position.getTileEntity(worldObj);
        TileEntity source = UtilDirection.translateDirectionToTile(this, worldObj, this.getOrientation().getOpposite());

        if(sink != null && source != null) {

            if(source != sink) {

                if(source instanceof IInventory && sink instanceof IInventory) {

                    IInventory sourceI = (IInventory)source;
                    IInventory sinkI = (IInventory)sink;

                    switch(this.getMode()) {

                        case EXTRACTING_MODE: {

                            if(PowerHelper.removeEnergyFromProvider(this, side.getOpposite(), Variables.SENDER_ITEM_COST, true)) {

                                UtilInventory.moveEntireInventory(sourceI, sinkI);
                                clustor.stopClustor();
                                break;
                            }
                        }

                        case SMELTING_MODE: {

                            if(PowerHelper.removeEnergyFromProvider(this, side.getOpposite(), Variables.SENDER_SMELT_COST, true)) {

                                for(int i = 0; i < sourceI.getSizeInventory(); i++) {

                                    ItemStack stack = sourceI.getStackInSlot(i);

                                    if(stack != null) {

                                        if(UtilInventory.smeltItemStack(stack) != null) {

                                            if(UtilInventory.removeItemStackFromInventory(sourceI, stack, 1, true)) {

                                                if(!UtilInventory.addItemStackToInventory(sinkI, UtilInventory.smeltItemStack(stack), true)) {

                                                    UtilInventory.addItemStackToInventoryAndSpawnExcess(worldObj, sinkI, UtilInventory.smeltItemStack(stack), position.intX(), position.intY(), position.intZ());
                                                    clustor.stopClustor();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            break;
                        }
                    }
                }
                if(sink instanceof IPowerMisc) {

                    IPowerMisc sinkI = (IPowerMisc)sink;

                    switch(this.getMode()) {

                        case POWER_MODE: {

                            if(PowerHelper.removeEnergyFromProvider(this, side.getOpposite(), Variables.SENDER_POWERSEND_COST, false) && PowerHelper.moveEnergy(this, sinkI, side, Variables.SENDER_POWERSEND_AMOUNT, false)) {

                                PowerHelper.removeEnergyFromProvider(this, side.getOpposite(), Variables.SENDER_POWERSEND_COST, false);
                                PowerHelper.moveEnergy(this, sinkI, side, Variables.SENDER_POWERSEND_AMOUNT, true);
                                clustor.stopClustor();
                            }
                            break;
                        }
                    }
                }
                else {
                    
                    if(sink instanceof IFluidHandler && source instanceof IFluidHandler) {
                        
                        if(LIQUID_MODE == this.getMode()) {
                            
                            if(PowerHelper.removeEnergyFromProvider(this, this.getOrientation().getOpposite(), Variables.SENDER_BREAKBLOCK_AMOUNT, false) && UtilFluid.moveFluid((IFluidHandler)source, side, (IFluidHandler)sink, false)) {
                                
                                PowerHelper.removeEnergyFromProvider(this, this.getOrientation().getOpposite(), Variables.SENDER_BREAKBLOCK_AMOUNT, true);
                                UtilFluid.moveFluid((IFluidHandler)source, side, (IFluidHandler)sink, true);  
                            }                          
                        }
                    }
                }
            }
        }            
    }

    @Override
    public void onClustorCollosion(IClusterTrigger source, ForgeDirection side, Vector3 position, IClustor clustor) {
        // TODO Auto-generated method stub

    }
    
    @Override
    public void trigger(int id) {

        if(BLOCK_BREAK_MODE == this.getMode()) {

            if(PowerHelper.removeEnergyFromProvider(this, this.getOrientation().getOpposite(), Variables.SENDER_BREAKBLOCK_AMOUNT, false)) {

                if(UtilBlock.breakAndAddToInventory(this, worldObj, xCoord + this.getOrientation().getOpposite().offsetX, yCoord + this.getOrientation().getOpposite().offsetY, zCoord + this.getOrientation().getOpposite().offsetZ, false)) {

                    PowerHelper.removeEnergyFromProvider(this, this.getOrientation().getOpposite(), Variables.SENDER_BREAKBLOCK_AMOUNT, true);
                    UtilBlock.breakAndAddToInventory(this, worldObj, xCoord + this.getOrientation().getOpposite().offsetX, yCoord + this.getOrientation().getOpposite().offsetY, zCoord + this.getOrientation().getOpposite().offsetZ, true);
                }
            }
        }
        else {
            
            TileEntity tile = UtilDirection.translateDirectionToTile(this, worldObj, this.getOrientation().getOpposite());

            if(tile != null) {

                if(tile instanceof IInventory || tile instanceof IPowerMisc || tile instanceof IFluidHandler) {

                    if(!worldObj.isRemote) {

                        worldObj.spawnEntityInWorld(new EntityInfoCluster(worldObj, new Vector3(this), this.getOrientation(), this, 0));
                    }
                }
            }
        }
    }

    @Override
    public PowerProvider getPowerProvider() {

        return powerProvider;
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

        return "Universal Sender";
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);

        currentMode = tag.getInteger("mode");        
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);

        tag.setInteger("mode", currentMode);
    }
}

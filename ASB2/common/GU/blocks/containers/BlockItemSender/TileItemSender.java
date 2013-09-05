package GU.blocks.containers.BlockItemSender;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.IFluidHandler;
import ASB2.utils.UtilDirection;
import ASB2.utils.UtilFluid;
import ASB2.utils.UtilInventory;
import ASB2.vector.Vector3;
import GU.api.cluster.IClusterTrigger;
import GU.api.cluster.IClustor;
import GU.api.power.IPowerMisc;
import GU.api.power.PowerHelper;
import GU.api.wait.Wait;
import GU.blocks.containers.TileBase;
import GU.entity.EntityCluster.EntityInfoCluster;

public class TileItemSender extends TileBase implements IClusterTrigger, IInventory {

    public TileItemSender() {

        waitTimer = new Wait(20 * 10, this, 0);
    }

    public void updateEntity() {

        waitTimer.update();
        this.tileItemStacks = new ItemStack[10];
    }

    @Override
    public void onClustorCollosion(ForgeDirection side, Vector3 position, IClustor clustor) {

        TileEntity sink = position.getTileEntity(worldObj);

        if(sink != null) {

            TileEntity source = UtilDirection.translateDirectionToTile(this, worldObj, this.getOrientation().getOpposite());

            if(source != null) {

                if(source != sink) {

                    if(source instanceof IInventory) {

                        if(sink instanceof IInventory) {

                            UtilInventory.moveEntireInventory((IInventory)source, (IInventory)sink);
                        }
                    }

                    if(source instanceof IFluidHandler) {

                        if(sink instanceof IFluidHandler) {

                            UtilFluid.moveFluid((IFluidHandler)source, side, (IFluidHandler)sink, true);
                        }
                    }

                    if(source instanceof IPowerMisc) {

                        if(sink instanceof IPowerMisc) {

                            if(PowerHelper.moveEnergy((IPowerMisc)source, (IPowerMisc)sink, side, true)) {

                                System.out.println("Packet Recieved");
                            }
                        }
                    }
                }
            }                 
        }   
    }
    
    public boolean hasAdjacent(TileEntity tile) {
        
        if(tile != null) {
            
        }
        return false;
    }

    @Override
    public void trigger(int id) {

        TileEntity tile = UtilDirection.translateDirectionToTile(this, worldObj, this.getOrientation().getOpposite());

        if(tile != null) {

            if(tile instanceof IInventory || tile instanceof IPowerMisc || tile instanceof IFluidHandler) {

                if(!worldObj.isRemote) {

                    worldObj.spawnEntityInWorld(new EntityInfoCluster(worldObj, new Vector3(this), this.getOrientation(), this, 20));
                }
            }
        }
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
}

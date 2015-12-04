package GU.blocks.containers.BlockCreativeMetadata;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import ASB2.utils.UtilInventory;
import GU.blocks.containers.TileBase;

public class TileCreativeItem extends TileBase implements IInventory {
    
    ItemStack toDuplicate;
    
    public TileCreativeItem() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void updateEntity() {
        
        if (!worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {
            
            if (toDuplicate != null) {
                
                for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
                    
                    TileEntity tile = worldObj.getTileEntity(xCoord + direction.offsetX, yCoord + direction.offsetY, zCoord + direction.offsetZ);
                    
                    if (tile != null) {
                        
                        if (tile instanceof ISidedInventory) {
                            
                            UtilInventory.addItemStackToISidedInventory((ISidedInventory) tile, direction, toDuplicate, true);
                        } else if (tile instanceof IInventory) {
                            
                            UtilInventory.addItemStackToInventory((IInventory) tile, toDuplicate, true);
                        }
                    }
                }
            }
        } else {
            
            for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
                
                TileEntity tile = worldObj.getTileEntity(xCoord + direction.offsetX, yCoord + direction.offsetY, zCoord + direction.offsetZ);
                
                if (tile != null) {
                    
                    if (tile instanceof ISidedInventory) {
                        
                        if (toDuplicate != null) {
                            
                            UtilInventory.removeItemStackFromISidedInventory((ISidedInventory) tile, direction, toDuplicate, toDuplicate.stackSize, true);
                        } else {
                            
                            UtilInventory.moveEntireISidedInventory((ISidedInventory) tile, direction, this);
                        }
                    } else if (tile instanceof IInventory) {
                        
                        if (toDuplicate != null) {
                            
                            UtilInventory.removeItemStackFromInventory((IInventory) tile, toDuplicate, toDuplicate.stackSize, true);
                        } else {
                            
                            UtilInventory.moveEntireInventory((IInventory) tile, this);
                        }
                    }
                }
            }
        }
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag) {
        
        if (toDuplicate != null) {
            
            tag.setTag("toDuplicate", toDuplicate.writeToNBT(new NBTTagCompound()));
        }
        super.writeToNBT(tag);
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag) {
        
        toDuplicate = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("toDuplicate"));
        super.readFromNBT(tag);
    }
    
    @Override
    public int getSizeInventory() {
        
        return 1;
    }
    
    @Override
    public ItemStack getStackInSlot(int var1) {
        
        return toDuplicate;
    }
    
    @Override
    public ItemStack decrStackSize(int var1, int var2) {
        
        return toDuplicate;
    }
    
    @Override
    public ItemStack getStackInSlotOnClosing(int var1) {
        
        return toDuplicate;
    }
    
    @Override
    public void setInventorySlotContents(int var1, ItemStack var2) {
        
    }
    
    @Override
    public String getInventoryName() {
        
        return "Creative Item";
    }
    
    @Override
    public boolean hasCustomInventoryName() {
        
        return true;
    }
    
    @Override
    public int getInventoryStackLimit() {
        
        return 64;
    }
    
    @Override
    public boolean isUseableByPlayer(EntityPlayer var1) {
        
        return true;
    }
    
    @Override
    public void openInventory() {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void closeInventory() {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public boolean isItemValidForSlot(int var1, ItemStack var2) {
        
        return true;
    }
}

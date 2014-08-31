package GU.blocks.containers.BlockMultiInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import ASB2.utils.UtilInventory;
import ASB2.utils.UtilVector;
import GU.GearUtilities;
import GU.api.color.AbstractColorable.IColorableTile;
import GU.api.multiblock.MultiBlockAbstract.EnumMultiBlockPartPosition;
import GU.api.multiblock.MultiBlockAbstract.IInventoryMultiBlock;
import GU.api.multiblock.MultiBlockAbstract.IItemInterface;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlock;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlockPart;
import GU.blocks.containers.TileMultiBase;
import GU.packets.EnumInputIconPacket;
import GU.render.EnumInputIcon;
import GU.render.IEnumInputIcon;
import UC.color.Color4i;
import UC.math.vector.Vector3i;

public class TileItemMultiInterface extends TileMultiBase implements IMultiBlockPart, IItemInterface, IInventory, IColorableTile, IEnumInputIcon {
    
    Map<SlotHolder, IInventoryMultiBlock> inventorise = new HashMap<SlotHolder, IInventoryMultiBlock>();
    int maxSizeInventory = 0;
    Vector3i position;
    public EnumInputIcon[] sideState;
    
    public TileItemMultiInterface() {
        
        this.setMaxMultiBlocks(2);
        sideState = new EnumInputIcon[ForgeDirection.values().length];
        
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
            
            sideState[direction.ordinal()] = EnumInputIcon.NONE;
        }
    }
    
    int wait = 0;
    
    @Override
    public void updateEntity() {
        
        if (position == null) {
            
            position = UtilVector.createTileEntityVector(this);
        }
        
        wait++;
        
        if (!inventorise.isEmpty() && !worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord) && !worldObj.isRemote) {
            
            wait = 0;
            
            for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
                
                EnumInputIcon side = sideState[direction.ordinal()];
                
                if (side == EnumInputIcon.OUTPUT || side == EnumInputIcon.INPUT) {
                    
                    TileEntity tile = worldObj.getTileEntity(xCoord + direction.offsetX, yCoord + direction.offsetY, zCoord + direction.offsetZ);
                    
                    if (tile != null) {
                        
                        if (tile instanceof ISidedInventory) {
                            
                            if (side == EnumInputIcon.INPUT) {
                                
                                UtilInventory.moveItems((ISidedInventory) tile, direction, 1, 1, this);
                                // UtilInventory.moveEntireISidedInventory((ISidedInventory)
                                // tile, direction, this);
                            } else {
                                
                                UtilInventory.moveItems(this, 1, 1, (ISidedInventory) tile, direction);
                                // UtilInventory.moveEntireISidedInventory(this,
                                // direction, (ISidedInventory) tile);
                            }
                        }
                        
                        if (tile instanceof IInventory) {
                            
                            if (side == EnumInputIcon.INPUT) {
                                
                                UtilInventory.moveItems((IInventory) tile, 1, 1, this);
                                // UtilInventory.moveEntireInventory((IInventory)
                                // tile, this);
                            } else {
                                UtilInventory.moveItems(this, 1, 1, (IInventory) tile);
                                // UtilInventory.moveEntireInventory(this,
                                // (IInventory) tile);
                            }
                        }
                    }
                }
            }
        }
    }
    
    public void setSideState(EnumInputIcon[] sideState) {
        this.sideState = sideState;
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }
    
    public EnumInputIcon[] getSideState() {
        return sideState;
    }
    
    @Override
    public Color4i getColor(ForgeDirection direction) {
        
        Color4i color = sideState[direction.ordinal()].getColor();
        color = color.getRed() == 255 && color.getGreen() == 255 && color.getRed() == 255 ? Color4i.GREEN : color;
        
        switch (sideState[direction.ordinal()]) {
        
            case BOTH:
                return sideState[direction.ordinal()].getColor();
            case INPUT:
                return Color4i.BLUE;
            case NONE:
                return Color4i.GREEN;
            case OUTPUT:
                return Color4i.ORANGE;
            default:
                break;
        }
        return new Color4i(color.getRed(), (color.getGreen() + 255) / 2, color.getBlue());
    }
    
    @Override
    public boolean setColor(Color4i color, ForgeDirection direction) {
        
        return false;
    }
    
    @Override
    public boolean isPositionValid(EnumMultiBlockPartPosition position) {
        
        return position == EnumMultiBlockPartPosition.FACE;
    }
    
    @Override
    public boolean triggerBlock(World world, EntityPlayer player, int x, int y, int z, ForgeDirection axis) {
        
        sideState[axis.ordinal()] = sideState[axis.ordinal()].increment();
        world.markBlockForUpdate(x, y, z);
        return true;
    }
    
    @Override
    public void setEnumInputIcon(EnumInputIcon[] newIcon) {
        
        sideState = newIcon;
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag) {
        
        int side = 0;
        
        for (EnumInputIcon state : sideState) {
            
            if (state != null)
                tag.setInteger("state" + side, state.ordinal());
            side++;
        }
        super.writeToNBT(tag);
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag) {
        
        for (int index = 0; index < sideState.length; index++) {
            
            sideState[index] = EnumInputIcon.values()[tag.getInteger("state" + index)];
        }
        super.readFromNBT(tag);
    }
    
    @Override
    public void sendUpdatePacket() {
        
        if (!worldObj.isRemote)
            GearUtilities.getPipeline().sendToDimension(new EnumInputIconPacket(xCoord, yCoord, zCoord, sideState), worldObj.provider.dimensionId);
        super.sendUpdatePacket();
    }
    
    private class SlotHolder {
        
        public int minSlot, maxSlot;
        
        public SlotHolder(int minSlot, int maxSlot) {
            this.minSlot = minSlot;
            this.maxSlot = maxSlot;
        }
        
        public int getMinSlot() {
            
            return minSlot;
        }
        
        public int getMaxSlot() {
            
            return maxSlot;
        }
    }
    
    @Override
    public boolean addMultiBlock(IMultiBlock multiBlock) {
        
        if (super.addMultiBlock(multiBlock)) {
            
            recalculate();
            return true;
        }
        return false;
    }
    
    @Override
    public void removeMultiBlock(IMultiBlock multiBlock) {
        super.removeMultiBlock(multiBlock);
        recalculate();
    }
    
    @SuppressWarnings("unused")
    public void recalculate() {
        
        if (false) {
            
            int lastSlotMin = 0;
            
            for (IMultiBlock multi : this.getMultiBlocks()) {
                
                if (multi instanceof IInventoryMultiBlock) {
                    
                    int size = ((IInventoryMultiBlock) multi).getInventory(position).getSizeInventory();
                    
                    if (size > 0) {
                        
                        this.inventorise.put(new SlotHolder(lastSlotMin, ((IInventoryMultiBlock) multi).getInventory(position).getSizeInventory() + lastSlotMin), (IInventoryMultiBlock) multi);
                        lastSlotMin += ((IInventoryMultiBlock) multi).getInventory(position).getSizeInventory() + 1;
                    }
                }
            }
            maxSizeInventory = lastSlotMin;
        } else {
            
            int minSlot = 0;
            maxSizeInventory = 0;
            
            for (IMultiBlock multi : this.getMultiBlocks()) {
                
                if (multi instanceof IInventoryMultiBlock) {
                    
                    IInventory inventory = ((IInventoryMultiBlock) multi).getInventory(position);
                    
                    if (inventory != null) {
                        
                        int size = inventory.getSizeInventory();
                        
                        if (size > 0) {
                            
                            SlotHolder holder = new SlotHolder(minSlot, minSlot + size);
                            this.inventorise.put(holder, (IInventoryMultiBlock) multi);
                            minSlot += size + 1;
                            maxSizeInventory += size;
                        }
                    }
                }
            }
        }
    }
    
    public IInventory redirectSlot(int slot) {
        
        for (SlotHolder slots : inventorise.keySet()) {
            
            if (slots.getMinSlot() <= slot && slots.getMaxSlot() >= slot) {
                
                return ((IInventoryMultiBlock) inventorise.get(slots)).getInventory(position);
            }
        }
        return null;
    }
    
    @Override
    public int getSizeInventory() {
        
        return maxSizeInventory;
    }
    
    @Override
    public ItemStack getStackInSlot(int i) {
        
        IInventory inventory = redirectSlot(i);
        
        if (inventory != null) {
            
            return inventory.getStackInSlot(i);
        }
        return null;
    }
    
    @Override
    public ItemStack decrStackSize(int i, int j) {
        
        IInventory inventory = redirectSlot(i);
        
        if (inventory != null) {
            
            return inventory.decrStackSize(i, j);
        }
        return null;
    }
    
    @Override
    public ItemStack getStackInSlotOnClosing(int i) {
        
        IInventory inventory = redirectSlot(i);
        
        if (inventory != null) {
            
            return inventory.getStackInSlotOnClosing(i);
        }
        return null;
    }
    
    @Override
    public void setInventorySlotContents(int i, ItemStack itemstack) {
        
        IInventory inventory = redirectSlot(i);
        
        if (inventory != null) {
            
            inventory.setInventorySlotContents(i, itemstack);
        }
    }
    
    @Override
    public String getInventoryName() {
        
        if (inventorise != null && inventorise.size() == 1) {
            
            IInventory inventory = inventorise.entrySet().iterator().next().getValue().getInventory(position);
            
            if (inventory != null) {
                
                return inventory.getInventoryName();
            }
        }
        return "Item Multi Interface";
    }
    
    @Override
    public boolean hasCustomInventoryName() {
        
        return true;
    }
    
    @Override
    public int getInventoryStackLimit() {
        
        if (inventorise != null && inventorise.size() == 1) {
            
            IInventory inventory = inventorise.entrySet().iterator().next().getValue().getInventory(position);
            
            if (inventory != null) {
                
                return inventory.getInventoryStackLimit();
            }
        }
        return 64;
    }
    
    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer) {
        // TODO Auto-generated method stub
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
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        
        IInventory inventory = redirectSlot(i);
        
        if (inventory != null) {
            
            return inventory.isItemValidForSlot(i, itemstack);
        }
        return false;
    }
    
    @Override
    public List<IInventory> getAvaliableInventorys() {
        
        List<IInventory> inventoryList = new ArrayList<IInventory>();
        
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
            
            if (sideState[direction.ordinal()] == EnumInputIcon.OUTPUT) {
                
                TileEntity tile = worldObj.getTileEntity(xCoord + direction.offsetX, yCoord + direction.offsetY, zCoord + direction.offsetZ);
                
                if (tile != null && tile instanceof IInventory) {
                    
                    inventoryList.add((IInventory) tile);
                }
            }
        }
        return inventoryList;
    }
}
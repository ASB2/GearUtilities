package GU.blocks.containers.BlockDrill;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import ASB2.inventory.Inventory;
import ASB2.utils.UtilBlock;
import ASB2.utils.UtilEntity;
import ASB2.utils.UtilInventory;
import GU.blocks.containers.TileBase;
import GU.blocks.containers.BlockElectisPolyhedron.TileElectisPolyhedron;
import UC.Wait;
import UC.Wait.IWaitTrigger;
import UC.math.vector.Vector3i;

public class TileDrill extends TileBase {
    
    Wait breakBlockWait;
    boolean coordsSet = false;
    
    Vector3i size, corner;
    List<Vector3i> lastBrokenBlocks;
    
    Inventory blackListInventory;
    
    public TileDrill() {
        
        breakBlockWait = new Wait(new BreakWait(), 20);
        size = new Vector3i();
        corner = new Vector3i();
        lastBrokenBlocks = new ArrayList<Vector3i>();
        blackListInventory = new Inventory(-1, "Blacklist");
    }
    
    @Override
    public void updateEntity() {
        
        breakBlockWait.update();
    }
    
    public boolean breakBlock(int x, int y, int z, IInventory inventory) {
        
        Block block = worldObj.getBlock(x, y, z);
        
        if (!worldObj.isRemote) {
            
            if (!block.isAir(worldObj, x, y, z)) {
                
                if (block.getBlockHardness(worldObj, x, y, z) != -1) {
                    
                    if (!UtilInventory.hasItemStack(blackListInventory, new ItemStack(block, 1, worldObj.getBlockMetadata(x, y, z)))) {
                        
                        if (inventory instanceof ISidedInventory) {
                            
                            return UtilBlock.breakAndAddToISidedInventory((ISidedInventory) inventory, this.getOrientation(), worldObj, x, y, z, true);
                        } else {
                            
                            return UtilBlock.breakAndAddToInventory(inventory, worldObj, x, y, z, true);
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }
    
    public void populateBlackList() {
        
        ForgeDirection oppositeOrientation = this.getOrientation(), orientation = oppositeOrientation.getOpposite();
        
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
            
            if (direction == oppositeOrientation || direction == orientation) {
                
                continue;
            }
            
            TileEntity tile = worldObj.getTileEntity(xCoord + direction.offsetX, yCoord + direction.offsetY, zCoord + direction.offsetZ);
            
            if (tile != null) {
                
                if (tile instanceof IInventory) {
                    
                    if (tile instanceof ISidedInventory) {
                        
                        int[] slots = ((ISidedInventory) tile).getAccessibleSlotsFromSide(direction.getOpposite().ordinal());
                        
                        if (slots != null) {
                            
                            for (int index = 0; index < slots.length; index++) {
                                
                                int slot = slots[index];
                                
                                ItemStack stack = ((ISidedInventory) tile).getStackInSlot(slot);
                                
                                if (stack != null && stack.getItem() instanceof ItemBlock) {
                                    
                                    UtilInventory.addItemStackToInventory(blackListInventory, stack, true);
                                }
                            }
                        }
                    } else {
                        
                        for (int index = 0; index < ((IInventory) tile).getSizeInventory(); index++) {
                            
                            ItemStack stack = ((IInventory) tile).getStackInSlot(index);
                            
                            if (stack != null && stack.getItem() instanceof ItemBlock) {
                                
                                UtilInventory.addItemStackToInventory(blackListInventory, stack, true);
                            }
                        }
                    }
                }
            }
        }
    }
    
    @Override
    public boolean triggerBlock(World world, EntityPlayer player, int x, int y, int z, ForgeDirection axis) {
        
        if (player.isSneaking()) {
            
            if (!world.isRemote) {
                
                if (!coordsSet) {
                    this.rotateBlock(world, x, y, z, axis);
                    return true;
                }
                UtilEntity.sendChatToPlayer(player, "Size Cleared");
            }
            
            this.coordsSet = false;
            size.setXYZ(0, 0, 0);
            corner.setXYZ(0, 0, 0);
            lastBrokenBlocks.clear();
            
            if (blackListInventory != null) {
                
                blackListInventory.clear();
            }
        } else if (!coordsSet) {
            
            int xMin = 0, yMin = 0, zMin = 0;
            int xMax = 0, yMax = 0, zMax = 0;
            
            ForgeDirection oppositeOrientation = this.getOrientation(), orientation = oppositeOrientation.getOpposite();
            
            for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
                
                if (direction == oppositeOrientation) {
                    
                    continue;
                }
                
                for (int distance = 1; distance <= 256; distance++) {
                    
                    TileEntity tile = world.getTileEntity(x + (distance * direction.offsetX), y + (distance * direction.offsetY), z + (distance * direction.offsetZ));
                    
                    if (tile != null) {
                        
                        if (tile instanceof TileElectisPolyhedron) {
                            
                            if (world.isBlockIndirectlyGettingPowered(x + (distance * direction.offsetX), y + (distance * direction.offsetY), z + (distance * direction.offsetZ))) {
                                
                                if (direction.offsetX > 0) {
                                    
                                    xMax += distance;
                                }
                                if (direction.offsetX < 0) {
                                    
                                    xMin += distance;
                                }
                                if (direction.offsetY > 0) {
                                    
                                    yMax += distance;
                                }
                                if (direction.offsetY < 0) {
                                    
                                    yMin += distance;
                                }
                                if (direction.offsetZ > 0) {
                                    
                                    zMax += distance;
                                }
                                if (direction.offsetZ < 0) {
                                    
                                    zMin += distance;
                                }
                                continue;
                            } else {
                                
                                UtilEntity.sendChatToPlayer(player, "The Electis Polyhedron must have a redstone signal to be used. Size detection has stopped to prevent unwanted quarry sizes.");
                                
                                return false;
                            }
                        }
                    }
                    
                    // if (UtilBlock.isBlockAir(world, x + (distance *
                    // direction.offsetX), y + (distance * direction.offsetY), z
                    // + (distance * direction.offsetZ))) {
                    //
                    // continue;
                    // } else {
                    //
                    // break;
                    // }
                }
            }
            
            // if (xMax + xMin != 0 || yMax + yMin != 0 || zMax + zMin != 0) {
            
            corner.setXYZ(xMax + orientation.offsetX, yMax + orientation.offsetY, zMax + orientation.offsetZ);
            size.setXYZ((xMax + xMin), (yMax + yMin), (zMax + zMin));
            
            coordsSet = true;
            
            this.populateBlackList();
            
            if (!world.isRemote) {
                
                UtilEntity.sendChatToPlayer(player, "Size set: ");
                UtilEntity.sendChatToPlayer(player, size);
                UtilEntity.sendChatToPlayer(player, "Beginning Digging");
            }
            // }
        } else {
            
            if (!world.isRemote) {
                
                UtilEntity.sendChatToPlayer(player, "Layer Size: " + lastBrokenBlocks.size());
            }
        }
        return true;
    }
    
    @Override
    public boolean rotateBlock(World world, int x, int y, int z, ForgeDirection axis) {
        
        ForgeDirection direction = this.getOrientation();
        
        if (direction.ordinal() + 1 < ForgeDirection.VALID_DIRECTIONS.length) {
            
            world.setBlockMetadataWithNotify(x, y, z, direction.ordinal() + 1, 3);
        } else {
            world.setBlockMetadataWithNotify(x, y, z, 0, 3);
        }
        return true;
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag) {
        // TODO Auto-generated method stub
        super.readFromNBT(tag);
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag) {
        // TODO Auto-generated method stub
        super.writeToNBT(tag);
    }
    
    private class BreakWait implements IWaitTrigger {
        
        @Override
        public void trigger(int id) {
            
            if (!worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord) && coordsSet) {
                
                ForgeDirection direction = getOrientation().getOpposite();
                
                if (lastBrokenBlocks.size() == 0) {
                    
                    for (int x = 0; x <= size.getX(); x++) {
                        
                        for (int y = 0; y <= size.getY(); y++) {
                            
                            for (int z = 0; z <= size.getZ(); z++) {
                                
                                int finalPosX = x, finalPosY = y, finalPosZ = z;
                                lastBrokenBlocks.add(new Vector3i(finalPosX, finalPosY, finalPosZ));
                            }
                        }
                    }
                }
                
                TileEntity tile = worldObj.getTileEntity(xCoord - direction.offsetX, yCoord - direction.offsetY, zCoord - direction.offsetZ);
                
                if (tile != null && tile instanceof IInventory) {
                    
                    for (Vector3i block : lastBrokenBlocks) {
                        
                        int x = xCoord + (corner.getX() - block.getX()), y = yCoord + (corner.getY() - block.getY()), z = zCoord + (corner.getZ() - block.getZ());
                        
                        if (y >= 0 && breakBlock(x, y, z, ((IInventory) tile)))
                            block.move(-direction.offsetX, -direction.offsetY, -direction.offsetZ);
                    }
                }
            }
        }
        
        @Override
        public boolean shouldTick(int id) {
            
            return true;
        }
    }
}

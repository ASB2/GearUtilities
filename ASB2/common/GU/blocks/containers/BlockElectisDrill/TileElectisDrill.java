package GU.blocks.containers.BlockElectisDrill;

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
import ASB2.utils.UtilVector;
import GU.api.EnumSimulationType;
import GU.api.power.PowerNetAbstract.EnumPowerStatus;
import GU.api.power.PowerNetAbstract.IPowerManager;
import GU.api.power.PowerNetAbstract.ITilePowerHandler;
import GU.api.power.PowerNetObject.DefaultPowerManager;
import GU.blocks.containers.TileBase;
import GU.blocks.containers.BlockElectisPolyhedron.TileElectisPolyhedron;
import UC.Wait;
import UC.Wait.IWaitTrigger;
import UC.math.vector.Vector3i;

public class TileElectisDrill extends TileBase implements ITilePowerHandler {
    
    Wait breakBlockWait;
    boolean coordsSet = false, finished = false, darkCystal = false;
    
    Vector3i size, corner;
    List<Vector3i> lastBrokenBlocks;
    int blocksPastLimit = 0;
    
    Inventory blackListInventory;
    
    DefaultPowerManager powerManager;
    
    public TileElectisDrill() {
        
        breakBlockWait = new Wait(new BreakWait(), 20);
        size = new Vector3i();
        corner = new Vector3i();
        lastBrokenBlocks = new ArrayList<Vector3i>();
        blackListInventory = new Inventory(-1, "Blacklist");
        powerManager = new DefaultPowerManager(4000).setPowerStatus(EnumPowerStatus.SINK);
    }
    
    @Override
    public void updateEntity() {
        
        breakBlockWait.update();
    }
    
    public boolean breakBlock(int x, int y, int z, IInventory inventory) {
        
        if (powerManager.decreasePower(10, EnumSimulationType.FORCED_SIMULATE)) {
            
            Block block = worldObj.getBlock(x, y, z);
            
            if (!UtilBlock.isBlockAir(worldObj, x, y, z)) {
                
                if (block.getBlockHardness(worldObj, x, y, z) != -1) {
                    
                    if (!UtilInventory.hasItemStack(blackListInventory, new ItemStack(block, 1, worldObj.getBlockMetadata(x, y, z)))) {
                        
                        if (inventory instanceof ISidedInventory) {
                            
                            if (UtilBlock.breakAndAddToISidedInventory((ISidedInventory) inventory, this.getOrientation(), worldObj, x, y, z, true)) {
                                
                                powerManager.decreasePower(10, EnumSimulationType.FORCED_LIGITIMATE);
                                return true;
                            }
                        } else {
                            
                            if (UtilBlock.breakAndAddToInventory(inventory, worldObj, x, y, z, true)) {
                                
                                powerManager.decreasePower(10, EnumSimulationType.FORCED_LIGITIMATE);
                                return true;
                            }
                        }
                    }
                }
            }
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
            blocksPastLimit = 0;
            finished = false;
            lastBrokenBlocks.clear();
            darkCystal = false;
            if (blackListInventory != null) {
                
                blackListInventory.clear();
            }
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        } else if (!coordsSet) {
            
            int xMin = 0, yMin = 0, zMin = 0;
            int xMax = 0, yMax = 0, zMax = 0;
            
            ForgeDirection oppositeOrientation = this.getOrientation(), orientation = oppositeOrientation.getOpposite();
            
            for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
                
                if (direction == oppositeOrientation) {
                    
                    continue;
                }
                
                for (int distance = 1; distance <= 17; distance++) {
                    
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
            
            corner.setXYZ(xMax + orientation.offsetX, yMax + orientation.offsetY, zMax + orientation.offsetZ);
            size.setXYZ((xMax + xMin), (yMax + yMin), (zMax + zMin));
            
            coordsSet = true;
            darkCystal = true;
            if ((size.getX() * oppositeOrientation.offsetX) + (size.getY() * oppositeOrientation.offsetY) + (size.getZ() * oppositeOrientation.offsetZ) == 0) {
                
                int orX = Math.abs(oppositeOrientation.offsetX), orY = Math.abs(oppositeOrientation.offsetY), orZ = Math.abs(oppositeOrientation.offsetZ);
                size.move(orX * 10, orY * 10, orZ * 10);
            }
            
            this.populateBlackList();
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
            if (!world.isRemote) {
                
                UtilEntity.sendChatToPlayer(player, "Size set: ");
                UtilEntity.sendChatToPlayer(player, size.add(1 - Math.abs(oppositeOrientation.offsetX), 1 - Math.abs(oppositeOrientation.offsetY), 1 - Math.abs(oppositeOrientation.offsetZ)));
                UtilEntity.sendChatToPlayer(player, "Beginning Digging");
            }
        } else {
            
            if (!world.isRemote) {
                
                UtilEntity.sendChatToPlayer(player, "Layer Size: " + lastBrokenBlocks.size());
                UtilEntity.sendChatToPlayer(player, "Is Finished: " + finished);
            }
        }
        return true;
    }
    
    @Override
    public void sendUpdatePacket() {
        
        NBTTagCompound tag = new NBTTagCompound();
        tag.setBoolean("darkCystal", darkCystal);
        this.sendNBTPacket(tag, 0);
    }
    
    @Override
    public void readNBTPacket(NBTTagCompound tag, int id) {
        
        darkCystal = tag.getBoolean("darkCystal");
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
        
        if (tag.getBoolean("shouldLoad")) {
            
            int vecNumber = tag.getInteger("vecNumber");
            
            if (vecNumber > 0) {
                
                lastBrokenBlocks.clear();
                
                for (int index = 0; index < vecNumber; index++) {
                    
                    Vector3i vec = UtilVector.loadVector3i(tag.getCompoundTag("vec" + index));
                    lastBrokenBlocks.add(vec);
                }
            }
            coordsSet = true;
            finished = false;
            darkCystal = true;
            
            size = UtilVector.loadVector3i(tag.getCompoundTag("size"));
            corner = UtilVector.loadVector3i(tag.getCompoundTag("corner"));
            blackListInventory.load(tag.getCompoundTag("blackList"));
            powerManager.load(tag.getCompoundTag("power"));
        }
        super.readFromNBT(tag);
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag) {
        
        if (coordsSet && !finished) {
            
            int index = 0;
            for (Vector3i vec : lastBrokenBlocks) {
                
                tag.setTag("vec" + index, UtilVector.saveVector(new NBTTagCompound(), vec));
                index++;
            }
            tag.setInteger("vecNumber", index);
            tag.setTag("size", UtilVector.saveVector(new NBTTagCompound(), size));
            tag.setTag("corner", UtilVector.saveVector(new NBTTagCompound(), corner));
            tag.setTag("blackList", blackListInventory.save(new NBTTagCompound()));
            tag.setTag("power", powerManager.save(new NBTTagCompound()));
        }
        tag.setBoolean("shouldLoad", coordsSet && !finished);
        super.writeToNBT(tag);
    }
    
    @Override
    public IPowerManager getPowerManager(ForgeDirection direction) {
        
        return powerManager;
    }
    
    private class BreakWait implements IWaitTrigger {
        
        @Override
        public void trigger(int id) {
            
            if (coordsSet) {
                
                ForgeDirection direction = getOrientation().getOpposite();
                
                if (lastBrokenBlocks.size() == 0) {
                    
                    for (int x = 0; x <= size.getX() * (1 - Math.abs(direction.offsetX)); x++) {
                        
                        for (int y = 0; y <= size.getY() * (1 - Math.abs(direction.offsetY)); y++) {
                            
                            for (int z = 0; z <= size.getZ() * (1 - Math.abs(direction.offsetZ)); z++) {
                                
                                int finalPosX = x, finalPosY = y, finalPosZ = z;
                                lastBrokenBlocks.add(new Vector3i(finalPosX, finalPosY, finalPosZ));
                            }
                        }
                    }
                }
                
                if (!finished) {
                    
                    if (!worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {
                        
                        if (blocksPastLimit < lastBrokenBlocks.size()) {
                            
                            TileEntity tile = worldObj.getTileEntity(xCoord - direction.offsetX, yCoord - direction.offsetY, zCoord - direction.offsetZ);
                            
                            if (tile != null && tile instanceof IInventory) {
                                
                                for (Vector3i block : lastBrokenBlocks) {
                                    
                                    boolean checkX = Math.abs(direction.offsetX) == 1 ? true : false, checkY = Math.abs(direction.offsetY) == 1 ? true : false, checkZ = Math.abs(direction.offsetZ) == 1 ? true : false;
                                    
                                    if ((checkX && block.getX() < size.getX()) || (checkY && block.getY() < size.getY()) || (checkZ && block.getZ() < size.getZ())) {
                                        
                                        int x = xCoord + (corner.getX() - block.getX()), y = yCoord + (corner.getY() - block.getY()), z = zCoord + (corner.getZ() - block.getZ());
                                        
                                        if (y >= 0) {
                                            
                                            if (breakBlock(x, y, z, ((IInventory) tile))) {
                                                
                                                block.move(-direction.offsetX, -direction.offsetY, -direction.offsetZ);
                                            }
                                        }
                                    } else {
                                        
                                        blocksPastLimit++;
                                    }
                                }
                            }
                        } else {
                            
                            finished = true;
                        }
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

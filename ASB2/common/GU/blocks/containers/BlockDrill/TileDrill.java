package GU.blocks.containers.BlockDrill;

import net.minecraft.block.BlockAir;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import ASB2.utils.UtilBlock;
import ASB2.utils.UtilEntity;
import GU.blocks.containers.TileBase;
import GU.blocks.containers.BlockElectisPolyhedron.TileElectisPolyhedron;
import UC.Wait;
import UC.Wait.IWaitTrigger;
import UC.math.vector.Vector3i;

public class TileDrill extends TileBase {
    
    Wait breakBlockWait;
    boolean coordsSet = false;
    
    Vector3i size, corner, position;
    
    public TileDrill() {
        
        breakBlockWait = new Wait(new BreakWait(), 2);
        size = new Vector3i();
        corner = new Vector3i();
        position = new Vector3i();
    }
    
    @Override
    public void updateEntity() {
        
        breakBlockWait.update();
    }
    
    public boolean breakBlock(int x, int y, int z, IInventory inventory) {
        
        return false;
    }
    
    @Override
    public boolean triggerBlock(World world, EntityPlayer player, int x, int y, int z, ForgeDirection axis) {
        
        if (player.isSneaking()) {
            
            if (!world.isRemote)
                UtilEntity.sendChatToPlayer(player, "Size Cleared");
            
            this.coordsSet = false;
            size.setXYZ(0, 0, 0);
            corner.setXYZ(0, 0, 0);
            position.setXYZ(0, 0, 0);
        } else if (!coordsSet) {
            
            int xMin = 0, yMin = 0, zMin = 0;
            int xMax = 0, yMax = 0, zMax = 0;
            
            ForgeDirection oppositeOrientation = this.getOrientation(), orientation = oppositeOrientation.getOpposite();
            
            for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
                
                if (direction == orientation) {
                    
                    if (direction.offsetX < 0) {
                        
                        xMin += 1;
                    }
                    
                    if (direction.offsetX > 0) {
                        
                        xMax += 1;
                    }
                    
                    if (direction.offsetY < 0) {
                        
                        yMin += 1;
                    }
                    
                    if (direction.offsetY > 0) {
                        
                        yMax += 1;
                    }
                    
                    if (direction.offsetZ < 0) {
                        
                        zMin += 1;
                    }
                    
                    if (direction.offsetZ > 0) {
                        
                        zMax += 1;
                    }
                } else if (direction == oppositeOrientation) {
                    
                    continue;
                }
                
                for (int distance = 1; distance <= 256; distance++) {
                    
                    TileEntity tile = world.getTileEntity(x + (distance * direction.offsetX), y + (distance * direction.offsetY), z + (distance * direction.offsetZ));
                    
                    if (tile != null) {
                        
                        if (tile instanceof TileElectisPolyhedron) {
                            
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
                        }
                    }
                    
                    if (world.getBlock(x + (distance * direction.offsetX), y + (distance * direction.offsetY), z + (distance * direction.offsetZ)) instanceof BlockAir) {
                        
                        continue;
                    } else {
                        
                        break;
                    }
                }
            }
            
            if (xMax + xMin != 0 || yMax + yMin != 0 || zMax + zMin != 0) {
                
                corner.setXYZ(xMax + orientation.offsetX, yMax + orientation.offsetY, zMax + orientation.offsetZ);
                size.setXYZ(xMax + xMin, corner.getY() - (yMax + yMin) < 0 ? yMax + yMin - (corner.getY() - (yMax + yMin)) : yMax + yMin, zMax + zMin);
                
                coordsSet = true;
                
                if (!world.isRemote) {
                    
                    UtilEntity.sendChatToPlayer(player, "Size set: ");
                    UtilEntity.sendChatToPlayer(player, size);
                    UtilEntity.sendChatToPlayer(player, "Beginning Digging");
                }
            }
        } else {
            
            if (!world.isRemote) {
                
                ForgeDirection direction = this.getOrientation().getOpposite();
                
                UtilEntity.sendChatToPlayer(player, "Currently Digging: " + this.corner.subtract(position).add(xCoord + direction.offsetX, yCoord + direction.offsetY, zCoord + direction.offsetZ));
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
        
        boolean changeX, changeY, changeZ;
        
        @Override
        public void trigger(int id) {
            
            if (!worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord) && coordsSet) {
                
                UtilBlock.breakBlockNoDrop(worldObj, xCoord + (corner.getX() - position.getX()), yCoord + (corner.getY() - position.getY()), zCoord + (corner.getZ() - position.getZ()));
                position.move(1, 0, 0);
                if (position.getX() == size.getX()) {
                    if (position.getZ() == size.getZ()) {
                        position.move(0, 0, 1); // change y coord because x and
                                                // z
                                                // have been done
                    } else {
                        position.setX(0); // change x coord
                                          // because x has been
                                          // done but z has not
                        position.move(0, 1, 0); // change z coord because z has
                                                // not been done
                    }
                }
                
                // for (int x = 0; x <= 1; x++) {
                //
                // for (int y = 0; y <= 1; y++) {
                //
                // for (int z = 0; z <= 1; z++) {
                //
                // UtilBlock.breakBlockNoDrop(worldObj, xCoord + (corner.getX()
                // - position.getX()), yCoord + (corner.getY() -
                // position.getY()), zCoord + (corner.getZ() -
                // position.getZ()));
                //
                // position.move(x, y, z);
                //
                // if (position.getX() >= size.getX()) {
                //
                // position.setX(0);
                // }
                //
                // if (position.getY() >= size.getY()) {
                //
                // position.setY(0);
                // }
                //
                // if (position.getZ() >= size.getZ()) {
                //
                // position.setZ(0);
                // }
                // }
                // }
                // }
                
                // if (position.getX() < size.getX()) {
                //
                // } else if (position.getY() < size.getY()) {
                //
                // position.move(0, 1, 0);
                //
                // if (position.getX() == size.getX()) {
                //
                // position.setX(0);
                // }
                // } else if (position.getZ() < size.getZ()) {
                //
                // position.move(0, 0, 1);
                //
                // if (position.getY() == size.getY()) {
                //
                // position.setY(0);
                // }
                // } else {
                //
                // position.setXYZ(0, 0, 0);
                // }
            }
        }
        
        @Override
        public boolean shouldTick(int id) {
            
            return true;
        }
    }
}

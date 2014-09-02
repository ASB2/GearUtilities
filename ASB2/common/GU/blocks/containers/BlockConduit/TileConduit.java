package GU.blocks.containers.BlockConduit;

import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.block.BlockAir;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.IFluidHandler;
import ASB2.utils.UtilFluid;
import ASB2.utils.UtilInventory;
import GU.GearUtilities;
import GU.blocks.containers.TileBase;
import GU.blocks.containers.BlockMultiDirectionalConduit.TileMultiDirectionalConduit;
import GU.packets.ConduitTypePacket;
import UC.Wait;
import UC.Wait.IWaitTrigger;

public class TileConduit extends TileBase {
    
    Wait waitTimer;
    
    EnumConduitType conduitType;
    
    public TileConduit() {
        
        waitTimer = new Wait(new MoveWait(), 10);
        conduitType = EnumConduitType.NONE;
    }
    
    @Override
    public void updateEntity() {
        
        waitTimer.update();
    }
    
    @Override
    public void sendUpdatePacket() {
        
        if (!worldObj.isRemote)
            GearUtilities.getPipeline().sendToDimension(new ConduitTypePacket(xCoord, yCoord, zCoord, conduitType.ordinal()), worldObj.provider.dimensionId);
        super.sendUpdatePacket();
    }
    
    public void setConduitType(EnumConduitType conduitType) {
        this.conduitType = conduitType;
    }
    
    public EnumConduitType getConduitType() {
        return conduitType;
    }
    
    @Override
    public boolean triggerBlock(World world, EntityPlayer player, int x, int y, int z, ForgeDirection axis) {
        
        if (!player.isSneaking()) {
            
            switch (conduitType) {
            
                case NONE: {
                    
                    conduitType = EnumConduitType.ITEM;
                    break;
                }
                case ITEM: {
                    
                    conduitType = EnumConduitType.FLUID;
                    break;
                }
                
                case FLUID: {
                    
                    conduitType = EnumConduitType.GU_POWER;
                    break;
                }
                
                case GU_POWER: {
                    
                    conduitType = EnumConduitType.ITEM;
                    break;
                }
            }
            
            world.markBlockForUpdate(x, y, z);
        } else {
            
            this.rotateBlock(world, x, y, z, axis);
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
        
        conduitType = EnumConduitType.values()[tag.getInteger("conduitType")];
        super.readFromNBT(tag);
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag) {
        
        tag.setInteger("conduitType", conduitType.ordinal());
        super.writeToNBT(tag);
    }
    
    private class MoveWait implements IWaitTrigger {
        
        @Override
        public void trigger(int id) {
            
            if (worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {
                
                return;
            }
            
            ForgeDirection direction = getOrientation().getOpposite();
            
            TileEntity originalTile = worldObj.getTileEntity(xCoord + direction.offsetX, yCoord + direction.offsetY, zCoord + direction.offsetZ);
            
            direction = direction.getOpposite();
            
            if (originalTile != null && getConduitType().checkObject(originalTile)) {
                
                for (int distance = 1; distance <= 17; distance++) {
                    
                    int x = xCoord + (distance * direction.offsetX), y = yCoord + (distance * direction.offsetY), z = zCoord + (distance * direction.offsetZ);
                    
                    TileEntity foundTile = worldObj.getTileEntity(x, y, z);
                    
                    if (foundTile != null) {
                        
                        if (foundTile instanceof TileMultiDirectionalConduit) {
                            
                            Map<TileEntity, ForgeDirection> tileEntityList = ((TileMultiDirectionalConduit) foundTile).getAvaliableTiles(getConduitType());
                            
                            for (Entry<TileEntity, ForgeDirection> tile : tileEntityList.entrySet()) {
                                
                                this.applyMovement(originalTile, direction, tile.getKey(), tile.getValue());
                            }
                            return;
                        } else if (getConduitType().checkObject(foundTile)) {
                            
                            this.applyMovement(originalTile, direction, foundTile, direction.getOpposite());
                            return;
                        }
                    }
                    
                    if (worldObj.getBlock(x, y, z) instanceof BlockAir) {
                        
                        continue;
                    } else {
                        
                        break;
                    }
                }
            }
        }
        
        public void applyMovement(TileEntity beginTile, ForgeDirection direction1, TileEntity endTile, ForgeDirection direction2) {
            
            if (getConduitType().checkObject(endTile)) {
                
                switch (getConduitType()) {
                
                    case ITEM: {
                        
                        if (beginTile instanceof ISidedInventory) {
                            
                            if (endTile instanceof ISidedInventory) {
                                
                                UtilInventory.moveItems((ISidedInventory) beginTile, 1, 1, (ISidedInventory) endTile);
                            } else {
                                
                                UtilInventory.moveItems((ISidedInventory) beginTile, 1, 1, (IInventory) endTile);
                            }
                        } else {
                            
                            if (endTile instanceof ISidedInventory) {
                                
                                UtilInventory.moveItems((IInventory) beginTile, 1, 1, (ISidedInventory) endTile);
                            } else {
                                
                                UtilInventory.moveItems((IInventory) beginTile, 1, 1, (IInventory) endTile);
                            }
                        }
                        break;
                    }
                    
                    case FLUID: {
                        
                        UtilFluid.moveFluid((IFluidHandler) beginTile, direction1, (IFluidHandler) endTile, direction2, 1000, true);
                        break;
                    }
                    
                    case GU_POWER: {
                        
                        UtilFluid.moveFluid((IFluidHandler) beginTile, direction1, (IFluidHandler) endTile, direction2, 1000, true);
                        break;
                    }
                    default: {
                        break;
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

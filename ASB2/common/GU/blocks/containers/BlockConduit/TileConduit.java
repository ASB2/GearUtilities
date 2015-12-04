package GU.blocks.containers.BlockConduit;

import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import ASB2.utils.UtilBlock;
import ASB2.utils.UtilFluid;
import ASB2.utils.UtilInventory;
import GU.api.EnumSimulationType;
import GU.api.power.PowerNetAbstract.IPowerManager;
import GU.api.power.PowerNetAbstract.ITilePowerHandler;
import GU.api.power.PowerNetObject;
import GU.api.power.PowerNetVariables;
import GU.blocks.containers.TileBase;
import GU.blocks.containers.BlockMultiDirectionalConduit.TileMultiDirectionalConduit;
import UC.Wait;
import UC.Wait.IWaitTrigger;

public class TileConduit extends TileBase {
    
    Wait waitTimer;
    
    EnumConduitType conduitType;
    
    public TileConduit() {
        
        waitTimer = new Wait(new MoveWait(), 10) {
            
            @Override
            public void update() {
                
                if (thingToTrigger != null) {
                    
                    if (thingToTrigger.shouldTick(id)) {
                        
                        if (conduitType == EnumConduitType.FLUID) {
                            
                            timeKeeper += getRemainingTime();
                        } else {
                            
                            timeKeeper++;
                        }
                    }
                }
                
                if (timeKeeper >= timeToWait) {
                    
                    if (thingToTrigger != null) {
                        
                        thingToTrigger.trigger(id);
                        timeKeeper = 0;
                    }
                }
            }
        };
        conduitType = EnumConduitType.NONE;
    }
    
    @Override
    public void updateEntity() {
        
        waitTimer.update();
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
    public void sendUpdatePacket() {
        
        NBTTagCompound tag = new NBTTagCompound();
        
        tag.setInteger("conduitType", conduitType.ordinal());
        this.sendNBTPacket(tag, 0);
        super.sendUpdatePacket();
    }
    
    @Override
    public void readNBTPacket(NBTTagCompound tag, int id) {
        
        conduitType = EnumConduitType.values()[tag.getInteger("conduitType")];
        super.readNBTPacket(tag, id);
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
    
    public EnumConduitType getConduitType() {
        
        return conduitType;
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
                    
                    if (UtilBlock.isBlockAir(worldObj, x, y, z)) {
                        
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
                                
                                UtilInventory.moveItems((ISidedInventory) beginTile, direction1, 1, 1, (ISidedInventory) endTile, direction2);
                            } else {
                                
                                UtilInventory.moveItems((ISidedInventory) beginTile, direction1, 1, 1, (IInventory) endTile);
                            }
                        } else {
                            
                            if (endTile instanceof ISidedInventory) {
                                
                                UtilInventory.moveItems((IInventory) beginTile, 1, 1, (ISidedInventory) endTile, direction2);
                            } else {
                                
                                UtilInventory.moveItems((IInventory) beginTile, 1, 1, (IInventory) endTile);
                            }
                        }
                        break;
                    }
                    
                    case FLUID: {
                        
                        for (int index = 0; index < 17; index++) {
                            
                            // TODO Rewrite the fluid transfer method to move
                            // what the tank tells it to
                            int amount = 10;
                            IFluidHandler source = (IFluidHandler) beginTile;
                            ForgeDirection from = direction1;
                            IFluidHandler destination = (IFluidHandler) endTile;
                            ForgeDirection to = direction2;
                            FluidStack fluidToMove = null;
                            
                            if (source != null) {
                                
                                if (source.getTankInfo(from) != null) {
                                    
                                    for (FluidTankInfo info : source.getTankInfo(from)) {
                                        
                                        if (info != null) {
                                            
                                            if (info.fluid != null) {
                                                
                                                if (source.canDrain(from, info.fluid.getFluid())) {
                                                    
                                                    FluidStack temp = info.fluid.copy();
                                                    temp.amount = amount;
                                                    fluidToMove = source.drain(from, temp, false);
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            
                            if (fluidToMove != null) {
                                
                                fluidToMove = fluidToMove.copy();
                                
                                if (destination.canFill(from, fluidToMove.getFluid())) {
                                    
                                    amount = destination.fill(from, fluidToMove, false);
                                    
                                    if (fluidToMove.amount > 0) {
                                        
                                        UtilFluid.addFluidToTank(destination, from, fluidToMove, true);
                                        UtilFluid.removeFluidFromHandler(source, to, fluidToMove, true);
                                    }
                                }
                            }
                            
                            // UtilFluid.moveFluid((IFluidHandler) beginTile,
                            // direction1, (IFluidHandler) endTile, direction2,
                            // 1000, true);
                        }
                        break;
                    }
                    
                    case GU_POWER: {
                        
                        ITilePowerHandler handler1 = (ITilePowerHandler) beginTile;
                        ITilePowerHandler handler2 = (ITilePowerHandler) endTile;
                        
                        if (handler2 != null) {
                            
                            IPowerManager manager1 = handler1.getPowerManager(direction1);
                            IPowerManager manager2 = handler2.getPowerManager(direction2);
                            
                            if (manager1 != null && manager2 != null) {
                                
                                for (int index = 0; index < PowerNetVariables.ONE_GARNET_ENERGY_VALUE; index++)
                                    PowerNetObject.UtilPower.movePower(manager1, manager2, 1, EnumSimulationType.LIGITIMATE);
                            }
                        }
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

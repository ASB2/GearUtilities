package GU.blocks.containers.BlockMultiInterface;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import ASB2.utils.UtilVector;
import GU.api.EnumSideState;
import GU.api.multiblock.MultiBlockAbstract.EnumMultiBlockPartPosition;
import GU.api.multiblock.MultiBlockAbstract.IFluidMultiBlock;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlock;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlockPart;
import GU.blocks.containers.TileMultiBase;
import UC.math.vector.Vector3i;

public class TileFluidMultiInterface extends TileMultiBase implements IMultiBlockPart, IFluidHandler {
    
    int fluidPerTick = 200;
    
    IFluidMultiBlock handler1 = null, handler2 = null;
    Vector3i position;
    EnumSideState[] sideState;
    
    public TileFluidMultiInterface() {
        
        this.setMaxMultiBlocks(2);
        sideState = new EnumSideState[6];
        
        for (int index = 0; index < sideState.length; index++) {
            
            sideState[index] = EnumSideState.NONE;
        }
    }
    
    int wait = 0;
    
    @Override
    public void updateEntity() {
        
        wait++;
        
        if (wait == 10) {
            
            wait = 0;
            if (position == null) {
                
                position = UtilVector.createTileEntityVector(this);
            }
            
            if (!worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {
                
                if (handler1 != null && handler2 != null) {
                    
                    IFluidHandler handler1Handler = handler1.getTank(position);
                    
                    IFluidHandler handler2Handler = handler2.getTank(position);
                    
                    if (handler1Handler != null && handler2Handler != null) {
                        
                        FluidTankInfo[] handler1InfoArray = handler1Handler.getTankInfo(ForgeDirection.UNKNOWN);
                        FluidTankInfo[] handler2InfoArray = handler2Handler.getTankInfo(ForgeDirection.UNKNOWN);
                        
                        if (handler1InfoArray != null && handler2InfoArray != null) {
                            
                            for (int handler1Index = 0; handler1Index < handler1InfoArray.length; handler1Index++) {
                                
                                FluidTankInfo handler1Info = handler1InfoArray[handler1Index];
                                
                                if (handler1Info != null) {
                                    
                                    for (int handler2Index = 0; handler2Index < handler2InfoArray.length; handler2Index++) {
                                        
                                        FluidTankInfo handler2Info = handler2InfoArray[handler2Index];
                                        
                                        if (handler2Info != null) {
                                            
                                            if (handler1Info.capacity > 0 && handler2Info.capacity > 0) {
                                                
                                                double precentFilled1 = handler1Info.fluid != null ? handler1Info.fluid.amount / (double) handler1Info.capacity : 0;
                                                double precentFilled2 = handler2Info.fluid != null ? handler2Info.fluid.amount / (double) handler2Info.capacity : 0;
                                                
                                                if (precentFilled1 > precentFilled2) {
                                                    
                                                    FluidStack handler1Drain = handler1Handler.drain(ForgeDirection.UNKNOWN, fluidPerTick, false);
                                                    
                                                    if (handler1Drain != null) {
                                                        
                                                        int handler2FillAmount = handler2Handler.fill(ForgeDirection.UNKNOWN, handler1Drain.copy(), false);
                                                        
                                                        if (handler2FillAmount > 0) {
                                                            
                                                            handler2Handler.fill(ForgeDirection.UNKNOWN, handler1Handler.drain(ForgeDirection.UNKNOWN, fluidPerTick, true), true);
                                                        }
                                                    }
                                                } else if (precentFilled1 < precentFilled2) {
                                                    
                                                    FluidStack handler2Drain = handler2Handler.drain(ForgeDirection.UNKNOWN, fluidPerTick, false);
                                                    
                                                    if (handler2Drain != null) {
                                                        
                                                        int handler1FillAmount = handler1Handler.fill(ForgeDirection.UNKNOWN, handler2Drain.copy(), false);
                                                        
                                                        if (handler1FillAmount > 0) {
                                                            
                                                            handler1Handler.fill(ForgeDirection.UNKNOWN, handler2Handler.drain(ForgeDirection.UNKNOWN, fluidPerTick, true), true);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    
                    // for (ForgeDirection direction :
                    // ForgeDirection.VALID_DIRECTIONS) {
                    //
                    // }
                }
            }
        }
        super.updateEntity();
    }
    
    @Override
    public boolean addMultiBlock(IMultiBlock multiBlock) {
        
        if (super.addMultiBlock(multiBlock)) {
            
            if (multiBlock instanceof IFluidMultiBlock) {
                
                if (handler1 == null) {
                    
                    handler1 = (IFluidMultiBlock) multiBlock;
                } else if (handler2 == null) {
                    
                    handler2 = (IFluidMultiBlock) multiBlock;
                }
            }
            return true;
        }
        return false;
    }
    
    @Override
    public void removeMultiBlock(IMultiBlock multiBlock) {
        super.removeMultiBlock(multiBlock);
        
        if (handler1 == multiBlock) {
            
            handler1 = null;
            
            if (handler2 != null) {
                
                handler1 = handler2;
                handler2 = null;
            }
        } else if (handler2 == multiBlock) {
            
            handler2 = null;
        }
    }
    
    @Override
    public boolean isPositionValid(EnumMultiBlockPartPosition position) {
        
        return position == EnumMultiBlockPartPosition.FACE;
    }
    
    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        
        if (handler1 != null && handler1.getTank(position) != null) {
            
            return handler1.getTank(position).fill(from, resource, doFill);
        }
        return 0;
    }
    
    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        
        if (handler1 != null && handler1.getTank(position) != null) {
            
            return handler1.getTank(position).canFill(from, fluid);
        }
        return false;
    }
    
    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        
        if (handler1 != null && handler1.getTank(position) != null) {
            
            return handler1.getTank(position).drain(from, resource, doDrain);
        }
        return null;
    }
    
    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        
        if (handler1 != null && handler1.getTank(position) != null) {
            
            return handler1.getTank(position).drain(from, maxDrain, doDrain);
        }
        return null;
    }
    
    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        
        if (handler1 != null && handler1.getTank(position) != null) {
            
            return handler1.getTank(position).canDrain(from, fluid);
        }
        return false;
    }
    
    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        
        if (handler1 != null && handler1.getTank(position) != null) {
            
            return handler1.getTank(position).getTankInfo(from);
        }
        return null;
    }
    
    @Override
    public boolean triggerBlock(World world, EntityPlayer player, int x, int y, int z, ForgeDirection axis) {
        
        sideState[axis.ordinal()] = sideState[axis.ordinal()].increment();
        
        if (sideState[axis.ordinal()] == EnumSideState.BOTH) {
            sideState[axis.ordinal()] = sideState[axis.ordinal()].increment();
        }
        
        world.markBlockForUpdate(x, y, z);
        return true;
    }
    
    @Override
    public void sendUpdatePacket() {
        
        NBTTagCompound tag = new NBTTagCompound();
        
        this.writeToNBT(tag);
        this.sendNBTPacket(tag, 0);
        super.sendUpdatePacket();
    }
    
    @Override
    public void readNBTPacket(NBTTagCompound tag, int id) {
        
        this.readFromNBT(tag);
        super.readNBTPacket(tag, id);
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag) {
        
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
            
            tag.setInteger("sideState" + direction.ordinal(), sideState[direction.ordinal()].ordinal());
        }
        super.writeToNBT(tag);
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag) {
        
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
            
            sideState[direction.ordinal()] = EnumSideState.values()[tag.getInteger("sideState" + direction.ordinal())];
        }
        super.readFromNBT(tag);
    }
}

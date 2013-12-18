package GU.blocks.containers.BlockSpacialProvider;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import ASB2.utils.UtilEntity;
import ASB2.vector.Vector3;
import GU.EnumState;
import GU.multiblock.MultiBlockTank;

public class TileFluidSpacialProvider extends TileSpacialProvider implements IFluidHandler {
    
    public boolean createMultiBlock() {
        
        return createMultiBlock(false);
    }
    
    public boolean createMultiBlock(boolean hasStructure) {
        
        TileSpacialProvider tile = (TileSpacialProvider) worldObj.getBlockTileEntity(xCoord, yCoord, zCoord);
        
        if (!hasStructure) {
            
            if (this.getCurrentStructure() == null) {
                
                int found = 0;
                
                for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
                    
                    if (tile.getSideStateArray(direction.ordinal()) == EnumState.OUTPUT) {
                        
                        TileEntity foundTile = tile.getNearestProvider(direction);
                        
                        if (foundTile != null) {
                            
                            found++;
                        } else {
                            
                            return false;
                        }
                    }
                }
                
                if (found > 1) {
                    
                    MultiBlockTank tank = new MultiBlockTank(worldObj, new Vector3(xCoord, yCoord, zCoord), tile.getMultiBlockXChange(), tile.getMultiBlockYChange(), tile.getMultiBlockZChange());
                    UtilEntity.sendClientChat("First Check " + tank.isMultiBlockAreaValid());
                    boolean valid = tank.makeMultiBlockValid();
                    UtilEntity.sendClientChat("Second Check " + valid);
                    return valid;
                } else {
                    return false;
                }
            } else {
                
                return false;
            }
        } else {
            
            if (this.getCurrentStructure().getWorld() == null) {
                this.getCurrentStructure().setWorld(this.worldObj);
            }
            hasBufferedCreateMultiBlock = false;
            UtilEntity.sendClientChat("First Check " + this.getCurrentStructure().isMultiBlockAreaValid());
            boolean valid = this.getCurrentStructure().makeMultiBlockValid();
            UtilEntity.sendClientChat("Second Check " + valid);
            return valid;
        }
        // return false;
    }
    
    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        
        if (this.getCurrentStructure() != null && this.getCurrentStructure() instanceof IFluidHandler) {
            
            return ((IFluidHandler) this.getCurrentStructure()).fill(from, resource, doFill);
        }
        return 0;
    }
    
    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        
        if (this.getCurrentStructure() != null && this.getCurrentStructure() instanceof IFluidHandler) {
            
            return ((IFluidHandler) this.getCurrentStructure()).canFill(from, fluid);
        }
        return false;
    }
    
    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        
        if (this.getCurrentStructure() != null && this.getCurrentStructure() instanceof IFluidHandler) {
            
            return ((IFluidHandler) this.getCurrentStructure()).drain(from, resource, doDrain);
        }
        return null;
    }
    
    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        
        if (this.getCurrentStructure() != null && this.getCurrentStructure() instanceof IFluidHandler) {
            
            return ((IFluidHandler) this.getCurrentStructure()).drain(from, maxDrain, doDrain);
        }
        return null;
    }
    
    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        
        if (this.getCurrentStructure() != null && this.getCurrentStructure() instanceof IFluidHandler) {
            
            return ((IFluidHandler) this.getCurrentStructure()).canDrain(from, fluid);
        }
        return false;
    }
    
    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        
        if (this.getCurrentStructure() != null && this.getCurrentStructure() instanceof IFluidHandler) {
            
            return ((IFluidHandler) this.getCurrentStructure()).getTankInfo(from);
        }
        return null;
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        
        if (this.getCurrentStructure() != null) {
            
            if (new Vector3(this).intEquals(this.getCurrentStructure().getMultiBlockCore())) {
                
                tag.setCompoundTag("multiBlockSave", this.getCurrentStructure().save(new NBTTagCompound()));
                tag.setBoolean("isInMultiBlock", isInMultiBlock);
            }
        }
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        this.setStructure(MultiBlockTank.load(tag.getCompoundTag("multiBlockSave")));
        hasBufferedCreateMultiBlock = tag.getBoolean("isInMultiBlock");
    }
}

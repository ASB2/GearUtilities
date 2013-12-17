package GU.multiblock;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import ASB2.utils.UtilEntity;
import ASB2.vector.Vector3;
import GU.api.multiblock.IMultiBlockPart;
import GU.api.multiblock.MultiBlockManager;

public class MultiBlockTank extends MultiBlockManager implements IFluidHandler {
    
    FluidTank fluidTank = new FluidTank(0);
    
    public MultiBlockTank() {
    }
    
    public MultiBlockTank(World worldObj, Vector3 multiBlockCore, int relativeXPlus, int relativeYPlus, int relativeZPlus) {
        super(worldObj, multiBlockCore, relativeXPlus, relativeYPlus, relativeZPlus);
        
        fluidTank.setCapacity(((Math.abs(relativeXPlus) + 1) * (Math.abs(relativeYPlus) + 1) * (Math.abs(relativeZPlus) + 1)) * 16000);
    }
    
    @Override
    public boolean isMultiBlockAreaValid() {
        
        boolean itWorked = false;
        
        int sx = relativeXPlus < 0 ? -1 : 1;
        int sy = relativeYPlus < 0 ? -1 : 1;
        int sz = relativeZPlus < 0 ? -1 : 1;
        
        int xplusABS = Math.abs(relativeXPlus);
        int yplsuABS = Math.abs(relativeYPlus);
        int zPlusABS = Math.abs(relativeZPlus);
        
        for (int x = 0; x <= xplusABS; x++) {
            
            for (int y = 0; y <= yplsuABS; y++) {
                
                for (int z = 0; z <= zPlusABS; z++) {
                    
                    Vector3 foundVec = this.getMultiBlockCore().add(x * sx, y * sy, z * sz);
                    TileEntity tile = foundVec.getTileEntity(getWorld());
                    
                    if (tile != null) {
                        
                        if (tile instanceof IMultiBlockPart) {
                            
                            itWorked = true;
                        } else {
                            
                            return false;
                        }
                    } else {
                        
                        return false;
                    }
                }
            }
        }
        return itWorked;
    }
    
    @Override
    public boolean makeMultiBlockValid() {
        
        int sx = relativeXPlus < 0 ? -1 : 1;
        int sy = relativeYPlus < 0 ? -1 : 1;
        int sz = relativeZPlus < 0 ? -1 : 1;
        
        int xplusABS = Math.abs(relativeXPlus);
        int yplsuABS = Math.abs(relativeYPlus);
        int zPlusABS = Math.abs(relativeZPlus);
        
        if (this.isMultiBlockAreaValid()) {
            
            for (int x = 0; x <= xplusABS; x++) {
                
                for (int y = 0; y <= yplsuABS; y++) {
                    
                    for (int z = 0; z <= zPlusABS; z++) {
                        
                        Vector3 foundVec = this.getMultiBlockCore().add(x * sx, y * sy, z * sz);
                        
                        if (foundVec.getTileEntity(getWorld()) != null) {
                            
                            if (foundVec.getTileEntity(getWorld()) instanceof IMultiBlockPart) {
                                
                                if (!((IMultiBlockPart) foundVec.getTileEntity(getWorld())).setStructure(this)) {
                                    this.invalidate();
                                    return false;
                                }
                            } else {
                                this.invalidate();
                                return false;
                            }
                        } else {
                            this.invalidate();
                            return false;
                        }
                    }
                }
            }
            return this.isMultiBlockAreaValid();
        }
        return false;
    }
    
    @Override
    public void update() {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void invalidate() {
        super.invalidate();
        
    }
    
    @Override
    public NBTTagCompound save(NBTTagCompound tag) {
        super.save(tag);
        fluidTank.writeToNBT(tag);
        return tag;
    }
    
    @Override
    public NBTTagCompound load(NBTTagCompound tag) {
        super.load(tag);
        
        fluidTank.readFromNBT(tag);
        fluidTank.setCapacity(((Math.abs(relativeXPlus) + 1) * (Math.abs(relativeYPlus) + 1) * (Math.abs(relativeZPlus) + 1)) * 16000);
        return tag;
    }
    
    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        
        return fluidTank.fill(resource, doFill);
    }
    
    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        
        if (fluidTank != null) {
            
            if (fluid != null) {
                
                if (fluidTank.getFluid() != null) {
                    
                    if (this.fluidTank.getFluid().isFluidEqual(new FluidStack(fluid, 0))) {
                        
                        return true;
                    }
                } else {
                    
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        
        if (resource == null || !resource.isFluidEqual(fluidTank.getFluid())) {
            
            return null;
        }
        
        return fluidTank.drain(resource.amount, doDrain);
    }
    
    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        
        return fluidTank.drain(maxDrain, doDrain);
    }
    
    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        
        if (this.fluidTank.getFluid() != null) {
            
            if (fluidTank.getFluidAmount() > 0) {
                
                if (this.fluidTank.getFluid().isFluidEqual(new FluidStack(fluid, 1))) {
                    
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        
        return new FluidTankInfo[] { fluidTank.getInfo() };
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        
        if (this.getWorld() == null) {
            
            this.setWorld(world);
        }
        
        if (!world.isRemote) {
            
            if (this.isMultiBlockAreaValid()) {
                
                UtilEntity.sendClientChat("Fluid Amount: " + this.fluidTank.getFluidAmount() + " / " + this.fluidTank.getCapacity());
                return true;
            } else {
                
                this.invalidate();
                return false;
            }
        }
        return false;
    }
}

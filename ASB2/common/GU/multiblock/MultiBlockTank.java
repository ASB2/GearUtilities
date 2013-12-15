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
import ASB2.utils.UtilBlock;
import ASB2.vector.Vector3;
import GU.BlockRegistry;
import GU.api.multiblock.IMultiBlockPart;
import GU.api.multiblock.MultiBlockManager;

public class MultiBlockTank extends MultiBlockManager implements IFluidHandler {
    
    FluidTank fluidTank = new FluidTank(1600);
    
    public MultiBlockTank(World world) {
        super(world);
    }
    
    public MultiBlockTank(World worldObj, Vector3 multiBlockCore, int relativeXPlus, int relativeYPlus, int relativeZPlus) {
        super(worldObj, multiBlockCore, relativeXPlus, relativeYPlus, relativeZPlus);
        
        int size = relativeXPlus * relativeYPlus * relativeZPlus * 16;
        fluidTank.setCapacity(size < 0 ? -size : size);
    }
    
    @Override
    public boolean isMultiBlockAreaValid() {
        
        boolean itWorked = false;
        
        for (int x = 0; x < relativeXPlus; x++) {
            
            for (int y = 0; y < relativeYPlus; y++) {
                
                for (int z = 0; z < relativeZPlus; z++) {
                    
                    Vector3 foundVec = this.getMultiBlockCore().add(x, y, z);
                    TileEntity tile = foundVec.getTileEntity(getWorld());
                    
                    if (!UtilBlock.isBlockAir(getWorld(), foundVec.intX(), foundVec.intY(), foundVec.intZ())) {
                        
                        if (tile != null) {
                            
                            if (tile instanceof IMultiBlockPart) {
                                
                                itWorked = true;
                            } else {
                                
                                return false;
                            }
                        }
                    } else {
                        
                        itWorked = true;
                    }
                }
            }
        }
        return itWorked;
    }
    
    @Override
    public boolean makeMultiBlockValid() {
        
        // if (this.isMultiBlockAreaValid()) {
        
        for (int x = 0; x <= relativeXPlus; x++) {
            
            for (int y = 0; y <= relativeYPlus; y++) {
                
                for (int z = 0; z <= relativeZPlus; z++) {
                    
                    Vector3 foundVec = this.getMultiBlockCore().add(x, y, z);
                    
//                    if (x == 0 || y == 0 || z == 0) {
//                        
//                        UtilBlock.placeBlockInAir(worldObj, foundVec.intX(), foundVec.intY(), foundVec.intZ(), BlockRegistry.BlockMultiBlockBuilders.blockID, BlockMultiBlockBuilders.GLASS);
//                    } else {
                        
                        UtilBlock.placeBlockInAir(worldObj, foundVec.intX(), foundVec.intY(), foundVec.intZ(), BlockRegistry.BlockMultiBlockBuilders.blockID, BlockMultiBlockBuilders.CORNER);
//                    }
                    ((IMultiBlockPart) foundVec.getTileEntity(getWorld())).addToMultiBlock(this);
                }
            }
        }
        return this.isMultiBlockAreaValid();
        // }
        // return false;
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
        int size = relativeXPlus * relativeYPlus * relativeZPlus * 16;
        fluidTank.setCapacity(size < 0 ? -size : size);
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
        // TODO Auto-generated method stub
        return false;
    }
}

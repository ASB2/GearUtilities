package GU.multiblock;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import ASB2.utils.UtilEntity;
import UC.math.vector.Vector3i;

public abstract class MultiBlockFluidHandler extends MultiBlockBase implements IFluidHandler {
    
    public FluidTank fluidTank = new FluidTank(1000);
    
    public MultiBlockFluidHandler(World world, Vector3i positionRelativeTo, Vector3i size, Vector3i updater) {
        super(world, positionRelativeTo, size, updater);
        
        fluidTank.setCapacity((size.getX() - 1) * (size.getY() - 1) * (size.getZ() - 1) * 16 * FluidContainerRegistry.BUCKET_VOLUME);
    }
    
    public MultiBlockFluidHandler(World world) {
        super(world);
        
        fluidTank.setCapacity((size.getX() - 1) * (size.getY() - 1) * (size.getZ() - 1) * 16 * FluidContainerRegistry.BUCKET_VOLUME);
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
                }
                else {
                    
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
    
    public void onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        
        if (this.isValid) {
            // UtilEntity.sendChatToPlayer(player, "Fluid: " +
            // this.fluidTank.getFluid() != null ?
            // this.fluidTank.getFluid().getFluid() != null ?
            // this.fluidTank.getFluid().getFluid().getName() : "null" :
            // "null");
            
            UtilEntity.sendChatToPlayer(player, "Fluid Amount: " + this.fluidTank.getFluidAmount() + " / " + fluidTank.getCapacity());
        }
        else {
            UtilEntity.sendChatToPlayer(player, "Im not Valids");
        }
    }
    
    @Override
    public NBTTagCompound save(NBTTagCompound tag) {
        
        tag.setTag("TankData", fluidTank.writeToNBT(new NBTTagCompound()));
        return super.save(tag);
    }
    
    @Override
    public void load(NBTTagCompound tag) {
        
        fluidTank.readFromNBT(tag.getCompoundTag("TankData"));
        super.load(tag);
    }
}

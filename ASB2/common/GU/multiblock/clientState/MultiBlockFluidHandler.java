package GU.multiblock.clientState;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fluids.IFluidHandler;
import GU.api.multiblock.MultiBlockAbstract.IFluidMultiBlock;
import GU.api.multiblock.MultiBlockObject.FluidHandlerWrapper;
import GU.multiblock.MultiBlockBase;
import UC.math.vector.Vector3i;

public abstract class MultiBlockFluidHandler extends MultiBlockBase implements IFluidMultiBlock {
    
    public FluidHandlerWrapper fluidTank = new FluidHandlerWrapper(0);
    
    public MultiBlockFluidHandler(World world, Vector3i positionRelativeTo, Vector3i size, Vector3i updater) {
        super(world, positionRelativeTo, size, updater);
    }
    
    public MultiBlockFluidHandler(World world) {
        super(world);
    }
    
    @Override
    public IFluidHandler getTank(Vector3i tilePosition) {
        
        return fluidTank;
    }
    
    @Override
    public NBTTagCompound save(NBTTagCompound tag) {
        
        tag.setTag("TankData", fluidTank.save(new NBTTagCompound()));
        return super.save(tag);
    }
    
    @Override
    public void load(NBTTagCompound tag) {
        
        fluidTank.load(tag.getCompoundTag("TankData"));
        super.load(tag);
    }
}

package GU.multiblock;

import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.IFluidHandler;
import ASB2.inventory.Inventory;
import GU.GearUtilities;
import GU.api.color.VanillaColor;
import GU.api.multiblock.MultiBlockAbstract.IFluidMultiBlock;
import GU.api.multiblock.MultiBlockAbstract.IInventoryMultiBlock;
import GU.api.multiblock.MultiBlockObject.FluidHandlerWrapper;
import GU.multiblock.construction.ConstructionManager;
import GU.multiblock.construction.FlameConstructionManager;
import GU.packets.MultiBlockFlamePacket;
import UC.color.Color4i;
import UC.math.vector.Vector3i;

public class MultiBlockFlame extends MultiBlockBase implements IFluidMultiBlock, IInventoryMultiBlock {
    
    FluidHandlerWrapper fuelTank = new FluidHandlerWrapper(0), outputTank = new FluidHandlerWrapper(0);
    Inventory input = new Inventory("MultiBlockFlame: Input"), output = new Inventory("MultiBlockFlame: Output");
    
    public MultiBlockFlame(World world, Vector3i positionRelativeTo, Vector3i size, Vector3i updater) {
        super(world, positionRelativeTo, size, updater);
    }
    
    public MultiBlockFlame(World world) {
        super(world);
    }
    
    @Override
    public Color4i getDefaultBlockColor() {
        
        return VanillaColor.MAGENTA.getRGBValue().darker(150);
    }
    
    @Override
    public ConstructionManager getConstructionManager() {
        
        return new FlameConstructionManager(world, this, positionRelativeTo, size);
    }
    
    public boolean startCreation() {
        
        return size.getX() >= 2 && size.getY() >= 2 && size.getZ() >= 2 && (size.getX() == size.getZ() && size.getY() == size.getZ() + 4) && super.startCreation();
    }
    
    @Override
    public IInventory getInventory(Vector3i tilePosition) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public IFluidHandler getTank(Vector3i tilePosition) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public void onSetSize() {
        
        if (fuelTank != null && fuelTank.getFluidTank() != null && fuelTank.getFluidTank().getCapacity() == 0) {
            
            fuelTank.getFluidTank().setCapacity((size.getX() - 1) * (((int) (size.getY() / 3)) - 1) * (size.getZ() - 1) * 8 * FluidContainerRegistry.BUCKET_VOLUME);
        }
        
        if (outputTank != null && outputTank.getFluidTank() != null && outputTank.getFluidTank().getCapacity() == 0) {
            
            outputTank.getFluidTank().setCapacity((size.getX() - 1) * (((int) (size.getY() / 3)) - 1) * (size.getZ() - 1) * 8 * FluidContainerRegistry.BUCKET_VOLUME);
        }
        
        if (input != null && input.getSizeInventory() == 0) {
            
            input.setSizeInventory(((size.getX() - 1) * (((int) (size.getY() / 3)) - 1) * (size.getZ() - 1)) * 4);
        }
        if (output != null && output.getSizeInventory() == 0) {
            
            output.setSizeInventory(((size.getX() - 1) * (((int) (size.getY() / 3)) - 1) * (size.getZ() - 1)) * 4);
        }
    }
    
    @Override
    public void logicUpdate() {
        // TODO Auto-generated method stub
        super.logicUpdate();
    }
    
    @Override
    public void sendPacket() {
        
        GearUtilities.getPipeline().sendToDimension(new MultiBlockFlamePacket(this.positionRelativeTo.subtract(1), size), world.provider.dimensionId);
    }
    
    @Override
    public NBTTagCompound save(NBTTagCompound tag) {
        
        if (fuelTank != null) tag.setTag("fuelTank", fuelTank.save(new NBTTagCompound()));
        if (outputTank != null) tag.setTag("outputTank", outputTank.save(new NBTTagCompound()));
        if (input != null) tag.setTag("input", input.save(new NBTTagCompound()));
        if (output != null) tag.setTag("output", output.save(new NBTTagCompound()));
        return super.save(tag);
    }
    
    @Override
    public void load(NBTTagCompound tag) {
        
        if (fuelTank != null) fuelTank.load(tag.getCompoundTag("fuelTank"));
        if (outputTank != null) outputTank.load(tag.getCompoundTag("outputTank"));
        if (input != null) input.load(tag.getCompoundTag("input"));
        if (output != null) output.load(tag.getCompoundTag("output"));
        
        super.load(tag);
    }
}

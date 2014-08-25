package GU.multiblock;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import ASB2.utils.UtilEntity;
import GU.GearUtilities;
import GU.api.multiblock.MultiBlockAbstract.IGuiMultiBlock;
import GU.api.multiblock.MultiBlockAbstract.IRedstoneMultiBlock;
import GU.multiblock.construction.ConstructionManager;
import GU.multiblock.construction.TankConstructionManager;
import GU.packets.MutliBlockTankPacket;
import UC.color.Color4i;
import UC.math.vector.Vector3i;

public class MultiBlockTank extends MultiBlockFluidHandler implements IGuiMultiBlock, IRedstoneMultiBlock {
    
    public MultiBlockTank(World world, Vector3i positionRelativeTo, Vector3i size, Vector3i updater) {
        super(world, positionRelativeTo, size, updater);
    }
    
    public MultiBlockTank(World world) {
        super(world);
    }
    
    @Override
    public Color4i getDefaultBlockColor() {
        
        return Color4i.BLUE;
    }
    
    @Override
    public ConstructionManager getConstructionManager() {
        
        return new TankConstructionManager(world, this, positionRelativeTo, size);
    }
    
    @Override
    public void logicUpdate() {
        
    }
    
    @Override
    public void onSetSize() {
        
        if (fluidTank != null && fluidTank.getFluidTank() != null && fluidTank.getFluidTank().getCapacity() == 0) fluidTank.getFluidTank().setCapacity((size.getX() - 1) * (size.getY() - 1) * (size.getZ() - 1) * 16 * FluidContainerRegistry.BUCKET_VOLUME);
    }
    
    public boolean startCreation() {
        
        if (size.getX() >= 2 && size.getY() >= 2 && size.getZ() >= 2) {
            
            if (super.startCreation()) {
                
                fluidTank.getFluidTank().setCapacity((size.getX() - 1) * (size.getY() - 1) * (size.getZ() - 1) * 16 * FluidContainerRegistry.BUCKET_VOLUME);
                return true;
            }
        }
        return false;
    }
    
    FluidStack lastStack;
    int lastCapacity;
    
    @Override
    public void sendPacket() {
        
        if (!world.isRemote) {
            
            boolean firstWorked = false;
            
            if (fluidTank.getFluidTank().getFluid() == null && lastStack == null || fluidTank.getFluidTank().getFluid() != null && lastStack != null && fluidTank.getFluidTank().getFluid().isFluidEqual(lastStack) && lastStack.amount == fluidTank.getFluidTank().getFluidAmount()) {
                
                firstWorked = true;
            }
            
            if (fluidTank.getFluidTank().getCapacity() == lastCapacity && firstWorked) {
                
                return;
            }
            
            GearUtilities.getPipeline().sendToDimension(new MutliBlockTankPacket(this.positionRelativeTo.subtract(1), size, fluidTank.getFluidTank()), world.provider.dimensionId);
            lastStack = fluidTank.getFluidTank().getFluid() != null ? fluidTank.getFluidTank().getFluid().copy() : null;
            lastCapacity = fluidTank.getFluidTank().getCapacity();
        }
    }
    
    @Override
    public boolean openGui(Vector3i position, EntityPlayer player, int side) {
        
        UtilEntity.sendChatToPlayer(player, "Tank: Gui Would Have Opened If It Exzited");
        return true;
    }
    
    @Override
    public int getLevel(Vector3i tilePosition) {
        
        // return (int) (Math.floor(15 *
        // (fluidTank.getFluidTank().getFluidAmount() / (float)
        // fluidTank.getFluidTank().getCapacity())));
        
        float fluidPrecent = (fluidTank.getFluidTank().getFluidAmount() / (float) fluidTank.getFluidTank().getCapacity());
        float blockFluidIsIn = (size.getY() - 1) * fluidPrecent;
        int blockPosition = (size.getY() - (positionRelativeTo.getY() - tilePosition.getY()));
        return Math.ceil(blockFluidIsIn) >= blockPosition ? 15 : 0;
    }
}

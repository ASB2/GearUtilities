package GU.multiblock;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import ASB2.utils.UtilEntity;
import GU.GUGuiHandler;
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
    public void load(NBTTagCompound tag) {
        super.load(tag);
    }
    
    @Override
    public void onSetSize() {
        
        if (fluidTank != null && fluidTank.getFluidTank() != null && fluidTank.getFluidTank().getCapacity() == 0)
            fluidTank.getFluidTank().setCapacity((size.getX() - 1) * (size.getY() - 1) * (size.getZ() - 1) * 16 * FluidContainerRegistry.BUCKET_VOLUME);
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
            
            if (lastStack == null && lastCapacity == 0) {
                
                actuallySendPacket();
            } else {
                
                FluidStack current = fluidTank.getFluidTank().getFluid();
                
                if ((current == null && lastStack == null || current != null && current.isFluidEqual(current) || current == null && lastStack != null) || fluidTank.getFluidTank().getCapacity() != lastCapacity) {
                    
                    actuallySendPacket();
                }
            }
            
        }
    }
    
    public void actuallySendPacket() {
        
        GearUtilities.getPipeline().sendToDimension(new MutliBlockTankPacket(this.positionRelativeTo.subtract(1), size, fluidTank.getFluidTank()), world.provider.dimensionId);
        lastStack = fluidTank.getFluidTank().getFluid() != null ? fluidTank.getFluidTank().getFluid().copy() : null;
        lastCapacity = fluidTank.getFluidTank().getCapacity();
    }
    
    @Override
    public boolean openGui(Vector3i position, EntityPlayer player, int side) {
        
        if (!world.isRemote) {
            
            if (!player.isSneaking()) {
                
                player.openGui(GearUtilities.instance, GUGuiHandler.MULTI_BLOCK_TANK, world, position.getX(), position.getY(), position.getZ());
                return true;
            } else {
                
                UtilEntity.sendChatToPlayer(player, "Tank: Stop Shifitng");
                return false;
            }
        }
        return false;
    }
    
    @Override
    public void onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        
        if (!world.isRemote) {
            FluidStack current = fluidTank.getFluidTank().getFluid();
            UtilEntity.sendChatToPlayer(player, "Tank: " + (current == null ? "0" : current.amount) + " / " + fluidTank.getFluidTank().getCapacity());
        }
    }
    
    @Override
    public int getRedstoneLevel(Vector3i tilePosition) {
        
        // return (int) (Math.floor(15 *
        // (fluidTank.getFluidTank().getFluidAmount() / (float)
        // fluidTank.getFluidTank().getCapacity())));
        
        float fluidPrecent = (fluidTank.getFluidTank().getFluidAmount() / (float) fluidTank.getFluidTank().getCapacity());
        float blockFluidIsIn = (size.getY() - 1) * fluidPrecent;
        int blockPosition = (size.getY() - (positionRelativeTo.getY() - tilePosition.getY()));
        return Math.ceil(blockFluidIsIn) >= blockPosition ? 15 : 0;
    }
}

package GU.blocks.containers;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.FluidTank;
import ASB2.vector.Vector3;
import ASB2.wait.IWaitTrigger;
import ASB2.wait.Wait;
import GU.EnumState;
import GU.api.IWrenchable;
import GU.api.color.IVanillaColorable;
import GU.api.color.VanillaColor;
import GU.api.power.PowerProvider;
import cpw.mods.fml.common.network.PacketDispatcher;

public abstract class TileBase extends TileEntity implements IVanillaColorable, IWaitTrigger, IWrenchable {
    
    int wait;
    protected PowerProvider powerProvider;
    protected VanillaColor color;
    protected Inventory tileInventory;
    public FluidTank fluidTank;
    public Wait waitTimer;
    protected EnumState[] sideState;
    public boolean useSidesRendering = true;
    public double[] renderDoubles;
    
    public TileBase() {
        
        if (color == null)
            color = VanillaColor.NONE;
        
        fluidTank = new FluidTank(0);
    }
    
    public void onButtonEvent(int buttonID) {
        
    }
    
    public ForgeDirection getOrientation() {
        
        return ForgeDirection.getOrientation(worldObj.getBlockMetadata(xCoord, yCoord, zCoord));
    }
    
    public EnumState getSideStateArray(int side) {
        
        if (sideState != null) {
            
            if (sideState[side] != null) {
                
                return sideState[side];
            }
        }
        return EnumState.NONE;
        
    }
    
    @Override
    public VanillaColor getColorEnum() {
        
        return color;
    }
    
    @Override
    public void setColor(VanillaColor color) {
        
        this.color = color;
    }
    
    @Override
    public void trigger(int id) {
        
    }
    
    @Override
    public boolean shouldTick(int id) {
        
        return true;
    }
    
    @Override
    public void triggerBlock(World world, boolean isSneaking, ItemStack itemStack, int x, int y, int z, int side) {
        
        Block block = new Vector3(this).getBlock(world);
        
        if (block instanceof ContainerBase && ((ContainerBase) block).specialMetadata) {
            return;
        }
        
        switch (getOrientation()) {
        
            case DOWN:
                worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, ForgeDirection.UP.ordinal(), 3);
                return;
            case UP:
                worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, ForgeDirection.SOUTH.ordinal(), 3);
                return;
            case SOUTH:
                worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, ForgeDirection.WEST.ordinal(), 3);
                return;
            case WEST:
                worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, ForgeDirection.NORTH.ordinal(), 3);
                return;
            case NORTH:
                worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, ForgeDirection.EAST.ordinal(), 3);
                return;
            case EAST:
                worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, ForgeDirection.DOWN.ordinal(), 3);
                return;
                
            default:
                worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 3);
                return;
        }
    }
    
    @Override
    public final Packet132TileEntityData getDescriptionPacket() {
        
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        
        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, nbt);
    }
    
    @Override
    public final void onDataPacket(INetworkManager net, Packet132TileEntityData packet) {
        
        NBTTagCompound nbt = packet.data;
        
        if (nbt != null) {
            
            this.readFromNBT(nbt);
        }
    }
    
    public final void updateClients() {
        
        if (!worldObj.isRemote) {
            
            Packet132TileEntityData packet = this.getDescriptionPacket();
            PacketDispatcher.sendPacketToAllAround(xCoord, yCoord, zCoord, 50, this.worldObj.provider.dimensionId, packet);
        }
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        
        if (fluidTank != null)
            fluidTank.readFromNBT(tag.getCompoundTag("fluidTank"));
        
        if (color == VanillaColor.NONE || color == null)
            color = VanillaColor.values()[tag.getInteger("color")];
        
        if (this.powerProvider != null)
            this.powerProvider.readFromNBT(tag);
        
        if (tileInventory != null)
            tileInventory.load(tag);
        
        if (sideState != null) {
            
            sideState = new EnumState[tag.getInteger("stateLength")];
            
            for (int i = 0; i < sideState.length; i++) {
                
                sideState[i] = EnumState.values()[tag.getInteger("state" + i)];
            }
        }
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        
        if (fluidTank != null)
            tag.setCompoundTag("fluidTank", fluidTank.writeToNBT(new NBTTagCompound()));
        
        if (this.getColorEnum() != VanillaColor.NONE)
            tag.setInteger("color", this.getColorEnum().ordinal());
        
        if (this.powerProvider != null)
            this.powerProvider.writeToNBT(tag);
        
        if (tileInventory != null)
            tileInventory.save(tag);
        
        if (sideState != null) {
            
            tag.setInteger("stateLength", sideState.length);
            
            for (int i = 0; i < sideState.length; i++) {
                
                if (sideState[i] != null) {
                    
                    tag.setInteger("state" + i, sideState[i].ordinal());
                }
            }
        }
    }
}

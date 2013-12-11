package GU.blocks.containers;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.FluidTank;
import ASB2.wait.IWaitTrigger;
import ASB2.wait.Wait;
import GU.api.IWrenchable;
import GU.api.color.IVanillaColorable;
import GU.api.color.VanillaColor;
import GU.api.power.PowerProvider;
import GU.packets.PowerPacket;
import cpw.mods.fml.common.network.PacketDispatcher;

public abstract class TileBase extends TileEntity implements IVanillaColorable, IWaitTrigger, IWrenchable {

    int wait;
    protected PowerProvider powerProvider;
    protected VanillaColor color;
    protected Inventory tileInventory;
    public FluidTank fluidTank;
    public Wait waitTimer;

    public TileBase() {

        if(color == null)
            color = VanillaColor.NONE;

        fluidTank = new FluidTank(0);
    }

    public void onButtonEvent(int buttonID) {

    }

    public void sendReqularPowerPackets(int delay) {

        wait++;

        if(wait >= delay) {

            PacketDispatcher.sendPacketToAllAround(xCoord, yCoord, zCoord, 20, worldObj.provider.dimensionId, (new PowerPacket(xCoord, yCoord, zCoord, this.powerProvider.getPowerStored(), this.powerProvider.getPowerMax()).makePacket()));
            wait = 0;
        }
    }

    public ForgeDirection getOrientation() {

        return ForgeDirection.getOrientation(worldObj.getBlockMetadata(xCoord, yCoord, zCoord));
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

        switch(getOrientation()) {

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

        if(nbt != null) {

            this.readFromNBT(nbt);
        }
    }

    public final void updateClients() {

        if(!worldObj.isRemote) {

            Packet132TileEntityData packet = this.getDescriptionPacket();
            PacketDispatcher.sendPacketToAllAround(xCoord, yCoord, zCoord, 50, this.worldObj.provider.dimensionId, packet);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);

        if(fluidTank != null)
            fluidTank.readFromNBT(tag);

        if(color == VanillaColor.NONE || color == null)
            color = VanillaColor.translateNumberToColor(tag.getInteger("color"));

        if(this.powerProvider != null)
            this.powerProvider.readFromNBT(tag);

        if(tileInventory != null)
            tileInventory.load(tag);
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);

        if(fluidTank != null)
            fluidTank.writeToNBT(tag);

        if(this.getColorEnum() != VanillaColor.NONE)
            tag.setInteger("color", VanillaColor.translateColorToNumber(this.getColorEnum()));

        if(this.powerProvider != null)
            this.powerProvider.writeToNBT(tag);

        if(tileInventory != null)
            tileInventory.save(tag);
    }
}

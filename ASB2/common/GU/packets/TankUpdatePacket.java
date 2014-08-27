package GU.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import GU.GearUtilities;
import GU.blocks.containers.TileBase;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class TankUpdatePacket implements IMessageHandler<TankUpdatePacket, TankUpdatePacket>, IMessage {
    
    int x, y, z;
    FluidTank tank;
    int id;
    
    public TankUpdatePacket() {
        // TODO Auto-generated constructor stub
    }
    
    public TankUpdatePacket(int x, int y, int z, FluidTank tank, int id) {
        
        this.x = x;
        this.y = y;
        this.z = z;
        this.tank = tank;
        this.id = id;
    }
    
    @Override
    public void fromBytes(ByteBuf buf) {
        
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
        
        int fluidID = buf.readInt();
        
        if (fluidID != -1) {
            
            int fluidAmount = buf.readInt();
            int capasity = buf.readInt();
            
            NBTTagCompound tag = ByteBufUtils.readTag(buf);
            tank = new FluidTank(new FluidStack(fluidID, fluidAmount, tag), capasity);
        }
        
        id = buf.readInt();
    }
    
    @Override
    public void toBytes(ByteBuf buf) {
        
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        
        if (tank.getFluid() != null) {
            
            buf.writeInt(tank.getFluid().getFluid().getID());
            buf.writeInt(tank.getFluidAmount());
            buf.writeInt(tank.getCapacity());
            ByteBufUtils.writeTag(buf, tank.getFluid().tag);
        } else {
            
            buf.writeInt(-1);
        }
        
        buf.writeInt(id);
    }
    
    @Override
    public TankUpdatePacket onMessage(TankUpdatePacket message, MessageContext ctx) {
        
        TileEntity tile = GearUtilities.proxy.getClientWorld().getTileEntity(message.x, message.y, message.z);
        
        if (tile != null && tile instanceof TileBase) {
            
            ((TileBase) tile).setTank(message.tank, message.id);
        }
        return null;
    }
}

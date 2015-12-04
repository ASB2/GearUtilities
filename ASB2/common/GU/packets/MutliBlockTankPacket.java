package GU.packets;

import io.netty.buffer.ByteBuf;

import javax.xml.ws.handler.MessageContext;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import ASB2.utils.UtilVector;
import GU.GearUtilities;
import GU.blocks.containers.BlockMultiPart.TileMultiPartRender;
import GU.multiblock.clientState.MultiBlockClientState;
import GU.multiblock.clientState.MultiBlockTankClientState;
import UC.math.vector.Vector3i;

public class MutliBlockTankPacket implements IMessageHandler<MutliBlockTankPacket, MutliBlockTankPacket>, IMessage {
    
    Vector3i renderHandler;
    Vector3i size;
    
    FluidTank tank;
    
    public MutliBlockTankPacket() {
        // TODO Auto-generated constructor stub
    }
    
    public MutliBlockTankPacket(Vector3i vector, Vector3i size, FluidTank tank) {
        
        renderHandler = vector;
        this.size = size;
        this.tank = tank;
    }
    
    @Override
    public void toBytes(ByteBuf buf) {
        
        buf.writeInt(renderHandler.getX());
        buf.writeInt(renderHandler.getY());
        buf.writeInt(renderHandler.getZ());
        
        buf.writeInt(size.getX());
        buf.writeInt(size.getY());
        buf.writeInt(size.getZ());
        
        if (tank.getFluid() != null) {
            
            buf.writeInt(tank.getFluid().getFluid().getID());
            buf.writeInt(tank.getFluidAmount());
            buf.writeInt(tank.getCapacity());
            ByteBufUtils.writeTag(buf, tank.getFluid().tag);
        } else {
            
            buf.writeInt(-1);
        }
    }
    
    @Override
    public void fromBytes(ByteBuf buf) {
        
        renderHandler = new Vector3i(buf.readInt(), buf.readInt(), buf.readInt());
        
        size = new Vector3i(buf.readInt(), buf.readInt(), buf.readInt());
        
        int fluidID = buf.readInt();
        
        if (fluidID != -1) {
            
            int fluidAmount = buf.readInt();
            int capasity = buf.readInt();
            
            NBTTagCompound tag = ByteBufUtils.readTag(buf);
            tank = new FluidTank(new FluidStack(fluidID, fluidAmount, tag), capasity);
        }
    }
    
    @Override
    public MutliBlockTankPacket onMessage(MutliBlockTankPacket message, MessageContext ctx) {
        
        TileEntity tile = UtilVector.getTileAtPostion(GearUtilities.proxy.getClientWorld(), message.renderHandler);
        
        if (tile != null && tile instanceof TileMultiPartRender) {
            
            MultiBlockClientState state = ((TileMultiPartRender) tile).getClientState();
            
            if (state != null && state instanceof MultiBlockTankClientState) {
                
                if (((MultiBlockTankClientState) state).getRenderHandler() == renderHandler && ((MultiBlockTankClientState) state).getMultiBlockSize() == size) {
                    
                    ((MultiBlockTankClientState) state).setTank(tank);
                    return null;
                }
            }
            ((TileMultiPartRender) tile).setClientState(new MultiBlockTankClientState(message.renderHandler, message.size, message.tank));
            
        }
        return null;
    }
}

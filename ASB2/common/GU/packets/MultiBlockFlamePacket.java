package GU.packets;

import io.netty.buffer.ByteBuf;

import javax.xml.ws.handler.MessageContext;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import ASB2.utils.UtilVector;
import GU.GearUtilities;
import GU.blocks.containers.BlockMultiPart.TileMultiPartRender;
import GU.multiblock.clientState.MultiBlockFlameClientState;
import UC.math.vector.Vector3i;

public class MultiBlockFlamePacket implements IMessageHandler<MultiBlockFlamePacket, MultiBlockFlamePacket>, IMessage {
    
    Vector3i renderHandler;
    Vector3i size;
    
    public MultiBlockFlamePacket() {
        // TODO Auto-generated constructor stub
    }
    
    public MultiBlockFlamePacket(Vector3i vector, Vector3i size) {
        
        renderHandler = vector;
        this.size = size;
    }
    
    @Override
    public void toBytes(ByteBuf buf) {
        
        buf.writeInt(renderHandler.getX());
        buf.writeInt(renderHandler.getY());
        buf.writeInt(renderHandler.getZ());
        
        buf.writeInt(size.getX());
        buf.writeInt(size.getY());
        buf.writeInt(size.getZ());
    }
    
    @Override
    public void fromBytes(ByteBuf buf) {
        
        renderHandler = new Vector3i(buf.readInt(), buf.readInt(), buf.readInt());
        
        size = new Vector3i(buf.readInt(), buf.readInt(), buf.readInt());
    }
    
    @Override
    public MultiBlockFlamePacket onMessage(MultiBlockFlamePacket message, MessageContext ctx) {
        
        TileEntity tile = UtilVector.getTileAtPostion(GearUtilities.proxy.getClientWorld(), message.renderHandler);
        
        if (tile != null && tile instanceof TileMultiPartRender) {
            
            // MultiBlockClientState state = ((TileMultiPartRender)
            // tile).getClientState();
            
            // if (state != null && state instanceof MultiBlockFlamePacket) {
            //
            // if (((MultiBlockTankClientState) state).getRenderHandler() ==
            // renderHandler && ((MultiBlockTankClientState)
            // state).getMultiBlockSize() == size) {
            //
            // ((MultiBlockTankClientState) state).setTank(tank);
            // return null;
            // }
            // }
            ((TileMultiPartRender) tile).setClientState(new MultiBlockFlameClientState(message.renderHandler, message.size));
            
        }
        return null;
    }
}

package GU.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import GU.GearUtilities;
import GU.blocks.containers.BlockConduit.EnumConduitType;
import GU.blocks.containers.BlockConduit.TileConduit;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class ConduitTypePacket implements IMessageHandler<ConduitTypePacket, ConduitTypePacket>, IMessage {
    
    int x, y, z;
    int crystalType;
    
    public ConduitTypePacket() {
        // TODO Auto-generated constructor stub
    }
    
    public ConduitTypePacket(int x, int y, int z, int crystalType) {
        
        this.x = x;
        this.y = y;
        this.z = z;
        this.crystalType = crystalType;
    }
    
    @Override
    public void fromBytes(ByteBuf buf) {
        
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
        crystalType = buf.readInt();
    }
    
    @Override
    public void toBytes(ByteBuf buf) {
        
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeInt(crystalType);
    }
    
    @Override
    public ConduitTypePacket onMessage(ConduitTypePacket message, MessageContext ctx) {
        
        TileEntity tile = GearUtilities.proxy.getClientWorld().getTileEntity(message.x, message.y, message.z);
        
        if (tile != null && tile instanceof TileConduit) {
            
            ((TileConduit) tile).setConduitType(EnumConduitType.values()[message.crystalType]);
        }
        return null;
    }
}

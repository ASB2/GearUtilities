package GU.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import GU.GearUtilities;
import GU.blocks.containers.TileBase;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class NBTPacket implements IMessageHandler<NBTPacket, NBTPacket>, IMessage {
    
    int x, y, z;
    NBTTagCompound tag;
    int id;
    
    public NBTPacket() {
        // TODO Auto-generated constructor stub
    }
    
    public NBTPacket(int x, int y, int z, NBTTagCompound tag, int id) {
        
        this.x = x;
        this.y = y;
        this.z = z;
        this.tag = tag;
        this.id = id;
    }
    
    @Override
    public void fromBytes(ByteBuf buf) {
        
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
        tag = ByteBufUtils.readTag(buf);
        id = buf.readInt();
    }
    
    @Override
    public void toBytes(ByteBuf buf) {
        
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        ByteBufUtils.writeTag(buf, tag);
        buf.writeInt(id);
    }
    
    @Override
    public NBTPacket onMessage(NBTPacket message, MessageContext ctx) {
        
        TileEntity tile = GearUtilities.proxy.getClientWorld().getTileEntity(message.x, message.y, message.z);
        
        if (tile != null && tile instanceof TileBase) {
            
            ((TileBase) tile).readNBTPacket(message.tag, message.id);
        }
        return null;
    }
}

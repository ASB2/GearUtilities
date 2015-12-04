package GU.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import GU.GearUtilities;
import GU.blocks.containers.TileBase;

public class NBTPacket implements IMessageHandler<NBTPacket, NBTPacket>, IMessage {
    
    BlockPos pos;
    NBTTagCompound tag;
    int id;
    
    public NBTPacket() {
        // TODO Auto-generated constructor stub
    }
    
    public NBTPacket(int x, int y, int z, NBTTagCompound tag, int id) {
        this(new BlockPos(x, y, z), tag, id);
    }
    
    public NBTPacket(BlockPos pos, NBTTagCompound tag, int id) {
        
        this.pos = pos;
        this.tag = tag;
        this.id = id;
    }
    
    @Override
    public void fromBytes(ByteBuf buf) {
        
        pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
        tag = ByteBufUtils.readTag(buf);
        id = buf.readInt();
    }
    
    @Override
    public void toBytes(ByteBuf buf) {
        
        buf.writeInt(pos.getX());
        buf.writeInt(pos.getY());
        buf.writeInt(pos.getZ());
        ByteBufUtils.writeTag(buf, tag);
        buf.writeInt(id);
    }
    
    @Override
    public NBTPacket onMessage(NBTPacket message, MessageContext ctx) {
        
        TileEntity tile = GearUtilities.proxy.getClientWorld().getTileEntity(message.pos);
        
        if (tile != null && tile instanceof TileBase) {
            
            ((TileBase) tile).readNBTPacket(message.tag, message.id);
        }
        return null;
    }
}

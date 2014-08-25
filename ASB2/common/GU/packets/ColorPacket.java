package GU.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import GU.api.color.AbstractColorable.IColorableTile;
import UC.color.Color4i;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class ColorPacket implements IMessageHandler<ColorPacket, ColorPacket>, IMessage {
    
    Color4i color;
    int x, y, z;
    ForgeDirection side;
    
    public ColorPacket() {
        
    }
    
    public ColorPacket(Color4i color, TileEntity tile, ForgeDirection direction) {
        this(color, tile.xCoord, tile.yCoord, tile.zCoord, direction);
    }
    
    public ColorPacket(Color4i color, int x, int y, int z, ForgeDirection direction) {
        
        this.color = color;
        this.x = x;
        this.y = y;
        this.z = z;
        this.side = direction;
    }
    
    @Override
    public void fromBytes(ByteBuf buf) {
        
        color = new Color4i(buf.readInt(), buf.readInt(), buf.readInt(), buf.readInt());
        
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
        
        side = ForgeDirection.getOrientation(buf.readInt());
        
    }
    
    @Override
    public void toBytes(ByteBuf buf) {
        
        buf.writeInt(color.getRed());
        buf.writeInt(color.getGreen());
        buf.writeInt(color.getBlue());
        buf.writeInt(color.getAlpha());
        
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        
        buf.writeInt(side.ordinal());
    }
    
    @Override
    public ColorPacket onMessage(ColorPacket message, MessageContext ctx) {
        
        TileEntity tile = Minecraft.getMinecraft().theWorld.getTileEntity(message.x, message.y, message.z);
        
        if (tile != null && tile instanceof IColorableTile) {
            
            ((IColorableTile) tile).setColor(message.color, message.side);
        }
        return null;
    }
    
}

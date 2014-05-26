package GU.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import GU.packets.abstractPacket.IAbstractPacket;
import UC.color.Color4i;
import GU.api.color.AbstractColorable.*;

public class ColorPacket implements IAbstractPacket {
    
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
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
        
        buffer.writeInt(color.getRed());
        buffer.writeInt(color.getGreen());
        buffer.writeInt(color.getBlue());
        buffer.writeInt(color.getAlpha());
        
        buffer.writeInt(x);
        buffer.writeInt(y);
        buffer.writeInt(z);
        
        buffer.writeInt(side.ordinal());
    }
    
    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
        
        color = new Color4i(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt());
        
        x = buffer.readInt();
        y = buffer.readInt();
        z = buffer.readInt();
        
        side = ForgeDirection.getOrientation(buffer.readInt());
    }
    
    @Override
    public void handleClientSide(EntityPlayer player) {
        
        if (player.worldObj.isRemote) {
            
            TileEntity tile = player.worldObj.getTileEntity(x, y, z);
            
            if (tile != null && tile instanceof IColorableTile) {
                
                ((IColorableTile) tile).setColor(color, side);
            }
        }
    }
    
    @Override
    public void handleServerSide(EntityPlayer player) {
        // TODO Auto-generated method stub
        
    }
    
}

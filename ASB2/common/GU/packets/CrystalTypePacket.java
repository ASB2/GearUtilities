package GU.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import GU.blocks.containers.BlockElectisCrystal.EnumElectisCrystalType;
import GU.blocks.containers.BlockElectisCrystal.TileElectisCrystal;
import GU.packets.abstractPacket.AbstractPacket;

public class CrystalTypePacket implements AbstractPacket {
    
    int x, y, z;
    int crystalType;
    
    public CrystalTypePacket() {
        // TODO Auto-generated constructor stub
    }
    
    public CrystalTypePacket(int x, int y, int z, int crystalType) {
        
        this.x = x;
        this.y = y;
        this.z = z;
        this.crystalType = crystalType;
    }
    
    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
        
        buffer.writeInt(x);
        buffer.writeInt(y);
        buffer.writeInt(z);
        buffer.writeInt(crystalType);
    }
    
    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
        
        x = buffer.readInt();
        y = buffer.readInt();
        z = buffer.readInt();
        crystalType = buffer.readInt();
    }
    
    @Override
    public void handleClientSide(EntityPlayer player) {
        
        TileEntity tile = player.worldObj.getTileEntity(x, y, z);
        
        if (tile != null && tile instanceof TileElectisCrystal) {
            
            ((TileElectisCrystal) tile).setCrystalType(EnumElectisCrystalType.values()[crystalType]);
        }
    }
    
    @Override
    public void handleServerSide(EntityPlayer player) {
        // TODO Auto-generated method stub
        
    }
    
}

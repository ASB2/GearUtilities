package GU.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import GU.packets.abstractPacket.IAbstractPacket;
import GU.api.crystals.*;

public class CrystalNetPacket implements IAbstractPacket {
    
    int x,y,z;
    CrystalNetwork network;
    
    public CrystalNetPacket() {
        // TODO Auto-generated constructor stub
    }
    
    public CrystalNetPacket(int x, int y, int z, CrystalNetPacket network) {
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void handleClientSide(EntityPlayer player) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void handleServerSide(EntityPlayer player) {
        // TODO Auto-generated method stub
        
    }
}

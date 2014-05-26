package GU.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import GU.api.power.PowerNetObject.DefaultPowerManager;
import GU.api.power.PowerNetAbstract.*;
import GU.packets.abstractPacket.IAbstractPacket;

public class PowerPacket implements IAbstractPacket {
    
    int x, y, z;
    DefaultPowerManager powerToUpdate;
    
    public PowerPacket() {
        // TODO Auto-generated constructor stub
    }
    
    public PowerPacket(int x, int y, int z, DefaultPowerManager manager) {
        
        this.x = x;
        this.y = y;
        this.z = z;
        this.powerToUpdate = manager;
    }
    
    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
        
        buffer.writeInt(x);
        buffer.writeInt(y);
        buffer.writeInt(z);
        
        buffer.writeInt(powerToUpdate.getStoredPower());
        buffer.writeInt(powerToUpdate.getMaxPower());
    }
    
    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
        
        x = buffer.readInt();
        y = buffer.readInt();
        z = buffer.readInt();
        
        if (powerToUpdate == null) {
            
            powerToUpdate = new DefaultPowerManager();
        }
        
        powerToUpdate.setPowerStored(buffer.readInt());
        powerToUpdate.setPowerMax(buffer.readInt());
    }
    
    @Override
    public void handleClientSide(EntityPlayer player) {
        
        TileEntity tile = player.worldObj.getTileEntity(x, y, z);
        
        if (tile != null && tile instanceof ITilePowerHandler) {
            
            IPowerManager manager = ((ITilePowerHandler) tile).getPowerManager();
            
            if (manager != null && manager instanceof DefaultPowerManager) {
                
                DefaultPowerManager dManager = ((DefaultPowerManager) manager);
                
                dManager.setPowerMax(powerToUpdate.getMaxPower());
                dManager.setPowerStored(powerToUpdate.getStoredPower());
            }
        }
    }
    
    @Override
    public void handleServerSide(EntityPlayer player) {
        
    }
}

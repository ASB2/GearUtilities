package GU.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import GU.api.power.PowerNetObject.DefaultPowerManager;
import GU.api.power.PowerNetAbstract.*;

public class PowerPacket implements AbstractPacket {
    
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
        
        buffer.writeInt(powerToUpdate.getMinInputPacketSize());
        buffer.writeInt(powerToUpdate.getMaxInputPacketSize());
        
        buffer.writeInt(powerToUpdate.getMinOutputPacketSize());
        buffer.writeInt(powerToUpdate.getMaxOutputPacketSize());
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
        
        powerToUpdate.setMinInputPacketSize(buffer.readInt());
        powerToUpdate.setMaxInputPacketSize(buffer.readInt());
        
        powerToUpdate.setMinOutputPacketSize(buffer.readInt());
        powerToUpdate.setMaxOutputPacketSize(buffer.readInt());
    }
    
    @Override
    public void handleClientSide(EntityPlayer player) {
        
        if (player.worldObj.isRemote) {
            
            TileEntity tile = player.worldObj.getTileEntity(x, y, z);
            
            if (tile != null && tile instanceof ITilePowerHandler) {
                
                IPowerManager manager = ((ITilePowerHandler) tile).getPowerManager();
                
                if (manager != null && manager instanceof DefaultPowerManager) {
                    
                    DefaultPowerManager dManager = ((DefaultPowerManager) manager);
                    
                    dManager.setMaxInputPacketSize(powerToUpdate.getMaxInputPacketSize());
                    dManager.setMaxOutputPacketSize(powerToUpdate.getMaxOutputPacketSize());
                    
                    dManager.setMinInputPacketSize(powerToUpdate.getMinInputPacketSize());
                    dManager.setMinOutputPacketSize(powerToUpdate.getMinOutputPacketSize());
                    
                    dManager.setPowerMax(powerToUpdate.getMaxPower());
                    dManager.setPowerStored(powerToUpdate.getStoredPower());
                }
            }
        }
    }
    
    @Override
    public void handleServerSide(EntityPlayer player) {
        
    }
}

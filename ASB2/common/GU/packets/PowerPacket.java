package GU.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import GU.api.power.PowerNetAbstract.IPowerManager;
import GU.api.power.PowerNetAbstract.ITilePowerHandler;
import GU.api.power.PowerNetObject.DefaultPowerManager;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PowerPacket implements IMessageHandler<PowerPacket, PowerPacket>, IMessage {
    
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
    public void fromBytes(ByteBuf buf) {
        
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
        
        if (powerToUpdate == null) {
            
            powerToUpdate = new DefaultPowerManager();
        }
        
        powerToUpdate.setPowerStored(buf.readInt());
        powerToUpdate.setPowerMax(buf.readInt());
    }
    
    @Override
    public void toBytes(ByteBuf buf) {
        
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        
        buf.writeInt(powerToUpdate.getStoredPower());
        buf.writeInt(powerToUpdate.getMaxPower());
    }
    
    @Override
    public PowerPacket onMessage(PowerPacket message, MessageContext ctx) {
        
        TileEntity tile = Minecraft.getMinecraft().theWorld.getTileEntity(x, y, z);
        
        if (tile != null && tile instanceof ITilePowerHandler) {
            
            IPowerManager manager = ((ITilePowerHandler) tile).getPowerManager();
            
            if (manager != null && manager instanceof DefaultPowerManager) {
                
                DefaultPowerManager dManager = ((DefaultPowerManager) manager);
                
                dManager.setPowerMax(powerToUpdate.getMaxPower());
                dManager.setPowerStored(powerToUpdate.getStoredPower());
            }
        }
        return null;
    }
}

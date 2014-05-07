package GU.packets;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import GU.api.power.IPowerHandler;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.relauncher.Side;

public class PowerPacket extends GUPacketBase {
    
    int x;
    int y;
    int z;
    float power;
    float max;
    
    public PowerPacket() {
        
    }
    
    public PowerPacket(int x, int y, int z, float power, float max) {
        
        this.x = x;
        this.y = y;
        this.z = z;
        this.power = power;
        this.max = max;
    }
    
    @Override
    protected void write(ByteArrayDataOutput out) {
        
        out.writeInt(x);
        out.writeInt(y);
        out.writeInt(z);
        
        out.writeFloat(power);
        out.writeFloat(max);
    }
    
    @Override
    protected void read(ByteArrayDataInput in) throws ProtocolException {
        
        x = in.readInt();
        y = in.readInt();
        z = in.readInt();
        
        power = in.readFloat();
        max = in.readFloat();
    }
    
    @Override
    protected void execute(EntityPlayer player, Side side) throws ProtocolException {
        
        TileEntity tile = player.worldObj.getTileEntity(x, y, z);
        
        if (tile != null && tile instanceof IPowerHandler) {
            
            if (((IPowerHandler) tile).getPowerProvider() != null) {
                
                ((IPowerHandler) tile).getPowerProvider().setPowerStored(power);
                ((IPowerHandler) tile).getPowerProvider().setPowerMax(max);
            }
        }
    }
}

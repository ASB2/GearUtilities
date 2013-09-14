package GU.packets;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import GU.api.power.IPowerMisc;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.relauncher.Side;

public class PowerPacket extends GUPacketBase {

    public PowerPacket() {

    }

    public PowerPacket(int x, int y, int z, float power, float max) {

        this.x = x;
        this.y = y;
        this.z = z;
        this.power = power;
        this.max = max;
    }

    int x;
    int y;
    int z;
    float power;
    float max;

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

        TileEntity tile = player.worldObj.getBlockTileEntity(x, y, z);

        if(tile != null && tile instanceof IPowerMisc) {

            if(((IPowerMisc)tile).getPowerProvider() != null) {

                ((IPowerMisc)tile).getPowerProvider().setPowerStored(power);
                ((IPowerMisc)tile).getPowerProvider().setPowerMax(max);
            }
        }
    }
}

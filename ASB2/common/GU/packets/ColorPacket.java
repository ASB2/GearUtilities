package GU.packets;

import java.awt.Color;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.ForgeDirection;
import GU.color.IColorable;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.relauncher.Side;

public class ColorPacket extends GUPacketBase {

    int x;
    int y;
    int z;
    
    Color color;
    int side;
    
    boolean itWorked = false;

    public ColorPacket(int x, int y, int z, Color color, int side) {

        this.x = x;
        this.y = y;
        this.z = z;
        
        this.color = color;
        this.side = side;
    }

    public ColorPacket() {

    }

    @Override
    protected void write(ByteArrayDataOutput out) {

        out.writeInt(x);
        out.writeInt(y);
        out.writeInt(z);
        out.writeInt(color.getRed());
        out.writeInt(color.getGreen());
        out.writeInt(color.getBlue());
        out.writeInt(color.getAlpha());
        out.writeInt(side);
    }

    @Override
    protected void read(ByteArrayDataInput in) throws ProtocolException {

        try {
            x = in.readInt();
            y = in.readInt();
            z = in.readInt();
            
            color = new Color(in.readInt(), in.readInt(), in.readInt(), in.readInt());
            
            side = in.readInt();
            
            itWorked = true;
        } catch (IllegalStateException e) {

            // e.printStackTrace();
            return;
        }
    }

    @Override
    protected void execute(EntityPlayer player, Side side) throws ProtocolException {

        if (itWorked && side == Side.CLIENT) {

            if (player.worldObj.getBlockTileEntity(x, y, z) != null && player.worldObj.getBlockTileEntity(x, y, z) instanceof IColorable) {

                IColorable colorable = (IColorable)player.worldObj.getBlockTileEntity(x, y, z);

                colorable.setColor(color, ForgeDirection.getOrientation(this.side));                
                player.worldObj.markBlockForRenderUpdate(x, y, z);
            }
        }
    }
}

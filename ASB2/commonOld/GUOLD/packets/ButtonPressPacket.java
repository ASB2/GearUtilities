package GUOLD.packets;

import net.minecraft.entity.player.EntityPlayer;
import GUOLD.gui.ContainerBase;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.relauncher.Side;

public class ButtonPressPacket extends GUPacketBase {

    int buttonID;

    public ButtonPressPacket() {

    }

    public ButtonPressPacket(int buttonID) {

        this.buttonID = buttonID;
    }

    @Override
    protected void write(ByteArrayDataOutput out) {

        out.writeInt(buttonID);
    }

    @Override
    protected void read(ByteArrayDataInput in) throws ProtocolException {

        buttonID = in.readInt();
    }

    @Override
    protected void execute(EntityPlayer player, Side side) throws ProtocolException {

        if(player.openContainer != null && player.openContainer instanceof ContainerBase ) {

            ((ContainerBase)player.openContainer).onButtonEvent(buttonID);
        }
    }
}

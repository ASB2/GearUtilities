package GU.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import GU.blocks.containers.BlockMultiInterface.TileItemMultiInterface;
import GU.render.EnumInputIcon;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class ItemMultiInterfacePacket implements IMessageHandler<ItemMultiInterfacePacket, ItemMultiInterfacePacket>, IMessage {
    
    int x, y, z;
    EnumInputIcon[] sideState;
    
    public ItemMultiInterfacePacket() {
        // TODO Auto-ItemMultiInterfacePacket constructor stub
    }
    
    public ItemMultiInterfacePacket(int x, int y, int z, EnumInputIcon[] sideState) {
        
        this.x = x;
        this.y = y;
        this.z = z;
        this.sideState = sideState;
    }
    
    @Override
    public void fromBytes(ByteBuf buf) {
        
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
        sideState = new EnumInputIcon[ForgeDirection.values().length];
        sideState[0] = EnumInputIcon.values()[buf.readInt()];
        sideState[1] = EnumInputIcon.values()[buf.readInt()];
        sideState[2] = EnumInputIcon.values()[buf.readInt()];
        sideState[3] = EnumInputIcon.values()[buf.readInt()];
        sideState[4] = EnumInputIcon.values()[buf.readInt()];
        sideState[5] = EnumInputIcon.values()[buf.readInt()];
    }
    
    @Override
    public void toBytes(ByteBuf buf) {
        
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeInt(sideState[0].ordinal());
        buf.writeInt(sideState[1].ordinal());
        buf.writeInt(sideState[2].ordinal());
        buf.writeInt(sideState[3].ordinal());
        buf.writeInt(sideState[4].ordinal());
        buf.writeInt(sideState[5].ordinal());
    }
    
    @Override
    public ItemMultiInterfacePacket onMessage(ItemMultiInterfacePacket message, MessageContext ctx) {
        
        TileEntity tile = Minecraft.getMinecraft().theWorld.getTileEntity(message.x, message.y, message.z);
        
        if (tile != null && tile instanceof TileItemMultiInterface) {
            
            ((TileItemMultiInterface) tile).setSideState(message.sideState);
        }
        return null;
    }
    
}

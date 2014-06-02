package GU.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import GU.blocks.containers.BlockElectisCrystal.EnumElectisCrystalType;
import GU.blocks.containers.BlockElectisCrystal.TileElectisCrystal;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class CrystalTypePacket implements IMessageHandler<CrystalTypePacket, CrystalTypePacket>, IMessage {
    
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
    public void fromBytes(ByteBuf buf) {
        
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
        crystalType = buf.readInt();
    }
    
    @Override
    public void toBytes(ByteBuf buf) {
        
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeInt(crystalType);
    }
    
    @Override
    public CrystalTypePacket onMessage(CrystalTypePacket message, MessageContext ctx) {
        
        TileEntity tile = Minecraft.getMinecraft().theWorld.getTileEntity(message.x, message.y, message.z);
        
        if (tile != null && tile instanceof TileElectisCrystal) {
            
            ((TileElectisCrystal) tile).setCrystalType(EnumElectisCrystalType.values()[message.crystalType]);
        }
        return null;
    }
    
}

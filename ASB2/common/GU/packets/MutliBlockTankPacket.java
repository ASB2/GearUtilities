package GU.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import ASB2.utils.UtilVector;
import GU.blocks.containers.BlockMultiPart.BlockMultiPartRender.TileMultiPartRender;
import GU.multiblock.MultiBlockTankClientState;
import UC.math.vector.Vector3i;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MutliBlockTankPacket implements IMessageHandler<MutliBlockTankPacket, MutliBlockTankPacket>, IMessage {
    
    Vector3i renderHandler;
    Vector3i size;
    
    FluidTank tank;
    
    public MutliBlockTankPacket() {
        // TODO Auto-generated constructor stub
    }
    
    public MutliBlockTankPacket(Vector3i vector, Vector3i size, FluidTank tank) {
        
        renderHandler = vector;
        this.size = size;
        this.tank = tank;
    }
    
    @Override
    public void toBytes(ByteBuf buf) {
        
        buf.writeInt(renderHandler.getX());
        buf.writeInt(renderHandler.getY());
        buf.writeInt(renderHandler.getZ());
        
        buf.writeInt(size.getX());
        buf.writeInt(size.getY());
        buf.writeInt(size.getZ());
        
        buf.writeInt(tank.getFluid().getFluid().getID());
        buf.writeInt(tank.getFluidAmount());
        buf.writeInt(tank.getCapacity());
        // ByteBufUtils.writeTag(buffer, tank.getFluid().tag);
    }
    
    @Override
    public void fromBytes(ByteBuf buf) {
        
        renderHandler = new Vector3i(buf.readInt(), buf.readInt(), buf.readInt());
        
        size = new Vector3i(buf.readInt(), buf.readInt(), buf.readInt());
        
        int fluidID = buf.readInt();
        int fluidAmount = buf.readInt();
        int capasity = buf.readInt();
        // NBTTagCompound tag = ByteBufUtils.readTag(buffer);
        tank = new FluidTank(new FluidStack(fluidID, fluidAmount), capasity);
    }
    
    @Override
    public MutliBlockTankPacket onMessage(MutliBlockTankPacket message, MessageContext ctx) {
        
        TileEntity tile = UtilVector.getTileAtPostion(Minecraft.getMinecraft().theWorld, renderHandler);
        
        if (tile != null && tile instanceof TileMultiPartRender) {
            
            ((TileMultiPartRender) tile).setClientState(new MultiBlockTankClientState(renderHandler, size, tank));
        }
        return null;
    }
}

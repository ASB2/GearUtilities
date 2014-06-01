package GU.packets.abstractPacket;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import ASB2.utils.UtilVector;
import GU.blocks.containers.BlockMultiPart.BlockMultiPartRender.TileMultiPartRender;
import GU.multiblock.MultiBlockTankClientState;
import UC.math.vector.Vector3i;
import cpw.mods.fml.common.network.ByteBufUtils;

public class MutliBlockTankPacket implements IAbstractPacket {
    
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
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
        
        buffer.writeInt(renderHandler.getX());
        buffer.writeInt(renderHandler.getY());
        buffer.writeInt(renderHandler.getZ());
        
        buffer.writeInt(size.getX());
        buffer.writeInt(size.getY());
        buffer.writeInt(size.getZ());
        
        buffer.writeInt(tank.getFluid().getFluid().getID());
        buffer.writeInt(tank.getFluidAmount());
        buffer.writeInt(tank.getCapacity());
        // ByteBufUtils.writeTag(buffer, tank.getFluid().tag);
    }
    
    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
        
        renderHandler = new Vector3i(buffer.readInt(), buffer.readInt(), buffer.readInt());
        
        size = new Vector3i(buffer.readInt(), buffer.readInt(), buffer.readInt());
        
        int fluidID = buffer.readInt();
        int fluidAmount = buffer.readInt();
        int capasity = buffer.readInt();
        // NBTTagCompound tag = ByteBufUtils.readTag(buffer);
        tank = new FluidTank(new FluidStack(fluidID, fluidAmount), capasity);
    }
    
    @Override
    public void handleClientSide(EntityPlayer player) {
        
        TileEntity tile = UtilVector.getTileAtPostion(player.worldObj, renderHandler);
        
        if (tile != null && tile instanceof TileMultiPartRender) {
            
            ((TileMultiPartRender) tile).setClientState(new MultiBlockTankClientState(renderHandler, size, tank));
        }
    }
    
    @Override
    public void handleServerSide(EntityPlayer player) {
        // TODO Auto-generated method stub
        
    }
}

package GU.api.network;

import java.util.EnumSet;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;


public interface IPacket {

    ForgeDirection getDirection();    
    EnumSet<PacketType> getPacketType();
    
    void save(NBTTagCompound tag);
    void load(NBTTagCompound tag);
}

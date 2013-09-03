package GU.api.conduit.packet;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import ASB2.vector.Vector3;

public interface IConduitPacket {

    void updatePacket(World world);
    boolean destory(World world);
    PacketType getPacketType();
    Vector3 getPosition();
    void savePacket(NBTTagCompound tag);
    void loadPacket(NBTTagCompound tag);
}

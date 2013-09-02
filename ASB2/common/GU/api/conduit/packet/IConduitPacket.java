package GU.api.conduit.packet;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public interface IConduitPacket {

    void updatePacket(World world);
    void savePacket(NBTTagCompound tag);
    void loadPacket(NBTTagCompound tag);
}

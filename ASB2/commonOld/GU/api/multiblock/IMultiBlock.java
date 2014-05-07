package GU.api.multiblock;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import ASB2.vector.Cuboid;

public interface IMultiBlock {

    /**
     * Called to Create Multoblck
     */
    boolean create();

    void invalidate();

    boolean isValid();

    void setWorld(World world);

    World getWorldObj();

    boolean setSize(Cuboid size);

    Cuboid getSize();

    NBTTagCompound save(NBTTagCompound tag);

    void load(NBTTagCompound tag);

    void update();

    void render(double x, double y, double z);

    boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ);
}

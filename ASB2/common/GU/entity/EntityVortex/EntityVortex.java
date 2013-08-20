package GU.entity.EntityVortex;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityVortex extends Entity {

    public boolean isDead = false;

    public EntityVortex(World world) {
        super(world);
    }

    public EntityVortex(World world, int x, int y, int z) {
        super(world);

        this.setPosition(x + .5d, y + .5d, z + .5d);
    }

    @Override
    protected void entityInit() {
        // TODO Auto-generated method stub

    }

    public void onEntityUpdate() {

    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
        // TODO Auto-generated method stub

    }

}

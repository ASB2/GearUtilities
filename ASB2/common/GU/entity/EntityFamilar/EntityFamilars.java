package GU.entity.EntityFamilar;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import ASB2.vector.Vector3;
import GU.entity.EntityBase;

public class EntityFamilars extends EntityBase {

    String owner;

    public EntityFamilars(World world) {
        super(world);
        this.setSize(.5f, .5f);
    }

    public EntityFamilars(World world, Vector3 pos, String owner) {
        this(world, pos.x, pos.y, pos.z, owner);
    }

    public EntityFamilars(World world, double x, double y, double z, String owner) {
        super(world, x, y, z);

        this.owner = owner;
        this.setSize(.5f, .5f);
    }

    @Override
    public void onEntityUpdate() {

        this.setSize(.5f, .5f);
        super.onEntityUpdate();
    }

    @Override
    protected void onImpactEntity(Entity entity) {

    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound tag) {
        super.readEntityFromNBT(tag);

        owner = tag.getString("owner");
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound tag) {
        super.writeEntityToNBT(tag);
        tag.setString("owner", owner);
    }
}

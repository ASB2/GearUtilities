package GU.entity.EntityTest;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import GU.entity.EntityBase;

public class EntityTestEntity extends EntityBase {

    public EntityTestEntity(World world) {
        super(world);
        this.setSize(.7F, .7F);
    }

    @Override
    public void onEntityUpdate() {
        super.onEntityUpdate();

        this.updateMovement();
    }

    @Override
    protected void entityInit() {

    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound tag) {

    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound tag) {

    }

}

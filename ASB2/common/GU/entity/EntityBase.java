package GU.entity;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public abstract class EntityBase extends Entity implements
        IEntityAdditionalSpawnData {

    public EntityBase(World world) {
        super(world);
    }

    @Override
    public void onEntityUpdate() {
        super.onEntityUpdate();

    }

    @Override
    protected void entityInit() {
        // TODO Auto-generated method stub

    }

    public void updateMovement() {

        this.setPosition(posX + motionX, posY + motionY, posZ + motionZ);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
        // TODO Auto-generated method stub

    }

    @Override
    public void writeSpawnData(ByteArrayDataOutput data) {
        // TODO Auto-generated method stub

    }

    @Override
    public void readSpawnData(ByteArrayDataInput data) {
        // TODO Auto-generated method stub

    }

    @Override
    public AxisAlignedBB getBoundingBox() {

        return boundingBox;
    }

    @Override
    public AxisAlignedBB getCollisionBox(Entity entity) {

        return entity.boundingBox;
    }

    @Override
    public boolean canBeCollidedWith() {

        return !isDead;
    }

    @Override
    public boolean func_130002_c(EntityPlayer player) {

        if (!worldObj.isRemote && riddenByEntity == null) {

            // player.mountEntity(this);
        }
        return false;
    }
}

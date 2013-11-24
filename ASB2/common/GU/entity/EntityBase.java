package GU.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import ASB2.vector.Vector3;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;

public abstract class EntityBase extends Entity implements IEntityAdditionalSpawnData {

    public EntityBase(World world) {
        super(world);
    }

    public EntityBase(World world, double x, double y, double z) {
        super(world);

        this.posX = x;
        this.posY = y;
        this.posZ = z;
    }

    public EntityBase(World world, Vector3 position) {
        super(world);

        this.posX = position.x;
        this.posY = position.y;
        this.posZ = position.z;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
    }

    protected abstract void onImpactEntity(Entity entity);

    @Override
    public void onEntityUpdate() {
        super.onEntityUpdate();
    }


    @Override
    protected void entityInit() {
    }

    public void setPosition(Vector3 vector) {

        this.setPosition(vector.x, vector.y, vector.z);    
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer player) {

        this.onImpactEntity(player);
    }

    public void updateMovement() {

        this.setPosition(posX + motionX, posY + motionY, posZ + motionZ);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
        super.readFromNBT(nbttagcompound);

    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
        super.writeToNBT(nbttagcompound);
    }

    @Override
    public void writeSpawnData(ByteArrayDataOutput data) {
    }

    @Override
    public void readSpawnData(ByteArrayDataInput data) {
    }

    @Override
    public AxisAlignedBB getBoundingBox() {

        return boundingBox;
    }

    @Override
    public AxisAlignedBB getCollisionBox(Entity entity) {

        if(entity != riddenByEntity) {

            return entity.boundingBox;
        }
        return null;
    }

    @Override
    public boolean canBeCollidedWith() {

        return !isDead;
    }
}

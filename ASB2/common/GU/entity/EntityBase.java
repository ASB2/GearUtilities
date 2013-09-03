package GU.entity;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;

public abstract class EntityBase extends Entity implements
IEntityAdditionalSpawnData {

    public EntityBase(World world) {
        super(world);
    }

    public void onUpdate() {
        super.onUpdate();

        Vec3 pos3 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
        Vec3 newPos3 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        Entity entityHit = getEntityHit(pos3, newPos3);

        if (entityHit != null) {

            this.onImpactEntity(entityHit);
        }
    }

    private Entity getEntityHit(Vec3 pos3, Vec3 newPos3) {

        List<?> var10 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, AxisAlignedBB.getBoundingBox(pos3.xCoord, pos3.yCoord, pos3.zCoord, pos3.xCoord, pos3.yCoord, pos3.zCoord).addCoord(newPos3.xCoord, newPos3.yCoord, newPos3.zCoord).expand(1.0D, 1.0D, 1.0D));  
        double var11 = 1.0D;
        Iterator<?> var13 = var10.iterator();
        Entity entityHit = null;

        while (var13.hasNext()) {

            Entity var14 = (Entity)var13.next();

            if (var14.canBeCollidedWith()) {

                float var15 = var14.getCollisionBorderSize();
                AxisAlignedBB var16 = var14.boundingBox.expand(var15, var15, var15);
                MovingObjectPosition var17 = var16.calculateIntercept(pos3, newPos3);

                if (var16.isVecInside(pos3)) {

                    if ((0.0D < var11) || (var11 == 0.0D)) {

                        entityHit = var14;
                        var11 = 0.0D;
                    }
                }
                else if (var17 != null) {

                    double var18 = pos3.distanceTo(var17.hitVec);

                    if ((var18 < var11) || (var11 == 0.0D)) {

                        entityHit = var14;
                        var11 = var18;
                    }
                }
            }
        }

        return entityHit;
    }

    protected abstract void onImpactEntity(Entity entity);

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

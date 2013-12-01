package GU.entity.EntityTileFinder;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import ASB2.utils.UtilEntity;
import ASB2.vector.Vector3;
import GU.api.cluster.ITileFinderSource;
import GU.entity.EntityBase;
import GU.info.GUDamageSource;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityTileFinder extends EntityBase {

    int id;

    public EntityTileFinder(World world) {
        super(world);
    }

    public EntityTileFinder(World world, Vector3 start, Vector3 destination, Vector3 tileToCallin, int id) {
        super(world, start);

        vectors = new Vector3[3];
        this.vectors[0] = start;
        this.vectors[1] = destination;
        this.vectors[2] = tileToCallin;
        this.setSize(.1f, .1f);
        this.id = id;
        this.recalulateMotion(.2);
    }

    @Override
    protected void entityInit() {

        preventEntitySpawning = false;
        noClip = true;
        isImmuneToFire = true;
    }

    @Override
    public void onEntityUpdate() {

        if(vectors != null && vectors[0] != null && vectors[1] != null && vectors[2] != null) {

            vectors[0] = new Vector3(this);

            if(!vectors[0].intEquals(vectors[1])) {

                this.updateMovement();
                return;
            }
        }
    }

    public void recalulateMotion(double speedChange) {

        Vector3 tempVec = vectors[1].clone().subtract(vectors[0]).normalize().multiply(speedChange);
        motionX = tempVec.x;
        motionY = tempVec.y;
        motionZ = tempVec.z;
    }

    @Override
    protected void onImpactEntity(Entity entity) {

        UtilEntity.damageEntity(worldObj, entity, GUDamageSource.entityFinderCollision, 100);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getShadowSize() {

        return 0;
    }

    @Override
    public boolean doesEntityNotTriggerPressurePlate() {

        return false;
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        id = tag.getInteger("id");
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound tag) {
        super.writeEntityToNBT(tag);
        tag.setInteger("id", id);
    }
}

package GU.entity.EntityCluster;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import ASB2.utils.UtilEntity;
import ASB2.vector.Vector3;
import GU.api.cluster.IClusterTrigger;
import GU.api.cluster.IClustor;
import GU.entity.EntityBase;
import GU.info.GUDamageSource;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityInfoCluster extends EntityBase implements IClustor {

    Vector3 start;
    Vector3 position;
    ForgeDirection direction;
    IClusterTrigger source;
    int id;
    
    public EntityInfoCluster(World world, Vector3 start, ForgeDirection direction, IClusterTrigger source, int id) {
        super(world, start);

        this.start = start;
        position = start.clone();
        this.direction = direction;
        this.source = source;
        this.setSize(.1f, .1f);
        this.id = id;
    }

    public EntityInfoCluster(World world) {
        super(world);
    }

    @Override
    protected void entityInit() {

        preventEntitySpawning = false;
        noClip = true;
        isImmuneToFire = true;
    }


    @Override
    public void onUpdate() {
        super.onUpdate();

        if(!worldObj.isRemote) {

            double divided = 1;            
            position.add(direction.offsetX / divided, direction.offsetY / divided,direction.offsetZ / divided);
            this.setPosition(position);

            source.onSentClustorCollosion(source, direction, position, this, id);

            if(position.getTileEntity(worldObj) != null) {

                if(position.getTileEntity(worldObj) instanceof IClusterTrigger) {

                    ((IClusterTrigger)position.getTileEntity(worldObj)).onClustorCollosion(source, direction, position, this);
                }
            }      
        }
    }

    @Override
    public boolean stopClustor() {

        this.setDead();
        return false;
    }

    @Override
    protected void onImpactEntity(Entity entity) {

        UtilEntity.damageEntity(worldObj, entity, GUDamageSource.clusterCollision, 100);       
    }

    @SideOnly(Side.CLIENT)
    public float getShadowSize() {

        return 0;
    }

    public boolean doesEntityNotTriggerPressurePlate() {

        return false;
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
    }
}

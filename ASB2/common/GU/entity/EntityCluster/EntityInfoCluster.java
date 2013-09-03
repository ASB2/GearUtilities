package GU.entity.EntityCluster;

import net.minecraft.entity.Entity;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.IFluidHandler;
import ASB2.utils.UtilPlayers;
import ASB2.vector.Vector3;
import GU.api.cluster.IClusterTrigger;
import GU.api.cluster.IClustor;
import GU.entity.EntityBase;
import GU.info.GUDamageSource;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import GU.api.power.*;

public class EntityInfoCluster extends EntityBase implements IClustor {

    Vector3 start;
    Vector3 position;
    ForgeDirection direction;
    IClusterTrigger source;
    int maxRange = 1;

    public EntityInfoCluster(World world, Vector3 start, ForgeDirection direction, IClusterTrigger source, int maxRange) {
        super(world, start);

        this.start = start;
        position = start.clone();
        this.direction = direction;
        this.source = source;
        this.setSize(0f, 0f);
        this.maxRange = maxRange;
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

        if(start != null && position != null) {

            this.setPosition(position.x, position.y, position.z);

            int divided = 1;

            this.moveEntity(direction.offsetX / divided, direction.offsetY / divided, direction.offsetZ / divided);
            position = new Vector3(this);

            if(position.getTileEntity(worldObj) instanceof IInventory || position.getTileEntity(worldObj) instanceof IPowerMisc || position.getTileEntity(worldObj) instanceof IFluidHandler) {

                source.onClustorCollosion(direction, position, this);
                worldObj.markBlockForUpdate(position.intX(), position.intY(), position.intZ());
                this.setDead();
            }   
        }
    }

    @Override
    protected void onImpactEntity(Entity entity) {

        UtilPlayers.damagePlayer(worldObj, entity, GUDamageSource.clusterCollision, 100);       
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

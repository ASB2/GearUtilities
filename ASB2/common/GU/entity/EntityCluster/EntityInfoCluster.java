package GU.entity.EntityCluster;

import java.awt.Color;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.IFluidHandler;
import ASB2.utils.UtilPlayers;
import ASB2.vector.Vector3;
import GU.api.cluster.IClusterTrigger;
import GU.api.cluster.IClustor;
import GU.api.power.IPowerMisc;
import GU.entity.EntityBase;
import GU.info.GUDamageSource;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityInfoCluster extends EntityBase implements IClustor {

    Vector3 start;
    Vector3 position;
    ForgeDirection direction;
    IClusterTrigger source;
    int range = 0;
    Color color;

    public EntityInfoCluster(World world, Vector3 start, ForgeDirection direction, IClusterTrigger source, int range, Color color) {
        this(world);

        this.start = start;
        position = start.clone();
        this.direction = direction;
        this.source = source;
        this.range = range;
        this.color = color;
        
        this.posX = start.x;
        this.posY = start.y;
        this.posZ = start.z;
    }

    public EntityInfoCluster(World world) {
        super(world);

        this.setSize(.02f, .02f);
        isImmuneToFire = true;
    }


    @Override
    public void onEntityUpdate() {
        super.onEntityUpdate();

        if(direction != null && source != null) {    

            if(start.distanceTo(position) <= range) {

                int divided = 5;
                this.moveEntity(direction.offsetX / divided, direction.offsetY / divided, direction.offsetZ / divided);
                position.add(new Vector3(direction));
                if(position.getTileEntity(worldObj) != null) {

                    if(position.getTileEntity(worldObj) instanceof IInventory || position.getTileEntity(worldObj) instanceof IPowerMisc || position.getTileEntity(worldObj) instanceof IFluidHandler) {

                        source.onClustorCollosion(direction, position, this);
                        this.setDead();
                    }                
                }
            }
        }
        else {
            
            this.setDead();
        }
    }

    @Override
    protected void onImpactEntity(Entity entity) {

        UtilPlayers.damagePlayer(worldObj, entity, GUDamageSource.clusterCollision, 100);       
    }

    @SideOnly(Side.CLIENT)
    public float getShadowSize() {

        return super.getShadowSize();
    }

    public void onStruckByLightning(EntityLightningBolt par1EntityLightningBolt) {

        super.onStruckByLightning(par1EntityLightningBolt);
    }

    public void onKillEntity(EntityLivingBase par1EntityLivingBase) {

        super.onKillEntity(par1EntityLivingBase);
    }

    public boolean doesEntityNotTriggerPressurePlate() {

        return false;
    }

    public ItemStack getPickedResult(MovingObjectPosition target) {

        return super.getPickedResult(target);
    }

    public boolean shouldRenderInPass(int pass)
    {
        return true;
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer player) {

        this.onImpactEntity(player);
    }

    @Override
    protected void entityInit() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
        super.readEntityFromNBT(nbttagcompound);

        //        start = Vector3.readFromNBT(nbttagcompound);
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
        super.writeToNBT(nbttagcompound);

        //        start.writeToNBT(nbttagcompound);
    }
}

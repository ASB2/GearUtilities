package GU.entity.EntityCluster;

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

    public EntityInfoCluster(World world, Vector3 start, ForgeDirection direction, IClusterTrigger source) {
        this(world);

        this.start = start;
        position = new Vector3(start);
        this.direction = direction;
        this.source = source;
    }

    public EntityInfoCluster(World world) {
        super(world);

        this.setSize(.7f, .7f);
        isImmuneToFire = true;
        isAirBorne = true;
        this.noClip = true;
    }


    @Override
    public void onEntityUpdate() {
        super.onEntityUpdate();

        if(direction != null && source != null) {            

            int divided = 1;
            this.moveEntity(direction.offsetX / divided, direction.offsetY / divided, direction.offsetZ / divided);
        
            if(position.getTileEntity(worldObj) != null) {
                
                if(position.getTileEntity(worldObj) instanceof IInventory || position.getTileEntity(worldObj) instanceof IPowerMisc || position.getTileEntity(worldObj) instanceof IFluidHandler) {
                    
                    source.onClustorCollosion(direction, position, this);
                    this.setDead();
                }                
            }
            else {
                this.setDead();
            }
        }
    }

    @Override
    protected void onImpactEntity(Entity entity) {
 
        UtilPlayers.damagePlayer(worldObj, entity, GUDamageSource.clusterCollision, 100);       
    }

    protected void kill() {

        super.kill();
    }

    protected boolean canTriggerWalking() {

        return false;
    }

    @SideOnly(Side.CLIENT)
    public float getShadowSize() {

        return 0;
    }

    public void onStruckByLightning(EntityLightningBolt par1EntityLightningBolt) {

        onStruckByLightning(par1EntityLightningBolt);
    }

    public void onKillEntity(EntityLivingBase par1EntityLivingBase) {

    }

    public boolean doesEntityNotTriggerPressurePlate() {

        return false;
    }

    public ItemStack getPickedResult(MovingObjectPosition target) {

        return super.getPickedResult(target);
    }

    public boolean shouldRenderInPass(int pass)
    {
        return pass == 0;
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

        start = Vector3.readFromNBT(nbttagcompound);
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
        super.writeToNBT(nbttagcompound);

        start.writeToNBT(nbttagcompound);
    }
}

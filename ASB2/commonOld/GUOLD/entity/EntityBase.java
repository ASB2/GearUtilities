package GUOLD.entity;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import ASB2.vector.Vector3;
import ASB2.wait.IWaitTrigger;
import ASB2.wait.Wait;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;

public abstract class EntityBase extends Entity implements IEntityAdditionalSpawnData, IWaitTrigger {
    
    protected Wait[] waits;
    protected Vector3[] vectors;
    
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
    
    public void trigger(int id) {
        
    }
    
    public boolean shouldTick(int id) {
        
        return true;
    }
    
    @Override
    public void onUpdate() {
        super.onUpdate();
    }
    
    protected void onImpactEntity(Entity entity) {
        
    }
    
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
        
        if (vectors != null) {
            
            vectors = new Vector3[nbttagcompound.getInteger("vectorSize")];
            
            for (int i = 0; i < vectors.length; i++) {
                
                vectors[i] = Vector3.readFromNBT(nbttagcompound.getCompoundTag("vectors " + i));
            }
        }
    }
    
    @Override
    protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
        
        if (vectors != null) {
            
            for (int i = 0; i < vectors.length; i++) {
                
                NBTTagCompound tag = new NBTTagCompound();
                
                vectors[i].writeToNBT(tag);
                nbttagcompound.setTag("vectors " + i, tag);
            }
            
            nbttagcompound.setInteger("vectorSize", vectors.length);
        }
    }
    
    @Override
    public void writeSpawnData(ByteBuf data) {
        
        data.writeDouble(posX);
        data.writeDouble(posY);
        data.writeDouble(posZ);
    }
    
    @Override
    public void readSpawnData(ByteBuf data) {
        
        posX = data.readDouble();
        posY = data.readDouble();
        posZ = data.readDouble();
    }
    
    @Override
    public AxisAlignedBB getBoundingBox() {
        
        return boundingBox;
    }
    
    @Override
    public AxisAlignedBB getCollisionBox(Entity entity) {
        
        if (entity != riddenByEntity) {
            
            return entity.boundingBox;
        }
        return null;
    }
    
    @Override
    public boolean canBeCollidedWith() {
        
        return !isDead;
    }
}

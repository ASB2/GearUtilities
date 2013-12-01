package GU.entity.EntityFamilar;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import GU.entity.EntityBase;

public class EntityFamilars extends EntityBase {
    
    String ownerName = "";
    
    public EntityFamilars(World world) {
        super(world);
    }

    public EntityFamilars(World world, double x, double y, double z, String owner) {
        super(world);
        
        this.setPosition(x, y, z);
        ownerName = owner;
    }
    
    @Override
    protected void onImpactEntity(Entity entity) {

    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
        // TODO Auto-generated method stub
        super.readEntityFromNBT(nbttagcompound);
    }
    
    @Override
    protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
        // TODO Auto-generated method stub
        super.writeEntityToNBT(nbttagcompound);
    }
}

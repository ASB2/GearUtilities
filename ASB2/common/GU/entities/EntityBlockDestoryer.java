package GU.entities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import ASB2.utils.UtilBlock;
import ASB2.utils.UtilVector;
import UC.math.vector.Vector3i;

public class EntityBlockDestoryer extends EntityBase {
    
    Vector3i startPosition;
    ForgeDirection direction;
    int distance;
    String playerName;
    
    public EntityBlockDestoryer(World par1World) {
        super(par1World);
        
        startPosition = Vector3i.ZERO;
        direction = ForgeDirection.UNKNOWN;
    }
    
    public EntityBlockDestoryer(World world, Vector3i start, ForgeDirection direction, int distance) {
        super(world);
        this.position = start.toVector3d().clone();
        this.startPosition = start;
        this.direction = direction;
        this.distance = distance;
    }
    
    @Override
    public void onUpdate() {
        
        position.move(direction.offsetX, direction.offsetY, direction.offsetZ);
        this.updateVinillaPosition();
        
        Vector3i positionFloor = position.toVector3iFloor();
        
        if (!positionFloor.equals(startPosition)) {
            
            UtilBlock.breakBlock(worldObj, positionFloor.getX(), positionFloor.getY(), positionFloor.getZ());
        }
        if (startPosition.distanceTo(position) >= distance) {
            
            this.setDead();
        }
    }
    
    @Override
    protected void writeEntityToNBT(NBTTagCompound tag) {
        
        tag.setInteger("distance", distance);
        tag.setInteger("direction", direction.ordinal());
        tag.setTag("startPosition", UtilVector.saveVector(new NBTTagCompound(), startPosition));
    }
    
    @Override
    protected void readEntityFromNBT(NBTTagCompound tag) {
        
        distance = tag.getInteger("distance");
        direction = ForgeDirection.getOrientation(tag.getInteger("direction"));
        startPosition = UtilVector.loadVector3i(tag.getCompoundTag("startPosition"));
    }
}

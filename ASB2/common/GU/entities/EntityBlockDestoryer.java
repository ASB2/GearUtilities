package GU.entities;

import net.minecraft.block.Block;
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
    Block block;
    int meta;
    
    public EntityBlockDestoryer(World par1World) {
        super(par1World);
        
        startPosition = Vector3i.ZERO;
        direction = ForgeDirection.UNKNOWN;
    }
    
    public EntityBlockDestoryer(World world, Vector3i start, ForgeDirection direction, int distance, Block block, int meta) {
        super(world);
        this.position = start.toVector3d().clone();
        this.startPosition = start;
        this.direction = direction;
        this.distance = distance;
        this.block = block;
        this.meta = meta;
    }
    
    @Override
    public void onUpdate() {
        
        // if (!worldObj.isRemote) {
        
        if (worldObj.rand.nextInt(5) == 0) {
            
            Vector3i positionFloor = position.toVector3iFloor();
            
            UtilBlock.breakBlock(worldObj, positionFloor.getX(), positionFloor.getY(), positionFloor.getZ());
            this.setDead();
            // }
            
            // position.move(direction.offsetX, direction.offsetY,
            // direction.offsetZ);
            // this.updateVinillaPosition();
            //
            // if (startPosition.distanceTo(position) >= distance || block ==
            // null) {
            //
            // this.setDead();
            // return;
            // }
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

package GU.entity.EntityTest;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import ASB2.vector.Vector3;
import ASB2.wait.Wait;
import GU.entity.EntityBase;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

public class EntityTestEntity extends EntityBase {
    
    public EntityTestEntity(World world) {
        super(world);
        
        waits = new Wait[2];
        vectors = new Vector3[2];
    }
    
    public EntityTestEntity(World world, double x, double y, double z, double x2, double y2, double z2) {
        this(world);
        
        this.setPosition(x, y, z);
        waits[0] = new Wait(20, this, 0);
        vectors[0] = new Vector3(this);
        vectors[1] = new Vector3(x2, y2, z2);
        Vector3 copy = vectors[1].clone().subtract(vectors[0]).normalize().multiply(.5);
        motionX = copy.x;
        motionY = copy.y;
        motionZ = copy.z;
    }
    
    @Override
    public void onEntityUpdate() {
        
        if (vectors != null && vectors[0] != null && vectors[1] != null) {
            
            vectors[0] = new Vector3(this);
            
            if (!((vectors[0].intX() == vectors[1].intX()) && (vectors[0].intY() == vectors[1].intY()) && (vectors[0].intZ() == vectors[1].intZ()))) {
                
                this.updateMovement();
                return;
            }
        }
        // this.setDead();
    }
    
    public void trigger(int id) {
    }
    
    @Override
    protected void entityInit() {
        
    }
    
    @Override
    protected void onImpactEntity(Entity entity) {
        // TODO Auto-generated method stub
        
    }
    
}

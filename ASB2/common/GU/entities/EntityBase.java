package GU.entities;

import javax.swing.text.html.parser.Entity;

import net.minecraft.world.World;

public abstract class EntityBase extends Entity {
    
    Vector3d position, momentum;
    
    public EntityBase(World par1World) {
        super(par1World);
        // TODO Auto-generated constructor stub
    }
    
    @Override
    protected void entityInit() {
        // TODO Auto-generated method stub
        
    }
    
    public void updateVinillaPosition() {
        
        if (position != null) {
            this.setPosition(position.getX(), position.getY(), position.getZ());
        }
    }
    
    public void updateVinillaMomentum() {
        
        if (momentum != null) {
            
            motionX = momentum.getX();
            motionY = momentum.getY();
            motionZ = momentum.getZ();
        }
    }
    
    public void applyMomentum() {
        
        if (momentum != null) {
            
            motionX += momentum.getX();
            motionY += momentum.getY();
            motionZ += momentum.getZ();
        }
    }
}

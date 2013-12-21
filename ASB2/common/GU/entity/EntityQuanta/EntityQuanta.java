package GU.entity.EntityQuanta;

import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import ASB2.utils.UtilDirection;
import ASB2.vector.Vector3;
import GU.api.power.IPowerHandler;
import GU.entity.EntityBase;

public class EntityQuanta extends EntityBase {
    
    public ForgeDirection direction;
    public int range = 0;
    public float power = 0;
    
    public EntityQuanta(World world) {
        super(world);
        
    }
    
    public EntityQuanta(World world, ForgeDirection direction, Vector3 startPosition, int range, float power) {
        super(world);
        this.direction = direction;
        this.range = range;
        this.power = power;
        this.vectors = new Vector3[2];
        vectors[0] = startPosition;
        vectors[1] = startPosition.clone();
    }
    
    @Override
    public void onEntityUpdate() {
        
        this.setPosition(vectors[1].add(direction));
        
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
            
            TileEntity tile = UtilDirection.translateDirectionToTile(worldObj, direction, vectors[1].intX(), vectors[1].intY(), vectors[1].intZ());
            
            int tiles = 0;
            
            if (tile != null && tile instanceof IPowerHandler) {
                
                tiles++;
            }
            
            if (tile != null && tile instanceof IPowerHandler) {
                
                if (((IPowerHandler) tile).getPowerProvider().gainPower(this.power / tiles, direction, true)) {
                    this.setDead();
                }
            }
        }
    }
    
    @Override
    protected void onImpactEntity(Entity entity) {
        // TODO Auto-generated method stub
        
    }
}

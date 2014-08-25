package GU.crystals;

import java.util.List;

import net.minecraft.world.World;
import UC.math.vector.Vector3i;

public class CrystalPowerNet {
    
    World worldObj;
    
    List<Vector3i> powerHandlers;
    
    public CrystalPowerNet(World world) {
        
        this.worldObj = world;
    }
    
    public World getWorldObj() {
        return worldObj;
    }
}

package GU.multiblock.construction;

import net.minecraft.world.World;
import GU.multiblock.MultiBlockBase;
import UC.math.vector.Vector3i;

public class TankConstructionManager extends CubeConstructionManager {
    
    public TankConstructionManager(World world, MultiBlockBase multiBlock, Vector3i positiveMostPoint, Vector3i size) {
        super(world, multiBlock, positiveMostPoint, size);
        // TODO Auto-generated constructor stub
    }
    
    @Override
    protected void updateDuringConstruction() {
        
        for (int x = 0; x <= size.getX(); x++) {
            
            for (int y = 0; y <= size.getY(); y++) {
                
                for (int z = 0; z <= size.getZ(); z++) {
                    
                    Vector3i vec = positiveMostPoint.subtract(x, y, z);
                    
                    if (x == 1 && y == 1 && z == 1) {
                        
                        if (!placeRenderBlock(vec)) {
                            
                            startDestruction();
                            return;
                        }
                    }
                    else if (((x == 0 || x == size.getX()) && (y == 0 || y == size.getY())) && (z == 0 || z == size.getZ())) {
                        
                        if (!placeCornerBlock(vec)) {
                            
                            startDestruction();
                            return;
                        }
                    }
                    else if (((x == 0 || x == size.getX()) && (y == 0 || y == size.getY())) || ((y == 0 || y == size.getY()) && (z == 0 || z == size.getZ())) || ((x == 0 || x == size.getX()) && (z == 0 || z == size.getZ()))) {
                        
                        if (!placeEdgeBlock(vec)) {
                            
                            startDestruction();
                            return;
                        }
                    }
                    else if (((x == 0 || x == size.getX()) && (y != 0 && y != size.getY())) && (z != 0 && z != size.getZ())) {
                        
                        if (!placeGlassBlock(vec)) {
                            
                            startDestruction();
                            return;
                        }
                    }
                    else if (((x != 0 && x != size.getX()) && (y == 0 || y == size.getY())) && (z != 0 && z != size.getZ())) {
                        
                        if (!placeGlassBlock(vec)) {
                            
                            startDestruction();
                            return;
                        }
                    }
                    else if (((x != 0 && x != size.getX()) && (y != 0 && y != size.getY())) && (z == 0 || z == size.getZ())) {
                        
                        if (!placeGlassBlock(vec)) {
                            
                            startDestruction();
                            return;
                        }
                    }
                    else if (!placeAirBlock(vec)) {
                        
                        startDestruction();
                        return;
                    }
                    // else if (!placeAirBlock(vec)) {
                    //
                    // deconstruct();
                    // return;
                    // }
                }
            }
        }
        this.finishConstruction();
    }
}

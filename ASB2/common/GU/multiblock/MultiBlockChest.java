package GU.multiblock;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ASB2.inventory.Inventory;
import ASB2.utils.UtilVector;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlockPart;
import UC.color.Color4i;
import UC.math.vector.Vector3i;

public class MultiBlockChest extends MultiBlockInventory {
    
    public MultiBlockChest(World world, Vector3i positionRelativeTo, Vector3i size, Vector3i updater) {
        super(world, positionRelativeTo, size, updater);
        
        inventory = new Inventory(8 * ((this.size.getX() + 1) * (this.size.getY() + 1) * (this.size.getZ() + 1)), "Inventory Base", true);
    }
    
    public MultiBlockChest(World world) {
        super(world);
        inventory = new Inventory(0, "Inventory Base", true);
    }
    
    @Override
    public Color4i getDefaultBlockColor() {
        
        return Color4i.GREEN;
    }
    
    @Override
    public void update(Object... objects) {
        
        if (isConstructing) {
            
            for (int x = 0; x <= size.getX(); x++) {
                
                for (int y = 0; y <= size.getY(); y++) {
                    
                    for (int z = 0; z <= size.getZ(); z++) {
                        
                        Vector3i vec = positionRelativeTo.subtract(x, y, z);
                        
                        if (((x == 0 || x == size.getX()) && (y == 0 || y == size.getY())) && (z == 0 || z == size.getZ())) {
                            
                            if (!placeCornerBlock(vec)) {
                                
                                deconstruct();
                                return;
                            }
                        }
                        else if (((x == 0 || x == size.getX()) && (y == 0 || y == size.getY())) || ((y == 0 || y == size.getY()) && (z == 0 || z == size.getZ())) || ((x == 0 || x == size.getX()) && (z == 0 || z == size.getZ()))) {
                            
                            if (!placeEdgeBlock(vec)) {
                                
                                deconstruct();
                                return;
                            }
                        }
                        else if (((x == 0 || x == size.getX()) && (y != 0 && y != size.getY())) && (z != 0 && z != size.getZ())) {
                            
                            if (!placeGlassBlock(vec)) {
                                
                                deconstruct();
                                return;
                            }
                        }
                        else if (((x != 0 && x != size.getX()) && (y == 0 || y == size.getY())) && (z != 0 && z != size.getZ())) {
                            
                            if (!placeGlassBlock(vec)) {
                                
                                deconstruct();
                                return;
                            }
                        }
                        else if (((x != 0 && x != size.getX()) && (y != 0 && y != size.getY())) && (z == 0 || z == size.getZ())) {
                            
                            if (!placeGlassBlock(vec)) {
                                
                                deconstruct();
                                return;
                            }
                        }
                        else if (!placeInnerBlock(vec)) {
                            
                            deconstruct();
                            return;
                        }
                    }
                }
            }
            isConstructing = false;
            isValid = true;
        }
        else if (forceLoad) {
            
            for (int x = 0; x <= size.getX(); x++) {
                
                for (int y = 0; y <= size.getY(); y++) {
                    
                    for (int z = 0; z <= size.getZ(); z++) {
                        
                        forceCheckBlock(positionRelativeTo.subtract(x, y, z));
                    }
                }
            }
            forceLoad = false;
            isValid = true;
        }
    }
    
    public boolean startCreation() {
        
        if (!isValid && !isConstructing) {
            
            if (size.getX() >= 2 && size.getY() >= 2 && size.getZ() >= 2) {
                
                isConstructing = true;
                
                TileEntity tile = UtilVector.getTileAtPostion(world, updater);
                
                if (tile != null && tile instanceof IMultiBlockPart) {
                    
                    return ((IMultiBlockPart) tile).addMultiBlock(this);
                }
            }
        }
        return false;
    }
}
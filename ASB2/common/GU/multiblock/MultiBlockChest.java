package GU.multiblock;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import ASB2.inventory.Inventory;
import ASB2.utils.UtilBlock;
import ASB2.utils.UtilVector;
import GU.BlockRegistry;
import GU.api.color.AbstractColorable.IColorableTile;
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
    
    Vector3i savedSize = Vector3i.ZERO;
    int positionHint;
    
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
                        
                        checkBlock(positionRelativeTo.subtract(x, y, z));
                    }
                }
            }
            forceLoad = false;
            isValid = true;
        }
    }
    
    public void deconstruct() {
        
        isConstructing = false;
        isValid = false;
        isDeconstructing = true;
        
        for (int x = 0; x <= size.getX(); x++) {
            
            for (int y = 0; y <= size.getY(); y++) {
                
                for (int z = 0; z <= size.getZ(); z++) {
                    
                    deconstructBlock(positionRelativeTo.subtract(x, y, z));
                }
            }
        }
    }
    
    public boolean placeAirBlock(Vector3i position) {
        
        TileEntity tile = UtilVector.getTileAtPostion(world, position);
        if (tile == null || !(tile instanceof IMultiBlockPart)) {
            
            UtilBlock.breakBlock(world, position.getX(), position.getY(), position.getZ());
            world.setBlock(position.getX(), position.getY(), position.getZ(), BlockRegistry.MULTI_BLOCK_PART);
        }
        
        tile = UtilVector.getTileAtPostion(world, position);
        
        if (checkBlock(position)) {
            
            if (tile instanceof IColorableTile) {
                
                ((IColorableTile) tile).setColor(this.getDefaultBlockColor(), ForgeDirection.UNKNOWN);
            }
            return true;
        }
        return false;
    }
    
    public boolean placeEdgeBlock(Vector3i position) {
        
        TileEntity tile = UtilVector.getTileAtPostion(world, position);
        if (tile == null || !(tile instanceof IMultiBlockPart)) {
            
            UtilBlock.breakBlock(world, position.getX(), position.getY(), position.getZ());
            world.setBlock(position.getX(), position.getY(), position.getZ(), BlockRegistry.MULTI_BLOCK_PART, 1, 3);
        }
        
        tile = UtilVector.getTileAtPostion(world, position);
        
        if (checkBlock(position)) {
            
            if (tile instanceof IColorableTile) {
                
                ((IColorableTile) tile).setColor(this.getDefaultBlockColor(), ForgeDirection.UNKNOWN);
            }
            return true;
        }
        return false;
    }
    
    public boolean placeCornerBlock(Vector3i position) {
        
        TileEntity tile = UtilVector.getTileAtPostion(world, position);
        if (tile == null || !(tile instanceof IMultiBlockPart)) {
            
            UtilBlock.breakBlock(world, position.getX(), position.getY(), position.getZ());
            world.setBlock(position.getX(), position.getY(), position.getZ(), BlockRegistry.MULTI_BLOCK_PART, 2, 3);
        }
        
        tile = UtilVector.getTileAtPostion(world, position);
        
        if (checkBlock(position)) {
            
            if (tile instanceof IColorableTile) {
                
                ((IColorableTile) tile).setColor(this.getDefaultBlockColor(), ForgeDirection.UNKNOWN);
            }
            return true;
        }
        return false;
    }
    
    public boolean checkBlock(Vector3i position) {
        
        TileEntity tile = UtilVector.getTileAtPostion(world, position);
        
        if (tile != null && tile instanceof IMultiBlockPart) {
            
            return ((IMultiBlockPart) tile).addMultiBlock(this);
        }
        return false;
    }
    
    public void deconstructBlock(Vector3i position) {
        
        TileEntity tile = UtilVector.getTileAtPostion(world, position);
        
        if (tile != null && tile instanceof IMultiBlockPart) {
            
            ((IMultiBlockPart) tile).removeMultiBlock(this);
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
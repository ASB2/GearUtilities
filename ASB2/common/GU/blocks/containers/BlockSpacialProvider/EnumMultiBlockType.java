package GU.blocks.containers.BlockSpacialProvider;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import ASB2.utils.UtilBlock;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlockMarker;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlockPart;
import GU.multiblock.MultiBlockChest;
import UC.math.vector.Vector3i;

public enum EnumMultiBlockType {
    
    STANDARD, CHEST, FURNACE, TANK;
    
    public static final int MAX_DISTANCE = 9;
    
    public boolean createMultiBlock(World world, Vector3i spacialPosition) {
        
        Vector3i size = new Vector3i();
        Vector3i corner = spacialPosition.clone();
        
        int xPos = 0, xNeg = 0, yPos = 0, yNeg = 0, zPos = 0, zNeg = 0;
        
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
            
            for (int distance = 1; distance <= MAX_DISTANCE; distance++) {
                
                int x = spacialPosition.getX() + (direction.offsetX * distance), y = spacialPosition.getY() + (direction.offsetY * distance), z = spacialPosition.getZ() + (direction.offsetZ * distance);
                
                TileEntity tile = world.getTileEntity(x, y, z);
                Block block = world.getBlock(x, y, z);
                
                if (tile != null && tile instanceof IMultiBlockPart) {
                    
                    continue;
                }
                else if (tile != null && tile instanceof IMultiBlockMarker) {
                    
                    if (direction.offsetX > 0) {
                        
                        xPos += distance;
                    }
                    if (direction.offsetX < 0) {
                        
                        xNeg += distance;
                    }
                    if (direction.offsetY > 0) {
                        
                        yPos += distance;
                    }
                    if (direction.offsetY < 0) {
                        
                        yNeg += distance;
                    }
                    if (direction.offsetZ > 0) {
                        
                        zPos += distance;
                    }
                    if (direction.offsetZ < 0) {
                        
                        zNeg += distance;
                    }
                    // size.move(Math.abs(direction.offsetX * distance),
                    // Math.abs(direction.offsetY * distance),
                    // Math.abs(direction.offsetZ * distance));
                    // markers[direction.ordinal()] = spacialPosition.add(x,
                    // y, z);
                    break;
                    
                }
                else if (block != null) {
                    
                    if (block instanceof IMultiBlockMarker) {
                        
                        if (direction.offsetX > 0) {
                            
                            xPos += distance;
                        }
                        if (direction.offsetX < 0) {
                            
                            xNeg += distance;
                        }
                        if (direction.offsetY > 0) {
                            
                            yPos += distance;
                        }
                        if (direction.offsetY < 0) {
                            
                            yNeg += distance;
                        }
                        if (direction.offsetZ > 0) {
                            
                            zPos += distance;
                        }
                        if (direction.offsetZ < 0) {
                            
                            zNeg += distance;
                        }
                        
                        // size.move(Math.abs(direction.offsetX * distance),
                        // Math.abs(direction.offsetY * distance),
                        // Math.abs(direction.offsetZ * distance));
                        // markers[direction.ordinal()] = new Vector3i(x, y, z);
                        break;
                    }
                    else if (!UtilBlock.isBlockAir(world, x, y, z)) {
                        
                        break;
                    }
                }
            }
        }
        
        size.move(xPos + xNeg, yPos + yNeg, zPos + zNeg);
        
        if (size.getX() != 0 || size.getY() != 0 || size.getZ() != 0) {
            
            corner.move(xPos, yPos, zPos);
            MultiBlockChest chest = new MultiBlockChest(world, corner, size, spacialPosition);
            
            return chest.startCreation();
            
            // for (int x = 0; x <= size.getX(); x++) {
            //
            // for (int y = 0; y <= size.getY(); y++) {
            //
            // for (int z = 0; z <= size.getZ(); z++) {
            //
            // Vector3i vec = corner.subtract(x, y, z);
            // world.setBlock(vec.getX(), vec.getY(), vec.getZ(), Blocks.stone);
            // }
            // }
            // }
            // return true;
        }
        return false;
    }
}

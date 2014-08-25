package GU.multiblock;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import ASB2.utils.UtilBlock;
import GU.BlockRegistry;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlockMarker;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlockPart;
import UC.math.vector.Vector3i;

public enum EnumMultiBlockType {
    
    STANDARD, CHEST, FURNACE, TANK, FLAME, GRINDER;
    
    public static final int MAX_DISTANCE = 16;
    
    public MultiBlockBase createWithData(World world, Vector3i positionRelativeTo, Vector3i size, Vector3i updater) {
        
        switch (this) {
        
            case CHEST:
                return new MultiBlockChest(world, positionRelativeTo, size, updater);
            case FURNACE:
                return new MultiBlockFurnace(world, positionRelativeTo, size, updater);
            case TANK:
                return new MultiBlockTank(world, positionRelativeTo, size, updater);
            case FLAME:
                return new MultiBlockFlame(world, positionRelativeTo, size, updater);
            case GRINDER:
                return new MultiBlockGrinder(world, positionRelativeTo, size, updater);
            default:
        }
        return null;
    }
    
    public MultiBlockBase createFromLoad(World worldObj) {
        
        switch (this.ordinal()) {
        
            case 1:
                return new MultiBlockChest(worldObj);
            case 2:
                return new MultiBlockFurnace(worldObj);
            case 3:
                return new MultiBlockTank(worldObj);
            case 4:
                return new MultiBlockFlame(worldObj);
            case 5:
                return new MultiBlockGrinder(worldObj);
        }
        return null;
    }
    
    public boolean createMultiBlock(World world, Vector3i spacialPosition) {
        
        if (this.ordinal() == 0) {
            
            return false;
        }
        
        Vector3i size = new Vector3i();
        Vector3i corner = spacialPosition.clone();
        
        int xPos = 0, xNeg = 0, yPos = 0, yNeg = 0, zPos = 0, zNeg = 0;
        
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
            
            for (int distance = 1; distance <= MAX_DISTANCE; distance++) {
                
                int x = spacialPosition.getX() + (direction.offsetX * distance), y = spacialPosition.getY() + (direction.offsetY * distance), z = spacialPosition.getZ() + (direction.offsetZ * distance);
                
                TileEntity tile = world.getTileEntity(x, y, z);
                Block block = world.getBlock(x, y, z);
                
                if (block == BlockRegistry.SPATIAL_PROVIDER) {
                    
                    int meta = world.getBlockMetadata(x, y, z);
                    
                    if (meta != this.ordinal() && meta != STANDARD.ordinal()) {
                        
                        break;
                    }
                }
                
                if (tile != null) {
                    
                    if (tile instanceof IMultiBlockPart) {
                        
                    }
                    else if (tile instanceof IMultiBlockMarker) {
                        
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
                }
                if (block != null) {
                    
                    // int meta = world.getBlockMetadata(spacialPosition.getX(),
                    // spacialPosition.getY(), spacialPosition.getZ());
                    //
                    // if (block == BlockRegistry.SPACIAL_PROVIDER && meta != 0
                    // && meta != this.ordinal()) {
                    //
                    // break;
                    // }
                    
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
            
            return this.createWithData(world, corner, size, spacialPosition).startCreation();
        }
        return false;
    }
}

package GU.multiblock.construction;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import ASB2.utils.UtilBlock;
import ASB2.utils.UtilVector;
import GU.api.color.AbstractColorable.IColorableTile;
import GU.api.multiblock.MultiBlockAbstract.EnumMultiBlockPartPosition;
import GU.api.multiblock.MultiBlockAbstract.IFluidInterface;
import GU.api.multiblock.MultiBlockAbstract.IItemInterface;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlockPart;
import GU.multiblock.MultiBlockBase;
import UC.color.Color4i;
import UC.math.vector.Vector3i;

public abstract class ConstructionManager {
    
    protected World world;
    
    protected boolean isConstructing = false, isConstructionFinished = false, isDeconstructing = false, finishedDeconstructing = false;
    
    protected Map<Vector3i, IItemInterface> itemInterfaceList = new HashMap<Vector3i, IItemInterface>();
    protected Map<Vector3i, IFluidInterface> fluidInterfaceList = new HashMap<Vector3i, IFluidInterface>();
    
    protected MultiBlockBase multiBlockStructure;
    
    public ConstructionManager(World world, MultiBlockBase multiBlock) {
        
        this.world = world;
        this.multiBlockStructure = multiBlock;
    }
    
    public void startConstruction() {
        
        isConstructing = true;
        isConstructionFinished = false;
        isDeconstructing = false;
        finishedDeconstructing = false;
    }
    
    public void update() {
        
        if (isConstructing && !isConstructionFinished) {
            
            updateDuringConstruction();
        } else if (isDeconstructing && !finishedDeconstructing) {
            
            updateDuringDestruction();
        }
    }
    
    public boolean isFinishedConstructing() {
        
        return isConstructionFinished;
    }
    
    public void startDestruction() {
        
        isConstructing = false;
        isConstructionFinished = false;
        isDeconstructing = true;
        finishedDeconstructing = false;
    }
    
    public boolean isFinishedDeconstruction() {
        
        return finishedDeconstructing;
    }
    
    public void finishConstruction() {
        
        isConstructing = false;
        isConstructionFinished = true;
        isDeconstructing = false;
    }
    
    public void finishDeconstruction() {
        
        isDeconstructing = false;
        finishedDeconstructing = true;
    }
    
    public void stopConstruction() {
        
        isConstructing = false;
    }
    
    public void stopDeconstruction() {
        
        isDeconstructing = false;
    }
    
    protected abstract void updateDuringConstruction();
    
    protected abstract void updateDuringDestruction();
    
    public abstract boolean checkAfterLoad();
    
    /**
     * 
     * Checks blocks of the structure and returns false if the structure si not
     * complete
     */
    public abstract boolean checkStructure();
    
    public boolean addMultiBlockToBlock(Vector3i position, EnumMultiBlockPartPosition part) {
        
        TileEntity tile = UtilVector.getTileAtPostion(world, position);
        
        if (tile != null && tile instanceof IMultiBlockPart) {
            
            if (((IMultiBlockPart) tile).isPositionValid(part)) {
                
                if (tile instanceof IItemInterface) {
                    
                    itemInterfaceList.put(position, (IItemInterface) tile);
                }
                if (tile instanceof IFluidInterface) {
                    
                    fluidInterfaceList.put(position, (IFluidInterface) tile);
                }
                return ((IMultiBlockPart) tile).addMultiBlock(multiBlockStructure);
            }
        }
        return false;
    }
    
    public boolean addForceMultiBlockToBlock(Vector3i position) {
        
        TileEntity tile = UtilVector.getTileAtPostion(world, position);
        
        if (tile != null && tile instanceof IMultiBlockPart) {
            
            if (tile instanceof IItemInterface) {
                
                itemInterfaceList.put(position, (IItemInterface) tile);
            }
            if (tile instanceof IFluidInterface) {
                
                fluidInterfaceList.put(position, (IFluidInterface) tile);
            }
            return ((IMultiBlockPart) tile).addMultiBlock(multiBlockStructure);
        }
        return false;
    }
    
    /**
     * 
     * Places a block regardless of what was there before.
     */
    public void forcePlaceBlock(Vector3i position, Block block, int meta) {
        
        world.setBlock(position.getX(), position.getY(), position.getZ(), block, meta, 3);
    }
    
    /**
     * 
     * Places a block only if it meets certain speficications
     */
    public boolean placeBlock(Vector3i position, Block block) {
        
        return placeBlock(position, block, 0);
    }
    
    /**
     * 
     * Places a block only if it meets certain speficications
     */
    public boolean placeBlock(Vector3i position, Block block, int meta) {
        
        if (canPlaceBlock(position, block)) {
            
            UtilBlock.breakBlock(world, position.getX(), position.getY(), position.getZ());
            world.setBlock(position.getX(), position.getY(), position.getZ(), block, meta, 3);
            return true;
        }
        return false;
    }
    
    public boolean placeBlockAndColor(Vector3i position, Block block, int meta, Color4i color) {
        
        if (placeBlock(position, block, meta)) {
            
            this.setBlockColor(position, color);
            return true;
        }
        return false;
    }
    
    public boolean placeBlockAndColor(Vector3i position, Block block, Color4i color) {
        
        return placeBlockAndColor(position, block, 0, color);
    }
    
    public boolean canPlaceBlock(Vector3i position, Block block) {
        
        TileEntity tile = UtilVector.getTileAtPostion(world, position);
        return ((tile == null || !(tile instanceof IMultiBlockPart)));
    }
    
    public void setBlockColor(Vector3i position, Color4i color) {
        
        setBlockColor(position, color, EnumMultiBlockPartPosition.INNER);
    }
    
    public void setBlockColor(Vector3i position, Color4i color, EnumMultiBlockPartPosition part) {
        
        TileEntity tile = UtilVector.getTileAtPostion(world, position);
        
        if (tile instanceof IColorableTile) {
            
            ((IColorableTile) tile).setColor(multiBlockStructure.getDefaultBlockColor(), ForgeDirection.UNKNOWN);
        }
    }
    
    public void deconstructBlock(Vector3i position) {
        
        TileEntity tile = UtilVector.getTileAtPostion(world, position);
        
        if (tile != null && tile instanceof IMultiBlockPart) {
            
            ((IMultiBlockPart) tile).removeMultiBlock(multiBlockStructure);
        }
    }
    
    public boolean checkBlock(Vector3i position) {
        
        TileEntity tile = UtilVector.getTileAtPostion(world, position);
        
        if (tile != null && tile instanceof IMultiBlockPart) {
            
            return true;
        }
        return false;
    }
    
    public Map<Vector3i, IItemInterface> getItemInterfaceList() {
        return itemInterfaceList;
    }
    
    public Map<Vector3i, IFluidInterface> getFluidInterfaceList() {
        return fluidInterfaceList;
    }
}

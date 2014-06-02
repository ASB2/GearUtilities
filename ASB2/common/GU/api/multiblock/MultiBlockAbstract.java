package GU.api.multiblock;

import java.util.List;

import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;
import UC.math.vector.Vector3i;

public class MultiBlockAbstract {
    
    private MultiBlockAbstract() {
        
    }
    
    public static interface IMultiBlock {
        
        void onBlockBreak(int x, int y, int z);
    }
    
    public static interface IMultiBlockPart {
        
        boolean addMultiBlock(IMultiBlock multiBlock);
        
        void removeMultiBlock(IMultiBlock multiBlock);
        
        List<IMultiBlock> getMultiBlocks();
        
        boolean isPositionValid(EnumMultiBlockPartPosition position);
    }
    
    public static interface IMultiBlockCore extends IMultiBlockPart {
        
    }
    
    public static interface IMultiBlockMarker {
        
        boolean isValid(World world, int x, int y, int z);
    }
    
    public static enum EnumMultiBlockPartPosition {
        
        EDGE, CORNER, FACE, INNER;
    }
    
    public static interface IFluidMultiBlock extends IMultiBlock {
        
        int fill(Vector3i tilePosition, ForgeDirection from, FluidStack resource, boolean doFill);
        
        FluidStack drain(Vector3i tilePosition, ForgeDirection from, FluidStack resource, boolean doDrain);
        
        FluidStack drain(Vector3i tilePosition, ForgeDirection from, int maxDrain, boolean doDrain);
        
        boolean canFill(Vector3i tilePosition, ForgeDirection from, Fluid fluid);
        
        boolean canDrain(Vector3i tilePosition, ForgeDirection from, Fluid fluid);
        
        FluidTankInfo[] getTankInfo(Vector3i tilePosition, ForgeDirection from);
        
        IFluidTank getFluidTank(Vector3i tilePosition);
    }
}

package GU.blocks.containers.BlockBasicElemental;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;
import ASB2.utils.UtilBlock;
import ASB2.utils.UtilDirection;
import ASB2.utils.UtilFluid;
import ASB2.wait.Wait;
import GU.blocks.containers.TileBase;

public class TileBasicElemental extends TileBase {
    
    public TileBasicElemental() {
        
        waitTimer = new Wait(this, 20, 0);
    }
    
    @Override
    public void updateEntity() {
        
        switch (worldObj.getBlockMetadata(xCoord, yCoord, zCoord)) {
        
            case BlockBasicElemental.FIRE_CUBE: {
                
                if (worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {
                    
                    UtilBlock.placeBlockInAir(worldObj, xCoord, yCoord + 1, zCoord, Block.fire.blockID, 0);
                }
                break;
            }
            case BlockBasicElemental.WATER_CUBE: {
                
                for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
                    
                    TileEntity tile = UtilDirection.translateDirectionToTile(this, worldObj, direction);
                    
                    if (tile != null && tile instanceof IFluidHandler) {
                        
                        UtilFluid.addFluidToTank((IFluidHandler) tile, direction.getOpposite(), new FluidStack(FluidRegistry.WATER, 100), true);
                    }
                }
                break;
            }
        }
    }
}

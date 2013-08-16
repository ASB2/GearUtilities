package GU.blocks.containers.BlockFluidProvider;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;
import GU.blocks.containers.TileBase;
import GU.utils.UtilDirection;
import GU.utils.UtilFluid;

public class TileFluidProvider extends TileBase {

    public FluidStack fluidStack;

    public TileFluidProvider() {

    }

    public void updateEntity() {

        if(fluidStack != null) {

            for(ForgeDirection direction: ForgeDirection.values()) {

                TileEntity tile = UtilDirection.translateDirectionToTile(this, worldObj, direction);

                if(tile != null) {

                    if(tile instanceof IFluidHandler) {

                        IFluidHandler fTile = (IFluidHandler)tile;

                        if(!worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {

                            UtilFluid.addFluidToTank(fTile, direction, fluidStack);
                        }
                        else {

                            UtilFluid.removeFluidFromTank(fTile, direction, fluidStack);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);

        if(FluidRegistry.getFluid(tag.getString("fluidName")) != null) {

            fluidStack = new FluidStack(FluidRegistry.getFluid(tag.getString("fluidName")), 1000);
        }    
    }

    @Override
    public void writeToNBT(NBTTagCompound tag){
        super.writeToNBT(tag); 

        if(fluidStack != null) {
            
            if(fluidStack.getFluid() != null) {

                tag.setString("fluidName", fluidStack.getFluid().getUnlocalizedName());            
            }
        }
    }
}

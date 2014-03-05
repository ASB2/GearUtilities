package GU.blocks.containers.BlockGlassPipe;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.IFluidHandler;
import ASB2.utils.UtilDirection;
import ASB2.utils.UtilFluid;
import ASB2.wait.Wait;
import GU.blocks.containers.TileFluidBase;

public class TileGlassPipe extends TileFluidBase {

    boolean[] importing = new boolean[7];

    public TileGlassPipe() {

        waitTimer = new Wait(20 * 2, this, 0);
        fluidTank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME);
    }

    @Override
    public void updateEntity() {

        waitTimer.update();
    }

    @Override
    public void trigger(int id) {

        if(!worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {

            int amount = 0;

            for(ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {

                TileEntity tile = UtilDirection.translateDirectionToTile(this, worldObj, direction);

                if(tile != null && tile instanceof IFluidHandler) {

                    amount++;
                }
            }

            if(this.fluidTank.getFluidAmount() >= amount * 125) {

                for(ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {

                    TileEntity tile = UtilDirection.translateDirectionToTile(this, worldObj, direction);

                    if(tile != null) {

                        if(tile instanceof TileGlassPipe) {

                            if(((TileGlassPipe) tile).fluidTank.getFluidAmount() < this.fluidTank.getFluidAmount()) {

                                UtilFluid.moveFluid(this, direction, (IFluidHandler) tile, direction.getOpposite(), 125, true);
                            }
                        }
                        else if(tile instanceof IFluidHandler) {

                            UtilFluid.moveFluid(this, direction, (IFluidHandler) tile, direction.getOpposite(), 125, true);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void triggerBlock(World world, boolean isSneaking, ItemStack itemStack, int x, int y, int z, int side) {

        if(!isSneaking) {

            if(importing[side]) {

                importing[side] = false;
                updateClients();
                return;
            }
            else {

                importing[side] = true;
                updateClients();
                return;
            }
        }
        else {

            side = ForgeDirection.getOrientation(side).getOpposite().ordinal();

            if(importing[side]) {

                importing[side] = false;
                updateClients();
                return;
            }
            else {

                importing[side] = true;
                updateClients();
                return;
            }
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);

        for(int i = 0; i < importing.length; i++) {

            importing[i] = tag.getBoolean("importing " + i);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);

        for(int i = 0; i < importing.length; i++) {

            tag.setBoolean("importing " + i, importing[i]);
        }
    }
}
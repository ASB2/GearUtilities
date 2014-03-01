package GU.multiblock;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import ASB2.utils.UtilEntity;
import ASB2.vector.Cuboid;
import ASB2.vector.Vector3;
import GU.blocks.containers.BlockSpacialProvider.TileFluidSpacialProvider;
import GU.info.Variables;

public class MultiBlockTank extends MultiBlockBase implements IFluidHandler {

    public FluidTank fluidTank = new FluidTank(1000);

    public MultiBlockTank(World world) {
        super(world);
    }

    public MultiBlockTank(World world, Cuboid size) {
        super(world, size);
        init();
    }

    @Override
    protected void init() {

        isValid = true;
        centerBlocks = this.getSize().squareShrink(2, 2, 2);

        if (Variables.COUNT_JUST_MULTI_TANK_AIR_BLOCKS) {

            fluidTank.setCapacity((centerBlocks.getXSize() + 1) * (centerBlocks.getYSize() + 1) * (centerBlocks.getZSize() + 1) * 16 * FluidContainerRegistry.BUCKET_VOLUME);
        } else {

            fluidTank.setCapacity(((size.getXSize() + 1) * (size.getYSize() + 1) * (size.getZSize() + 1)) * 16000);
        }
    }

    @Override
    public void invalidate() {
        super.invalidate();
    }

    public boolean isValidCore(Vector3 vector, TileEntity tile) {

        return tile.getClass() == TileFluidSpacialProvider.class;
    }

    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {

        return fluidTank.fill(resource, doFill);
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {

        if (fluidTank != null) {

            if (fluid != null) {

                if (fluidTank.getFluid() != null) {

                    if (this.fluidTank.getFluid().isFluidEqual(new FluidStack(fluid, 0))) {

                        return true;
                    }
                } else {

                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {

        if (resource == null || !resource.isFluidEqual(fluidTank.getFluid())) {

            return null;
        }

        return fluidTank.drain(resource.amount, doDrain);
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {

        return fluidTank.drain(maxDrain, doDrain);
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {

        if (this.fluidTank.getFluid() != null) {

            if (fluidTank.getFluidAmount() > 0) {

                if (this.fluidTank.getFluid().isFluidEqual(new FluidStack(fluid, 1))) {

                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {

        return new FluidTankInfo[] { fluidTank.getInfo() };
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ, boolean isAlone) {

        if (this.isValid) {
            // UtilEntity.sendChatToPlayer(player, "Fluid: " +
            // this.fluidTank.getFluid() != null ?
            // this.fluidTank.getFluid().getFluid() != null ?
            // this.fluidTank.getFluid().getFluid().getName() : "null" : "null");

            UtilEntity.sendChatToPlayer(player, "Fluid Amount: " + this.fluidTank.getFluidAmount() + " / " + fluidTank.getCapacity());
        } else {
            UtilEntity.sendChatToPlayer(player, "Fix me idiot");
        }
        return false;
    }

    @Override
    public NBTTagCompound save(NBTTagCompound tag) {

        fluidTank.writeToNBT(tag);
        return super.save(tag);
    }

    @Override
    public void load(NBTTagCompound tag) {

        fluidTank.readFromNBT(tag);
        super.load(tag);
    }
}

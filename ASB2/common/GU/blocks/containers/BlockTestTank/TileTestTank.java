package GU.blocks.containers.BlockTestTank;

import java.util.ArrayList;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.IFluidTank;
import GU.api.wait.Wait;
import GU.blocks.containers.TileBase;

public class TileTestTank extends TileBase {

    private int maxLiquid = FluidContainerRegistry.BUCKET_VOLUME * 10;
    public int[] fluidFromFace;
    public boolean isLiquidGoingDown;
    public int[] liquidCommingTop;

    public TileTestTank() {

        fluidFromFace = new int[4];
        isLiquidGoingDown = false;
        liquidCommingTop = new int[4];

        fluidTank = new FluidTank(maxLiquid);
        waitTimer = new Wait(10, this, 0);
    }

    @Override
    public void updateEntity() {

        if(!worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {
            
            if (fluidTank.getFluid() != null && getTankBelow(this) != null) {
                moveFluidBelow();
            }
            // Have liquid flow into adjacent tanks if any.
            if (fluidTank.getFluid() != null && (getTankBelow(this) == null || getTankBelow(this).isFull())) {

                moveFluidToAdjacent();
            }
        }
        worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
    }

    public boolean isFull() {

        return fluidTank.getCapacity() == fluidTank.getFluidAmount();
    }

    public TileTestTank getBottomTank() {

        TileTestTank lastTank = this;

        while (true) {

            TileTestTank below = getTankBelow(lastTank);

            if (below != null) {

                lastTank = below;
            }
            else {

                break;
            }
        }

        return lastTank;
    }

    public TileTestTank getTopTank() {

        TileTestTank lastTank = this;

        while (true) {

            TileTestTank above = getTankAbove(lastTank);
            if (above != null) {

                lastTank = above;
            }
            else {

                break;
            }
        }

        return lastTank;
    }

    public static TileTestTank getTankBelow(TileTestTank tile) {

        TileEntity below = tile.worldObj.getBlockTileEntity(tile.xCoord, tile.yCoord - 1, tile.zCoord);

        if (below instanceof TileTestTank) {

            return (TileTestTank) below;
        }
        else {

            return null;
        }
    }

    public static TileTestTank getTankAbove(TileTestTank tile) {
        TileEntity above = tile.worldObj.getBlockTileEntity(tile.xCoord, tile.yCoord + 1, tile.zCoord);

        if (above instanceof TileTestTank) {

            return (TileTestTank) above;
        }
        else {

            return null;
        }
    }

    public void moveFluidBelow() {

        TileTestTank below = getTankBelow(this);

        if (below == null) {

            return;
        }

        int used = below.fluidTank.fill(fluidTank.getFluid(), true);

        if (used > 0) {

            fluidTank.drain(used, true);
            below.liquidCommingTop = liquidCommingTop.clone();

            if (fluidFromFace[0] != 0)
                below.liquidCommingTop[0] = fluidFromFace[0];

            if (fluidFromFace[1] != 0)
                below.liquidCommingTop[1] = fluidFromFace[1];

            if (fluidFromFace[2] != 0)
                below.liquidCommingTop[2] = fluidFromFace[2];

            if (fluidFromFace[3] != 0)
                below.liquidCommingTop[3] = fluidFromFace[3];
        }
    }

    public static ArrayList<TileTestTank> getAdjacentTanks(TileTestTank tile) {

        ArrayList<TileTestTank> adjacents = new ArrayList<TileTestTank>();
        // fillWithAdjacentTanks(tile, adjacents, tile.tank.getFluid());
        FluidStack fluid = tile.fluidTank.getFluid();

        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {

            if (direction == ForgeDirection.DOWN || direction == ForgeDirection.UP)
                continue;

            TileEntity other = tile.worldObj.getBlockTileEntity(tile.xCoord + direction.offsetX, tile.yCoord,
                    tile.zCoord + direction.offsetZ);

            if (other instanceof TileTestTank
                    && (((TileTestTank) other).fluidTank.getFluidAmount() == 0
                    || ((TileTestTank) other).fluidTank.getFluid().isFluidEqual(fluid) || fluid == null)) {
                adjacents.add((TileTestTank) other);
            }
        }
        return adjacents;
    }

    /**
     * Recursive function, must only be called by getAdjacentTanks(TileTank
     * tile)
     */
    private static void fillWithAdjacentTanks(TileTestTank tile, ArrayList<TileTestTank> result, FluidStack fluid) {

        result.add(tile);

        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {

            if (direction == ForgeDirection.DOWN || direction == ForgeDirection.UP)
                continue;

            TileEntity other = tile.worldObj.getBlockTileEntity(tile.xCoord + direction.offsetX, tile.yCoord,
                    tile.zCoord + direction.offsetZ);

            if (other instanceof TileTestTank
                    && !result.contains(other)
                    && (((TileTestTank) other).fluidTank.getFluidAmount() == 0
                    || ((TileTestTank) other).fluidTank.getFluid().isFluidEqual(fluid) || fluid == null)) {

                if (fluid == null)
                    fluid = ((TileTestTank) other).fluidTank.getFluid();
                fillWithAdjacentTanks((TileTestTank) other, result, fluid);
            }
        }
    }

    public void moveFluidToAdjacent() {

        FluidStack fluid = fluidTank.getFluid();

        if (fluid == null)
            return;

        ArrayList<TileTestTank> adjacents = getAdjacentTanks(this);

        if (adjacents.size() <= 0)
            return;

        int totalAmount = fluid.amount;

        for (TileTestTank other : adjacents)
            totalAmount += other.fluidTank.getFluidAmount();

        int splitAmount = totalAmount / (adjacents.size() + 1);
        int balance = 0; // Prevent creation or destruction of fluid cause of
        // Euclidean division
        for (TileTestTank other : adjacents) {

            if (other.fluidTank.getFluidAmount() < splitAmount) {
                int filled = other.fluidTank.fill(new FluidStack(fluid, splitAmount - other.fluidTank.getFluidAmount()), true);
                balance += filled;
                other.fluidFromFace[other.getDirectionTo(this).ordinal() - 2] = filled;
            }

            else if (other.fluidTank.getFluidAmount() > splitAmount) {
                int drained = other.fluidTank.drain(other.fluidTank.getFluidAmount() - splitAmount, true).amount;
                balance -= drained;
                fluidFromFace[getDirectionTo(other).ordinal() - 2] = drained;
            }
            else
                continue;
        }

        if (balance > 0)
            fluidTank.drain(balance, true);
        else if (balance < 0)
            fluidTank.fill(new FluidStack(fluid, -balance), true);
        else
            return;
    }

    private ForgeDirection getDirectionTo(TileTestTank tile) {
        if (xCoord - tile.xCoord > 0)
            return ForgeDirection.WEST;
        if (xCoord - tile.xCoord < 0)
            return ForgeDirection.EAST;
        if (zCoord - tile.zCoord > 0)
            return ForgeDirection.NORTH;
        if (zCoord - tile.zCoord < 0)
            return ForgeDirection.SOUTH;
        return ForgeDirection.UNKNOWN;
    }

    public TileTestTank getTank(int blocksBelow) {

        TileEntity tile = worldObj.getBlockTileEntity(xCoord, yCoord - blocksBelow, zCoord);

        if(tile != null) {

            if(tile instanceof IFluidTank) {

                return (TileTestTank)tile;
            }
        }
        return null;
    }

    @Override
    public int fill(FluidStack resource, boolean doFill) {

        if (resource.isFluidEqual(fluidTank.getFluid()) || fluidTank.getFluid() == null) {

            worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
            return fluidTank.fill(resource, doFill);
        }
        return 0;
    }

    @Override
    public FluidStack drain(int maxDrain, boolean doDrain) {

        worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
        return fluidTank.drain(maxDrain, doDrain);
    }

    public void trigger(int id) {
    }
}

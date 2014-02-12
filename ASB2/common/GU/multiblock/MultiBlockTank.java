package GU.multiblock;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
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
import ASB2.utils.UtilBlock;
import ASB2.utils.UtilEntity;
import ASB2.vector.Cuboid;
import ASB2.vector.Vector3;
import GU.BlockRegistry;
import GU.api.multiblock.IMultiBlockCore;
import GU.api.multiblock.IMultiBlockInterface;
import GU.api.multiblock.IMultiBlockPart;
import GU.api.multiblock.ISpecialTileMultiBlock;
import GU.api.multiblock.MultiBlockBase;
import GU.blocks.containers.BlockSpacialProvider.TileFluidSpacialProvider;

public class MultiBlockTank extends MultiBlockBase implements IFluidHandler {

    public FluidTank fluidTank = new FluidTank(0);
    public Cuboid airBlocks;

    public MultiBlockTank(World world) {
        super(world);
    }

    public MultiBlockTank(World world, Cuboid size) {
        super(world, size);
        fluidTank.setCapacity((size.getXSize() + 1) * (size.getYSize() + 1) * (size.getZSize() + 1) * 16 * FluidContainerRegistry.BUCKET_VOLUME);

        airBlocks = this.getSize().squareShrink(2, 2, 2);
    }

    public boolean isStructureValid() {

        if (this.getSize().getXSize() < 2 || this.getSize().getYSize() < 2 || this.getSize().getZSize() < 2) {

            return false;
        }

        for (Vector3 vector : airBlocks.getComposingBlock()) {

            if (!UtilBlock.isBlockAir(this.getWorldObj(), vector.intX(), vector.intY(), vector.intZ())) {

                return false;
            }
        }
        return super.isStructureValid();
    }

    public boolean checkArea(Vector3 vector) {

        TileEntity tile = vector.getTileEntity(this.getWorldObj());

        if (tile != null) {

            if (tile instanceof IMultiBlockPart) {

                return true;
            }
        } else {

            Block block = vector.getBlock(this.getWorldObj());

            if (block != null && block instanceof ISpecialTileMultiBlock) {

                return true;
            } else if (block == null || vector.getBlockMaterial(getWorldObj()) == Material.air) {

                return airBlocks.contains(vector);
            }
        }
        return false;
    }

    public boolean createMultiblock(Vector3 vector) {

        TileEntity tile = vector.getTileEntity(this.getWorldObj());

        if (tile == null) {

            Block block = vector.getBlock(this.getWorldObj());

            if (block != null && block instanceof ISpecialTileMultiBlock) {

                tile = ((ISpecialTileMultiBlock) block).getBlockTileEntity(this.getWorldObj(), vector.intX(), vector.intY(), vector.intZ());

            } else if (block == null || vector.getBlockMaterial(getWorldObj()) == Material.air) {

                if (airBlocks.contains(vector)) {

                    vector.setBlock(this.getWorldObj(), BlockRegistry.BlockStructureAir.blockID);
                    return true;
                }
            } else if (tile == null) {

                this.size.iterate(this, 2);
                return false;
            }
        }

        if (tile instanceof IMultiBlockPart && !((IMultiBlockPart) tile).addMultiBlock(this)) {

            this.size.iterate(this, 2);
            return false;
        }
        if (tile instanceof IMultiBlockInterface) {

            switch (((IMultiBlockInterface) tile).getInterfaceType()) {

                case FLUID: {

                    fluidMultiBlockInterfaces.add(vector);
                    break;
                }
                case ITEM: {

                    itemMultiBlockInterfaces.add(vector);
                    break;
                }
                case POWER: {

                    powerMultiBlockInterfaces.add(vector);
                    break;
                }
                default:
                    break;

            }

        }

        if (tile instanceof IMultiBlockCore) {

            multiBlockCores.add(vector);
        }
        return true;
    }

    @Override
    public void postLoad() {

        fluidTank.setCapacity((size.getXSize() + 1) * (size.getYSize() + 1) * (size.getZSize() + 1) * 16000);
        airBlocks = this.getSize().squareShrink(2, 2, 2);
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
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {

        // UtilEntity.sendChatToPlayer(player, "Fluid: " +
        // this.fluidTank.getFluid() != null ?
        // this.fluidTank.getFluid().getFluid() != null ?
        // this.fluidTank.getFluid().getFluid().getName() : "null" : "null");
        UtilEntity.sendChatToPlayer(player, this.size.toString());
        UtilEntity.sendChatToPlayer(player, "Fluid Amount: " + this.fluidTank.getFluidAmount() + " / " + fluidTank.getCapacity());
        return true;
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

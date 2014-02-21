package GU.multiblock;

import java.util.ArrayList;

import net.minecraft.block.Block;
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
import GU.api.multiblock.ISpecialMultiBlockOpaque;
import GU.api.multiblock.ISpecialTileMultiBlock;
import GU.api.multiblock.MultiBlockBase;
import GU.blocks.containers.BlockSpacialProvider.TileFluidSpacialProvider;
import GU.blocks.containers.BlockStructureCube.TileReplacementStructureCube;
import GU.info.Variables;

public class MultiBlockTank extends MultiBlockBase implements IFluidHandler {

    public FluidTank fluidTank = new FluidTank(1000);
    public Cuboid airBlocks;
    public ArrayList<Vector3> placeHolderBlocks = new ArrayList<Vector3>();

    public MultiBlockTank(World world) {
        super(world);
    }

    public MultiBlockTank(World world, Cuboid size) {
        super(world, size);
        init();
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

        for (Vector3 vector : size.getEdges()) {

            Block block = vector.getBlock(getWorldObj());

            if (block == null || (!block.isOpaqueCube() && !(block instanceof ISpecialMultiBlockOpaque && ((ISpecialMultiBlockOpaque) block).isTrueOpaqueCube(getWorldObj(), vector.intX(), vector.intY(), vector.intZ())))) {

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
            } else if (block == null || block.isAirBlock(getWorldObj(), vector.intX(), vector.intY(), vector.intZ())) {

                if (airBlocks.contains(vector)) {

                    return true;
                }
            } else {

                if (Variables.CAN_USE_NON_STRUCURE_TANK_BLOCKS) {

                    if (!block.hasTileEntity(vector.getBlockMetadata(getWorldObj())) && block != null) {

                        if (size.getEdges().contains(vector)) {

                            if (block.isOpaqueCube() || (block instanceof ISpecialMultiBlockOpaque && ((ISpecialMultiBlockOpaque) block).isTrueOpaqueCube(getWorldObj(), vector.intX(), vector.intY(), vector.intZ()))) {

                                return true;
                            }
                        } else {

                            return true;
                        }
                    }
                }
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

            } else if (block == null || block.isAirBlock(getWorldObj(), vector.intX(), vector.intY(), vector.intZ())) {

                if (airBlocks.contains(vector)) {

                    vector.setBlock(this.getWorldObj(), BlockRegistry.BlockStructureAir.blockID);
                    return true;
                }
            } else if (tile == null) {

                if (Variables.CAN_USE_NON_STRUCURE_TANK_BLOCKS) {

                    int metadata = vector.getBlockMetadata(getWorldObj());

                    if (!block.hasTileEntity(metadata) && block != null) {

                        if (size.getEdges().contains(vector)) {

                            if (!block.isOpaqueCube() && !(block instanceof ISpecialMultiBlockOpaque && ((ISpecialMultiBlockOpaque) block).isTrueOpaqueCube(getWorldObj(), vector.intX(), vector.intY(), vector.intZ()))) {

                                return false;
                            }
                        }
                    }

                    vector.setBlock(getWorldObj(), BlockRegistry.BlockReplacementStructureCube.blockID, vector.getBlockMetadata(getWorldObj()));
                    tile = vector.getTileEntity(this.getWorldObj());

                    TileReplacementStructureCube castedTile = (TileReplacementStructureCube) tile;

                    castedTile.setSavedID(block.blockID);
                    castedTile.setSavedMetadata(metadata);
                    placeHolderBlocks.add(vector);
                }
            }
        }

        if (tile instanceof IMultiBlockPart && !((IMultiBlockPart) tile).addMultiBlock(this)) {

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
    protected void init() {
        isValid = true;
        airBlocks = this.getSize().squareShrink(2, 2, 2);

        if (Variables.COUNT_JUST_TANK_AIR_BLOCKS) {

            fluidTank.setCapacity((airBlocks.getXSize() + 1) * (airBlocks.getYSize() + 1) * (airBlocks.getZSize() + 1) * 16 * FluidContainerRegistry.BUCKET_VOLUME);
        } else {

            fluidTank.setCapacity(((size.getXSize() + 1) * (size.getYSize() + 1) * (size.getZSize() + 1)) * 16000);
        }
    }

    @Override
    public void invalidate() {

        // for (Vector3 vector : this.placeHolderBlocks) {
        //
        // TileEntity tile = vector.getTileEntity(this.getWorldObj());
        //
        // if (tile != null && tile.getClass() == TileReplacementStructureCube.class) {
        //
        // TileReplacementStructureCube replace = (TileReplacementStructureCube) tile;
        //
        // vector.setBlock(this.getWorldObj(), replace.getSavedID(), replace.getSavedMetadata());
        // break;
        // }
        // // this.getWorldObj().setBlockToAir(vector.intX(), vector.intY(), vector.intZ());
        // }
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

        if (this.isValid) {
            // UtilEntity.sendChatToPlayer(player, "Fluid: " +
            // this.fluidTank.getFluid() != null ?
            // this.fluidTank.getFluid().getFluid() != null ?
            // this.fluidTank.getFluid().getFluid().getName() : "null" : "null");
            UtilEntity.sendChatToPlayer(player, this.size.toString());
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

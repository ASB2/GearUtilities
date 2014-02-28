package GU.blocks.containers.BlockSpacialProvider;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import ASB2.vector.Vector3;
import GU.EnumState;
import GU.api.multiblock.IMultiBlock;
import GU.api.multiblock.IMultiBlockCore;
import GU.api.multiblock.ISpecialTileMultiBlock;
import GU.blocks.containers.TileMultiBase;

public class TileSpacialProvider extends TileMultiBase implements IMultiBlockCore {

    public static final int MAX_DISTANCE = 16;
    protected boolean hasBufferedCreateMultiBlock = false;
    protected NBTTagCompound bufferedTankData;

    public TileSpacialProvider() {

        sideState = new EnumState[] { EnumState.NONE, EnumState.NONE, EnumState.NONE, EnumState.NONE, EnumState.NONE, EnumState.NONE };
        useSidesRendering = true;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (hasBufferedCreateMultiBlock) {

            this.createMultiBlock(true);
            hasBufferedCreateMultiBlock = false;
            bufferedTankData = null;
        }
    }

    @SuppressWarnings("unused")
    public TileEntity getFarthestProvider(ForgeDirection direction) {

        TileEntity last = this;
        Vector3 myPosition = new Vector3(this);

        for (int i = 1; i <= MAX_DISTANCE; i++) {

            Vector3 testingPosition = myPosition.clone().add(direction, i);
            TileEntity tile = testingPosition.getTileEntity(worldObj);

            if (tile == null) {

                Block block = testingPosition.getBlock((IBlockAccess) worldObj);

                if (block != null && block instanceof ISpecialTileMultiBlock) {

                    TileEntity blockTile = ((ISpecialTileMultiBlock) block).getBlockTileEntity(worldObj, testingPosition.intX(), testingPosition.intY(), testingPosition.intZ());

                    if (blockTile != null) {

                        tile = blockTile;
                    }
                }
            }
            if (tile != null && tile != this && tile instanceof IMultiBlockCore) {

                // last = tile;
                return tile;
            }/*
              * else {
              * 
              * return last == this ? null : last; }
              */
        }
        return null;
    }

    public int getMultiBlockXChange() {

        Vector3 myPosition = new Vector3(this);

        int height = 0;

        if (sideState[ForgeDirection.EAST.ordinal()] != EnumState.NONE) {

            if (this.getFarthestProvider(ForgeDirection.EAST) != null) {

                height = myPosition.clone().subtract(new Vector3(this.getFarthestProvider(ForgeDirection.EAST))).intX();
            }
        }

        if (height == 0) {

            if (sideState[ForgeDirection.WEST.ordinal()] != EnumState.NONE) {

                if (this.getFarthestProvider(ForgeDirection.WEST) != null) {

                    height = myPosition.clone().subtract(new Vector3(this.getFarthestProvider(ForgeDirection.WEST))).intX();
                }
            }
        }
        return height * -1;
    }

    public int getMultiBlockYChange() {

        Vector3 myPosition = new Vector3(this);

        int height = 0;

        if (sideState[ForgeDirection.UP.ordinal()] != EnumState.NONE) {

            if (this.getFarthestProvider(ForgeDirection.UP) != null) {

                height = myPosition.clone().subtract(new Vector3(this.getFarthestProvider(ForgeDirection.UP))).intY();
            }
        }

        if (height == 0) {

            if (sideState[ForgeDirection.DOWN.ordinal()] != EnumState.NONE) {

                if (this.getFarthestProvider(ForgeDirection.DOWN) != null) {

                    height = myPosition.clone().subtract(new Vector3(this.getFarthestProvider(ForgeDirection.DOWN))).intY();
                }
            }
        }
        return height * -1;
    }

    public int getMultiBlockZChange() {

        Vector3 myPosition = new Vector3(this);

        int height = 0;

        if (sideState[ForgeDirection.SOUTH.ordinal()] != EnumState.NONE) {

            if (this.getFarthestProvider(ForgeDirection.SOUTH) != null) {

                height = myPosition.clone().subtract(new Vector3(this.getFarthestProvider(ForgeDirection.SOUTH))).intZ();
            }
        }

        if (height == 0) {

            if (sideState[ForgeDirection.NORTH.ordinal()] != EnumState.NONE) {

                if (this.getFarthestProvider(ForgeDirection.NORTH) != null) {

                    height = myPosition.clone().subtract(new Vector3(this.getFarthestProvider(ForgeDirection.NORTH))).intZ();
                }
            }
        }
        return height * -1;
    }

    @Override
    public void triggerBlock(World world, boolean isSneaking, ItemStack itemStack, int x, int y, int z, int side) {

        ForgeDirection direction = ForgeDirection.getOrientation(side);

        if (isSneaking) {
            direction = direction.getOpposite();
        }
        if (sideState[direction.ordinal()] == EnumState.OUTPUT) {
            sideState[direction.ordinal()] = EnumState.NONE;
        } else {
            sideState[direction.ordinal()] = EnumState.OUTPUT;
        }
        world.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
    }

    public boolean createMultiBlock() {

        return createMultiBlock(false);
    }

    public boolean createMultiBlock(boolean hasStructure) {

        return false;
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);

        if (!this.getComprisedMultiBlocks().isEmpty()) {

            for (IMultiBlock multi : this.getComprisedMultiBlocks()) {

                if (new Vector3(this).intEquals(multi.getSize().getCore())) {

                    tag.setCompoundTag("multiBlockSave", multi.save(new NBTTagCompound()));
                }
            }
            tag.setBoolean("isInMultiBlock", isInMultiBlock);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);

        hasBufferedCreateMultiBlock = tag.getBoolean("isInMultiBlock");

        if (hasBufferedCreateMultiBlock) {

            bufferedTankData = tag.getCompoundTag("multiBlockSave");
        }
    }
}

package GU.blocks.containers.BlockSpacialProvider;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import ASB2.utils.UtilEntity;
import ASB2.vector.Cuboid;
import ASB2.vector.Vector3;
import GU.EnumState;
import GU.MultiRegistry;
import GU.api.multiblock.IMultiBlock;
import GU.api.multiblock.IMultiBlockCore;
import GU.api.multiblock.ISpecialTileMultiBlock;
import GU.api.multiblock.MultiBlockRegistry;
import GU.blocks.containers.TileMultiBase;

public class TileSpacialProvider extends TileMultiBase implements IMultiBlockCore {

    public static final int MAX_DISTANCE = 16;
    public Set<IMultiBlock> iAmCoreOfMultiBlocks = new HashSet<IMultiBlock>();
    public static final String[] multiBlockNames = new String[] { "", MultiRegistry.MultiBlockTankName, MultiRegistry.MultiBlockFurnaceName, MultiRegistry.MultiBlockChestName, MultiRegistry.MultiBlockFlameSourceName };

    public TileSpacialProvider() {

        sideState = new EnumState[] { EnumState.NONE, EnumState.NONE, EnumState.NONE, EnumState.NONE, EnumState.NONE, EnumState.NONE };
        useSidesRendering = true;
    }

    @Override
    public void updateEntity() {

//        for (IMultiBlock multi : iAmCoreOfMultiBlocks) {
//
//            multi.update();
//        }
        super.updateEntity();
    }

    @Override
    public boolean addMultiBlock(IMultiBlock multiBlock) {

        if (multiBlock.getSize().getCore().intEquals(this)) {
            iAmCoreOfMultiBlocks.add(multiBlock);
        }
        return super.addMultiBlock(multiBlock);
    }

    @Override
    public void removeMultiBlock(IMultiBlock multiBlock) {
        iAmCoreOfMultiBlocks.remove(multiBlock);
        super.removeMultiBlock(multiBlock);
    }

    public TileEntity getNearesthestProvider(ForgeDirection direction) {

        @SuppressWarnings("unused")
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

            if (this.getNearesthestProvider(ForgeDirection.EAST) != null) {

                height = myPosition.clone().subtract(new Vector3(this.getNearesthestProvider(ForgeDirection.EAST))).intX();
            }
        }

        if (height == 0) {

            if (sideState[ForgeDirection.WEST.ordinal()] != EnumState.NONE) {

                if (this.getNearesthestProvider(ForgeDirection.WEST) != null) {

                    height = myPosition.clone().subtract(new Vector3(this.getNearesthestProvider(ForgeDirection.WEST))).intX();
                }
            }
        }
        return height * -1;
    }

    public int getMultiBlockYChange() {

        Vector3 myPosition = new Vector3(this);

        int height = 0;

        if (sideState[ForgeDirection.UP.ordinal()] != EnumState.NONE) {

            if (this.getNearesthestProvider(ForgeDirection.UP) != null) {

                height = myPosition.clone().subtract(new Vector3(this.getNearesthestProvider(ForgeDirection.UP))).intY();
            }
        }

        if (height == 0) {

            if (sideState[ForgeDirection.DOWN.ordinal()] != EnumState.NONE) {

                if (this.getNearesthestProvider(ForgeDirection.DOWN) != null) {

                    height = myPosition.clone().subtract(new Vector3(this.getNearesthestProvider(ForgeDirection.DOWN))).intY();
                }
            }
        }
        return height * -1;
    }

    public int getMultiBlockZChange() {

        Vector3 myPosition = new Vector3(this);

        int height = 0;

        if (sideState[ForgeDirection.SOUTH.ordinal()] != EnumState.NONE) {

            if (this.getNearesthestProvider(ForgeDirection.SOUTH) != null) {

                height = myPosition.clone().subtract(new Vector3(this.getNearesthestProvider(ForgeDirection.SOUTH))).intZ();
            }
        }

        if (height == 0) {

            if (sideState[ForgeDirection.NORTH.ordinal()] != EnumState.NONE) {

                if (this.getNearesthestProvider(ForgeDirection.NORTH) != null) {

                    height = myPosition.clone().subtract(new Vector3(this.getNearesthestProvider(ForgeDirection.NORTH))).intZ();
                }
            }
        }
        return height * -1;
    }

    public boolean createMultiBlock() {

        String multiBlockName = multiBlockNames[this.getBlockMetadata()];

        if (multiBlocks.isEmpty() && !multiBlockName.equalsIgnoreCase("")) {

            int found = 0;

            for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {

                if (getSideStateArray(direction.ordinal()) == EnumState.OUTPUT) {

                    TileEntity foundTile = getNearesthestProvider(direction);

                    if (foundTile != null) {

                        found++;
                    }
                }
            }

            if (found >= 3) {

                IMultiBlock multiInstance = MultiBlockRegistry.getInstance().getMultiBlockInstanceFromMutliBlockName(multiBlockName);

                if (multiInstance != null) {

                    multiInstance.setWorld(worldObj);
                    multiInstance.setSize(new Cuboid(new Vector3(xCoord, yCoord, zCoord), getMultiBlockXChange(), getMultiBlockYChange(), getMultiBlockZChange()));
                    UtilEntity.sendClientChat("Structure Created:  " + multiInstance.create());
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    public boolean createNewStructure(Cuboid size) {

        return false;
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

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
    }
}

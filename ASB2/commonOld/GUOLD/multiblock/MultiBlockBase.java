package GUOLD.multiblock;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ASB2.utils.UtilBlock;
import ASB2.utils.UtilEntity;
import ASB2.vector.Cuboid;
import ASB2.vector.ICuboidIterator;
import ASB2.vector.Vector3;
import GUOLD.BlockRegistry;
import GUOLD.api.multiblock.IMultiBlock;
import GUOLD.api.multiblock.IMultiBlockCore;
import GUOLD.api.multiblock.IMultiBlockInterface;
import GUOLD.api.multiblock.IMultiBlockPart;
import GUOLD.api.multiblock.ISpecialMultiBlockOpaque;
import GUOLD.api.multiblock.ISpecialTileMultiBlock;
import GUOLD.blocks.containers.BlockStructureCube.TileReplacementStructureCube;
import GUOLD.info.Variables;

public class MultiBlockBase implements IMultiBlock, ICuboidIterator {

    protected World worldObj;
    protected boolean isValid = false;
    //TODO Remove composing blocks array
    protected Set<Vector3> composingBlock = new HashSet<Vector3>();
    protected Set<Vector3> fluidMultiBlockInterfaces = new HashSet<Vector3>(), itemMultiBlockInterfaces = new HashSet<Vector3>(), powerMultiBlockInterfaces = new HashSet<Vector3>();
    protected Set<Vector3> multiBlockCores = new HashSet<Vector3>();
    protected Cuboid size;
    protected Cuboid centerBlocks;

    public MultiBlockBase() {
    }

    @Override
    public boolean create() {

        if (size.getXSize() < 2 || size.getYSize() < 2 || size.getZSize() < 2) {

            return false;
        }

        for (Vector3 vector : centerBlocks.getComposingBlock()) {

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

        for (Vector3 vector : size.getCornerBlocks()) {

            TileEntity tile = vector.getTileEntity(worldObj);

            if (tile == null || !isValidCore(vector, tile)) {

                return false;
            }
        }

        if (size.iterate(this, 1)) {

            createWorked();
            return true;
        }
        return false;
    }

    public boolean isValidCore(Vector3 vector, TileEntity tile) {

        return true;
    }

    public void createWorked() {
        isValid = true;
        init();
    }

    @Override
    public boolean iterate(Vector3 vector, Object... providedInfo) {

        // Create Multiblock
        if ((int) providedInfo[0] == 1) {

            if (!createMultiblock(vector)) {

                invalidate();
                return false;
            }
            return true;
        }

        // Destroy Multiblock
        if ((int) providedInfo[0] == 2) {

            destoryMultiblock(vector);
            return true;
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

                if (centerBlocks.contains(vector)) {

                    if (this.centerBlocks.getCore().add(0, centerBlocks.getRelativeYSize(), 0).intEquals(vector)) {

                        vector.setBlock(this.getWorldObj(), BlockRegistry.BlockMultiCore.blockID);
                    } else {
                        vector.setBlock(this.getWorldObj(), BlockRegistry.BlockStructureAir.blockID);
                    }
                    tile = vector.getTileEntity(this.getWorldObj());
                }
            } else if (tile == null) {

                if (Variables.CAN_USE_NON_STRUCURE_MULTI_BLOCKS) {

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
                }
            }
        }

        if (!(tile instanceof IMultiBlockPart)) {

            return false;
        }
        if (!((IMultiBlockPart) tile).addMultiBlock(this)) {

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

    protected void destoryMultiblock(Vector3 vector) {

        TileEntity tile = vector.getTileEntity(this.getWorldObj());

        if (tile != null && tile instanceof IMultiBlockPart) {

            ((IMultiBlockPart) tile).removeMultiBlock(this);
        }
    }

    @Override
    public void invalidate() {

        isValid = false;
        composingBlock.clear();
        fluidMultiBlockInterfaces.clear();
        multiBlockCores.clear();
        size.iterate(this, 2);
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

    @Override
    public void setWorld(World world) {

        worldObj = world;
    }

    @Override
    public World getWorldObj() {

        return worldObj;
    }

    @Override
    public boolean setSize(Cuboid size) {
        this.size = size;
        this.centerBlocks = size.clone().squareShrink(2, 2, 2);
        return true;
    }

    @Override
    public Cuboid getSize() {

        return size.clone();
    }

    @Override
    public boolean isValid() {

        return isValid;
    }

    @Override
    public NBTTagCompound save(NBTTagCompound tag) {

        tag.setCompoundTag("CuboidData", size.save(new NBTTagCompound()));
        return tag;
    }

    @Override
    public void load(NBTTagCompound tag) {

        this.size = Cuboid.load(tag.getCompoundTag("CuboidData"));
        init();
    }

    protected void init() {
        isValid = true;
        centerBlocks = this.getSize().squareShrink(2, 2, 2);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {

        UtilEntity.sendChatToPlayer(player, "Cuboid Size: " + this.size);
        return false;
    }

    @Override
    public void render(double x, double y, double z) {
        // TODO Auto-generated method stub

    }
}

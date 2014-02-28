package GU.api.multiblock;

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
import GU.BlockRegistry;
import GU.blocks.containers.BlockStructureCube.TileReplacementStructureCube;
import GU.info.Variables;
import GU.multiblock.MultiBlockChest;
import GU.multiblock.MultiBlockTank;

public class MultiBlockBase implements IMultiBlock, ICuboidIterator {

    protected World worldObj;
    protected boolean isValid = false;
    protected Set<Vector3> composingBlock = new HashSet<Vector3>();
    protected Set<Vector3> fluidMultiBlockInterfaces = new HashSet<Vector3>(), itemMultiBlockInterfaces = new HashSet<Vector3>(), powerMultiBlockInterfaces = new HashSet<Vector3>();
    protected Set<Vector3> multiBlockCores = new HashSet<Vector3>();
    protected Cuboid size;
    protected Cuboid centerBlocks;

    public MultiBlockBase(World world) {
        this.worldObj = world;
    }

    public MultiBlockBase(World world, Cuboid size) {
        this(world);
        this.size = size;
    }

    public boolean isStructureValid() {

        // if ((this.size.getXSize() + 1) % 2 == 0 || (this.size.getYSize() + 1)
        // % 2 == 0 || (this.size.getZSize() + 1) % 2 == 0) {
        //
        // return false;
        // }

        if (this.getSize().getXSize() < 2 || this.getSize().getYSize() < 2 || this.getSize().getZSize() < 2) {

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

            if (tile == null || !(tile instanceof IMultiBlockCore) || !isValidCore(vector, tile)) {

                return false;
            }
        }
        return size.iterate(this, 0);
    }

    @Override
    public boolean create() {

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

    }

    @Override
    public boolean iterate(Vector3 vector, Object... providedInfo) {

        // Is Area Valid
        if ((Integer) providedInfo[0] == 0) {

            return checkArea(vector);
        }

        // Create Multiblock
        if ((Integer) providedInfo[0] == 1) {

            if (!createMultiblock(vector)) {

                invalidate();
                return false;
            }
            return true;
        }

        // Destroy Multiblock
        if ((Integer) providedInfo[0] == 2) {

            destoryMultiblock(vector);
            return true;
        }
        return false;
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

                if (centerBlocks.contains(vector)) {

                    return true;
                }
            } else {

                if ((this instanceof MultiBlockChest && Variables.CAN_USE_NON_STRUCURE_MULTI_CHEST_BLOCKS) || (this instanceof MultiBlockTank && Variables.CAN_USE_NON_STRUCURE_MULTI_TANK_BLOCKS)) {

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

                if (centerBlocks.contains(vector)) {

                    vector.setBlock(this.getWorldObj(), BlockRegistry.BlockStructureAir.blockID);
                    return true;
                }
            } else if (tile == null) {

                if (Variables.CAN_USE_NON_STRUCURE_MULTI_CHEST_BLOCKS) {

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
    public void setWorld(World world) {

        worldObj = world;
    }

    @Override
    public World getWorldObj() {

        return worldObj;
    }

    @Override
    public Set<Vector3> getContainingBlocks() {

        return composingBlock;
    }

    @Override
    public Set<Vector3> getMultiBlockInterfaces() {

        return fluidMultiBlockInterfaces;
    }

    @Override
    public Cuboid getSize() {

        return size.clone();
    }

    @Override
    public NBTTagCompound save(NBTTagCompound tag) {

        size.save(tag);
        return tag;
    }

    @Override
    public void load(NBTTagCompound tag) {

        this.size = Cuboid.load(tag);
        init();
    }

    protected void init() {
        isValid = true;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ, boolean hi) {

        UtilEntity.sendChatToPlayer(player, "Cuboid Size: " + this.size);
        return false;
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        
    }
}

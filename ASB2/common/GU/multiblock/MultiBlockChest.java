package GU.multiblock;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidContainerRegistry;
import ASB2.utils.UtilBlock;
import ASB2.vector.Cuboid;
import ASB2.vector.Vector3;
import GU.BlockRegistry;
import GU.api.multiblock.IMultiBlockCore;
import GU.api.multiblock.IMultiBlockInterface;
import GU.api.multiblock.IMultiBlockPart;
import GU.api.multiblock.ISpecialMultiBlockOpaque;
import GU.api.multiblock.ISpecialTileMultiBlock;
import GU.api.multiblock.MultiBlockBase;
import GU.blocks.containers.Inventory;
import GU.blocks.containers.BlockSpacialProvider.TileChestSpacialProvider;
import GU.blocks.containers.BlockStructureCube.TileReplacementStructureCube;
import GU.info.Variables;

public class MultiBlockChest extends MultiBlockBase implements IInventory {

    public Cuboid airBlocks;
    Inventory multiInventory = new Inventory(6, "Multi Furnace", true);

    public MultiBlockChest(World world, Cuboid size) {
        super(world, size);
        init();
    }

    public MultiBlockChest(World world) {
        super(world);
        // TODO Auto-generated constructor stub
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

                if (Variables.CAN_USE_NON_STRUCURE_MULTI_CHEST_BLOCKS) {

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

    @Override
    protected void init() {
        isValid = true;
        airBlocks = this.getSize().squareShrink(2, 2, 2);

        if (Variables.COUNT_JUST_MULTI_TANK_AIR_BLOCKS) {

            multiInventory.setSizeInventory((airBlocks.getXSize() + 1) * (airBlocks.getYSize() + 1) * (airBlocks.getZSize() + 1) * 16 * FluidContainerRegistry.BUCKET_VOLUME);
        } else {

            multiInventory.setSizeInventory(((size.getXSize() + 1) * (size.getYSize() + 1) * (size.getZSize() + 1)) * 16000);
        }
    }

    public boolean isValidCore(Vector3 vector, TileEntity tile) {

        return tile.getClass() == TileChestSpacialProvider.class;
    }

    @Override
    public NBTTagCompound save(NBTTagCompound tag) {

        multiInventory.save(tag);
        return super.save(tag);
    }

    @Override
    public void load(NBTTagCompound tag) {

        multiInventory.load(tag);
        super.load(tag);
    }

    @Override
    public int getSizeInventory() {

        return multiInventory.getSizeInventory();
    }

    @Override
    public ItemStack getStackInSlot(int i) {

        return multiInventory.getStackInSlot(i);
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {

        return multiInventory.decrStackSize(slot, amount);
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {

        return multiInventory.getStackInSlotOnClosing(i);
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemStack) {

        multiInventory.setInventorySlotContents(i, itemStack);
    }

    @Override
    public boolean isInvNameLocalized() {

        return multiInventory.isInvNameLocalized();
    }

    @Override
    public int getInventoryStackLimit() {

        return multiInventory.getInventoryStackLimit();
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer) {

        return multiInventory.isUseableByPlayer(entityplayer);
    }

    @Override
    public void openChest() {

        multiInventory.openChest();
    }

    @Override
    public void closeChest() {

        multiInventory.closeChest();
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {

        return multiInventory.isItemValidForSlot(i, itemstack);
    }

    @Override
    public String getInvName() {

        return multiInventory.getInvName();
    }

    @Override
    public void onInventoryChanged() {

        multiInventory.onInventoryChanged();
    }

}

package GU.multiblock;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ASB2.utils.UtilBlock;
import ASB2.vector.Cuboid;
import ASB2.vector.Vector3;
import GU.api.multiblock.ISpecialMultiBlockOpaque;
import GU.api.multiblock.MultiBlockBase;
import GU.blocks.containers.BlockSpacialProvider.TileChestSpacialProvider;
import GU.info.Variables;
import GU.inventory.Inventory;

public class MultiBlockChest extends MultiBlockBase implements IInventory {

    Inventory multiInventory = new Inventory(1, "Multi Chest");

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
        return super.isStructureValid();
    }

    @Override
    protected void init() {

        isValid = true;
        centerBlocks = this.getSize().squareShrink(2, 2, 2);

        if (multiInventory.hasLoaded()) {

            if (Variables.COUNT_JUST_MULTI_CHEST_AIR_BLOCKS) {

                multiInventory.setSizeInventory((centerBlocks.getXSize() + 1) * (centerBlocks.getYSize() + 1) * (centerBlocks.getZSize() + 1) * 27);
            } else {

                multiInventory.setSizeInventory(((size.getXSize() + 1) * (size.getYSize() + 1) * (size.getZSize() + 1)) * 27);
            }
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

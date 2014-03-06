package GU.multiblock;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ASB2.vector.Cuboid;
import ASB2.vector.Vector3;
import GU.blocks.containers.BlockSpacialProvider.TileFlameSpacialProvider;
import GU.info.Variables;
import GU.inventory.Inventory;

public class MultiBlockFlameSource extends MultiBlockBase implements IInventory {

    Inventory multiInventory = new Inventory(1, "Multi Flame Source");

    public MultiBlockFlameSource(World world, Cuboid size) {
        super(world, size);
        init();
    }

    public MultiBlockFlameSource(World world) {
        super(world);
    }

    @Override
    protected void init() {

        isValid = true;
        centerBlocks = this.getSize().squareShrink(2, 2, 2);

        if (!multiInventory.hasLoaded()) {

            if (Variables.COUNT_JUST_MULTI_CHEST_AIR_BLOCKS) {

                multiInventory.setSizeInventory((centerBlocks.getXSize() + 1) * (centerBlocks.getYSize() + 1) * (centerBlocks.getZSize() + 1) * 27);
            } else {

                multiInventory.setSizeInventory(((size.getXSize() + 1) * (size.getYSize() + 1) * (size.getZSize() + 1)) * 27);
            }
        }
    }

    public boolean isValidCore(Vector3 vector, TileEntity tile) {

        return tile.getClass() == TileFlameSpacialProvider.class;
    }

    @Override
    public NBTTagCompound save(NBTTagCompound tag) {

        tag.setCompoundTag("Inventory", multiInventory.save(new NBTTagCompound()));
        return super.save(tag);
    }

    @Override
    public void load(NBTTagCompound tag) {

        multiInventory.load(tag.getCompoundTag("Inventory"));
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
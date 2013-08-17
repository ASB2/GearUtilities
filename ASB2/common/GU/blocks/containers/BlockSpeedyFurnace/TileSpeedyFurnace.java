package GU.blocks.containers.BlockSpeedyFurnace;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import GU.blocks.containers.TileBase;
import GU.utils.UtilInventory;

public class TileSpeedyFurnace extends TileBase implements IInventory {

    int burnTime = 0;

    public TileSpeedyFurnace() {

        this.tileItemStacks = new ItemStack[3];
    }

    public void updateEntity() {

        UtilInventory.decreaseSlotContents(this, 0, 1);
        
        if(this.getInputSlot() != null && FurnaceRecipes.smelting().getSmeltingResult(this.getInputSlot()) != null) {

            if(burnTime >= 200) {

                if(this.getOutputSlot() == null || this.getOutputSlot().isItemEqual(FurnaceRecipes.smelting().getSmeltingResult(this.getInputSlot()))) {

                    if(UtilInventory.addItemStackToSlot(this, FurnaceRecipes.smelting().getSmeltingResult(this.getInputSlot()), 1)) {

                        UtilInventory.decreaseSlotContents(this, 0, 1);
                    }
                }
            }
            else {

                if(this.getFuelSlot() != null && TileEntityFurnace.getItemBurnTime(this.getFuelSlot()) > 0) {

                    if(UtilInventory.decreaseSlotContents(this, 2, 1)) {

                        burnTime = TileEntityFurnace.getItemBurnTime(this.getFuelSlot());
                    }   
                }
            }
        }
    }

    public ItemStack getInputSlot() {

        return this.getStackInSlot(0);
    }

    public ItemStack getOutputSlot() {

        return this.getStackInSlot(1);
    }

    public ItemStack getFuelSlot() {

        return this.getStackInSlot(2);
    }

    public boolean isBurning() {

        return burnTime > 0;
    }

    @Override
    public int getSizeInventory() {

        return tileItemStacks.length;
    }

    @Override
    public ItemStack getStackInSlot(int i) {

        return tileItemStacks[i];
    }

    @Override
    public ItemStack decrStackSize(int i, int j) {

        if(UtilInventory.decreaseSlotContents(this, i, j)) {

            return this.getStackInSlot(i);
        }
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {

        return tileItemStacks[i];
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemStack) {

        tileItemStacks[i] = itemStack;        
    }

    @Override
    public String getInvName() {

        return "Speedy Furnace";
    }

    @Override
    public boolean isInvNameLocalized() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        // TODO Auto-generated method stub
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public void openChest() {
        // TODO Auto-generated method stub

    }

    @Override
    public void closeChest() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);

        burnTime = tag.getInteger("burnTime");
    }

    @Override
    public void writeToNBT(NBTTagCompound tag){
        super.writeToNBT(tag); 
        tag.setInteger("burnTIme", burnTime);
    }
}

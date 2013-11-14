package GU.blocks.containers.BlockSender;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.FakePlayer;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.IFluidHandler;
import ASB2.utils.UtilBlock;
import ASB2.utils.UtilDirection;
import ASB2.utils.UtilFluid;
import ASB2.utils.UtilInventory;
import ASB2.utils.UtilItemStack;
import ASB2.vector.Vector3;
import GU.BlockRegistry;
import GU.api.cluster.IClusterTrigger;
import GU.api.cluster.IClustor;
import GU.api.power.IPowerMisc;
import GU.api.power.PowerClass;
import GU.api.power.PowerProvider;
import GU.api.power.State;
import GU.api.recipe.SenderRecipe;
import GU.api.wait.Wait;
import GU.blocks.containers.Inventory;
import GU.blocks.containers.TileFluidBase;
import GU.entity.EntityTileFinder.EntityTileFinder;
import GU.info.Reference;

public class TileSender extends TileFluidBase implements IClusterTrigger, IInventory, IPowerMisc, IFluidHandler {

    public static final int ITEM_MOVEMENT = 1;
    public static final int FLUID_MOVEMENT = 2;
    public static final int GRINDING = 3;
    public static final int BLOCK_PLACE = 4;
    public static final int BLOCK_BREAK = 5;
    public static final int SMELTER = 6;
    public static final int CUSTOM = 7;

    int currentMode;

    public TileSender() {

        waitTimer = new Wait(10, this, 0);
        tileInventory = new Inventory(9, 64, "Sender", true);
        this.powerProvider = new PowerProvider(PowerClass.LOW, State.SINK);
        fluidTank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME);
    }

    public void updateEntity() {

        if(!worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {

            this.waitTimer.update();
        }
    }

    public void setMode(int mode) {

        this.currentMode = mode;
    }

    public int getMode() {

        return currentMode;
    }

    @Override
    public void trigger(int id) {

        this.updateClients();

        switch(this.getMode()) {

            case ITEM_MOVEMENT: {

                TileEntity tile = UtilDirection.translateDirectionToTile(this, worldObj, this.getOrientation().getOpposite());
                TileEntity destination = UtilDirection.translateDirectionToTile(this, worldObj, this.getOrientation());

                if(tile != null && tile instanceof IInventory) {

                    if(tile instanceof ISidedInventory) {

                        UtilInventory.moveEntireISidedInventory((ISidedInventory) tile, this.getOrientation(), this);
                    }
                    else {

                        UtilInventory.moveEntireInventory((IInventory) tile, this);
                    }
                }

                if(destination != null && destination instanceof IInventory) {

                    if(destination instanceof ISidedInventory) {

                        UtilInventory.moveEntireISidedInventory(this, this.getOrientation(), (ISidedInventory) destination);
                    }
                    else {

                        UtilInventory.moveEntireInventory(this, (IInventory) destination);
                    }
                }
                else {

                    if(!worldObj.isRemote) {

                        worldObj.spawnEntityInWorld(new EntityTileFinder(worldObj, new Vector3(this), this.getOrientation(), this, 0));
                    }
                }
                break;
            }

            case FLUID_MOVEMENT: {

                TileEntity tile = UtilDirection.translateDirectionToTile(this, worldObj, this.getOrientation().getOpposite());
                TileEntity destination = UtilDirection.translateDirectionToTile(this, worldObj, this.getOrientation());

                if(tile != null && tile instanceof IFluidHandler) {

                    UtilFluid.moveFluid((IFluidHandler) tile, this.getOrientation(), this, this.getOrientation().getOpposite(), true);
                }

                if(destination != null && destination instanceof IFluidHandler) {

                    UtilFluid.moveFluid(this, this.getOrientation(), (IFluidHandler) destination, this.getOrientation().getOpposite(), true);
                }
                else {

                    if(!worldObj.isRemote) {

                        worldObj.spawnEntityInWorld(new EntityTileFinder(worldObj, new Vector3(this), this.getOrientation(), this, 0));
                    }
                }
                break;
            }

            case GRINDING: {

                if(!worldObj.isRemote) {

                    Vector3 affecting = new Vector3(this).add(this.getOrientation());

                    if(!UtilBlock.isBlockAir(worldObj, affecting.intX(), affecting.intY(), affecting.intZ())) {

                        ItemStack[] itemStacks = SenderRecipe.getInstance().getResultsForBlock(affecting.getBlockID(worldObj), affecting.getBlockMetadata(worldObj));

                        if(itemStacks != null && itemStacks.length > 0) {

                            boolean itWorked = false;

                            for(ItemStack item : itemStacks) {

                                if(UtilInventory.addItemStackToInventory(this, item, false)) {

                                    itWorked = true;
                                }
                                else {

                                    itWorked = false;
                                }
                            }

                            worldObj.destroyBlock(affecting.intX(), affecting.intY(), affecting.intZ(), false);

                            if(itWorked) {

                                for(ItemStack item : itemStacks) {

                                    UtilInventory.addItemStackToInventory(this, item, true);
                                }
                            }
                        }
                    }

                    TileEntity destination = UtilDirection.translateDirectionToTile(this, worldObj, this.getOrientation().getOpposite());

                    if(destination != null && destination instanceof IInventory) {

                        if(destination instanceof ISidedInventory) {

                            UtilInventory.moveEntireISidedInventory(this, this.getOrientation().getOpposite(), (ISidedInventory) destination);
                        }
                        else {

                            UtilInventory.moveEntireInventory(this, (IInventory) destination);
                        }
                    }
                }
                break;
            }

            case BLOCK_PLACE: {

                Vector3 affecting = new Vector3(this).add(this.getOrientation());

                for(ItemStack item : tileInventory.getItemArray()) {

                    if(item != null) {

                        if(item.getItem() instanceof ItemBlock) {

                            if(((ItemBlock) item.getItem()).placeBlockAt(item, new FakePlayer(worldObj, Reference.NAME), worldObj, affecting.intX(), affecting.intY(), affecting.intZ(), this.getOrientation().getOpposite().ordinal(), 0, 0, 0, item.getItemDamage())) {

                                UtilInventory.removeItemStackFromInventory(this, item, 1, true);
                            }
                        }
                    }
                }

                TileEntity tile = UtilDirection.translateDirectionToTile(this, worldObj, this.getOrientation().getOpposite());

                if(tile != null && tile instanceof IInventory) {

                    if(tile instanceof ISidedInventory) {

                        UtilInventory.moveEntireISidedInventory((ISidedInventory) tile, this.getOrientation(), this);
                    }
                    else {

                        UtilInventory.moveEntireInventory((IInventory) tile, this);
                    }
                }
                break;
            }

            case BLOCK_BREAK: {

                if(!worldObj.isRemote) {

                    Vector3 affecting = new Vector3(this).add(this.getOrientation());
                    UtilBlock.breakAndAddToInventory(this, worldObj, affecting.intX(), affecting.intY(), affecting.intZ(), true);

                    TileEntity destination = UtilDirection.translateDirectionToTile(this, worldObj, this.getOrientation().getOpposite());

                    if(destination != null && destination instanceof IInventory) {

                        if(destination instanceof ISidedInventory) {

                            UtilInventory.moveEntireISidedInventory(this, this.getOrientation().getOpposite(), (ISidedInventory) destination);
                        }
                        else {

                            UtilInventory.moveEntireInventory(this, (IInventory) destination);
                        }
                    }
                }
                break;
            }

            case SMELTER: {

                if(!worldObj.isRemote) {

                    Vector3 affecting = new Vector3(this).add(this.getOrientation());

                    ItemStack stack = FurnaceRecipes.smelting().getSmeltingResult(new ItemStack(affecting.getBlockID(worldObj), 1, affecting.getBlockMetadata(worldObj)));

                    if(stack != null && UtilInventory.addItemStackToInventory(this, stack, false)) {

                        worldObj.destroyBlock(affecting.intX(), affecting.intY(), affecting.intZ(), false);
                        UtilInventory.addItemStackToInventory(this, stack, true);
                    }

                    TileEntity destination = UtilDirection.translateDirectionToTile(this, worldObj, this.getOrientation().getOpposite());

                    if(destination != null && destination instanceof IInventory) {

                        if(destination instanceof ISidedInventory) {

                            UtilInventory.moveEntireISidedInventory(this, this.getOrientation().getOpposite(), (ISidedInventory) destination);
                        }
                        else {

                            UtilInventory.moveEntireInventory(this, (IInventory) destination);
                        }
                    }
                }
                break;
            }
        }
    }

    @Override
    public void onSentClustorCollosion(IClusterTrigger sender, ForgeDirection side, Vector3 position, IClustor clustor, int id) {

        TileEntity destination = position.getTileEntity(worldObj);

        switch(this.getMode()) {

            case ITEM_MOVEMENT: {

                if(destination != null && destination instanceof IInventory) {

                    if(destination instanceof ISidedInventory) {

                        UtilInventory.moveEntireISidedInventory(this, side.getOpposite(), (ISidedInventory) destination);
                    }
                    else {

                        UtilInventory.moveEntireInventory(this, (IInventory) destination);
                    }
                }
                break;
            }
            case FLUID_MOVEMENT: {

                if(destination != null && destination instanceof IFluidHandler) {

                    UtilFluid.moveFluid(this, this.getOrientation(), (IFluidHandler) destination, side.getOpposite(), true);
                }
                break;
            }
        }
    }

    @Override
    public void invalidate() {

        ItemStack stack = new ItemStack(BlockRegistry.BlockSender);
        UtilItemStack.setNBTTagInt(stack, "mode", this.getMode());
        UtilBlock.spawnItemStackEntity(worldObj, xCoord, yCoord, zCoord, stack, 5);
        super.invalidate();
    }

    @Override
    public void onClustorCollosion(IClusterTrigger source, ForgeDirection side, Vector3 position, IClustor clustor) {
        // TODO Auto-generated method stub

    }

    @Override
    public PowerProvider getPowerProvider() {

        return powerProvider;
    }

    @Override
    public int getSizeInventory() {

        return tileInventory.getSizeInventory();
    }

    @Override
    public ItemStack getStackInSlot(int i) {

        return tileInventory.getStackInSlot(i);
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {

        return UtilInventory.decreaseSlotContents(this, slot, amount);
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {

        return tileInventory.getStackInSlotOnClosing(i);
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemStack) {

        tileInventory.setInventorySlotContents(i, itemStack);
    }

    @Override
    public boolean isInvNameLocalized() {

        return true;
    }

    @Override
    public int getInventoryStackLimit() {

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
    public String getInvName() {

        return "Universal Sender";
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);

        currentMode = tag.getInteger("mode");
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);

        tag.setInteger("mode", currentMode);
    }
}

package GUOLD.blocks.containers.BlockMultiPanel;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.IFluidHandler;
import ASB2.utils.UtilBlock;
import ASB2.utils.UtilDirection;
import ASB2.utils.UtilFluid;
import ASB2.utils.UtilInventory;
import ASB2.utils.UtilItemStack;
import ASB2.vector.Vector3;
import ASB2.wait.Wait;
import GUOLD.BlockRegistry;
import GUOLD.api.power.IPowerHandler;
import GUOLD.api.power.PowerClass;
import GUOLD.api.power.PowerProvider;
import GUOLD.api.power.State;
import GUOLD.api.recipe.MultiPanelGrinderRecipe;
import GUOLD.blocks.containers.TileFluidBase;
import GUOLD.inventory.Inventory;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileMultiPanel extends TileFluidBase implements IInventory, IPowerHandler, IFluidHandler {
    
    public static final int ITEM_MOVEMENT = 1;
    public static final int FLUID_MOVEMENT = 2;
    public static final int GRINDING = 3;
    public static final int BLOCK_PLACE = 4;
    public static final int BLOCK_BREAK = 5;
    public static final int SMELTER = 6;
    public static final int CUSTOM = 7;
    
    public static int MAX_DISTANCE = 20;
    public final boolean[] wireless = new boolean[] { true, true, false, false, false, false, false };
    
    int currentMode;
    
    public TileMultiPanel() {
        
        waitTimer = new Wait(this, 5, 0);
        tileInventory = new Inventory(9, "MultiPanel");
        this.powerProvider = new PowerProvider(PowerClass.LOW, State.SINK);
        fluidTank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME);
    }
    
    @Override
    public void updateEntity() {
        
        this.waitTimer.update();
    }
    
    public void setMode(int mode) {
        
        this.currentMode = mode;
    }
    
    public int getMode() {
        
        return currentMode;
    }
    
    @Override
    public void trigger(int id) {
        
        if (getMode() != 0 && !worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord) && !worldObj.isRemote) {
            
            ForgeDirection orientation = this.getOrientation();
            ForgeDirection oppositeOrientation = orientation.getOpposite();
            
            switch (this.getMode()) {
            
                case ITEM_MOVEMENT: {
                    
                    TileEntity tile = UtilDirection.translateDirectionToTile(this, worldObj, oppositeOrientation);
                    TileEntity foundTiledestination = this.getNearestInventory(orientation);
                    
                    if (tile != null && tile instanceof IInventory) {
                        
                        if (tile instanceof ISidedInventory) {
                            
                            UtilInventory.moveEntireISidedInventory((ISidedInventory) tile, orientation, this);
                        }
                        else {
                            
                            UtilInventory.moveEntireInventory((IInventory) tile, this);
                        }
                    }
                    
                    if (foundTiledestination != null && foundTiledestination instanceof IInventory) {
                        
                        if (foundTiledestination instanceof ISidedInventory) {
                            
                            UtilInventory.moveEntireISidedInventory(this, oppositeOrientation, (ISidedInventory) foundTiledestination);
                        }
                        else {
                            
                            UtilInventory.moveEntireInventory(this, (IInventory) foundTiledestination);
                        }
                    }
                    break;
                }
                
                case FLUID_MOVEMENT: {
                    
                    TileEntity tile = UtilDirection.translateDirectionToTile(this, worldObj, oppositeOrientation);
                    TileEntity destination = this.getNearestFluidHandler(orientation);
                    
                    if (tile != null && tile instanceof IFluidHandler) {
                        
                        UtilFluid.moveFluid((IFluidHandler) tile, orientation, this, oppositeOrientation, true);
                    }
                    
                    if (destination != null) {
                        
                        if (destination instanceof IFluidHandler) {
                            
                            UtilFluid.moveFluid(this, orientation, (IFluidHandler) destination, oppositeOrientation, true);
                        }
                    }
                    break;
                }
                
                case GRINDING: {
                    
                    Vector3 affecting = new Vector3(this).add(orientation);
                    
                    if (!UtilBlock.isBlockAir(worldObj, affecting.intX(), affecting.intY(), affecting.intZ())) {
                        
                        ItemStack[] itemStacks = MultiPanelGrinderRecipe.getInstance().getResultsForBlock(affecting.getBlock(worldObj), affecting.getBlockMetadata(worldObj));
                        
                        if (itemStacks != null && itemStacks.length > 0) {
                            
                            boolean itWorked = false;
                            
                            for (ItemStack item : itemStacks) {
                                
                                if (UtilInventory.addItemStackToInventory(this, item, false)) {
                                    
                                    itWorked = true;
                                }
                                else {
                                    
                                    itWorked = false;
                                }
                            }
                            
                            if (itWorked) {
                                
                                for (ItemStack item : itemStacks) {
                                    
                                    itWorked = UtilInventory.addItemStackToInventory(this, item, true);
                                }
                            }
                            if (itWorked) {
                                
                                UtilBlock.breakBlockNoDrop(worldObj, affecting.intX(), affecting.intY(), affecting.intZ());
                            }
                        }
                    }
                    
                    TileEntity destination = UtilDirection.translateDirectionToTile(this, worldObj, oppositeOrientation);
                    
                    if (destination != null && destination instanceof IInventory) {
                        
                        if (destination instanceof ISidedInventory) {
                            
                            UtilInventory.moveEntireISidedInventory(this, oppositeOrientation, (ISidedInventory) destination);
                        }
                        else {
                            
                            UtilInventory.moveEntireInventory(this, (IInventory) destination);
                        }
                    }
                    break;
                }
                
                case BLOCK_PLACE: {
                    
                    Vector3 affecting = new Vector3(this).add(this.getOrientation());
                    
                    for (ItemStack item : tileInventory.getItemArray().values()) {
                        
                        if (item != null) {
                            
                            if (item.getItem() instanceof ItemBlock) {
                                
                                if (((ItemBlock) item.getItem()).placeBlockAt(item, null, worldObj, affecting.intX(), affecting.intY(), affecting.intZ(), oppositeOrientation.ordinal(), 0, 0, 0, item.getItemDamage())) {
                                    
                                    UtilInventory.removeItemStackFromInventory(this, item, 1, true);
                                }
                            }
                        }
                    }
                    
                    TileEntity tile = UtilDirection.translateDirectionToTile(this, worldObj, oppositeOrientation);
                    
                    if (tile != null && tile instanceof IInventory) {
                        
                        if (tile instanceof ISidedInventory) {
                            
                            UtilInventory.moveEntireISidedInventory((ISidedInventory) tile, orientation, this);
                        }
                        else {
                            
                            UtilInventory.moveEntireInventory((IInventory) tile, this);
                        }
                    }
                    break;
                }
                
                case BLOCK_BREAK: {
                    
                    Vector3 affecting = new Vector3(this).add(orientation);
                    UtilBlock.breakAndAddToInventory(this, worldObj, affecting.intX(), affecting.intY(), affecting.intZ(), true);
                    
                    TileEntity destination = UtilDirection.translateDirectionToTile(this, worldObj, oppositeOrientation);
                    
                    if (destination != null && destination instanceof IInventory) {
                        
                        if (destination instanceof ISidedInventory) {
                            
                            UtilInventory.moveEntireISidedInventory(this, oppositeOrientation, (ISidedInventory) destination);
                        }
                        else {
                            
                            UtilInventory.moveEntireInventory(this, (IInventory) destination);
                        }
                    }
                    break;
                }
                
                case SMELTER: {
                    
                    Vector3 affecting = new Vector3(this).add(orientation);
                    TileEntity affectingTile = affecting.getTileEntity(worldObj);
                    
                    if (affectingTile != null && affectingTile instanceof IInventory) {
                        
                        if (affectingTile instanceof ISidedInventory) {
                            
                            ISidedInventory inventory = ((ISidedInventory) affectingTile);
                            int[] avaliableSlots = inventory.getAccessibleSlotsFromSide(oppositeOrientation.ordinal());
                            
                            for (int i = 0; i < avaliableSlots.length; i++) {
                                
                                ItemStack stack = inventory.getStackInSlot(avaliableSlots[i]);
                                
                                if (stack != null) {
                                    
                                    stack = stack.copy();
                                    
                                    ItemStack results = FurnaceRecipes.smelting().getSmeltingResult(stack);
                                    
                                    if (results != null) {
                                        
                                        if (UtilInventory.removeItemStackFromISidedSlot(inventory, oppositeOrientation, stack, avaliableSlots[i], 1, false) && UtilInventory.addItemStackToInventory(this, results, false)) {
                                            
                                            UtilInventory.removeItemStackFromISidedSlot(inventory, oppositeOrientation, stack, avaliableSlots[i], 1, true);
                                            UtilInventory.addItemStackToInventory(this, results, true);
                                        }
                                    }
                                }
                            }
                        }
                        else {
                            
                            IInventory inventory = ((IInventory) affectingTile);
                            
                            for (int i = 0; i < inventory.getSizeInventory(); i++) {
                                
                                ItemStack stack = inventory.getStackInSlot(i);
                                
                                if (stack != null) {
                                    
                                    stack = stack.copy();
                                    
                                    ItemStack results = FurnaceRecipes.smelting().getSmeltingResult(stack);
                                    
                                    if (results != null) {
                                        
                                        if (UtilInventory.removeItemStackFromSlot(inventory, stack, i, 1, false) && UtilInventory.addItemStackToInventory(this, results, false)) {
                                            
                                            UtilInventory.removeItemStackFromSlot(inventory, stack, i, 1, true);
                                            UtilInventory.addItemStackToInventory(this, results, true);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    else {
                        
                        ItemStack stack = FurnaceRecipes.smelting().getSmeltingResult(new ItemStack(affecting.getBlock(worldObj), 1, affecting.getBlockMetadata(worldObj)));
                        
                        if (stack != null && UtilInventory.addItemStackToInventory(this, stack, false)) {
                            
                            UtilBlock.breakBlockNoDrop(worldObj, affecting.intX(), affecting.intY(), affecting.intZ());
                            UtilInventory.addItemStackToInventory(this, stack, true);
                        }
                    }
                    
                    TileEntity destination = UtilDirection.translateDirectionToTile(this, worldObj, this.getOrientation().getOpposite());
                    if (destination != null && destination instanceof IInventory) {
                        
                        if (destination instanceof ISidedInventory) {
                            
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
    
    public TileEntity getNearestInventory(ForgeDirection direction) {
        
        Vector3 myLocation = new Vector3(this).add(direction);
        
        for (int distance = 1; distance <= MAX_DISTANCE; distance++) {
            
            TileEntity tile = myLocation.clone().add(direction, distance).getTileEntity(worldObj);
            
            if (tile != null && tile instanceof IInventory) {
                
                return tile;
            }
        }
        return null;
    }
    
    public TileEntity getNearestFluidHandler(ForgeDirection direction) {
        
        Vector3 myLocation = new Vector3(this).add(direction);
        
        for (int distance = 1; distance <= MAX_DISTANCE; distance++) {
            
            TileEntity tile = myLocation.clone().add(direction, distance).getTileEntity(worldObj);
            
            if (tile != null && tile instanceof IFluidHandler) {
                
                return tile;
            }
        }
        return null;
    }
    
    @Override
    public void invalidate() {
        
        ItemStack stack = new ItemStack(BlockRegistry.BlockMultiPanel, 1, worldObj.getBlockMetadata(xCoord, yCoord, zCoord));
        UtilItemStack.setNBTTagInt(stack, "mode", this.getMode());
        UtilBlock.spawnItemStackEntity(worldObj, xCoord, yCoord, zCoord, stack, 1);
        super.invalidate();
    }
    
    @Override
    public double getMaxRenderDistanceSquared() {
        
        return 16384;
    }
    
    @SuppressWarnings("static-access")
    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        
        // AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 1, 1);
        return this.INFINITE_EXTENT_AABB;
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
    public boolean hasCustomInventoryName() {
        
        return true;
    }
    
    @Override
    public int getInventoryStackLimit() {
        
        return tileInventory.getInventoryStackLimit();
    }
    
    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer) {
        // TODO Auto-generated method stub
        return true;
    }
    
    @Override
    public void openInventory() {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void closeInventory() {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        // TODO Auto-generated method stub
        return true;
    }
    
    @Override
    public String getInventoryName() {
        
        return tileInventory.getInventoryName();
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

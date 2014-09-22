package GU.multiblock;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.IFluidHandler;
import ASB2.inventory.Inventory;
import ASB2.utils.UtilEntity;
import ASB2.utils.UtilInventory;
import GU.GUGuiHandler;
import GU.GearUtilities;
import GU.api.multiblock.MultiBlockAbstract.IFluidMultiBlock;
import GU.api.multiblock.MultiBlockAbstract.IGuiMultiBlock;
import GU.api.multiblock.MultiBlockAbstract.IInventoryMultiBlock;
import GU.api.multiblock.MultiBlockAbstract.IRedstoneMultiBlock;
import GU.api.multiblock.MultiBlockObject.FluidHandlerWrapper;
import GU.multiblock.construction.ConstructionManager;
import GU.multiblock.construction.FurnaceConstructionManager;
import UC.color.Color4i;
import UC.math.vector.Vector3i;

public class MultiBlockFurnace extends MultiBlockBase implements IFluidMultiBlock, IInventoryMultiBlock, IRedstoneMultiBlock, IGuiMultiBlock {
    
    FluidHandlerWrapper fuelTank = new FluidHandlerWrapper(0);
    Inventory fuelInventory = new Inventory("MultiBlockFurnace: Fuel"), toBeSmelted = new Inventory("MultiBlockFurnace: Smelting"), outputInventory = new Inventory("MultiBlockFurnace: Output");
    
    int cookTimer = 0;
    int currentFuelTime = 0;
    int maxHeat, currentHeat;
    
    public MultiBlockFurnace(World world, Vector3i positionRelativeTo, Vector3i size, Vector3i updater) {
        super(world, positionRelativeTo, size, updater);
    }
    
    public MultiBlockFurnace(World world) {
        super(world);
    }
    
    @Override
    public Color4i getDefaultBlockColor() {
        
        return Color4i.RED;
    }
    
    @Override
    public ConstructionManager getConstructionManager() {
        
        return new FurnaceConstructionManager(world, this, positionRelativeTo, size);
    }
    
    @Override
    public IInventory getInventory(Vector3i tilePosition) {
        
        if (tilePosition != null) {
            
            Vector3i relativeVector = positionRelativeTo.subtract(tilePosition);
            
            if (((int) size.getY() / 3) > relativeVector.getY()) {
                
                return outputInventory;
            } else if (((int) size.getY() / 3) < relativeVector.getY() && relativeVector.getY() > ((int) size.getY() / 3) * 2) {
                
                return fuelInventory;
            } else {
                
                return toBeSmelted;
            }
        }
        return null;
    }
    
    @Override
    public IFluidHandler getTank(Vector3i tilePosition) {
        
        if (tilePosition != null) {
            
            Vector3i relativeVector = positionRelativeTo.subtract(tilePosition);
            
            if (size.getY() / 3 < relativeVector.getY()) {
                
                return fuelTank;
            }
        }
        return null;
    }
    
    @Override
    public void onSetSize() {
        
        if (fuelTank != null && fuelTank.getFluidTank() != null && fuelTank.getFluidTank().getCapacity() == 0) {
            
            fuelTank.getFluidTank().setCapacity((size.getX() - 1) * (((int) (size.getY() / 3)) - 1) * (size.getZ() - 1) * 8 * FluidContainerRegistry.BUCKET_VOLUME);
        }
        if (fuelInventory != null && fuelInventory.getSizeInventory() == 0) {
            
            fuelInventory.setSizeInventory(((size.getX() - 1) * (((int) (size.getY() / 3)) - 1) * (size.getZ() - 1)) * 4);
        }
        if (toBeSmelted != null && toBeSmelted.getSizeInventory() == 0) {
            
            toBeSmelted.setSizeInventory(((size.getX() - 1) * (((int) (size.getY() / 3)) - 1) * (size.getZ() - 1)) * 4);
        }
        if (outputInventory != null && outputInventory.getSizeInventory() == 0) {
            
            outputInventory.setSizeInventory((size.getX() - 1) * (((int) (size.getY() / 3)) - 1) * (size.getZ() - 1) * 4);
        }
        
        maxHeat = (size.getY() - 1) * 64;
    }
    
    @Override
    public void logicUpdate() {
        
        if (currentHeat < maxHeat) {
            
            if (currentFuelTime <= 0) {
                
                currentHeat = Math.max(0, currentHeat - 1);
                
                for (int index = 0; index < this.fuelInventory.getSizeInventory(); index++) {
                    
                    ItemStack stack = fuelInventory.getStackInSlot(index);
                    
                    if (stack != null) {
                        
                        int itemFuelValue = TileEntityFurnace.getItemBurnTime(stack);
                        
                        if (itemFuelValue > 0) {
                            
                            if (UtilInventory.removeItemStackFromSlot(fuelInventory, stack, index, 1, true)) {
                                
                                currentFuelTime = itemFuelValue;
                                break;
                            }
                        }
                    }
                }
            } else {
                
                currentFuelTime--;
                currentHeat++;
            }
        }
        
        if (currentHeat > 0) {
            
            cookTimer++;
            
            if (cookTimer >= 5) {
                
                cookTimer = 0;
                
                for (int index = 0; index < this.toBeSmelted.getSizeInventory(); index++) {
                    
                    ItemStack stack = toBeSmelted.getStackInSlot(index);
                    
                    if (stack != null) {
                        
                        ItemStack result = FurnaceRecipes.smelting().getSmeltingResult(stack);
                        
                        if (result != null) {
                            
                            if (UtilInventory.removeItemStackFromSlot(toBeSmelted, stack, index, 1, false) && UtilInventory.addItemStackToInventory(outputInventory, result, false)) {
                                
                                UtilInventory.addItemStackToInventory(outputInventory, result, true);
                                UtilInventory.removeItemStackFromSlot(toBeSmelted, stack, index, 1, true);
                                break;
                            }
                        }
                    }
                }
            }
        } else {
            
            cookTimer = 0;
        }
    }
    
    public boolean startCreation() {
        
        return size.getX() >= 2 && size.getY() >= 6 && size.getY() % 3 == 0 && size.getZ() >= 2 && super.startCreation();
    }
    
    @Override
    public boolean openGui(Vector3i position, EntityPlayer player, int side) {
        
        if (!world.isRemote) {
            
            // UtilEntity.sendChatToPlayer(player, "Furnace: " + currentHeat +
            // " / " + maxHeat);
            
            if (!player.isSneaking()) {
                
                player.openGui(GearUtilities.instance, GUGuiHandler.MULTI_BLOCK_FURNACE, world, position.getX(), position.getY(), position.getZ());
                return true;
            } else {
                
                UtilEntity.sendChatToPlayer(player, "Furnace: Stop Shifitng");
                return false;
            }
        }
        return false;
    }
    
    @Override
    public int getRedstoneLevel(Vector3i tilePosition) {
        // TODO Auto-generated method stub
        return 0;
    }
    
    @Override
    public NBTTagCompound save(NBTTagCompound tag) {
        
        if (fuelTank != null)
            tag.setTag("fuelTank", fuelTank.save(new NBTTagCompound()));
        if (fuelInventory != null)
            tag.setTag("fuelInventory", fuelInventory.save(new NBTTagCompound()));
        if (toBeSmelted != null)
            tag.setTag("toBeSmelted", toBeSmelted.save(new NBTTagCompound()));
        if (outputInventory != null)
            tag.setTag("outputInventory", outputInventory.save(new NBTTagCompound()));
        
        tag.setInteger("cookTimer", cookTimer);
        tag.setInteger("maxHeat", maxHeat);
        tag.setInteger("currentHeat", currentHeat);
        tag.setInteger("fuelInputTimer", currentFuelTime);
        return super.save(tag);
    }
    
    @Override
    public void load(NBTTagCompound tag) {
        
        if (fuelTank != null)
            fuelTank.load(tag.getCompoundTag("fuelTank"));
        if (fuelInventory != null)
            fuelInventory.load(tag.getCompoundTag("fuelInventory"));
        if (toBeSmelted != null)
            toBeSmelted.load(tag.getCompoundTag("toBeSmelted"));
        if (outputInventory != null)
            outputInventory.load(tag.getCompoundTag("outputInventory"));
        
        cookTimer = tag.getInteger("cookTimer");
        maxHeat = tag.getInteger("maxHeat");
        currentHeat = tag.getInteger("currentHeat");
        currentFuelTime = tag.getInteger("fuelInputTimer");
        
        super.load(tag);
    }
}

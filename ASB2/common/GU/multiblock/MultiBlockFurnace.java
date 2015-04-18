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
import GU.api.EnumSimulationType;
import GU.api.multiblock.MultiBlockAbstract.IFluidMultiBlock;
import GU.api.multiblock.MultiBlockAbstract.IGuiMultiBlock;
import GU.api.multiblock.MultiBlockAbstract.IInventoryMultiBlock;
import GU.api.multiblock.MultiBlockAbstract.IPowerMultiBlock;
import GU.api.multiblock.MultiBlockAbstract.IRedstoneMultiBlock;
import GU.api.multiblock.MultiBlockObject.FluidHandlerWrapper;
import GU.api.power.PowerNetAbstract.EnumPowerStatus;
import GU.api.power.PowerNetAbstract.IPowerManager;
import GU.api.power.PowerNetObject.DefaultPowerManager;
import GU.api.power.PowerNetVariables;
import GU.multiblock.construction.ConstructionManager;
import GU.multiblock.construction.FurnaceConstructionManager;
import UC.Wait;
import UC.Wait.IWaitTrigger;
import UC.color.Color4i;
import UC.math.vector.Vector3i;

public class MultiBlockFurnace extends MultiBlockBase implements IFluidMultiBlock, IInventoryMultiBlock, IRedstoneMultiBlock, IGuiMultiBlock, IPowerMultiBlock {
    
    FluidHandlerWrapper fuelTank = new FluidHandlerWrapper(0);
    Inventory fuelInventory = new Inventory("MultiBlockFurnace: Fuel") {
        
        public boolean isItemValidForSlot(int i, ItemStack itemstack) {
            
            return TileEntityFurnace.getItemBurnTime(itemstack) > 0;
        };
    }, toBeSmelted = new Inventory("MultiBlockFurnace: Smelting") {
        
        public boolean isItemValidForSlot(int i, ItemStack itemstack) {
            
            return FurnaceRecipes.smelting().getSmeltingResult(itemstack) != null;
        };
    }, outputInventory = new Inventory("MultiBlockFurnace: Output");
    
    DefaultPowerManager manager = new DefaultPowerManager().setPowerStatus(EnumPowerStatus.SINK);
    
    int maxHeat, currentHeat;
    
    Wait trigger = new Wait(new CookWait());
    
    // Conversion from fuel to power
    public static final double FUEL_POWER_EFFICENCY = .5;
    
    // Conversion from power to burntime
    public static final double POWER_BURN_EFFICENCY = .9;
    
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
    public void onSetSize() {
        
        if (fuelTank != null && fuelTank.getFluidTank() != null && fuelTank.getFluidTank().getCapacity() == 0) {
            
            fuelTank.getFluidTank().setCapacity(16 * FluidContainerRegistry.BUCKET_VOLUME * (size.getX() - 1) * (((int) (size.getY() / 3)) - 1) * (size.getZ() - 1));
        }
        if (fuelInventory != null && fuelInventory.getSizeInventory() == 0) {
            
            fuelInventory.setSizeInventory(16 * ((size.getX() - 1) * (((int) (size.getY() / 3)) - 1) * (size.getZ() - 1)));
        }
        if (toBeSmelted != null && toBeSmelted.getSizeInventory() == 0) {
            
            toBeSmelted.setSizeInventory(16 * ((size.getX() - 1) * (((int) (size.getY() / 3)) - 1) * (size.getZ() - 1)));
        }
        if (outputInventory != null && outputInventory.getSizeInventory() == 0) {
            
            outputInventory.setSizeInventory(16 * (size.getX() - 1) * (((int) (size.getY() / 3)) - 1) * (size.getZ() - 1));
        }
        
        maxHeat = (size.getY() - 1) * 64;
        manager.setPowerMax(1000 * (size.getX() - 1) * (((int) (size.getY() / 3)) - 1) * (size.getZ() - 1));
        trigger.setWaitTime((int) (5000.0 / Math.pow((size.getX() + 1) * (size.getY() + 1) * (size.getZ() + 1), 1.1)));
    }
    
    @Override
    public void logicUpdate() {
        
        trigger.update();
    }
    
    public boolean startCreation() {
        
        return size.getX() >= 2 && size.getY() >= 6 && size.getY() % 3 == 0 && size.getZ() >= 2 && super.startCreation();
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
    public IPowerManager getPowerManager(Vector3i tilePosition) {
        
        if (tilePosition != null) {
            
            Vector3i relativeVector = positionRelativeTo.subtract(tilePosition);
            
            if (((int) size.getY() / 3) > relativeVector.getY()) {
                
                return null;
            } else if (((int) size.getY() / 3) < relativeVector.getY() && relativeVector.getY() > ((int) size.getY() / 3) * 2) {
                
                return manager;
            } else {
                
                return null;
            }
        }
        return null;
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        
        if (super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ)) {
            
            if (!world.isRemote) {
                
                UtilEntity.sendChatToPlayer(player, "-------");
                // UtilEntity.sendChatToPlayer(player, "Furnace: Current Heat: "
                // + currentHeat);
                // UtilEntity.sendChatToPlayer(player, "Furnace: Max Heat: " +
                // maxHeat);
                // UtilEntity.sendChatToPlayer(player, "Furnace: Heat Precent: "
                // + (Math.round((currentHeat / (double) maxHeat) * 10) / 10.0)
                // * 100);
                UtilEntity.sendChatToPlayer(player, "Furnace: Wait Time: " + trigger.getTime());
                UtilEntity.sendChatToPlayer(player, "-------");
            }
        }
        return false;
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
        
        manager.save(tag);
        
        tag.setInteger("maxHeat", maxHeat);
        tag.setInteger("currentHeat", currentHeat);
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
        
        manager.load(tag);
        
        maxHeat = tag.getInteger("maxHeat");
        currentHeat = tag.getInteger("currentHeat");
        
        super.load(tag);
    }
    
    private class CookWait implements IWaitTrigger {
        
        @Override
        public void trigger(int id) {
            
            for (int index = 0; index < fuelInventory.getSizeInventory(); index++) {
                
                ItemStack stack = fuelInventory.getStackInSlot(index);
                
                if (stack != null) {
                    
                    int itemFuelValue = TileEntityFurnace.getItemBurnTime(stack);
                    
                    if (itemFuelValue > 0) {
                        
                        int scaledFuelValue = (int) (itemFuelValue * PowerNetVariables.ONE_TICK_ENERGY_VALUE * MultiBlockFurnace.FUEL_POWER_EFFICENCY);
                        
                        if (UtilInventory.removeItemStackFromSlot(fuelInventory, stack, index, 1, false) && manager.increasePower(scaledFuelValue, EnumSimulationType.FORCED_SIMULATE)) {
                            
                            manager.increasePower(scaledFuelValue, EnumSimulationType.FORCED_LIGITIMATE);
                            UtilInventory.removeItemStackFromSlot(fuelInventory, stack, index, 1, true);
                        }
                    }
                }
            }
            
            for (int index = 0; index < toBeSmelted.getSizeInventory(); index++) {
                
                if (manager.getStoredPower() >= (int) (PowerNetVariables.ONE_ITEM_BURN_ENERGY_VALUE * MultiBlockFurnace.POWER_BURN_EFFICENCY)) {
                    
                    ItemStack stack = toBeSmelted.getStackInSlot(index);
                    
                    if (stack != null) {
                        
                        ItemStack result = FurnaceRecipes.smelting().getSmeltingResult(stack);
                        
                        if (result != null) {
                            
                            if (UtilInventory.removeItemStackFromSlot(toBeSmelted, stack, index, 1, false) && UtilInventory.addItemStackToInventory(outputInventory, result, false) && manager.decreasePower((int) (PowerNetVariables.ONE_ITEM_BURN_ENERGY_VALUE * MultiBlockFurnace.POWER_BURN_EFFICENCY), EnumSimulationType.FORCED_SIMULATE)) {
                                
                                UtilInventory.addItemStackToInventory(outputInventory, result, true);
                                UtilInventory.removeItemStackFromSlot(toBeSmelted, stack, index, 1, true);
                                manager.decreasePower((int) (PowerNetVariables.ONE_ITEM_BURN_ENERGY_VALUE * MultiBlockFurnace.POWER_BURN_EFFICENCY), EnumSimulationType.FORCED_LIGITIMATE);
                                break;
                            }
                        }
                    }
                }
            }
        }
        
        @Override
        public boolean shouldTick(int id) {
            
            return true;
        }
    }
}

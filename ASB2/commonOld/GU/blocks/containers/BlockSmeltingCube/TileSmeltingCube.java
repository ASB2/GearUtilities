package GU.blocks.containers.BlockSmeltingCube;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntity;
import ASB2.utils.UtilDirection;
import ASB2.utils.UtilInventory;
import ASB2.wait.Wait;
import GU.api.power.IPowerHandler;
import GU.api.power.IPowerProvider;
import GU.api.power.PowerClass;
import GU.api.power.PowerProvider;
import GU.api.power.State;
import GU.blocks.containers.TileBase;

public class TileSmeltingCube extends TileBase implements IPowerHandler {
    
    public TileSmeltingCube() {
        
        this.useSidesRendering = false;
        powerProvider = new PowerProvider(PowerClass.LOW, State.SINK);
        waitTimer = new Wait(this, 0, 0);
    }
    
    @Override
    public void updateEntity() {
        
        waitTimer.update();
    }
    
    @Override
    public void trigger(int id) {
        
        TileEntity tileSource = UtilDirection.translateDirectionToTile(this, worldObj, this.getOrientation());
        TileEntity tileDestination = UtilDirection.translateDirectionToTile(this, worldObj, this.getOrientation().getOpposite());
        
        if (tileSource != null && tileSource instanceof IInventory && tileDestination != null && tileDestination instanceof IInventory) {
            
            if (tileSource instanceof ISidedInventory) {
                
                ISidedInventory inventory = (ISidedInventory) tileSource;
                int[] slots = inventory.getAccessibleSlotsFromSide(this.getOrientation().ordinal());
                boolean secondISided = tileDestination instanceof ISidedInventory;
                
                for (int i = 0; i < slots.length; i++) {
                    
                    ItemStack stack = inventory.getStackInSlot(slots[i]);
                    
                    if (stack != null) {
                        
                        ItemStack result = FurnaceRecipes.smelting().getSmeltingResult(stack);
                        
                        if (result != null) {
                            
                            if (UtilInventory.removeItemStackFromISidedInventory(inventory, this.getOrientation().getOpposite(), stack, 1, false)) {
                                
                                if (secondISided) {
                                    
                                    if (!(UtilInventory.addItemStackToISidedInventory((ISidedInventory) tileDestination, this.getOrientation(), result, false))) {
                                        return;
                                    }
                                } else if (!(UtilInventory.addItemStackToInventory((IInventory) tileDestination, result, false))) {
                                    return;
                                }
                                
                                UtilInventory.removeItemStackFromISidedInventory(inventory, this.getOrientation().getOpposite(), stack, 1, true);
                                if (secondISided) {
                                    UtilInventory.addItemStackToISidedInventory((ISidedInventory) tileDestination, this.getOrientation(), result, true);
                                } else {
                                    UtilInventory.addItemStackToInventory((IInventory) tileDestination, result, true);
                                }
                            }
                        }
                    }
                }
            } else {
                
                IInventory inventory = (IInventory) tileSource;
                int slots = inventory.getSizeInventory();
                boolean secondISided = tileDestination instanceof ISidedInventory;
                
                for (int i = 0; i < slots; i++) {
                    
                    ItemStack stack = inventory.getStackInSlot(i);
                    
                    if (stack != null) {
                        
                        ItemStack result = FurnaceRecipes.smelting().getSmeltingResult(stack);
                        
                        if (result != null) {
                            
                            if (UtilInventory.removeItemStackFromInventory(inventory, stack, 1, false)) {
                                
                                if (secondISided) {
                                    
                                    if (!(UtilInventory.addItemStackToISidedInventory((ISidedInventory) tileDestination, this.getOrientation(), result, false))) {
                                        return;
                                    }
                                } else if (!(UtilInventory.addItemStackToInventory((IInventory) tileDestination, result, false))) {
                                    return;
                                }
                                
                                UtilInventory.removeItemStackFromInventory(inventory, stack, 1, true);
                                if (secondISided) {
                                    UtilInventory.addItemStackToISidedInventory((ISidedInventory) tileDestination, this.getOrientation(), result, true);
                                } else {
                                    UtilInventory.addItemStackToInventory((IInventory) tileDestination, result, true);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    @Override
    public IPowerProvider getPowerProvider() {
        
        return powerProvider;
    }
}

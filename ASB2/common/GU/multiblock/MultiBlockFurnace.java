package GU.multiblock;

import java.util.List;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.IFluidHandler;
import ASB2.inventory.Inventory;
import ASB2.utils.UtilInventory;
import ASB2.utils.UtilVector;
import GU.api.multiblock.MultiBlockAbstract.IFluidMultiBlock;
import GU.api.multiblock.MultiBlockAbstract.IInventoryMultiBlock;
import GU.api.multiblock.MultiBlockAbstract.IItemInterface;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlockPart;
import GU.api.multiblock.MultiBlockObject.FluidHandlerWrapper;
import UC.color.Color4i;
import UC.math.vector.Vector3i;
import cpw.mods.fml.common.registry.GameRegistry;

public class MultiBlockFurnace extends MultiBlockBase implements IFluidMultiBlock, IInventoryMultiBlock {
    
    public FluidHandlerWrapper fuelTank = new FluidHandlerWrapper(0), outputTank = new FluidHandlerWrapper(0);
    Inventory fuelInventory = new Inventory("MultiBlockFurnace: Fuel"), outputInventory = new Inventory("MultiBlockFurnace: Output");
    
    int currentFuel;
    int cookTimer = 0;
    int maxFuel;
    
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
    public NBTTagCompound save(NBTTagCompound tag) {
        
        if (fuelTank != null) tag.setTag("fuelTank", fuelTank.save(new NBTTagCompound()));
        if (outputTank != null) tag.setTag("outputTank", outputTank.save(new NBTTagCompound()));
        if (fuelInventory != null) tag.setTag("fuelInventory", fuelInventory.save(new NBTTagCompound()));
        if (outputInventory != null) tag.setTag("outputInventory", outputInventory.save(new NBTTagCompound()));
        return super.save(tag);
    }
    
    @Override
    public void load(NBTTagCompound tag) {
        
        if (fuelTank != null) fuelTank.load(tag.getCompoundTag("fuelTank"));
        if (outputTank != null) outputTank.load(tag.getCompoundTag("outputTank"));
        if (fuelInventory != null) fuelInventory.load(tag.getCompoundTag("fuelInventory"));
        if (outputInventory != null) outputInventory.load(tag.getCompoundTag("outputInventory"));
        super.load(tag);
    }
    
    @Override
    public void update(Object... objects) {
        
        if (fuelTank.getFluidTank().getCapacity() == 0) {
            
            fuelTank.getFluidTank().setCapacity((size.getX() - 1) * (((int) (size.getY() / 2)) - 1) * (size.getZ() - 1) * 8 * FluidContainerRegistry.BUCKET_VOLUME);
        }
        if (outputTank.getFluidTank().getCapacity() == 0) {
            
            outputTank.getFluidTank().setCapacity((size.getX() - 1) * (((int) (size.getY() / 2)) - 1) * (size.getZ() - 1) * 8 * FluidContainerRegistry.BUCKET_VOLUME);
        }
        if (fuelInventory.getSizeInventory() == 0) {
            
            fuelInventory.setSizeInventory((size.getX() - 1) * (((int) (size.getY() / 2)) - 1) * (size.getZ() - 1) * 4);
        }
        if (outputInventory.getSizeInventory() == 0) {
            
            outputInventory.setSizeInventory((size.getX() - 1) * (((int) (size.getY() / 2)) - 1) * (size.getZ() - 1) * 4);
        }
        
        if (isConstructing) {
            
            for (int x = 0; x <= size.getX(); x++) {
                
                for (int y = 0; y <= size.getY(); y++) {
                    
                    for (int z = 0; z <= size.getZ(); z++) {
                        
                        Vector3i vec = positionRelativeTo.subtract(x, y, z);
                        
                        if (x == 1 && y == 1 && z == 1) {
                            
                            if (!placeRenderBlock(vec)) {
                                
                                deconstruct();
                                return;
                            }
                        }
                        else if (((x == 0 || x == size.getX()) && (y == 0 || y == size.getY())) && (z == 0 || z == size.getZ())) {
                            
                            if (!placeCornerBlock(vec)) {
                                
                                deconstruct();
                                return;
                            }
                        }
                        else if (((x == 0 || x == size.getX()) && (y == 0 || y == size.getY())) || ((y == 0 || y == size.getY()) && (z == 0 || z == size.getZ())) || ((x == 0 || x == size.getX()) && (z == 0 || z == size.getZ()))) {
                            
                            if (!placeEdgeBlock(vec)) {
                                
                                deconstruct();
                                return;
                            }
                        }
                        else if (y == (int) (size.getY() / 2)) {
                            
                            if (!placeEdgeBlock(vec)) {
                                
                                deconstruct();
                                return;
                            }
                        }
                        // else if (y == size.getY()) {
                        //
                        // if (!placeEdgeBlock(vec)) {
                        //
                        // deconstruct();
                        // return;
                        // }
                        // }
                        else if (((x == 0 || x == size.getX()) && (y != 0 && y != size.getY())) && (z != 0 && z != size.getZ())) {
                            
                            if (!placeGlassBlock(vec)) {
                                
                                deconstruct();
                                return;
                            }
                        }
                        else if (((x != 0 && x != size.getX()) && (y == 0 || y == size.getY())) && (z != 0 && z != size.getZ())) {
                            
                            if (!placeGlassBlock(vec)) {
                                
                                deconstruct();
                                return;
                            }
                        }
                        else if (((x != 0 && x != size.getX()) && (y != 0 && y != size.getY())) && (z == 0 || z == size.getZ())) {
                            
                            if (!placeGlassBlock(vec)) {
                                
                                deconstruct();
                                return;
                            }
                        }
                        else if (!placeAirBlock(vec)) {
                            
                            deconstruct();
                            return;
                        }
                    }
                }
            }
            isConstructing = false;
            isValid = true;
        }
        else if (forceLoad) {
            
            for (int x = 0; x <= size.getX(); x++) {
                
                for (int y = 0; y <= size.getY(); y++) {
                    
                    for (int z = 0; z <= size.getZ(); z++) {
                        
                        forceCheckBlock(positionRelativeTo.subtract(x, y, z));
                    }
                }
            }
            forceLoad = false;
            isValid = true;
        }
        else {
            if (!world.isRemote) furnaceLogic();
        }
    }
    
    public void furnaceLogic() {
        
//        if (currentFuel < 100) {
            
            for (int index = 0; index < this.fuelInventory.getSizeInventory(); index++) {
                
                ItemStack stack = fuelInventory.getStackInSlot(index);
                
                if (stack != null) {
                    
                    int itemFuelValue = TileEntityFurnace.getItemBurnTime(stack);
                    
                    if (itemFuelValue > 0) {
                        
                        if (UtilInventory.removeItemStackFromSlot(fuelInventory, stack, index, 1, true)) {
                            
                            currentFuel += itemFuelValue;
                        }
                    }
                }
            }
//        }
        
        if (currentFuel >= 100) {
            
            for (int index = 0; index < this.outputInventory.getSizeInventory(); index++) {
                
                ItemStack stack = outputInventory.getStackInSlot(index);
                
                if (stack != null) {
                    
                    ItemStack result = FurnaceRecipes.smelting().getSmeltingResult(stack);
                    
                    if (result != null) {
                        
                        if (UtilInventory.removeItemStackFromSlot(outputInventory, stack, index, 1, false)) {
                            
                            for (IItemInterface interfacee : this.itemInterfaceList) {
                                
                                if (interfacee != null) {
                                    
                                    List<IInventory> inventoryList = interfacee.getAvaliableInventorys();
                                    
                                    if (inventoryList != null) {
                                        
                                        for (IInventory inventory : inventoryList) {
                                            
                                            if (inventory != null) {
                                                
                                                if (UtilInventory.addItemStackToInventory(inventory, result, false)) {
                                                    
                                                    currentFuel -= 100;
                                                    UtilInventory.addItemStackToInventory(inventory, result, true);
                                                    UtilInventory.removeItemStackFromSlot(outputInventory, stack, index, 1, true);
                                                    return;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    public boolean startCreation() {
        
        if (!isValid && !isConstructing) {
            
            if (size.getX() >= 2 && size.getY() >= 4 && size.getY() % 2 == 0 && size.getZ() >= 2) {
                
                isConstructing = true;
                
                TileEntity tile = UtilVector.getTileAtPostion(world, updater);
                
                if (tile != null && tile instanceof IMultiBlockPart) {
                    
                    return ((IMultiBlockPart) tile).addMultiBlock(this);
                }
            }
        }
        return false;
    }
    
    @Override
    public IInventory getInventory(Vector3i tilePosition) {
        
        Vector3i relativeVector = positionRelativeTo.subtract(tilePosition);
        
        if (size.getY() / 2 > relativeVector.getY()) {
            
            return outputInventory;
        }
        else if (size.getY() / 2 < relativeVector.getY()) {
            
            return fuelInventory;
        }
        return null;
    }
    
    @Override
    public IFluidHandler getTank(Vector3i tilePosition) {
        
        Vector3i relativeVector = positionRelativeTo.subtract(tilePosition);
        
        if (size.getY() / 2 > relativeVector.getY()) {
            
            return outputTank;
        }
        else if (size.getY() / 2 < relativeVector.getY()) {
            
            return fuelTank;
        }
        return null;
    }
}

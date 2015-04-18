package GU.multiblock;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import ASB2.inventory.Inventory;
import ASB2.utils.UtilEntity;
import ASB2.utils.UtilInventory;
import GU.api.EnumSimulationType;
import GU.api.multiblock.MultiBlockAbstract.IGuiMultiBlock;
import GU.api.multiblock.MultiBlockAbstract.IInventoryMultiBlock;
import GU.api.multiblock.MultiBlockAbstract.IPowerMultiBlock;
import GU.api.multiblock.MultiBlockAbstract.IRedstoneMultiBlock;
import GU.api.power.PowerNetVariables;
import GU.api.power.PowerNetAbstract.EnumPowerStatus;
import GU.api.power.PowerNetAbstract.IPowerManager;
import GU.api.power.PowerNetObject.DefaultPowerManager;
import GU.api.recipe.GrinderRecipeManager;
import GU.multiblock.construction.ConstructionManager;
import GU.multiblock.construction.GrinderConstructionManager;
import UC.Wait;
import UC.Wait.IWaitTrigger;
import UC.color.Color4i;
import UC.math.vector.Vector3i;

public class MultiBlockGrinder extends MultiBlockBase implements IInventoryMultiBlock, IRedstoneMultiBlock, IGuiMultiBlock, IPowerMultiBlock {
    
    Inventory input = new Inventory("MultiBlockGrinder:Input") {
        
        public boolean isItemValidForSlot(int i, ItemStack itemstack) {
            
            return GrinderRecipeManager.getInstance().hasOutput(GrinderRecipeManager.getOreDictionaryName(itemstack));
        };
    }, output = new Inventory("MultiBlockGrinder:Output");
    
    DefaultPowerManager manager = new DefaultPowerManager().setPowerStatus(EnumPowerStatus.SINK);
    
    public static final double EFFICENCY = .9;
    
    Wait trigger = new Wait(new GrindWait());
    
    public MultiBlockGrinder(World world, Vector3i positionRelativeTo, Vector3i size, Vector3i updater) {
        super(world, positionRelativeTo, size, updater);
        // TODO Auto-generated constructor stub
    }
    
    public MultiBlockGrinder(World world) {
        super(world);
        // TODO Auto-generated constructor stub
    }
    
    public Color4i getDefaultBlockColor() {
        
        return Color4i.RUST;
    }
    
    @Override
    public ConstructionManager getConstructionManager() {
        
        return new GrinderConstructionManager(world, this, positionRelativeTo, size);
    }
    
    @Override
    public void logicUpdate() {
        
        trigger.update();
    }
    
    @Override
    public void onSetSize() {
        
        if (input != null && input.getSizeInventory() == 0) {
            
            input.setSizeInventory(16 * ((size.getX() - 1) * (((int) (size.getY() / 2)) - 1) * (size.getZ() - 1)));
        }
        if (output != null && output.getSizeInventory() == 0) {
            
            output.setSizeInventory(16 * ((size.getX() - 1) * (((int) (size.getY() / 2)) - 1) * (size.getZ() - 1)));
        }
        
        manager.setPowerMax((int) (PowerNetVariables.ONE_GARNET_ENERGY_VALUE * 4) * (size.getX() - 1) * (((int) (size.getY() / 2)) - 1) * (size.getZ() - 1));
        trigger.setWaitTime((int) (5000.0 / Math.pow((size.getX() + 1) * (size.getY() + 1) * (size.getZ() + 1), 1.1)));
    }
    
    public boolean startCreation() {
        
        if (size.getX() >= 2 && size.getY() >= 4 && size.getY() % 2 == 0 && size.getZ() >= 2) {
            
            return super.startCreation();
        }
        return false;
    }
    
    @Override
    public IPowerManager getPowerManager(Vector3i tilePosition) {
        
        if (tilePosition != null) {
            
            Vector3i relativeVector = positionRelativeTo.subtract(tilePosition);
            
            if (((int) size.getY() / 2) < relativeVector.getY()) {
                
                return manager;
            }
        }
        return null;
    }
    
    @Override
    public IInventory getInventory(Vector3i tilePosition) {
        
        if (tilePosition != null) {
            
            Vector3i relativeVector = positionRelativeTo.subtract(tilePosition);
            
            if (((int) size.getY() / 2) > relativeVector.getY()) {
                
                return output;
            } else if (((int) size.getY() / 2) < relativeVector.getY()) {
                
                return input;
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
                
                // player.openGui(GearUtilities.instance,
                // GUGuiHandler.MULTI_BLOCK_FURNACE, world, position.getX(),
                // position.getY(), position.getZ());
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
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        
        if (super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ)) {
            
            if (!world.isRemote) {
                
                UtilEntity.sendChatToPlayer(player, "-------");
                UtilEntity.sendChatToPlayer(player, "Grinder: Wait Time: " + trigger.getTime());
                UtilEntity.sendChatToPlayer(player, "-------");
            }
        }
        return false;
    }
    
    @Override
    public NBTTagCompound save(NBTTagCompound tag) {
        
        if (input != null)
            tag.setTag("input", input.save(new NBTTagCompound()));
        if (output != null)
            tag.setTag("output", output.save(new NBTTagCompound()));
        
        manager.save(tag);
        
        return super.save(tag);
    }
    
    @Override
    public void load(NBTTagCompound tag) {
        
        if (input != null)
            input.load(tag.getCompoundTag("fuelTainputnk"));
        if (output != null)
            output.load(tag.getCompoundTag("output"));
        
        manager.load(tag);
        
        super.load(tag);
    }
    
    private class GrindWait implements IWaitTrigger {
        
        @Override
        public void trigger(int id) {
            
            int power = (int) (MultiBlockGrinder.EFFICENCY * (PowerNetVariables.ONE_COAL_ENERGY_VALUE / 7));
            
            if (manager.getStoredPower() >= power) {
                
                for (int index = 0; index < input.getSizeInventory(); index++) {
                    
                    ItemStack stack = input.getStackInSlot(index);
                    
                    if (stack != null) {
                        
                        int[] oreArray = OreDictionary.getOreIDs(stack);
                        
                        if (oreArray.length > 0) {
                            
                            ItemStack[] resultArray = GrinderRecipeManager.getInstance().getOutputItemStack(OreDictionary.getOreName(oreArray[0]));
                            
                            if (resultArray != null && resultArray.length > 0) {
                                
                                if (UtilInventory.removeItemStackFromSlot(input, stack, index, 1, true)) {
                                    
                                    manager.decreasePower(power, EnumSimulationType.FORCED_LIGITIMATE);
                                    
                                    for (ItemStack result : resultArray) {
                                        
                                        if (result != null) {
                                            
                                            UtilInventory.addItemStackToInventory(output, result, true);
                                        }
                                    }
                                }
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

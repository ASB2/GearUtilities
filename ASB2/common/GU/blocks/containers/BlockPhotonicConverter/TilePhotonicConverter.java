package GU.blocks.containers.BlockPhotonicConverter;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;
import net.minecraftforge.fluids.IFluidHandler;
import ASB2.utils.UtilDirection;
import ASB2.utils.UtilEntity;
import ASB2.utils.UtilFluid;
import ASB2.utils.UtilInventory;
import GU.FluidRegistry;
import GU.api.EnumSimulationType;
import GU.api.power.PowerNetAbstract.EnumPowerStatus;
import GU.api.power.PowerNetAbstract.IPowerManager;
import GU.api.power.PowerNetAbstract.ITilePowerHandler;
import GU.api.power.PowerNetObject;
import GU.api.power.PowerNetObject.DefaultPowerManager;
import GU.api.power.PowerNetVariables;
import GU.blocks.containers.TileBase;
import UC.Wait;
import UC.Wait.IWaitTrigger;

public class TilePhotonicConverter extends TileBase implements ITilePowerHandler {
    
    DefaultPowerManager manager;
    FuelType fuelType;
    Wait wait;
    
    public static final double EFFICENCY = .7;
    
    public TilePhotonicConverter() {
        
        manager = new DefaultPowerManager((int) (PowerNetVariables.ONE_GARNET_ENERGY_VALUE));
        manager.setPowerStatus(EnumPowerStatus.SOURCE);
        fuelType = FuelType.NONE;
        wait = new Wait(new WaitTrigger(), 20);
        
    }
    
    @Override
    public void updateEntity() {
        
        TileEntity tile = UtilDirection.translateDirectionToTile(getThis(), worldObj, getOrientation());
        
        if (tile != null) {
            
            if (tile instanceof ITilePowerHandler) {
                
                IPowerManager manager = ((ITilePowerHandler) tile).getPowerManager(getOrientation().getOpposite());
                
                if (manager != null)
                    for (int index = 0; index < PowerNetVariables.ONE_GARNET_ENERGY_VALUE; index++)
                        PowerNetObject.UtilPower.movePower(getPowerManager(ForgeDirection.UNKNOWN), manager, 1, EnumSimulationType.LIGITIMATE);
            }
        }
        wait.update();
    }
    
    @Override
    public boolean triggerBlock(World world, EntityPlayer player, int x, int y, int z, ForgeDirection axis) {
        
        if (player.isSneaking()) {
            
            this.rotateBlock(world, x, y, z, axis);
        } else {
            
            if (fuelType.ordinal() + 1 < FuelType.values().length) {
                
                fuelType = FuelType.values()[fuelType.ordinal() + 1];
            } else {
                
                fuelType = FuelType.NONE;
            }
            if (!world.isRemote) {
                UtilEntity.sendChatToPlayer(player, "Current Mode: " + (fuelType.ordinal() == 1 ? "FURNACE" : fuelType.toString()));
            }
            world.markBlockForUpdate(x, y, z);
        }
        return true;
    }
    
    @Override
    public boolean rotateBlock(World world, int x, int y, int z, ForgeDirection axis) {
        
        ForgeDirection direction = this.getOrientation();
        
        if (direction.ordinal() + 1 < ForgeDirection.VALID_DIRECTIONS.length) {
            
            world.setBlockMetadataWithNotify(x, y, z, direction.ordinal() + 1, 3);
        } else {
            world.setBlockMetadataWithNotify(x, y, z, 0, 3);
        }
        return true;
    }
    
    @Override
    public IPowerManager getPowerManager(ForgeDirection direction) {
        
        return manager;
    }
    
    @Override
    public void readNBTPacket(NBTTagCompound tag, int id) {
        
        manager.load(tag);
        fuelType = FuelType.values()[tag.getInteger("fuelType")];
    }
    
    @Override
    public void sendUpdatePacket() {
        
        NBTTagCompound tag = new NBTTagCompound();
        manager.save(tag);
        tag.setInteger("fuelType", fuelType.ordinal());
        this.sendNBTPacket(tag, 0);
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag) {
        
        manager.load(tag);
        fuelType = FuelType.values()[tag.getInteger("fuelType")];
        super.readFromNBT(tag);
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag) {
        
        manager.save(tag);
        tag.setInteger("fuelType", fuelType.ordinal());
        super.writeToNBT(tag);
    }
    
    private TilePhotonicConverter getThis() {
        
        return this;
    }
    
    private class WaitTrigger implements IWaitTrigger {
        
        @Override
        public void trigger(int id) {
            
            ForgeDirection direction = getOrientation().getOpposite();
            
            switch (fuelType) {
            
                case ITEM: {
                    
                    TileEntity tile = worldObj.getTileEntity(xCoord + direction.offsetX, yCoord + direction.offsetY, zCoord + direction.offsetZ);
                    
                    if (tile != null) {
                        
                        if (tile instanceof ISidedInventory) {
                            
                            int[] accesableSlots = ((ISidedInventory) tile).getAccessibleSlotsFromSide(direction.ordinal());
                            
                            if (accesableSlots != null) {
                                
                                for (int index = 0; index < accesableSlots.length; index++) {
                                    
                                    ItemStack stack = ((ISidedInventory) tile).getStackInSlot(accesableSlots[index]);
                                    
                                    if (stack != null) {
                                        
                                        int itemFuelValue = TileEntityFurnace.getItemBurnTime(stack);
                                        
                                        if (itemFuelValue > 0) {
                                            
                                            int scaledFuelValue = (int) Math.ceil(itemFuelValue * PowerNetVariables.ONE_TICK_ENERGY_VALUE * TilePhotonicConverter.EFFICENCY);
                                            
                                            if (UtilInventory.removeItemStackFromSlot(((ISidedInventory) tile), stack, index, 1, false) && manager.increasePower(scaledFuelValue, EnumSimulationType.FORCED_SIMULATE)) {
                                                
                                                UtilInventory.removeItemStackFromSlot(((ISidedInventory) tile), stack, index, 1, true);
                                                manager.increasePower(scaledFuelValue, EnumSimulationType.FORCED_LIGITIMATE);
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        } else if (tile instanceof IInventory) {
                            
                            for (int index = 0; index < ((IInventory) tile).getSizeInventory(); index++) {
                                
                                ItemStack stack = ((IInventory) tile).getStackInSlot(index);
                                
                                if (stack != null) {
                                    
                                    int itemFuelValue = TileEntityFurnace.getItemBurnTime(stack);
                                    
                                    if (itemFuelValue > 0) {
                                        
                                        int scaledFuelValue = (int) Math.ceil(itemFuelValue * PowerNetVariables.ONE_TICK_ENERGY_VALUE * TilePhotonicConverter.EFFICENCY);
                                        
                                        if (UtilInventory.removeItemStackFromSlot(((IInventory) tile), stack, index, 1, false) && manager.increasePower(scaledFuelValue, EnumSimulationType.FORCED_SIMULATE)) {
                                            
                                            UtilInventory.removeItemStackFromSlot(((IInventory) tile), stack, index, 1, true);
                                            manager.increasePower(scaledFuelValue, EnumSimulationType.FORCED_LIGITIMATE);
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    break;
                }
                
                case FLUID: {
                    
                    TileEntity tile = worldObj.getTileEntity(xCoord + direction.offsetX, yCoord + direction.offsetY, zCoord + direction.offsetZ);
                    
                    if (tile != null) {
                        
                        if (tile instanceof IFluidHandler) {
                            
                            if (UtilFluid.removeFluidFromTank(((IFluidHandler) tile), direction, FluidRegistry.LAVA, 100, false)) {
                                
                                if (manager.increasePower((int) ((PowerNetVariables.ONE_LAVA_ENERGY_VALUE / 10) * TilePhotonicConverter.EFFICENCY), EnumSimulationType.FORCED_SIMULATE)) {
                                    
                                    UtilFluid.removeFluidFromTank(((IFluidHandler) tile), direction, FluidRegistry.LAVA, 100, true);
                                    manager.increasePower((int) ((PowerNetVariables.ONE_LAVA_ENERGY_VALUE / 10) * TilePhotonicConverter.EFFICENCY), EnumSimulationType.FORCED_LIGITIMATE);
                                    break;
                                }
                            }
                        }
                    }
                    break;
                }
                
                case SOLAR: {
                    
                    if (worldObj.canBlockSeeTheSky(xCoord, yCoord, zCoord)) {
                        
                        manager.increasePower(1, EnumSimulationType.FORCED_LIGITIMATE);
                    }
                    break;
                }
                
                case ENDER: {
                    
                    TileEntity tile = worldObj.getTileEntity(xCoord + direction.offsetX, yCoord + direction.offsetY, zCoord + direction.offsetZ);
                    
                    if (tile != null) {
                        
                        if (tile instanceof ISidedInventory) {
                            
                            int[] accesableSlots = ((ISidedInventory) tile).getAccessibleSlotsFromSide(direction.ordinal());
                            
                            if (accesableSlots != null) {
                                
                                for (int index = 0; index < accesableSlots.length; index++) {
                                    
                                    ItemStack stack = ((ISidedInventory) tile).getStackInSlot(accesableSlots[index]);
                                    
                                    if (stack != null) {
                                        
                                        if (stack.getItem() == Items.ender_pearl) {
                                            
                                            if (UtilInventory.removeItemStackFromSlot(((ISidedInventory) tile), stack, index, 1, false) && manager.increasePower(100, EnumSimulationType.SIMULATE)) {
                                                
                                                UtilInventory.removeItemStackFromSlot(((ISidedInventory) tile), stack, index, 1, true);
                                                manager.increasePower(100, EnumSimulationType.LIGITIMATE);
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        } else if (tile instanceof IInventory) {
                            
                            for (int index = 0; index < ((IInventory) tile).getSizeInventory(); index++) {
                                
                                ItemStack stack = ((IInventory) tile).getStackInSlot(index);
                                
                                if (stack != null) {
                                    
                                    if (stack.getItem() == Items.ender_pearl) {
                                        
                                        if (UtilInventory.removeItemStackFromSlot(((IInventory) tile), stack, index, 1, true) && manager.increasePower(100, EnumSimulationType.SIMULATE)) {
                                            
                                            UtilInventory.removeItemStackFromSlot(((IInventory) tile), stack, index, 1, true);
                                            manager.increasePower(100, EnumSimulationType.LIGITIMATE);
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    break;
                }
                
                case FOOD: {
                    
                    TileEntity tile = worldObj.getTileEntity(xCoord + direction.offsetX, yCoord + direction.offsetY, zCoord + direction.offsetZ);
                    
                    if (tile != null) {
                        
                        if (tile instanceof ISidedInventory) {
                            
                            int[] accesableSlots = ((ISidedInventory) tile).getAccessibleSlotsFromSide(direction.ordinal());
                            
                            if (accesableSlots != null) {
                                
                                for (int index = 0; index < accesableSlots.length; index++) {
                                    
                                    ItemStack stack = ((ISidedInventory) tile).getStackInSlot(accesableSlots[index]);
                                    
                                    if (stack != null) {
                                        
                                        if (stack.getItem() instanceof ItemFood) {
                                            
                                            if (UtilInventory.removeItemStackFromSlot(((ISidedInventory) tile), stack, index, 1, false) && manager.increasePower(((ItemFood) stack.getItem()).func_150905_g(stack), EnumSimulationType.SIMULATE)) {
                                                
                                                UtilInventory.removeItemStackFromSlot(((ISidedInventory) tile), stack, index, 1, true);
                                                manager.increasePower(((ItemFood) stack.getItem()).func_150905_g(stack), EnumSimulationType.LIGITIMATE);
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        } else if (tile instanceof IInventory) {
                            
                            for (int index = 0; index < ((IInventory) tile).getSizeInventory(); index++) {
                                
                                ItemStack stack = ((IInventory) tile).getStackInSlot(index);
                                
                                if (stack != null) {
                                    
                                    if (stack.getItem() instanceof ItemFood) {
                                        
                                        if (UtilInventory.removeItemStackFromSlot(((IInventory) tile), stack, index, 1, false) && manager.increasePower(((ItemFood) stack.getItem()).func_150905_g(stack), EnumSimulationType.SIMULATE)) {
                                            
                                            UtilInventory.removeItemStackFromSlot(((IInventory) tile), stack, index, 1, true);
                                            manager.increasePower(((ItemFood) stack.getItem()).func_150905_g(stack), EnumSimulationType.LIGITIMATE);
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    break;
                }
                
                case REDSTONE: {
                    
                    TileEntity tile = worldObj.getTileEntity(xCoord + direction.offsetX, yCoord + direction.offsetY, zCoord + direction.offsetZ);
                    
                    if (tile != null) {
                        
                        if (tile instanceof ISidedInventory) {
                            
                            int[] accesableSlots = ((ISidedInventory) tile).getAccessibleSlotsFromSide(direction.ordinal());
                            
                            if (accesableSlots != null) {
                                
                                for (int index = 0; index < accesableSlots.length; index++) {
                                    
                                    ItemStack stack = ((ISidedInventory) tile).getStackInSlot(accesableSlots[index]);
                                    
                                    if (stack != null) {
                                        
                                        if (stack.getItem() == Items.redstone) {
                                            
                                            if (UtilInventory.removeItemStackFromSlot(((ISidedInventory) tile), stack, index, 1, false) && manager.increasePower(5, EnumSimulationType.SIMULATE)) {
                                                
                                                UtilInventory.removeItemStackFromSlot(((ISidedInventory) tile), stack, index, 1, true);
                                                manager.increasePower(5, EnumSimulationType.LIGITIMATE);
                                                break;
                                            }
                                        }
                                        
                                        if (stack.getItem() == Item.getItemFromBlock(Blocks.redstone_block)) {
                                            
                                            if (UtilInventory.removeItemStackFromSlot(((ISidedInventory) tile), stack, index, 1, false) && manager.increasePower(50, EnumSimulationType.SIMULATE)) {
                                                
                                                UtilInventory.removeItemStackFromSlot(((ISidedInventory) tile), stack, index, 1, true);
                                                manager.increasePower(50, EnumSimulationType.LIGITIMATE);
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        } else if (tile instanceof IInventory) {
                            
                            for (int index = 0; index < ((IInventory) tile).getSizeInventory(); index++) {
                                
                                ItemStack stack = ((IInventory) tile).getStackInSlot(index);
                                
                                if (stack != null) {
                                    
                                    if (stack.getItem() == Items.redstone) {
                                        
                                        if (UtilInventory.removeItemStackFromSlot(((IInventory) tile), stack, index, 1, true) && manager.increasePower(5, EnumSimulationType.SIMULATE)) {
                                            
                                            UtilInventory.removeItemStackFromSlot(((IInventory) tile), stack, index, 1, true);
                                            manager.increasePower(5, EnumSimulationType.LIGITIMATE);
                                            break;
                                        }
                                    }
                                    
                                    if (stack.getItem() == Item.getItemFromBlock(Blocks.redstone_block)) {
                                        
                                        if (UtilInventory.removeItemStackFromSlot(((IInventory) tile), stack, index, 1, true) && manager.increasePower(50, EnumSimulationType.SIMULATE)) {
                                            
                                            UtilInventory.removeItemStackFromSlot(((IInventory) tile), stack, index, 1, true);
                                            manager.increasePower(50, EnumSimulationType.LIGITIMATE);
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }
        
        @Override
        public boolean shouldTick(int id) {
            // TODO Auto-generated method stub
            return true;
        }
    }
}

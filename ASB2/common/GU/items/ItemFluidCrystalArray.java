package GU.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidBlock;
import ASB2.utils.UtilBlock;
import ASB2.utils.UtilInventory;
import GU.GearUtilities;
import GU.items.ItemRenderers.FluidCrystalArrayRenderer;
import GU.utils.UtilGU;

public class ItemFluidCrystalArray extends ItemBase {
    
    public void postInit() {
        
        setCreativeTab(GearUtilities.tabGUFluids);
    }
    
    public void postInitRender() {
        
        MinecraftForgeClient.registerItemRenderer(this, FluidCrystalArrayRenderer.instance);
    }
    
    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        
        ForgeDirection direction = ForgeDirection.getOrientation(side);
        
        Block block = world.getBlock(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ);
        int meta = world.getBlockMetadata(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ);
        
        if (UtilBlock.isBlockAir(world, x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ)) {
            
            if (itemStack.getItemDamage() != 0) {
                
                Fluid fluid = FluidRegistry.getFluid(itemStack.getItemDamage());
                
                if (fluid != null) {
                    
                    Block fluidBlock = fluid.getBlock();
                    
                    if (fluidBlock != null && (player.capabilities.isCreativeMode || UtilInventory.removeItemStackFromInventory(player.inventory, itemStack, 1, true))) {
                        
                        UtilBlock.placeBlockInAir(world, x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ, fluidBlock, 0);
                        
                        UtilInventory.addItemStackToInventoryAndSpawnExcess(world, player.inventory, new ItemStack(this, 1, 0), player.posX, player.posY, player.posZ);
                        return true;
                    }
                }
            }
        } else {
            
            FluidStack pickupFluid = null;
            
            if (block == Blocks.lava && meta == 0) {
                
                pickupFluid = new FluidStack(FluidRegistry.LAVA, 1000);
            } else if (block == Blocks.water && meta == 0) {
                
                pickupFluid = new FluidStack(FluidRegistry.WATER, 1000);
            } else if (block instanceof IFluidBlock) {
                
                if (((IFluidBlock) block).canDrain(world, x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ)) {
                    
                    FluidStack test = ((IFluidBlock) block).drain(world, x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ, false);
                    
                    if (test != null && test.amount == 1000) {
                        
                        pickupFluid = ((IFluidBlock) block).drain(world, x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ, true);
                    }
                }
            }
            
            if (pickupFluid != null) {
                
                ItemStack toAdd = FluidContainerRegistry.fillFluidContainer(new FluidStack(pickupFluid, 1000), new ItemStack(this));
                
                if (toAdd != null && (player.capabilities.isCreativeMode || UtilInventory.removeItemStackFromInventory(player.inventory, itemStack, 1, true))) {
                    
                    if (!UtilInventory.addItemStackToInventory(player.inventory, toAdd, true)) {
                        
                        UtilBlock.spawnItemStackEntity(world, player.posX, player.posY, player.posZ, toAdd, 1);
                    }
                    UtilBlock.breakBlockNoDrop(world, x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ);
                    return true;
                }
            }
        }
        return false;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        
        Fluid saved = UtilGU.getFluid(itemStack);
        
        if (saved != null) {
            
            par3List.add("Fluid: ".concat(saved.getLocalizedName()));
            par3List.add("Fluid ID: " + saved.getID());
        }
        // else
        // par3List.add("Fluid: ".concat("Empty"));
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void getSubItems(Item item, CreativeTabs tab, List subItems) {
        
        subItems.add(new ItemStack(this));
        for (ItemStack itemStack : GU.FluidRegistry.FluidCrystalArrayList) {
            
            subItems.add(itemStack.copy());
        }
    }
    
    @SuppressWarnings("deprecation")
    public String getItemStackDisplayName(ItemStack itemStack) {
        
        Fluid saved = UtilGU.getFluid(itemStack);
        
        if (saved != null) {
            
            return "Fluid Crystal Array: ".concat(saved.getLocalizedName());
        }
        return "Fluid Crystal Array: Empty";
    }
}

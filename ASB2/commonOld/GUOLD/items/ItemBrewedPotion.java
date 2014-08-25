package GUOLD.items;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import ASB2.utils.UtilItemStack;
import ASB2.utils.UtilMisc;
import GUOLD.api.potion.IPotion;
import GUOLD.api.potion.IPotionIngredient;
import GUOLD.entity.EntityPotion.EntityModularPotion;

public class ItemBrewedPotion extends ItemBase implements IPotion {
    
    public ItemBrewedPotion() {
        
        this.setCreativeTab(null);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, java.util.List info, boolean var1) {
        super.addInformation(stack, player, info, var1);
        
        info.add(UtilMisc.getColorCode(EnumChatFormatting.GOLD) + "Duration: " + this.getDuration(stack));
        info.add(UtilMisc.getColorCode(EnumChatFormatting.GOLD) + "Strength: " + this.getStrength(stack));
    }
    
    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void addInformationSneaking(ItemStack stack, EntityPlayer player, java.util.List info, boolean var1) {
        
        info.add(UtilMisc.getColorCode(EnumChatFormatting.RED) + "Ingredients :");
        
        if (!this.getModules(stack).isEmpty()) {
            
            for (ItemStack itemStack : this.getModules(stack)) {
                
                info.add(UtilMisc.getColorCode(EnumChatFormatting.GOLD) + itemStack.getDisplayName());
            }
        }
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        
        if (!this.getThrowable(itemStack)) {
            
            player.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
        }
        else {
            
            if (!world.isRemote) {
                
                world.spawnEntityInWorld(new EntityModularPotion(world, player, itemStack));
            }
        }
        return itemStack;
    }
    
    @Override
    public void setDuration(ItemStack stack, int amount) {
        
        if (stack != null) {
            
            if (amount >= 0) {
                
                UtilItemStack.setNBTTagInt(stack, "duration", amount);
            }
        }
    }
    
    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        
        return getDuration(stack);
    }
    
    @Override
    public EnumAction getItemUseAction(ItemStack itemStack) {
        
        return EnumAction.eat;
    }
    
    @Override
    public ItemStack onEaten(ItemStack itemStack, World world, EntityPlayer player) {
        
        for (ItemStack stack : this.getModules(itemStack)) {
            
            if (stack.getItem() instanceof IPotionIngredient) {
                
                ((IPotionIngredient) stack.getItem()).onEntityDrinkPotion(world, stack, player);
            }
        }
        itemStack.stackSize -= 1;
        return itemStack;
    }
    
    @Override
    public int getDuration(ItemStack stack) {
        
        return UtilItemStack.getNBTTagInt(stack, "duration");
    }
    
    @Override
    public void setStrength(ItemStack stack, int amount) {
        
        if (stack != null) {
            
            if (amount >= 0) {
                
                UtilItemStack.setNBTTagInt(stack, "strength", amount);
            }
        }
    }
    
    @Override
    public int getStrength(ItemStack stack) {
        
        return UtilItemStack.getNBTTagInt(stack, "strength");
    }
    
    @Override
    public void addItemModule(ItemStack accepter, ItemStack moduleToAdd) {
        
        ArrayList<ItemStack> inventory = UtilItemStack.getNBTTagInventory(accepter, "Items");
        inventory.add(moduleToAdd);
        UtilItemStack.setNBTTagInventory(accepter, "Items", inventory);
    }
    
    @Override
    public ArrayList<ItemStack> getModules(ItemStack stack) {
        
        return UtilItemStack.getNBTTagInventory(stack, "Items");
    }
    
    @Override
    public void setThrowable(ItemStack stack, boolean isthrowable) {
        
        UtilItemStack.setNBTTagBoolean(stack, "throwable", isthrowable);
    }
    
    @Override
    public boolean getThrowable(ItemStack stack) {
        
        return UtilItemStack.getNBTTagBoolean(stack, "throwable");
    }
}

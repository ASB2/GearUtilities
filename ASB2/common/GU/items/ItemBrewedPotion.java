package GU.items;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import GU.api.potion.IPotion;
import GU.utils.UtilItemStack;
import GU.utils.UtilMisc;
import GU.api.potion.*;

public class ItemBrewedPotion extends ItemBase implements IPotion {

    public ItemBrewedPotion(int id) {
        super(id);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, java.util.List info, boolean var1) {
        super.addInformation(stack, player, info, var1);

        info.add(UtilMisc.getColorCode(EnumChatFormatting.GOLD) + "Duration: " + this.getDuration(stack));
        info.add(UtilMisc.getColorCode(EnumChatFormatting.GOLD) + "Strength: " + this.getStrength(stack));
        info.add(UtilMisc.getColorCode(EnumChatFormatting.GOLD) + "Is Throwable: " + this.isThrowable(stack));
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void addInformationSneaking(ItemStack stack, EntityPlayer player, java.util.List info, boolean var1) {

        info.add(UtilMisc.getColorCode(EnumChatFormatting.RED) + "Ingredients :");

        if(!this.getIngredients(stack).isEmpty()) {

            for(ItemStack itemStack : this.getIngredients(stack)) {

                info.add(UtilMisc.getColorCode(EnumChatFormatting.GOLD) + itemStack.getDisplayName());
            }
        }
    }

    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {

        player.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
        return itemStack;
    }

    @Override
    public void setDuration(ItemStack stack, int amount) {

        if(stack != null) {

            if(amount >= 0) {

                UtilItemStack.setNBTTagInt(stack, "duration", amount);
            }
        }
    }

    public int getMaxItemUseDuration(ItemStack stack) {

        return getDuration(stack);
    }

    public EnumAction getItemUseAction(ItemStack itemStack) {

        return EnumAction.eat;
    }

    public ItemStack onEaten(ItemStack itemStack, World world, EntityPlayer player) {

        for(ItemStack stack : this.getIngredients(itemStack)) {

            if(stack.getItem() instanceof IPotionIngredient) {
                
                ((IPotionIngredient)stack.getItem()).onEntityDrinkPotion(world, stack, player);
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

        if(stack != null) {

            if(amount >= 0) {

                UtilItemStack.setNBTTagInt(stack, "strength", amount);
            }
        }
    }

    @Override
    public int getStrength(ItemStack stack) {

        return UtilItemStack.getNBTTagInt(stack, "strength");
    }

    @Override
    public void setisThrowable(ItemStack stack, boolean newValue) {

        if(stack != null) {

            UtilItemStack.setNBTTagBoolean(stack, "canThrow", newValue);
        }
    }

    @Override
    public boolean isThrowable(ItemStack stack) {

        return UtilItemStack.getNBTTagBoolean(stack, "canThrow");
    }

    @Override
    public ArrayList<ItemStack> getIngredients(ItemStack stack) {

        return UtilItemStack.getNBTTagInventory(stack, "Items");
    }

    @Override
    public void setIngredients(ItemStack stack, ArrayList<ItemStack> ingredients) {

        UtilItemStack.setNBTTagInventory(stack, "Items", ingredients);
    }
}

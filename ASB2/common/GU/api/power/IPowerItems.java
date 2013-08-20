package GU.api.power;

import net.minecraft.item.ItemStack;

public interface IPowerItems {

    int getPowerStored(ItemStack item);

    int getPowerMax(ItemStack item);

    boolean usePower(ItemStack item, int PowerUsed);

    boolean gainPower(ItemStack item, int PowerGained);

    boolean canUsePower(ItemStack item, int PowerUsed);

    boolean canGainPower(ItemStack item, int PowerGained);

    String getName();
}

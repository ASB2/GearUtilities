package GU.api.familar;

import java.util.EnumSet;

import net.minecraft.item.ItemStack;

public interface IFamilarEgg {

    boolean grow(ItemStack stack, EnumType growthType);
    EnumType getFamilarType(ItemStack stack);
    EnumGrowthStage getGrowthStange(ItemStack stack);
    EnumSet<EnumGenes> getGenes(ItemStack stack);
}

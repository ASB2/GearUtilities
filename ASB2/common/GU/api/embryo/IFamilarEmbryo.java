package GU.api.embryo;

import java.util.EnumSet;

import net.minecraft.item.ItemStack;

public interface IFamilarEmbryo {

    boolean grow(ItemStack stack, EnumType growthType);
    EnumType getFamilarType(ItemStack stack);
    EnumGrowthStage getGrowthStange(ItemStack stack);
    EnumSet<EnumGenes> getGenes(ItemStack stack);
}

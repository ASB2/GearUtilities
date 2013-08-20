package GU.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import GU.info.*;

public abstract class EnchantmentBase extends Enchantment {

    String name = "";

    public EnchantmentBase(int par1, int par2,
            EnumEnchantmentType enchantmentType) {
        super(par1, par2, enchantmentType);

    }

    @Override
    public Enchantment setName(String string) {

        name = string;

        return super.setName(Reference.UNIQUE_ID + name);
    }
}

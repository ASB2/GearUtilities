package GU.api;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IWrenchable {

    void triggerBlock(World world, EntityLivingBase player, ItemStack itemStack, int x, int y, int z, int side);
    
    void triggerBlock(World world, boolean isSneaking, ItemStack itemStack, int x, int y, int z, int side);
}

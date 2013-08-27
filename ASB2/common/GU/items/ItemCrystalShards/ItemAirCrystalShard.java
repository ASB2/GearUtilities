package GU.items.ItemCrystalShards;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemAirCrystalShard extends ItemCrystal {

    public ItemAirCrystalShard(int par1) {
        super(par1);
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        
        return false;
    }
}
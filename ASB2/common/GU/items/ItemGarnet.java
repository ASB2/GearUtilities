package GU.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemGarnet extends ItemBase {

    public ItemGarnet(int id) {
        super(id);

    }

    @Override
    public boolean onItemUseFirst(ItemStack itemStack, EntityPlayer player,
            World world, int x, int y, int z, int side, float hitX, float hitY,
            float hitZ) {
        return super.onItemUseFirst(itemStack, player, world, x, y, z, side,
                hitX, hitY, hitZ);
    }
}
package GU.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import ASB2.utils.UtilPlayers;

public class ItemRainBGone extends ItemBase {

    public ItemRainBGone(int par1) {
        super(par1);
        this.setMaxDamage(10);
        this.setMaxStackSize(1);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world,
            EntityPlayer player) {

        if (world.isRaining()) {

            world.toggleRain();
            itemStack.damageItem(1, player);

            UtilPlayers.sendChatToPlayer(player, "The rain will soon be dispursed");
        }
        return itemStack;
    }
}

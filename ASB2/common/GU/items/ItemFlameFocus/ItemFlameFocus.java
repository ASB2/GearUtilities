package GU.items.ItemFlameFocus;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import GU.items.ItemBase;

public class ItemFlameFocus extends ItemBase {

    public ItemFlameFocus(int id) {
        super(id);
        this.setMaxStackSize(1);
    }

    @Override
    @SuppressWarnings({ "rawtypes" })
    public void addInformationSneaking(ItemStack itemStack, EntityPlayer player, java.util.List info, boolean var1) {
        super.addInformationSneaking(itemStack, player, info, var1);
        // info.add("Stored Power: " + this.getPowerProvider(itemStack).getPowerStored());
        // info.add("Maximum Power: " + this.getPowerProvider(itemStack).getPowerMax());
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitx, float hity, float hitz) {

        // if (player.isSneaking()) {
        // if (player.capabilities.isCreativeMode) {
        //
        // this.getPowerProvider(itemStack).setPowerStored(this.getPowerProvider(itemStack).getPowerMax());
        // return true;
        // }
        // }
        return false;
    }
}

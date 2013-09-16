package GU.items.ItemBloodStone;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import GU.api.power.IPowerItem;
import GU.api.power.IPowerProvider;
import GU.api.power.ItemPowerProvider;
import GU.items.ItemBase;

public class ItemBloodStone extends ItemBase implements IPowerItem {

    public ItemBloodStone(int id) {
        super(id);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void addInformationSneaking(ItemStack itemStack, EntityPlayer player, java.util.List info, boolean var1) {

        info.add("Stored Power: " + this.getPowerProvider(itemStack).getPowerStored());
        info.add("Maximum Power: " + this.getPowerProvider(itemStack).getPowerMax());
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitx, float hity, float hitz) {

        return true;
    }

    @Override
    public IPowerProvider getPowerProvider(ItemStack stack) {

        return new ItemPowerProvider(stack, 1000);
    }
}

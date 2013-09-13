package GU.items.ItemBloodStone;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import GU.api.power.IItemPowerProvider;
import GU.api.power.IPowerItems;
import GU.items.ItemBase;
import GU.power.ItemPowerProvider;

public class ItemBloodStone extends ItemBase implements IPowerItems {

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

        this.getPowerProvider(itemStack).setPowerStored(1000);
        return true;
    }
    
    @Override
    public IItemPowerProvider getPowerProvider(ItemStack stack) {

        return new ItemPowerProvider(stack, 1000);
    }
}

package GU.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import GU.api.runes.*;

public class ItemTestItem extends ItemBase implements IRuneItem {

    public ItemTestItem(int id) {
        super(id);
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10) {

        return true;
    }

    @Override
    public void onUpdate(IRuneBlock block, ItemStack stack, int x, int y, int z) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean shouldUpdate(IRuneBlock block, ItemStack stack, int x, int y, int z) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Item getItem() {
        // TODO Auto-generated method stub
        return this;
    }

    @Override
    public void onRemoval(IRuneBlock block, ItemStack stack, int x, int y, int z) {
        // TODO Auto-generated method stub
        
    }

}

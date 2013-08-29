package GU.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import GU.api.runes.IRuneBlock;
import GU.api.runes.IRuneItem;
import GU.utils.*;

public class ItemTestItem extends ItemBase implements IRuneItem, IBlockCycle {

    public ItemTestItem(int id) {
        super(id);
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10) {

        return true;
    }

    @Override
    public void onUpdate(World world, IRuneBlock block, ItemStack stack, int x, int y, int z) {

        UtilBlock.cycle3DBlock(null, world, x + block.getOrientation().offsetX, y + block.getOrientation().offsetY, z + block.getOrientation().offsetZ, block.getOrientation().getOpposite(), 25, 25 + 1 + 25, this, 0);
    }

    @Override
    public boolean shouldUpdate(World world, IRuneBlock block, ItemStack stack, int x, int y, int z) {

        return true;
    }

    @Override
    public void randomUpdate(World world, IRuneBlock block, ItemStack stack, int x, int y, int z) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Item getItem() {

        return this;
    }

    @Override
    public boolean execute(EntityPlayer player, World world, int x, int y, int z, ForgeDirection side, int id) {

        UtilBlock.breakBlock(world, x, y, z);
        return false;
    }

}

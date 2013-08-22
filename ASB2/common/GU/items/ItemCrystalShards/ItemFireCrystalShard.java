package GU.items.ItemCrystalShards;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import GU.color.IColorable;

public class ItemFireCrystalShard extends ItemCrystal {

    public ItemFireCrystalShard(int id) {
        super(id);
    }

    @SuppressWarnings("unused")
    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player,
            World world, int x, int y, int z, int side, float hitX, float hitY,
            float hitZ) {
        TileEntity tile = world.getBlockTileEntity(x, y, z);

        if (tile instanceof IColorable) {

            IColorable colorAble = ((IColorable) tile);
        }
        return false;
    }
}

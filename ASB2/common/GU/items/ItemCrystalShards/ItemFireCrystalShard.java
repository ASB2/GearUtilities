package GU.items.ItemCrystalShards;

import GU.color.IColorable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class ItemFireCrystalShard extends ItemCrystal {

    public ItemFireCrystalShard(int id) {
        super(id);
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player,
            World world, int x, int y, int z, int side, float hitX, float hitY,
            float hitZ) {
        TileEntity tile = world.getBlockTileEntity(x, y, z);

        if (tile instanceof IColorable) {

            IColorable colorAble = ((IColorable) tile);

            if (!player.isSneaking()) {

                colorAble.changeRed(1, ForgeDirection.getOrientation(side));
                return true;
            } else {

                colorAble.changeRed(-1, ForgeDirection.getOrientation(side));
                return true;
            }
        }
        return false;
    }
}

package GU.items.ItemCrystalShards;

import GU.color.IColorable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class ItemWaterCrystalShard extends ItemCrystal {

    public ItemWaterCrystalShard(int par1) {
        super(par1);

    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player,
            World world, int x, int y, int z, int side, float hitX, float hitY,
            float hitZ) {
        TileEntity tile = world.getBlockTileEntity(x, y, z);

        if (tile instanceof IColorable) {

            IColorable colorAble = ((IColorable) tile);

            if (!player.isSneaking()) {

                colorAble.changeBlue(1, ForgeDirection.getOrientation(side));
                return true;
            } else {

                colorAble.changeBlue(-1, ForgeDirection.getOrientation(side));
                return true;
            }
        }
        return false;
    }
}

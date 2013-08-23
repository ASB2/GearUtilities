package GU.items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import GU.api.IWrenchable;

public class ItemAdvancedStick extends ItemBase {

    public ItemAdvancedStick(int id) {
        super(id);
        this.setMaxStackSize(1);
        this.setFull3D();
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10) {

        TileEntity tile = world.getBlockTileEntity(x, y, z);

        if (tile != null && tile instanceof IWrenchable) {

            ((IWrenchable) tile).triggerBlock(world, player.isSneaking(), itemStack, x, y, z, side);
            return true;
        } else {

            Block block = Block.blocksList[world.getBlockId(x, y, z)];

            if (block instanceof IWrenchable) {

                ((IWrenchable) block).triggerBlock(world, player.isSneaking(), itemStack, x, y, z, side);
                return true;
            }
        }

        return true;
    }
}

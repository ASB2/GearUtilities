package GU.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import ASB2.utils.UtilBlock;
import ASB2.utils.UtilDirection;
import ASB2.utils.UtilEntity;
import GU.BlockRegistry;

public class ItemPhantomPlacer extends ItemBase {

    public ItemPhantomPlacer(int id) {
        super(id);
        this.setMaxStackSize(1);
        this.setFull3D();
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void addInformationSneaking(ItemStack itemStack, EntityPlayer player, java.util.List info, boolean var1) {

        info.add("Hold shift to place above or below you.");
        info.add("or don't to place at cardinal directions");
        info.add("Made just for you " + player.username);
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitx, float hity, float hitz) {

        int[] coords = UtilDirection.translateDirectionToCoords( ForgeDirection.getOrientation(side), x, y, z);

        UtilBlock.placeBlockInAir(world, coords[0], coords[1], coords[2], BlockRegistry.BlockPhantomBlock.blockID, 0);
        return true;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {

        if (!world.isRemote) {

            int[] coords = UtilDirection.translateDirectionToCoords(UtilEntity
                    .getEntityDirection(player, !player.isSneaking()),
                    (int) player.posX, (int) player.posY, (int) player.posZ);

            if (UtilEntity.getEntityDirection(player, !player.isSneaking()) == ForgeDirection.DOWN) {

                UtilBlock.placeBlockInAir(world, coords[0], coords[1],
                        coords[2] - 1, BlockRegistry.BlockPhantomBlock.blockID,
                        0);
                return itemStack;
            } else if (UtilEntity.getEntityDirection(player,
                    !player.isSneaking()) == ForgeDirection.DOWN) {

                UtilBlock.placeBlockInAir(world, coords[0] - 2, coords[1] + 1,
                        coords[2] - 1, BlockRegistry.BlockPhantomBlock.blockID,
                        0);
            } else {
                UtilBlock.placeBlockInAir(world, coords[0] - 1, coords[1] + 1,
                        coords[2] - 1, BlockRegistry.BlockPhantomBlock.blockID,
                        0);
            }
        }
        return itemStack;
    }
}

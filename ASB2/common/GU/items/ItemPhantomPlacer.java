package GU.items;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import GU.BlockRegistry;
import GU.utils.UtilBlock;
import GU.utils.UtilDirection;
import GU.utils.UtilPlayers;

public class ItemPhantomPlacer extends ItemBase {

    public ItemPhantomPlacer(int id) {
        super(id);
    }

    public int idDropped(int par1, Random random, int par3)
    {
        return -1;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {

        if(!world.isRemote) {
            
            int[] coords = UtilDirection.translateDirectionToCoords(UtilPlayers.getEntityDirection(player, !player.isSneaking()), (int)player.posX, (int)player.posY, (int)player.posZ);

            if(UtilPlayers.getEntityDirection(player, !player.isSneaking()) == ForgeDirection.DOWN) {
                
                UtilBlock.placeBlockInAir(world, coords[0], coords[1] - 1, coords[2], BlockRegistry.BlockPhantomBlock.blockID, 0);
                return itemStack;
            }
            UtilBlock.placeBlockInAir(world, coords[0], coords[1] + 1, coords[2], BlockRegistry.BlockPhantomBlock.blockID, 0);
        }
        return itemStack;
    }
}

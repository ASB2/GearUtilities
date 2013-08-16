package GU.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import GU.BlockRegistry;
import GU.utils.UtilBlock;
import GU.utils.UtilDirection;
import GU.utils.UtilMisc;
import GU.utils.UtilPlayers;

public class ItemPhantomPlacer extends ItemBase {

    public ItemPhantomPlacer(int id) {
        super(id);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, java.util.List info, boolean var1) {        
        super.addInformation(itemStack, player, info, var1);
        
        info.add(UtilMisc.getColorCode(EnumChatFormatting.GOLD) + "Hold shift to place above or below you");
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

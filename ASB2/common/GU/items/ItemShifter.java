package GU.items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import GU.api.IShiftable;
import GU.*;

public class ItemShifter extends ItemBase {

    public ItemShifter(int id) {
        super(id);
        this.setMaxStackSize(1);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void addInformationSneaking(ItemStack itemStack, EntityPlayer player, java.util.List info, boolean var1) {

        info.add("Made just for you " + player.username);
        info.add("Shifts Things to Other Things");
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10) {

        TileEntity tile = world.getBlockTileEntity(x, y, z);

        if(tile != null && tile instanceof IShiftable) {

            ((IShiftable)tile).shiftBlock(world, x, y, z, side, new Object[]{player, itemStack});            
            return true;
        }
        else { 

            Block block = Block.blocksList[world.getBlockId(x, y, z)];

            if(block != null) {

                if(block instanceof IShiftable) {

                    ((IShiftable)block).shiftBlock(world, x, y, z, side, new Object[]{player, itemStack});    
                }
                else {
                   
                    VinillaLookup.shiftingBlockLookup(world, x, y, z);
                }
            }
        }
        return false;
    }    
}

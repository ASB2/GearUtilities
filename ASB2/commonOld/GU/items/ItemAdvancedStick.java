package GU.items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import GU.api.IWrenchable;

public class ItemAdvancedStick extends ItemBase {
    
    public ItemAdvancedStick() {
        this.setFull3D();
    }
    
    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10) {
        
        TileEntity tile = world.getTileEntity(x, y, z);
        
        if (tile != null && tile instanceof IWrenchable) {
            
            ((IWrenchable) tile).triggerBlock(world, player.isSneaking(), itemStack, x, y, z, side);
            return true;
        }
        else {
            
            Block block = world.getBlock(x, y, z);
            
            if (block instanceof IWrenchable) {
                
                ((IWrenchable) block).triggerBlock(world, player.isSneaking(), itemStack, x, y, z, side);
                return true;
            }
            else {
                
                block.rotateBlock(world, x, y, z, ForgeDirection.getOrientation(side));
            }
        }
        
        return true;
    }
}

package GUOLD.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import ASB2.utils.UtilBlock;
import ASB2.utils.UtilDirection;
import ASB2.vector.Vector3;
import GUOLD.BlockRegistry;

public class ItemPhantomPlacer extends ItemBase {
    
    public ItemPhantomPlacer() {
        
        this.setMaxStackSize(1);
        this.setFull3D();
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void addInformationSneaking(ItemStack itemStack, EntityPlayer player, java.util.List info, boolean var1) {
        
        info.add("Hold shift to place above or below you.");
        info.add("or don't to place at cardinal directions");
    }
    
    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitx, float hity, float hitz) {
        
        int[] coords = UtilDirection.translateDirectionToCoords(ForgeDirection.getOrientation(side), x, y, z);
        
        UtilBlock.placeBlockInAir(world, coords[0], coords[1], coords[2], BlockRegistry.BlockPhantomBlock, 0);
        return true;
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        
        if (!world.isRemote) {
            
            Vector3 position = new Vector3(player);
            
            int[] coords = UtilDirection.translateDirectionToCoords(ForgeDirection.UP, position.intX(), position.intY() + 2, position.intZ());
            
            UtilBlock.placeBlockInAir(world, coords[0], coords[1], coords[2], BlockRegistry.BlockPhantomBlock, 0);
        }
        return itemStack;
    }
}
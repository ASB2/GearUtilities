package GU.items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import GU.api.IWrenchable;
import GU.items.ItemMetadata.ItemMetadataWrapper;
import GU.items.ItemRenderers.AdvancedStickRenderer;

public class AdvancedStickWrapper extends ItemMetadataWrapper {
    
    public AdvancedStickWrapper(String ign) {
        super(ign);
    }
    
    @Override
    public void postInitRender() {
        
        this.setRenderer(AdvancedStickRenderer.instance);
    }
    
    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        
        IWrenchable toWrench = null;
        TileEntity tile = world.getTileEntity(x, y, z);
        
        if (tile != null && tile instanceof IWrenchable) {
            
            toWrench = (IWrenchable) tile;
        } else {
            
            Block block = world.getBlock(x, y, z);
            
            if (block != null && block instanceof IWrenchable) {
                
                toWrench = (IWrenchable) block;
            }
        }
        
        if (toWrench != null) {
            
            return toWrench.triggerBlock(world, player.isSneaking(), itemStack, x, y, z, side);
        }
        return super.onItemUse(itemStack, player, world, x, y, z, side, hitX, hitY, hitZ);
    }
}

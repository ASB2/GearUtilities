package GU.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
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
        
        Block block = world.getBlock(x, y, z);
        
        if (block != null && block.isAir(world, x, y, z)) {
            
            return block.rotateBlock(world, x, y, z, ForgeDirection.getOrientation(side));
        }
        return false;
    }
    
    public boolean doesSneakBypassUse(World world, int x, int y, int z, EntityPlayer player) {
        
        return true;
    }
}

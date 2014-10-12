package GU.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import GU.items.ItemMetadata.ItemMetadataWrapper;
import GU.items.ItemRenderers.UtilityTabletRenderer;

public class UtilityTabletWrapper extends ItemMetadataWrapper {
    
    public UtilityTabletWrapper(String ign) {
        super(ign);
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void postInitRender() {
        
        this.setRenderer(UtilityTabletRenderer.instance);
    }
    
    @Override
    public int getItemStackLimit(ItemStack stack) {
        
        return 1;
    }
    
    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        
//        player.setPositionAndUpdate(0, 100, 0);
//        player.travelToDimension(373);
        
        return false;
    }
}

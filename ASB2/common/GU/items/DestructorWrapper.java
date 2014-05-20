package GU.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import GU.items.ItemMetadata.MetadataWrapper;

public class DestructorWrapper extends MetadataWrapper {
    
    public DestructorWrapper(String ign) {
        super(ign);
    }
    
    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        // TODO Auto-generated method stub
        return super.onItemUse(itemStack, player, world, x, y, z, side, hitX, hitY, hitZ);
    }
}

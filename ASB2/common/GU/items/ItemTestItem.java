package GU.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import GU.entity.EntityTest.*;

public class ItemTestItem extends ItemBase {

    public ItemTestItem(int id) {
        super(id);
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10)
    {
        if(!world.isRemote && side == 1 ) {

            if(!player.isSneaking()) {
                
                EntityTest testEntity = new EntityTest(world);

                testEntity.setPosition(x, y + 1, z);
                world.spawnEntityInWorld(testEntity);
                return true;
            }
            else {

            }
        }
        return false;
    }

}

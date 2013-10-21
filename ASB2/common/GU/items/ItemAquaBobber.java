package GU.items;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import ASB2.vector.Vector3;

public class ItemAquaBobber extends ItemBase {

    public ItemAquaBobber(int id) {
        super(id);

        this.setMaxStackSize(1);    
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {

        float amount = .5f;
        
        Vector3 position = new Vector3(player);
        Material material = position.getBlockMaterial(world);

        if(material != null) {

            if(material.isLiquid()) {

                if(!player.isSneaking()) {

                    player.motionY += amount;
                }
                else {

                    player.motionY -= amount;
                }

                player.fallDistance = 0;
                return stack;
            }
        }
        position.y -= 1;
        material = position.getBlockMaterial(world);
        
        if(material != null) {

            if(material.isLiquid()) {

                if(!player.isSneaking()) {

                    player.motionY += 1;
                }
                else {

                    player.motionY -= 1;
                }

                player.fallDistance = 0;
                return stack;
            }
        }
        return stack;
    }
}

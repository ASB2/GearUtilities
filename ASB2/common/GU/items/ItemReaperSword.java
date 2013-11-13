package GU.items;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import ASB2.items.SwordBase;
import GU.api.power.PowerHelper;
import GU.*;

public class ItemReaperSword extends SwordBase {

    public ItemReaperSword(int id, EnumToolMaterial material, String iconLocation) {
        super(id, material, iconLocation);
        this.setCreativeTab(GearUtilities.tabGUItems);
    }

    @Override
    public boolean hitEntity(ItemStack itemStack, EntityLivingBase gotHit, EntityLivingBase hitter) {

        if(hitter instanceof EntityPlayer && !(gotHit instanceof EntityPlayer)) {

            PowerHelper.addEnergyToInventory(((EntityPlayer)hitter).inventory, .5f, true);
        }
        return super.hitEntity(itemStack, gotHit, hitter);
    }
}

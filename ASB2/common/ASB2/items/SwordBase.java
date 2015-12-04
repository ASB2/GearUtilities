package ASB2.items;

import net.minecraft.item.ItemSword;


public class SwordBase extends ItemSword {

    String iconLocation;

    public SwordBase(ToolMaterial material, String iconLocation) {
        super( material);

        this.iconLocation = iconLocation;
        this.setUnlocalizedName(iconLocation);
    }
    
    @Override
    public void registerIcons(IIconRegister iconRegister){

        itemIcon = iconRegister.registerIcon(iconLocation);
    }
}

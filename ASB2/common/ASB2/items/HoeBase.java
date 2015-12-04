package ASB2.items;

import net.minecraft.item.ItemHoe;


public class HoeBase extends ItemHoe {

    String iconLocation;
    
    public HoeBase(ToolMaterial material, String iconLocation) {
        super( material);
        
        this.iconLocation = iconLocation;
        this.setUnlocalizedName(iconLocation);
    }

    @Override
    public void registerIcons(IIconRegister iconRegister){

        itemIcon = iconRegister.registerIcon(iconLocation);
    }
}

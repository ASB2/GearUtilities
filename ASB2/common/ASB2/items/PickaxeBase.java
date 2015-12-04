package ASB2.items;

import net.minecraft.item.ItemPickaxe;


public class PickaxeBase extends ItemPickaxe {

    String iconLocation;
    
    public PickaxeBase(ToolMaterial material, String iconLocation) {
        super( material);
        
        this.iconLocation = iconLocation;
        this.setUnlocalizedName(iconLocation);
    }

    @Override
    public void registerIcons(IIconRegister iconRegister){

        itemIcon = iconRegister.registerIcon(iconLocation);
    }
}

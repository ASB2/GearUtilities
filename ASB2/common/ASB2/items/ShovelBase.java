package ASB2.items;

import net.minecraft.item.ItemSpade;


public class ShovelBase extends ItemSpade {

    String iconLocation;

    public ShovelBase(ToolMaterial material, String iconLocation) {
        super( material);
        
        this.iconLocation = iconLocation;
        this.setUnlocalizedName(iconLocation);
    }

    @Override
    public void registerIcons(IIconRegister iconRegister){

        itemIcon = iconRegister.registerIcon(iconLocation);
    }
}

package ASB2.items;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemAxe;


public class AxeBase extends ItemAxe {
    
    String iconLocation;
    
    public AxeBase(ToolMaterial material, String iconLocation) {
        super(material);
        
        this.iconLocation = iconLocation;
        this.setUnlocalizedName(iconLocation);
    }
    
    @Override
    public void registerIcons(IIconRegister iconRegister) {
        
        itemIcon = iconRegister.registerIcon(iconLocation);
    }
}

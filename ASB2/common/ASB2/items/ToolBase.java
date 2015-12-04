package ASB2.items;

import java.util.Set;

import net.minecraft.item.ItemTool;

public class ToolBase extends ItemTool {
    
    String iconLocation;
    protected float speed = 1;
    
    public ToolBase(float speed, ToolMaterial material, Set<Block> validBlocks, String iconLocation) {
        super(speed, material, validBlocks);
        
        this.iconLocation = iconLocation;
        this.setUnlocalizedName(iconLocation);
        this.speed = speed;
    }
    
    @Override
    public void registerIcons(IIconRegister iconRegister) {
        
        itemIcon = iconRegister.registerIcon(iconLocation);
    }
}

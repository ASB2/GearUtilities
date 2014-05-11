package GUOLD;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import GUOLD.info.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GUCreativeTab extends CreativeTabs {
    
    String name;
    
    public GUCreativeTab(int par1, String name) {
        super(par1, name);
        
        this.name = name;
    }
    
    public GUCreativeTab(String name) {
        super(name);
        
        this.name = name;
    }
    
//    @Override
//    @SideOnly(Side.CLIENT)
//    public int getTabIconItemIndex() {
//        
//        if (name.equalsIgnoreCase(Reference.NAME + ": Blocks")) {
//            
//            return BlockRegistry.BlockEnhancedBricks.blockID;
//        } else if (name.equalsIgnoreCase(Reference.NAME + ": Fluids")) {
//            
//            return ItemRegistry.ItemStorageCrystal.itemID;
//        }
//        return ItemRegistry.ItemGearReader.itemID;
//    }
    
    @Override
    public String getTranslatedTabLabel() {
        
        return name;
    }

    @Override
    public Item getTabIconItem() {
        // TODO Auto-generated method stub
        return null;
    }
}
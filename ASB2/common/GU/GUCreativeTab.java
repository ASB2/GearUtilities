package GU;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class GUCreativeTab extends CreativeTabs {
    
    String name;
    
    public GUCreativeTab(String name) {
        super(name);
        
        this.name = name;
    }
    
    // @Override
    // @SideOnly(Side.CLIENT)
    // public int getTabIconItemIndex() {
    //
    // if (name.equalsIgnoreCase(Reference.NAME + ": Blocks")) {
    //
    // return BlockRegistry.BlockEnhancedBricks.blockID;
    // } else if (name.equalsIgnoreCase(Reference.NAME + ": Fluids")) {
    //
    // return ItemRegistry.ItemStorageCrystal.itemID;
    // }
    // return ItemRegistry.ItemGearReader.itemID;
    // }
    
    @Override
    public String getTranslatedTabLabel() {
        
        return name;
    }
    
    @Override
    public Item getTabIconItem() {
        // TODO Auto-generated method stub
        return Items.diamond;
    }
}
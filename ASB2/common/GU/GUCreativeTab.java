package GU;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import GU.info.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

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
        
        switch (name) {
        
            case (Reference.NAME + ": Blocks"): {
                
                return ItemRegistry.ELECTIS_CRYSTAL_SHARD.getItem();
            }
        }
        return Items.diamond;
    }
    
    @SideOnly(Side.CLIENT)
    public ItemStack getIconItemStack() {
        
        switch (name) {
        
            case (Reference.NAME + ": Blocks"): {
                
                return new ItemStack(BlockRegistry.METADATA_ORE, 1, 0);
            }
            case (Reference.NAME + ": Items"): {
                
                return ItemRegistry.ELECTIS_CRYSTAL_SHARD;
            }
            case (Reference.NAME + ": Fluids"): {
                
                // return ItemRegistry.ELECTIS_CRYSTAL;
                return new ItemStack(Items.diamond);
            }
        }
        return new ItemStack(Items.diamond);
    }
}
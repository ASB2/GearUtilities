package GU;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import GU.api.color.VanillaColor;
import GU.info.Reference;

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
                
                return new ItemStack(BlockRegistry.ELECTIS_POLYHEDRON, 1, 0);
            }
            case (Reference.NAME + ": Items"): {
                
                return ItemRegistry.ELECTIS_CRYSTAL_SHARD;
            }
            case (Reference.NAME + ": Fluids"): {
                
                return new ItemStack(ItemRegistry.ITEM_FLUID);
                // return new ItemStack(Items.diamond);
            }
            case (Reference.NAME + ": Structure Cubes"): {
                
                return new ItemStack(BlockRegistry.STRUCTURE_CUBES.get(VanillaColor.WHITE.ordinal()));
            }
        }
        return new ItemStack(Items.diamond);
    }
}
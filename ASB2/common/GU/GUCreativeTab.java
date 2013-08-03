package GU;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;

public class GUCreativeTab extends CreativeTabs {

    String name;

    public GUCreativeTab(int par1, String name) {
        super(par1, name);

        this.name = name;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getTabIconItemIndex() {

        return 1;
        // return ItemRegistry.ItemEnergyCrystalShard.itemID;
    }

    @Override
    public String getTranslatedTabLabel() {

        return name;
    }
}
package GU;

import GU.info.Reference;
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

        if (name.equalsIgnoreCase(Reference.NAME + ": Blocks")) {

            return BlockRegistry.BlockSpeedyRoad.blockID;
        }
        return ItemRegistry.ItemGearReader.itemID;
    }

    @Override
    public String getTranslatedTabLabel() {

        return name;
    }
}
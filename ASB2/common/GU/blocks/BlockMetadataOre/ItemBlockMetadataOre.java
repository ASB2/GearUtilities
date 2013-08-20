package GU.blocks.BlockMetadataOre;

import net.minecraft.item.ItemStack;
import GU.GUItemBlock;
import GU.info.Reference;

public class ItemBlockMetadataOre extends GUItemBlock {

    public ItemBlockMetadataOre(int id) {
        super(id);

        this.setHasSubtypes(true);
        this.setMaxDamage(0);
    }

    @Override
    public int getMetadata(int damageValue) {

        return damageValue;
    }

    public String getUnlocalizedName(ItemStack itemStack) {

        switch (itemStack.getItemDamage()) {

            case 0:
                return Reference.UNIQUE_ID + "BlockAirCrystalOre";
            case 1:
                return Reference.UNIQUE_ID + "BlockEarthCrystalOre";
            case 2:
                return Reference.UNIQUE_ID + "BlockFireCrystalOre";
            case 3:
                return Reference.UNIQUE_ID + "BlockWaterCrystalOre";
            case 4:
                return Reference.UNIQUE_ID + "BlockEnergyCrystalOre";
            default:
                return "Unknown Metadata Notify ASB2";
        }
    }

    @Override
    public String getItemStackDisplayName(ItemStack itemStack) {

        switch (itemStack.getItemDamage()) {

            case 0:
                return "Air Crystal Ore";
            case 1:
                return "Earth Crystal Ore";
            case 2:
                return "Fire Crystal Ore";
            case 3:
                return "Water Crystal Ore";
            case 4:
                return "Energy Crystal Ore";
            default:
                return "Unknown Metadata Notify ASB2";
        }
    }
}

package GU.blocks.BlockMetadataOre;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import GU.info.GUItemBlock;
import GU.info.Reference;

public class ItemBlockMetadataBlock extends GUItemBlock {

    public ItemBlockMetadataBlock(int id) {
        super(id);

        this.setHasSubtypes(true);
        this.setMaxDamage(0);
    }

    @Override
    public int getMetadata(int damageValue) {

        return damageValue;
    }

    @Override
    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
        
        boolean itWorked = super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);

        world.setBlockMetadataWithNotify(x, y, z, this.getDamage(stack), 3);
        return itWorked;
    }

    @Override
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
            case 5:
                return Reference.UNIQUE_ID + "BlockGarnetOre";                
            case 6:
                return Reference.UNIQUE_ID + "BlockGarnetBlock";
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
            case 5:
                return "Garnet Ore";
            case 6:
                return "Garnet Block";
            default:
                return "Unknown Metadata Notify ASB2";
        }
    }
}

package GU.blocks.BlockMetadataOre;

import java.util.List;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import GU.ItemRegistry;
import GU.blocks.BlockBase;
import GU.info.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockMetadataOre extends BlockBase {

    Icon air;
    Icon earth;
    Icon fire;
    Icon water;
    Icon energy;

    public BlockMetadataOre(int id, Material material) {
        super(id, material);
    }

    public void setBlockName(String texture) {

        this.blockName = texture;
        this.setUnlocalizedName(Reference.UNIQUE_ID + blockName);
        GameRegistry.registerBlock(this, ItemBlockMetadataOre.class,
                this.getUnlocalizedName());
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {
        super.registerIcons(iconRegister);

        air = iconRegister.registerIcon(Reference.MODDID
                + ":BlockAirCrystalOre");
        earth = iconRegister.registerIcon(Reference.MODDID
                + ":BlockEarthCrystalOre");
        fire = iconRegister.registerIcon(Reference.MODDID
                + ":BlockFireCrystalOre");
        water = iconRegister.registerIcon(Reference.MODDID
                + ":BlockWaterCrystalOre");
        energy = iconRegister.registerIcon(Reference.MODDID
                + ":BlockEnergyCrystalOre");
    }

    @Override
    public Icon getIcon(int side, int metadata) {

        switch (metadata) {
            case 0:
                return air;
            case 1:
                return earth;
            case 2:
                return fire;
            case 3:
                return water;
            case 4:
                return energy;
            default:
                return super.getIcon(side, metadata);
        }
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void getSubBlocks(int unknown, CreativeTabs tab, List subItems) {

        subItems.add(new ItemStack(this, 1, 0));
        subItems.add(new ItemStack(this, 1, 1));
        subItems.add(new ItemStack(this, 1, 2));
        subItems.add(new ItemStack(this, 1, 3));
        subItems.add(new ItemStack(this, 1, 4));

    }

    @Override
    public int idDropped(int par1, Random par2Random, int par3) {

        switch (par1) {

            case 0:
                return ItemRegistry.ItemAirCrystalShard.itemID;
            case 1:
                return ItemRegistry.ItemEarthCrystalShard.itemID;
            case 2:
                return ItemRegistry.ItemFireCrystalShard.itemID;
            case 3:
                return ItemRegistry.ItemWaterCrystalShard.itemID;
            case 4:
                return ItemRegistry.ItemEnergyCrystalShard.itemID;
            default:
                return 0;
        }
    }

    @Override
    public int quantityDropped(Random par1Random) {

        return par1Random.nextInt(5) + 1;
    }

    @Override
    public int damageDropped(int metadata) {

        return metadata;
    }
}

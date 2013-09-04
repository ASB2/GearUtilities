package GU.blocks.BlockMetadataOre;

import java.util.List;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
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
    Icon garnet;

    public BlockMetadataOre(int id, Material material) {
        super(id, material);
    }

    @Override
    public void setBlockName(String texture) {

        this.blockName = texture;
        this.setUnlocalizedName(Reference.UNIQUE_ID + blockName);
        GameRegistry.registerBlock(this, ItemBlockMetadataOre.class, this.getUnlocalizedName());
    }

    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int sideHit, float hitX, float hitY, float hitZ, int metaData) {

        return 0;
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {
        super.registerIcons(iconRegister);

        air = iconRegister.registerIcon(Reference.MODDID + ":BlockAirCrystalOre");
        earth = iconRegister.registerIcon(Reference.MODDID + ":BlockEarthCrystalOre");
        fire = iconRegister.registerIcon(Reference.MODDID + ":BlockFireCrystalOre");
        water = iconRegister.registerIcon(Reference.MODDID + ":BlockWaterCrystalOre");
        energy = iconRegister.registerIcon(Reference.MODDID + ":BlockEnergyCrystalOre");
        garnet = iconRegister.registerIcon(Reference.MODDID + ":BlockGarnetOre");
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
            case 5:
                return garnet; 

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
        subItems.add(new ItemStack(this, 1, 5));
    }

    @Override
    public int idDropped(int metadata, Random par2Random, int par3) {

        switch (metadata) {

            case 0:
                return ItemRegistry.ItemCrystal.ItemAirCrystalShard.itemID;
            case 1:
                return ItemRegistry.ItemCrystal.ItemEarthCrystalShard.itemID;
            case 2:
                return ItemRegistry.ItemCrystal.ItemFireCrystalShard.itemID;
            case 3:
                return ItemRegistry.ItemCrystal.ItemWaterCrystalShard.itemID;
            case 4:
                return ItemRegistry.ItemCrystal.ItemEnergyCrystalShard.itemID;
            case 5:
                return ItemRegistry.ItemGarnet.itemID;
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

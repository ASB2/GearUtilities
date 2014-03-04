package GU.blocks.BlockMetadataOre;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import ASB2.utils.UtilItemStack;
import GU.ItemRegistry;
import GU.api.flame.EnumFlameType;
import GU.blocks.BlockBase;
import GU.info.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BlockMetadataOre extends BlockBase {

    public final ItemStack BlockAirCrystalOre = new ItemStack(this, 1, AIR_CRYSTAL_ORE);
    public final ItemStack BlockEarthCrystalOre = new ItemStack(this, 1, EARTH_CRYSTAL_ORE);
    public final ItemStack BlockFireCrystalOre = new ItemStack(this, 1, FIRE_CRYSTAL_ORE);
    public final ItemStack BlockWaterCrystalOre = new ItemStack(this, 1, WATER_CRYSTAL_ORE);
    public final ItemStack BlockEnergyCrystalOre = new ItemStack(this, 1, ENERGY_CRYSTAL_ORE);
    public final ItemStack BlockGarnetOre = new ItemStack(this, 1, GARNET_ORE);

    public final ItemStack BlockGarnetBlock = new ItemStack(this, 1, GARNET_BLOCK);

    Icon[] icons = new Icon[7];

    public static final int AIR_CRYSTAL_ORE = 0;
    public static final int EARTH_CRYSTAL_ORE = 1;
    public static final int FIRE_CRYSTAL_ORE = 2;
    public static final int WATER_CRYSTAL_ORE = 3;
    public static final int ENERGY_CRYSTAL_ORE = 4;
    public static final int GARNET_ORE = 5;

    public static final int GARNET_BLOCK = 6;

    public BlockMetadataOre(int id, Material material) {
        super(id, material);

        this.specialMetadata = true;
        GameRegistry.registerBlock(this, ItemBlockMetadataBlock.class, this.getUnlocalizedName());

        LanguageRegistry.addName(BlockAirCrystalOre, "Air Crystal Ore");
        LanguageRegistry.addName(BlockEarthCrystalOre, "Earth Crystal Ore");
        LanguageRegistry.addName(BlockFireCrystalOre, "Fire Crystal Ore");
        LanguageRegistry.addName(BlockWaterCrystalOre, "Water Crystal Ore");
        LanguageRegistry.addName(BlockEnergyCrystalOre, "Energy Crystal Ore");
        LanguageRegistry.addName(BlockGarnetOre, "Garnet Ore");
        LanguageRegistry.addName(BlockGarnetBlock, "Garnet Block");
    }

    public String getUnlocalizedName(ItemStack stack) {

        switch (stack.getItemDamage()) {

            case AIR_CRYSTAL_ORE:
                return Reference.MODDID + ":" + "BlockAirCrystalOre";
            case EARTH_CRYSTAL_ORE:
                return Reference.MODDID + ":" + "BlockEarthCrystalOre";
            case FIRE_CRYSTAL_ORE:
                return Reference.MODDID + ":" + "BlockFireCrystalOre";
            case WATER_CRYSTAL_ORE:
                return Reference.MODDID + ":" + "BlockWaterCrystalOre";
            case ENERGY_CRYSTAL_ORE:
                return Reference.MODDID + ":" + "BlockEnergyCrystalOre";
            case GARNET_ORE:
                return Reference.MODDID + ":" + "BlockGarnetOre";
            case GARNET_BLOCK:
                return Reference.MODDID + ":" + "BlockGarnetBlock";
            default:
                return "Notify ASB2";
        }
    }

    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int sideHit, float hitX, float hitY, float hitZ, int metaData) {
        return 0;
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {
        super.registerIcons(iconRegister);

        icons[AIR_CRYSTAL_ORE] = iconRegister.registerIcon(Reference.MODDID + ":" + "BlockAirCrystalOre");
        icons[EARTH_CRYSTAL_ORE] = iconRegister.registerIcon(Reference.MODDID + ":" + "BlockEarthCrystalOre");
        icons[FIRE_CRYSTAL_ORE] = iconRegister.registerIcon(Reference.MODDID + ":" + "BlockFireCrystalOre");
        icons[WATER_CRYSTAL_ORE] = iconRegister.registerIcon(Reference.MODDID + ":" + "BlockWaterCrystalOre");
        icons[ENERGY_CRYSTAL_ORE] = iconRegister.registerIcon(Reference.MODDID + ":" + "BlockEnergyCrystalOre");
        icons[GARNET_ORE] = iconRegister.registerIcon(Reference.MODDID + ":" + "BlockGarnetOre");
        icons[GARNET_BLOCK] = iconRegister.registerIcon(Reference.MODDID + ":" + "BlockGarnetBlock");
    }

    @Override
    public Icon getIcon(int side, int metadata) {

        return icons[metadata];
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void getSubBlocks(int unknown, CreativeTabs tab, List subItems) {

        subItems.add(new ItemStack(this, 1, AIR_CRYSTAL_ORE));
        subItems.add(new ItemStack(this, 1, EARTH_CRYSTAL_ORE));
        subItems.add(new ItemStack(this, 1, FIRE_CRYSTAL_ORE));
        subItems.add(new ItemStack(this, 1, WATER_CRYSTAL_ORE));
        subItems.add(new ItemStack(this, 1, ENERGY_CRYSTAL_ORE));
        subItems.add(new ItemStack(this, 1, GARNET_ORE));
        subItems.add(new ItemStack(this, 1, GARNET_BLOCK));
    }

    @Override
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune) {

        ArrayList<ItemStack> list = new ArrayList<ItemStack>();

        if (!world.isRemote) {

            switch (metadata) {

                case AIR_CRYSTAL_ORE:
                    list.add(this.changeDrop(ItemRegistry.ItemCrystal.ItemAirCrystalShard, world.rand.nextInt(5) + 1));
                    break;
                case EARTH_CRYSTAL_ORE:
                    list.add(this.changeDrop(ItemRegistry.ItemCrystal.ItemEarthCrystalShard, world.rand.nextInt(5) + 1));
                    break;
                case FIRE_CRYSTAL_ORE:
                    list.add(this.changeDrop(ItemRegistry.ItemCrystal.ItemFireCrystalShard, world.rand.nextInt(5) + 1));
                    ItemStack stack = new ItemStack(ItemRegistry.ItemFlameShard, world.rand.nextInt(1), 0);
                    UtilItemStack.setNBTTagInt(stack, "flame", EnumFlameType.CLEAR.ordinal());
                    list.add(stack);
                    break;
                case WATER_CRYSTAL_ORE:
                    list.add(this.changeDrop(ItemRegistry.ItemCrystal.ItemWaterCrystalShard, world.rand.nextInt(5) + 1));
                    break;
                case ENERGY_CRYSTAL_ORE:
                    list.add(this.changeDrop(ItemRegistry.ItemCrystal.ItemEnergyCrystalShard, world.rand.nextInt(5) + 1));
                    break;
                case GARNET_ORE:
                    list.add(this.changeDrop(ItemRegistry.ItemCrystal.ItemGarnet, world.rand.nextInt(5) + 1));
                    break;
                case GARNET_BLOCK:
                    list.add(BlockGarnetOre);
                    break;
            }
            if (metadata != GARNET_BLOCK && metadata != GARNET_ORE) {

                list.add(this.changeDrop(ItemRegistry.ItemCrystal.ItemVoidCrystalShard, world.rand.nextInt(1)));
            }
        }
        return list;
    }

    public ItemStack changeDrop(ItemStack stack, int drop) {

        ItemStack temp = stack.copy();

        temp.stackSize = drop;
        return temp;
    }

    @Override
    public int damageDropped(int metadata) {

        return metadata;
    }
}

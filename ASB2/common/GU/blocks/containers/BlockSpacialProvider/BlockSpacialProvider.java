package GU.blocks.containers.BlockSpacialProvider;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import GU.api.multiblock.IMultiBlock;
import GU.blocks.containers.BlockMultiBase;
import GU.info.Reference;
import GU.items.GUItemBlock;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BlockSpacialProvider extends BlockMultiBase {

    public final ItemStack STANDARD_SPACIAL_PROVIDER = new ItemStack(this, 1, STANDARD), FLUID_SPACIAL_PROVIDER = new ItemStack(this, 1, FLUID), FURNACE_SPACIAL_PROVIDER = new ItemStack(this, 1, FURNACE), CHEST_SPACIAL_PROVIDER = new ItemStack(this, 1, CHEST), FLAME_SPACIAL_PROVIDER = new ItemStack(this, 1, FLAME);;
    public static final int STANDARD = 0, FLUID = 1, FURNACE = 2, CHEST = 3, FLAME = 4;

    Icon standard, fluid, furnace, chest, flame;

    public BlockSpacialProvider(int id, Material material) {
        super(id, material);
        specialMetadata = true;
        dropMetadata = true;

        this.registerTile(TileSpacialProvider.class);

        MinecraftForge.setBlockHarvestLevel(this, "pickaxe", 1);
        GameRegistry.registerBlock(this, GUItemBlock.class, "BlockSpacialProvider");

        LanguageRegistry.addName(STANDARD_SPACIAL_PROVIDER, this.getItemStackDisplayName(STANDARD_SPACIAL_PROVIDER));
        LanguageRegistry.addName(FLUID_SPACIAL_PROVIDER, this.getItemStackDisplayName(FLUID_SPACIAL_PROVIDER));
        LanguageRegistry.addName(FURNACE_SPACIAL_PROVIDER, this.getItemStackDisplayName(FURNACE_SPACIAL_PROVIDER));
        LanguageRegistry.addName(CHEST_SPACIAL_PROVIDER, this.getItemStackDisplayName(CHEST_SPACIAL_PROVIDER));
        LanguageRegistry.addName(FLAME_SPACIAL_PROVIDER, this.getItemStackDisplayName(FLAME_SPACIAL_PROVIDER));
    }

    @Override
    public boolean isBlockNormalCube(World world, int x, int y, int z) {

        return true;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {

        TileSpacialProvider tile = (TileSpacialProvider) world.getBlockTileEntity(x, y, z);

        List<IMultiBlock> multiBlocks = tile.getComprisedMultiBlocks();

        if (multiBlocks.isEmpty() && player.getHeldItem() == null) {

            tile.createMultiBlock();
            return true;
        } else if (!multiBlocks.isEmpty()) {

            for (IMultiBlock multi : multiBlocks) {

                multi.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);
            }
            return true;
        }
        return false;
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {
        super.registerIcons(iconRegister);
        standard = iconRegister.registerIcon(Reference.MODDID + ":BlockStandardSpacialProvider");
        fluid = iconRegister.registerIcon(Reference.MODDID + ":BlockFluidSpacialProvider");
        furnace = iconRegister.registerIcon(Reference.MODDID + ":BlockFurnaceSpacialProvider");
        chest = iconRegister.registerIcon(Reference.MODDID + ":BlockChestSpacialProvider");
        flame = iconRegister.registerIcon(Reference.MODDID + ":BlockFlameSpacialProvider");
    }

    @Override
    public Icon getIcon(int side, int metadata) {

        switch (metadata) {

            case STANDARD:
                return standard;
            case FLUID:
                return fluid;
            case FURNACE:
                return furnace;
            case CHEST:
                return chest;
            case FLAME:
                return flame;
            default:
                return super.getIcon(side, metadata);
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List list) {

        list.add(new ItemStack(this, 1, STANDARD));
        list.add(new ItemStack(this, 1, FLUID));
        list.add(new ItemStack(this, 1, FURNACE));
        list.add(new ItemStack(this, 1, CHEST));
        list.add(new ItemStack(this, 1, FLAME));
    }

    @Override
    public String getItemStackDisplayName(ItemStack itemStack) {

        switch (itemStack.getItemDamage()) {

            case STANDARD:
                return "Standard Spacial Provider";
            case FLUID:
                return "Fluid Spacial Provider";
            case FURNACE:
                return "Furnace Spacial Provider";
            case CHEST:
                return "Chest Spacial Provider";
            case FLAME:
                return "Flame Spacial Provider";
        }
        return "";
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {

        switch (itemStack.getItemDamage()) {

            case STANDARD:
                return "BlockStandardSpacialProvider";
            case FLUID:
                return "BlockFluidSpacialProvider";
            case FURNACE:
                return "BlockFurnaceSpacialProvider";
            case CHEST:
                return "BlockChestSpacialProvider";
            case FLAME:
                return "BlockFlameSpacialProvider";
        }
        return "";
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata) {

        // switch (metadata) {
        //
        // case STANDARD:
        // return new TileSpacialProvider();
        // case FLUID:
        // return new TileFluidSpacialProvider();
        // case FURNACE:
        // return new TileFurnaceSpacialProvider();
        // case CHEST:
        // return new TileChestSpacialProvider();
        // case FLAME:
        // return new TileFlameSpacialProvider();
        // }
        return new TileSpacialProvider();
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        // TODO Auto-generated method stub
        return null;
    }
}

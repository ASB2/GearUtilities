package GU.blocks.containers.BlockMultiInterface;

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
import net.minecraftforge.oredict.OreDictionary;
import GU.api.multiblock.IMultiBlock;
import GU.api.multiblock.IMultiBlockPart;
import GU.api.multiblock.ISpecialTileMultiBlock;
import GU.blocks.containers.ContainerBase;
import GU.info.Reference;
import GU.items.GUItemBlock;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BlockMultiInterface extends ContainerBase implements ISpecialTileMultiBlock {

    public final ItemStack ITEM_INTERFACE = new ItemStack(this, 1, 0), FLUID_INTERFACE = new ItemStack(this, 1, 1), POWER_INTERFACE = new ItemStack(this, 1, 2);

    public static final int MAX_META = 3;
    Icon[] texture = new Icon[MAX_META];
    String[] unlocalizedname = new String[] { "BlockItemMultiInterface", "BlockFluidMultiInterface", "BlockPowerMultiInterface" };
    String[] ign = new String[] { "Item Multiblock Interface", "Fluid Multiblock Interface", "Power Multiblock Interface" };
    ItemStack[] INTERFACES = new ItemStack[MAX_META];

    public BlockMultiInterface(int id, Material material) {
        super(id, material);

        specialMetadata = true;
        this.registerTile(TileItemMultiInterface.class);
        this.registerTile(TileFluidMultiInterface.class);

        MinecraftForge.setBlockHarvestLevel(this, "pickaxe", 1);
        GameRegistry.registerBlock(this, GUItemBlock.class, "BlockMultiInterface");

        for (int i = 0; i < MAX_META; i++) {

            INTERFACES[i] = new ItemStack(this, 1, i);
            OreDictionary.registerOre(Reference.STRUCTURE_CUBE, INTERFACES[i]);
            LanguageRegistry.addName(INTERFACES[i], ign[i]);
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List list) {

        for (int i = 0; i < MAX_META; i++) {

            list.add(INTERFACES[i]);
        }
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {

        for (int i = 0; i < MAX_META; i++) {

            texture[i] = iconRegister.registerIcon(Reference.MODDID + ":" + unlocalizedname[i]);
        }
    }

    @Override
    public Icon getIcon(int side, int metadata) {

        return texture[metadata];
    }

    @Override
    public String getItemStackDisplayName(ItemStack itemStack) {

        return ign[itemStack.getItemDamage()];
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {

        return unlocalizedname[itemStack.getItemDamage()];
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {

        IMultiBlockPart tile = (IMultiBlockPart) world.getBlockTileEntity(x, y, z);

        if (tile != null) {

            if (!tile.getComprisedMultiBlocks().isEmpty()) {

                for (IMultiBlock multi : tile.getComprisedMultiBlocks()) {

                    multi.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isBlockNormalCube(World world, int x, int y, int z) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public TileEntity getBlockTileEntity(World world, int x, int y, int z) {

        TileEntity tile = world.getBlockTileEntity(x, y, z);

        if (tile == null) {

            switch (world.getBlockMetadata(x, y, z)) {

                case 0:
                    tile = new TileItemMultiInterface();
                    break;

                case 1:
                    tile = new TileFluidMultiInterface();
                    break;
            }
            world.setBlockTileEntity(x, y, z, tile);
        }
        return tile;
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata) {

        return null;
    }

    @Override
    public TileEntity createNewTileEntity(World world) {

        return null;
    }
}

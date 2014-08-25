package GUOLD.blocks.containers.BlockMultiInterface;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import GUOLD.api.multiblock.IMultiBlock;
import GUOLD.api.multiblock.IMultiBlockPart;
import GUOLD.api.multiblock.ISpecialTileMultiBlock;
import GUOLD.blocks.containers.ContainerBase;
import GUOLD.info.Reference;
import GUOLD.items.GUItemBlock;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BlockMultiInterface extends ContainerBase implements ISpecialTileMultiBlock {
    
    public final ItemStack ITEM_INTERFACE = new ItemStack(this, 1, 0), FLUID_INTERFACE = new ItemStack(this, 1, 1), POWER_INTERFACE = new ItemStack(this, 1, 2);
    
    public static final int MAX_META = 3;
    IIcon[] texture = new IIcon[MAX_META];
    String[] unlocalizedname = new String[] { "BlockItemMultiInterface", "BlockFluidMultiInterface", "BlockPowerMultiInterface" };
    String[] ign = new String[] { "Item Multiblock Interface", "Fluid Multiblock Interface", "Power Multiblock Interface" };
    ItemStack[] INTERFACES = new ItemStack[MAX_META];
    
    public BlockMultiInterface(Material material) {
        super(material);
        
        specialMetadata = true;
        dropMetadata = true;
        this.registerTile(TileItemMultiInterface.class);
        this.registerTile(TileFluidMultiInterface.class);
        
        this.setHarvestLevel("pickaxe", 1);
        GameRegistry.registerBlock(this, GUItemBlock.class, "BlockMultiInterface");
        
        for (int i = 0; i < MAX_META; i++) {
            
            INTERFACES[i] = new ItemStack(this, 1, i);
            OreDictionary.registerOre(Reference.STRUCTURE_CUBE, INTERFACES[i]);
            LanguageRegistry.addName(INTERFACES[i], ign[i]);
        }
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List list) {
        
        for (int i = 0; i < MAX_META; i++) {
            
            list.add(INTERFACES[i]);
        }
    }
    
    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        
        for (int i = 0; i < MAX_META; i++) {
            
            texture[i] = iconRegister.registerIcon(Reference.MODDID + ":" + unlocalizedname[i]);
        }
    }
    
    @Override
    public IIcon getIcon(int side, int metadata) {
        
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
        
        IMultiBlockPart tile = (IMultiBlockPart) world.getTileEntity(x, y, z);
        
        if (tile != null) {
            
            List<IMultiBlock> multiBlocks = tile.getComprisedMultiBlocks();
            
            if (!multiBlocks.isEmpty()) {
                
                for (IMultiBlock multi : multiBlocks) {
                    
                    multi.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);
                }
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean isNormalCube(IBlockAccess world, int x, int y, int z) {
        // TODO Auto-generated method stub
        return true;
    }
    
    @Override
    public TileEntity getBlockTileEntity(World world, int x, int y, int z) {
        
        TileEntity tile = world.getTileEntity(x, y, z);
        
        if (tile == null) {
            
            switch (world.getBlockMetadata(x, y, z)) {
            
                case 0:
                    tile = new TileItemMultiInterface();
                    break;
                
                case 1:
                    tile = new TileFluidMultiInterface();
                    break;
            }
            world.setTileEntity(x, y, z, tile);
        }
        return tile;
    }
    
    @Override
    public TileEntity createTileEntity(World world, int metadata) {
        
        return null;
    }
    
    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        
        return null;
    }
}

package GU.blocks.containers.BlockStructureCube;

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
import GU.api.multiblock.IMultiBlock;
import GU.api.multiblock.IMultiBlockPart;
import GU.api.multiblock.ISpecialMultiBlockOpaque;
import GU.api.multiblock.ISpecialTileMultiBlock;
import GU.blocks.containers.BlockMultiBase;
import GU.info.Reference;
import GU.items.GUItemBlock;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BlockStructureCube extends BlockMultiBase implements ISpecialTileMultiBlock, ISpecialMultiBlockOpaque {
    
    public static final int CUBE0 = 0, CUBE1 = 1, CUBE2 = 2, CUBE3 = 3, GLASS4 = 4, BRICK5 = 5, BRICK6 = 6, CUBE7 = 7;
    public static final int MAX_META = 8;
    IIcon[] texture = new IIcon[MAX_META];
    String unlocalizedname = "BlockStructureCube";
    String[] ign = new String[] { "Structure Cube 0", "Structure Cube 1", "Structure Cube 2", "Structure Cube 3", "Structure Glass", "Structure Brick 1", "Structure Brick 2", "Metallic Structure Cube" };
    public ItemStack[] STRUCTURE_CUBES = new ItemStack[MAX_META];
    
    public BlockStructureCube(Material material) {
        super(material);
        
        specialMetadata = true;
        dropMetadata = true;
        this.registerTile(TileStructureCube.class);
        this.setLightOpacity(0);
        
        this.setHarvestLevel("pickaxe", 1);
        GameRegistry.registerBlock(this, GUItemBlock.class, "BlockStructureCube");
        
        for (int i = 0; i < MAX_META; i++) {
            
            STRUCTURE_CUBES[i] = new ItemStack(this, 1, i);
            OreDictionary.registerOre(Reference.STRUCTURE_CUBE, STRUCTURE_CUBES[i]);
            LanguageRegistry.addName(STRUCTURE_CUBES[i], ign[i]);
        }
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List list) {
        
        for (int i = 0; i < MAX_META; i++) {
            
            list.add(STRUCTURE_CUBES[i]);
        }
    }
    
    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        
        for (int i = 0; i < MAX_META; i++) {
            
            texture[i] = iconRegister.registerIcon(Reference.MODDID + ":BlockStructureCube" + i);
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
        
        return unlocalizedname + itemStack.getItemDamage();
    }
    
    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        
        return super.getLightValue(world, x, y, z);
    }
    
    @Override
    public int getLightOpacity(IBlockAccess world, int x, int y, int z) {
        
        return world.getBlockMetadata(x, y, z) == 4 ? 0 : 16;
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
    public boolean isBlockSolid(IBlockAccess p_149747_1_, int p_149747_2_, int p_149747_3_, int p_149747_4_, int p_149747_5_) {
        // TODO Auto-generated method stub
        return true;
    }
    
    // @Override
    // public boolean isBlockNormalCube(IBlockAccess world, int x, int y, int z,
    // int value) {
    //
    // return true;
    // }
    
    @Override
    public TileEntity createTileEntity(World world, int metadata) {
        
        return null;
    }
    
    @Override
    public TileEntity getBlockTileEntity(World world, int x, int y, int z) {
        
        TileEntity tile = world.getTileEntity(x, y, z);
        
        if (tile == null) {
            
            tile = new TileStructureCube();
            world.setTileEntity(x, y, z, tile);
        }
        return tile;
    }
    
    @Override
    public boolean isTrueOpaqueCube(IBlockAccess world, int x, int y, int z) {
        
        return world.getBlockMetadata(x, y, z) != GLASS4 ? true : false;
    }
    
    @Override
    public boolean isOpaqueCube() {
        
        return false;
    }
}

package GU.blocks.containers.BlockStructureCube;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import GU.api.multiblock.IMultiBlock;
import GU.api.multiblock.IMultiBlockPart;
import GU.api.multiblock.ISpecialTileMultiBlock;
import GU.blocks.containers.ContainerBase;
import GU.info.Reference;
import GU.items.GUItemBlock;

public class BlockStructureCube extends ContainerBase implements ISpecialTileMultiBlock {
    
    public static final int CUBE0 = 0, CUBE1 = 1, CUBE2 = 2, CUBE3 = 3, GLASS4 = 4;
    public static final int MAX_META = 5;
    Icon[] texture = new Icon[MAX_META];
    String[] unlocalizedname = new String[] { "BlockStructureCube0", "BlockStructureCube1", "BlockStructureCube2", "BlockStructureCube3", "BlockStructureCube4" };
    String[] ign = new String[] { "Structure Cube 0", "Structure Cube 1", "Structure Cube 2", "Structure Cube 3", "Structure Glass" };
    ItemStack[] STRUCTURE_CUBES = new ItemStack[MAX_META];
    
    public BlockStructureCube(int id, Material material) {
        super(id, material);
        
        specialMetadata = true;
        this.registerTile(TileStructureCube.class);
        this.setLightOpacity(0);
        
        MinecraftForge.setBlockHarvestLevel(this, "pickaxe", 1);
        GameRegistry.registerBlock(this, GUItemBlock.class, "BlockStructureCube");
        
        for (int i = 0; i < MAX_META; i++) {
            
            STRUCTURE_CUBES[i] = new ItemStack(this, 1, i);
            OreDictionary.registerOre(Reference.STRUCTURE_CUBE, STRUCTURE_CUBES[i]);
            LanguageRegistry.addName(STRUCTURE_CUBES[i], ign[i]);
        }
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List list) {
        
        for (int i = 0; i < MAX_META; i++) {
            
            list.add(STRUCTURE_CUBES[i]);
        }
    }
    
    @Override
    public void registerIcons(IconRegister iconRegister) {
        
        for (int i = 0; i < MAX_META; i++) {
            
            texture[i] = iconRegister.registerIcon(Reference.MODDID + ":BlockStructureCube" + i);
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
    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        
        return super.getLightValue(world, x, y, z);
    }
    
    @Override
    public int getLightOpacity(World world, int x, int y, int z) {
        
        return world.getBlockMetadata(x, y, z) == 4 ? 0 : super.getLightOpacity(world, x, y, z);
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
        
        return world.getBlockMetadata(x, y, z) != GLASS4 ? true : false;
    }
    
    @Override
    public TileEntity createTileEntity(World world, int metadata) {
        
        return null;
    }
    
    @Override
    public TileEntity getBlockTileEntity(World world, int x, int y, int z) {
        
        TileEntity tile = world.getBlockTileEntity(x, y, z);
        
        if (tile == null) {
            
            tile = new TileStructureCube();
            world.setBlockTileEntity(x, y, z, tile);
        }
        return tile;
    }
    
    @Override
    public TileEntity createNewTileEntity(World world) {
        
        return null;
    }
}

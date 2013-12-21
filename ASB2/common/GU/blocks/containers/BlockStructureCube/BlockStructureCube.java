package GU.blocks.containers.BlockStructureCube;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import GU.api.multiblock.ISpecialTileMultiBlock;
import GU.blocks.containers.ContainerBase;
import GU.info.Reference;

public class BlockStructureCube extends ContainerBase implements ISpecialTileMultiBlock {
    
    public static int MAX_META = 5;
    Icon[] texture = new Icon[MAX_META];
    String[] unlocalizedname = new String[] { "BlockStructureCube0", "BlockStructureCube1", "BlockStructureCube2", "BlockStructureCube3", "BlockStructureCube4" };
    String[] ign = new String[] { "Structure Cube 0", "Structure Cube 1", "Structure Cube 2", "Structure Cube 3", "Structure Glass" };
    ItemStack[] STRUCTURE_CUBES = new ItemStack[] { new ItemStack(this, 1, 0), new ItemStack(this, 1, 1), new ItemStack(this, 1, 2), new ItemStack(this, 1, 3), new ItemStack(this, 1, 4) };
    
    public BlockStructureCube(int id, Material material) {
        super(id, material);
        specialMetadata = true;
        this.registerTile(TileStructureCube.class);
        for (int i = 0; i < MAX_META; i++) {
            
            OreDictionary.registerOre(Reference.STRUCTURE_CUBE, new ItemStack(this, 1, i));
        }
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List list) {
        
        for (int i = 0; i < MAX_META; i++) {
            
            list.add(new ItemStack(this, 1, i));
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
        // TODO Auto-generated method stub
        return super.getLightValue(world, x, y, z);
    }
    
    @Override
    public int getLightOpacity(World world, int x, int y, int z) {
        
        return world.getBlockMetadata(x, y, z) == 4 ? 0 : 16;
    }
    
    @Override
    public TileEntity createTileEntity(World world, int metadata) {
        
        this.setLightOpacity(0);
        return null;
    }
    
    @Override
    public TileEntity getBlockTileEntity(World world, int x, int y, int z) {
        
        world.setBlockTileEntity(x, y, z, new TileStructureCube());
        return world.getBlockTileEntity(x, y, z);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        // TODO Auto-generated method stub
        return null;
    }
}

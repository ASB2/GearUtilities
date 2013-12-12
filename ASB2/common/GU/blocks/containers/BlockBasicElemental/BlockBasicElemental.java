package GU.blocks.containers.BlockBasicElemental;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import GU.blocks.containers.ContainerBase;

public class BlockBasicElemental extends ContainerBase {
    
    public static final int FIRE_CUBE = 0;
    public static final int WATER_CUBE = 1;
    public static final int EARTH_CUBE = 2;
    
    public BlockBasicElemental(int id, Material material) {
        super(id, material);
        this.registerTile(TileBasicElemental.class);
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List list) {

        list.add(new ItemStack(this, 1, FIRE_CUBE));
        list.add(new ItemStack(this, 1, WATER_CUBE));
        list.add(new ItemStack(this, 1, EARTH_CUBE));
    }
    
    @Override
    public Icon getIcon(int side, int metadata) {
        
        
        return icons[metadata];
    }
    
    @Override
    public TileEntity createNewTileEntity(World world) {
        
        return new TileBasicElemental();
    }

    @Override
    public String getItemStackDisplayName(ItemStack itemStack) {

        return "";
    }
}

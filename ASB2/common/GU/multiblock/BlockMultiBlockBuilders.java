package GU.multiblock;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import GU.api.multiblock.IMultiBlock;
import GU.blocks.containers.ContainerBase;
import GU.info.Reference;

public class BlockMultiBlockBuilders extends ContainerBase {
    
    public static final int GLASS = 0;
    public static final int CORNER = 1;
    
    Icon[] icons = new Icon[2];
    
    public BlockMultiBlockBuilders(int id, Material material) {
        super(id, material);
        this.registerTile(TileMultiBlockBuilders.class);
        specialMetadata = true;
    }
    
    @Override
    public void registerIcons(IconRegister iconRegister) {
        super.registerIcons(iconRegister);
        
        icons[0] = iconRegister.registerIcon(Reference.MODDID + ":BlockMultiBlockGlass");
        icons[1] = iconRegister.registerIcon(Reference.MODDID + ":BlockMultiBlockCorner");
    }
    
    @Override
    public Icon getIcon(int side, int metadata) {
        
        return icons[metadata];
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        
        TileMultiBlockBuilders tile = (TileMultiBlockBuilders) world.getBlockTileEntity(x, y, z);
        
        if (!tile.getComprisedMultiBlocks().isEmpty()) {
            
            for (IMultiBlock multi : tile.getComprisedMultiBlocks()) {
                
                multi.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);
            }
            return true;
        }
        return false;
    }
    
    @Override
    public TileEntity createNewTileEntity(World world) {
        
        return new TileMultiBlockBuilders();
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void getSubBlocks(int par1, CreativeTabs tab, List list) {
        
        list.add(new ItemStack(this, 1, GLASS));
        list.add(new ItemStack(this, 1, CORNER));
    }
}
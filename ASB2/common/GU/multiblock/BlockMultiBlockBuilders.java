package GU.multiblock;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import GU.blocks.containers.ContainerBase;
import GU.info.Reference;

public class BlockMultiBlockBuilders extends ContainerBase {
    
    public static final int GLASS = 0;
    public static final int CORNER = 0;
    
    Icon[] icons = new Icon[2];
    
    public BlockMultiBlockBuilders(int id, Material material) {
        super(id, material);
        this.registerTile(TileMultiBlockBuilders.class);
    }
    
    @Override
    public void registerIcons(IconRegister iconRegister) {
        super.registerIcons(iconRegister);
     
        icons[0] = iconRegister.registerIcon(Reference.MODDID + ":BlockMultiBlockCorner");
        icons[1] = iconRegister.registerIcon(Reference.MODDID + ":BlockMultiBlockGlass");
    }
    
    @Override
    public Icon getIcon(int side, int metadata) {

        return icons[metadata];
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        
        return false;
    }
    
    @Override
    public TileEntity createNewTileEntity(World world) {
        
        return new TileMultiBlockBuilders();
    }
    
}
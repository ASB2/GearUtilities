package GU.blocks.containers.BlockAdvancedElemental;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import GU.blocks.containers.ContainerBase;

public class BlockAdvancedElemental extends ContainerBase {
    
    public static final int SMELTING = 0;
    public static final int GRINDING = 1;
    public static final int HEATING = 2;
    
    Icon[][] icons = new Icon[3][6];
    
    public BlockAdvancedElemental(int id, Material material) {
        super(id, material);
        
        this.specialMetadata = true;
        this.registerTile(TileAdvancedElemental.class);
    }
    
    @Override
    public void registerIcons(IconRegister iconRegister) {
        super.registerIcons(iconRegister);
        
        icons[0][0] = null;
        icons[0][1] = null;
        icons[0][2] = null;
        icons[0][3] = null;
        icons[0][4] = null;
        icons[0][5] = null;
        
        icons[1][0] = null;
        icons[1][1] = null;
        icons[1][2] = null;
        icons[1][3] = null;
        icons[1][4] = null;
        icons[1][5] = null;
        
        icons[2][0] = null;
        icons[2][1] = null;
        icons[2][2] = null;
        icons[2][3] = null;
        icons[2][4] = null;
        icons[2][5] = null;
    }
    
    @Override
    public Icon getIcon(int side, int metadata) {
        
        return icons[metadata][side];
    }
    
    @Override
    public TileEntity createNewTileEntity(World world) {
        // TODO Auto-generated method stub
        return null;
    }
}

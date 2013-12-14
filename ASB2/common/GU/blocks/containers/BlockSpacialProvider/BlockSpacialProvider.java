package GU.blocks.containers.BlockSpacialProvider;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import GU.blocks.containers.ContainerBase;
import GU.info.Reference;

public class BlockSpacialProvider extends ContainerBase {
    
    public static final int STANDARD = 0;
    public static final int FLUID = 1;
    
    Icon standard, fluid;
    
    public BlockSpacialProvider(int id, Material material) {
        super(id, material);
        this.registerTile(TileSpacialProvider.class);
    }
    
    @Override
    public void registerIcons(IconRegister iconRegister) {
        super.registerIcons(iconRegister);
        standard = iconRegister.registerIcon(Reference.MODDID + ":BlockStandardSpecialProvider");
    }
    
    @Override
    public Icon getIcon(int side, int metadata) {
        switch (metadata) {
        
            case 0:
                return standard;
            case 1:
                return fluid;
            default:
                return super.getIcon(side, metadata);
        }
    }
    
    @Override
    public TileEntity createNewTileEntity(World world) {
        
        return new TileSpacialProvider();
    }
}

package GU.blocks.containers.BlockMultiPart;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class BlockMultiPartRender extends BlockMultiBlockPartAir {
    
    public BlockMultiPartRender(Material material) {
        super(material);
        this.registerTile(TileMultiPartRender.class);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void postInitRender() {
        
        ClientRegistry.bindTileEntitySpecialRenderer(TileMultiPartRender.class, MultiPartRenderer.instance);
    }
    
    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        
        return new TileMultiPartRender();
    }
}

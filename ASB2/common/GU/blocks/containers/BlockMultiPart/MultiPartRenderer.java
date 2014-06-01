package GU.blocks.containers.BlockMultiPart;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import GU.blocks.containers.BlockMultiPart.BlockMultiPartRender.TileMultiPartRender;

public class MultiPartRenderer extends TileEntitySpecialRenderer {
    
    public static MultiPartRenderer instance = new MultiPartRenderer();
    
    public MultiPartRenderer() {
        
    }
    
    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {
        
        TileMultiPartRender tile = (TileMultiPartRender) tileentity;
        
        if (tile.state != null) {
            
            tile.state.render(x, y, z, f);
        }
    }
}
package GU.blocks.containers.BlockMultiPart;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

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

package GU.blocks.containers.BlockMultiDirectionalConduit;

import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import GU.blocks.containers.BlockContainerBase;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMultiDirectionalConduit extends BlockContainerBase {
    
    public BlockMultiDirectionalConduit(Material material) {
        super(material);
        this.registerTile(TileMultiDirectionalConduit.class);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void postInitRender() {
        
        ClientRegistry.bindTileEntitySpecialRenderer(TileMultiDirectionalConduit.class, MultiDirectionalConduitRenderer.instance);
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(this), MultiDirectionalConduitRenderer.instance);
    }
    
    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float p_149660_6_, float p_149660_7_, float p_149660_8_, int p_149660_9_) {
        
        return side;
    }
    
    @Override
    public int getRenderType() {
        
        return -1;
    }
    
    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        
        return new TileMultiDirectionalConduit();
    }
}

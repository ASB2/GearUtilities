package GU.blocks.containers.BlockItemElectisPolyhedron;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import GU.blocks.containers.BlockContainerBase;

public class BlockItemElectisPolyhedron extends BlockContainerBase {
    
    public BlockItemElectisPolyhedron(Material material) {
        super(material);
        this.registerTile(TileItemElectisPolyhedron.class);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void postInitRender() {
        
        ClientRegistry.bindTileEntitySpecialRenderer(TileItemElectisPolyhedron.class, ItemElectisPolyhedronRenderer.instance);
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(this), ItemElectisPolyhedronRenderer.instance);
    }
    
    @Override
    public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
       
        return super.onBlockActivated(p_149727_1_, p_149727_2_, p_149727_3_, p_149727_4_, p_149727_5_, p_149727_6_, p_149727_7_, p_149727_8_, p_149727_9_);
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
    public String getBlockDisplayName(ItemStack stack) {
        
        return "Green Electis Polyhedron";
    }
    
    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        
        return new TileItemElectisPolyhedron();
    }
}

package GU.blocks.containers.BlockElectisPolyhedron;

import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import GU.blocks.containers.BlockContainerBase;

public class BlockElectisPolyhedron extends BlockContainerBase {
    
    public BlockElectisPolyhedron(Material material) {
        super(material);
        this.registerTile(TileElectisPolyhedron.class);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void postInitRender() {
        
        ClientRegistry.bindTileEntitySpecialRenderer(TileElectisPolyhedron.class, ElectisPolyhedronRenderer.instance);
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(this), ElectisPolyhedronRenderer.instance);
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
        
        return "Electis Polyhedron";
    }
    
    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        
        return new TileElectisPolyhedron();
    }
}

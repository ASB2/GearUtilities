package GU.blocks.containers.BlockPhotonicConverter;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import GU.blocks.containers.BlockContainerBase;

public class BlockPhotonicConverter extends BlockContainerBase {
    
    public BlockPhotonicConverter(Material material) {
        super(material);
        this.registerTile(TilePhotonicConverter.class);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void postInitRender() {
        
        ClientRegistry.bindTileEntitySpecialRenderer(TilePhotonicConverter.class, PhotonicConverterRenderer.instance);
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(this), PhotonicConverterRenderer.instance);
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void addInformation(ItemStack stack, EntityPlayer player, List par3List, boolean par4) {
        super.addInformation(stack, player, par3List, par4);
        par3List.add("Jenny 86753 o9");
    }
    
    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float p_149660_6_, float p_149660_7_, float p_149660_8_, int p_149660_9_) {
        
        return side;
    }
    
    @Override
    public int getRenderType() {
        
        return -1;
    }
    
    public String getBlockDisplayName(ItemStack stack) {
        
        return "Photonic Converter";
    }
    
    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        
        return new TilePhotonicConverter();
    }
}

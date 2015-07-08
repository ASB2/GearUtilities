package GU.blocks.containers.BlockInvertedPhotonicConverter;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import GU.blocks.containers.BlockContainerBase;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockInvertedPhotonicConverter extends BlockContainerBase {
    
    public BlockInvertedPhotonicConverter(Material material) {
        super(material);
        this.registerTile(TileInvertedPhotonicConverter.class);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void postInitRender() {
        
        ClientRegistry.bindTileEntitySpecialRenderer(TileInvertedPhotonicConverter.class, InvertedPhotonicConverterRenderer.instance);
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(this), InvertedPhotonicConverterRenderer.instance);
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
        
        return "Inverted Photonic Converter";
    }
    
    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        
        return new TileInvertedPhotonicConverter();
    }
}

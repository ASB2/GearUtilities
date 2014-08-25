package GU.blocks.containers.BlockPhotonSender;

import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import GU.blocks.containers.BlockContainerBase;
import cpw.mods.fml.client.registry.ClientRegistry;

public class BlockPhotonSender extends BlockContainerBase {
    
    public BlockPhotonSender(Material material) {
        super(material);
        this.registerTile(TilePhotonSender.class);
    }
    
    @Override
    public void postInit() {
        
        ClientRegistry.bindTileEntitySpecialRenderer(TilePhotonSender.class, PhotonSenderRenderer.instance);
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(this), PhotonSenderRenderer.instance);
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
        
        return "Photon Sender";
    }
    
    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        
        return new TilePhotonSender();
    }
}

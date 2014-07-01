package GU.blocks.containers.BlockPhotonSender;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
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
    }
    
    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        
        return new TilePhotonSender();
    }
}

package GU.proxy;

import net.minecraftforge.client.MinecraftForgeClient;
import GU.ItemRegistry;
import GU.info.Models;
import GU.items.ItemMetadataRenderer;
import GU.render.NoiseManager;

public class ClientProxy extends CommonProxy {
    
    @Override
    public void register() {
        
        Models.initModels();
        NoiseManager.instance.initImage();
        MinecraftForgeClient.registerItemRenderer(ItemRegistry.METADATA_ITEM, ItemMetadataRenderer.instance);
    }
}

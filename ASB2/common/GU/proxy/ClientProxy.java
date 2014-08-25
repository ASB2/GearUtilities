package GU.proxy;

import GU.info.Models;
import GU.render.BlockSimpleRenderer;
import GU.render.noise.NoiseManager;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
    
    @Override
    public void register() {
        
        Models.initModels();
        NoiseManager.instance.initImage();
        
        RenderingRegistry.registerBlockHandler(BlockSimpleRenderer.instance);
    }
}

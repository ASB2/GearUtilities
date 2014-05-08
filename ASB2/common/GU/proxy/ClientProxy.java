package GU.proxy;

import GU.info.Models;
import GU.render.NoiseManager;

public class ClientProxy extends CommonProxy {
    
    @Override
    public void register() {
        
        Models.initModels();
        NoiseManager.instance.initImage();
    }
}

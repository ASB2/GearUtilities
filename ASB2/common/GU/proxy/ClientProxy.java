package GU.proxy;

import GU.info.Models;

public class ClientProxy extends CommonProxy {
    
    @Override
    public void register() {
        
        Models.initModels();
    }
}

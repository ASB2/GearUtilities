package GU.info;

import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public final class Models {
    
    public static IModelCustom ModelMultiPanel;
    public static IModelCustom ModelCrystal1;
    
    public static final String MULTI_PANEL = Reference.RESOURCE_LOCATION + "models/ModelMultiPanel.obj";
    public static final String CRYSTAL_1 = Reference.RESOURCE_LOCATION + "models/ModelCrystal1.obj";
    
    public static void initModels() {
        
        ModelMultiPanel = AdvancedModelLoader.loadModel(Models.MULTI_PANEL);
        ModelCrystal1 = AdvancedModelLoader.loadModel(Models.CRYSTAL_1);
    }
}

package GU.info;

import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.obj.WavefrontObject;

public final class Models {
    
    public static WavefrontObject ModelMultiPanel;
    public static WavefrontObject ModelCrystal1;
    public static WavefrontObject ModelEssenceDiffuser;
    
    public static final String MULTI_PANEL = Reference.RESOURCE_LOCATION + "models/ModelMultiPanel.obj";
    public static final String CRYSTAL_1 = Reference.RESOURCE_LOCATION + "models/ModelCrystal1.obj";
    public static final String ESSENCE_DIFFUSER = Reference.RESOURCE_LOCATION + "models/ModelEssenceDiffuser.obj";
    
    public static void initModels() {
        
        ModelMultiPanel = (WavefrontObject) AdvancedModelLoader.loadModel(Models.MULTI_PANEL);
        ModelCrystal1 = (WavefrontObject) AdvancedModelLoader.loadModel(Models.CRYSTAL_1);
        ModelEssenceDiffuser = (WavefrontObject) AdvancedModelLoader.loadModel(Models.ESSENCE_DIFFUSER);
    }
}

package GU.info;

import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.obj.WavefrontObject;

public final class Models {

    public static WavefrontObject ModelMultiPanel;
    public static WavefrontObject ModelCrystal1;
    public static WavefrontObject ModelEssenceDiffuser;
    public static WavefrontObject ModelCentrifgue;
    public static WavefrontObject ModelElementalRefinery;
    public static WavefrontObject ModelFlameFocus;
    public static WavefrontObject ModelFlameAltar;

    private static final String MULTI_PANEL = Reference.RESOURCE_LOCATION + "models/ModelMultiPanel.obj";
    private static final String CRYSTAL_1 = Reference.RESOURCE_LOCATION + "models/ModelCrystal1.obj";
    private static final String ESSENCE_DIFFUSER = Reference.RESOURCE_LOCATION + "models/ModelEssenceDiffuser.obj";
    private static final String CENTRIFUGE = Reference.RESOURCE_LOCATION + "models/ModelCentrifuge.obj";
    private static final String ELEMENTAL_REFINERY = Reference.RESOURCE_LOCATION + "models/ModelElementalRefinery.obj";
    private static final String FLAME_FOCUS = Reference.RESOURCE_LOCATION + "models/ModelFlameFocus.obj";
    private static final String FLAME_ALTAR = Reference.RESOURCE_LOCATION + "models/ModelFlameAltar.obj";

    public static void initModels() {

        ModelMultiPanel = (WavefrontObject) AdvancedModelLoader.loadModel(Models.MULTI_PANEL);
        ModelCrystal1 = (WavefrontObject) AdvancedModelLoader.loadModel(Models.CRYSTAL_1);
        ModelEssenceDiffuser = (WavefrontObject) AdvancedModelLoader.loadModel(Models.ESSENCE_DIFFUSER);
        ModelCentrifgue = (WavefrontObject) AdvancedModelLoader.loadModel(Models.CENTRIFUGE);
        ModelElementalRefinery = (WavefrontObject) AdvancedModelLoader.loadModel(Models.ELEMENTAL_REFINERY);
        ModelFlameFocus = (WavefrontObject) AdvancedModelLoader.loadModel(Models.FLAME_FOCUS);
        ModelFlameAltar = (WavefrontObject) AdvancedModelLoader.loadModel(Models.FLAME_ALTAR);
    }
}

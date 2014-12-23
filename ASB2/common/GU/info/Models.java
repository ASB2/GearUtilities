package GU.info;

import net.minecraft.util.ResourceLocation;
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
    public static WavefrontObject ModelGlassPipe;
    public static WavefrontObject ModelGyro;
    public static WavefrontObject ModelElectisShard;
    public static WavefrontObject ModelFlameConduit;
    public static WavefrontObject ModelCrystal2;
    public static WavefrontObject ModelRhombicuboctahedron;
    public static WavefrontObject ModelAdvancedStick;
    public static WavefrontObject ModelUtilityTablet;
    public static WavefrontObject ModelConduit;
    public static WavefrontObject ModelMultiDirectionalConduit;
    public static WavefrontObject ModelElectisEnergyCube;
    public static WavefrontObject ModelCrystal4;
    
    private Models() {
    }
    
    public static void initModels() {
        
        ModelMultiPanel = (WavefrontObject) AdvancedModelLoader.loadModel(new ResourceLocation(Reference.MOD_ID + ":models/ModelMultiPanel.obj"));
        ModelCrystal1 = (WavefrontObject) AdvancedModelLoader.loadModel(new ResourceLocation(Reference.MOD_ID + ":models/ModelCrystal1.obj"));
        ModelEssenceDiffuser = (WavefrontObject) AdvancedModelLoader.loadModel(new ResourceLocation(Reference.MOD_ID + ":models/ModelEssenceDiffuser.obj"));
        ModelCentrifgue = (WavefrontObject) AdvancedModelLoader.loadModel(new ResourceLocation(Reference.MOD_ID + ":models/ModelCentrifuge.obj"));
        ModelElementalRefinery = (WavefrontObject) AdvancedModelLoader.loadModel(new ResourceLocation(Reference.MOD_ID + ":models/ModelElementalRefinery.obj"));
        ModelFlameFocus = (WavefrontObject) AdvancedModelLoader.loadModel(new ResourceLocation(Reference.MOD_ID + ":models/ModelFlameFocus.obj"));
        ModelFlameAltar = (WavefrontObject) AdvancedModelLoader.loadModel(new ResourceLocation(Reference.MOD_ID + ":models/ModelFlameAltar.obj"));
        ModelGlassPipe = (WavefrontObject) AdvancedModelLoader.loadModel(new ResourceLocation(Reference.MOD_ID + ":models/ModelGlassPipe.obj"));
        ModelGyro = (WavefrontObject) AdvancedModelLoader.loadModel(new ResourceLocation(Reference.MOD_ID + ":models/ModelGyro.obj"));
        ModelElectisShard = (WavefrontObject) AdvancedModelLoader.loadModel(new ResourceLocation(Reference.MOD_ID + ":models/ModelFlameShard.obj"));
        ModelFlameConduit = (WavefrontObject) AdvancedModelLoader.loadModel(new ResourceLocation(Reference.MOD_ID + ":models/ModelFlameConduit.obj"));
        ModelCrystal2 = (WavefrontObject) AdvancedModelLoader.loadModel(new ResourceLocation(Reference.MOD_ID + ":models/ModelCrystal2.obj"));
        ModelRhombicuboctahedron = (WavefrontObject) AdvancedModelLoader.loadModel(new ResourceLocation(Reference.MOD_ID + ":models/ModelRhombicuboctahedron.obj"));
        ModelAdvancedStick = (WavefrontObject) AdvancedModelLoader.loadModel(new ResourceLocation(Reference.MOD_ID + ":models/ModelAdvancedStick.obj"));
        ModelUtilityTablet = (WavefrontObject) AdvancedModelLoader.loadModel(new ResourceLocation(Reference.MOD_ID + ":models/ModelUtilityTablet.obj"));
        ModelConduit = (WavefrontObject) AdvancedModelLoader.loadModel(new ResourceLocation(Reference.MOD_ID + ":models/ModelConduit.obj"));
        ModelMultiDirectionalConduit = (WavefrontObject) AdvancedModelLoader.loadModel(new ResourceLocation(Reference.MOD_ID + ":models/ModelMultiDirectionalConduit.obj"));
        ModelElectisEnergyCube = (WavefrontObject) AdvancedModelLoader.loadModel(new ResourceLocation(Reference.MOD_ID + ":models/ModelElectisEnergyCube.obj"));
        ModelCrystal4 = (WavefrontObject) AdvancedModelLoader.loadModel(new ResourceLocation(Reference.MOD_ID + ":models/ModelCrystal4.obj"));
    }
}

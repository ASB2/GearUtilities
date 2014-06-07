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
    
    private static final ResourceLocation MULTI_PANEL = new ResourceLocation(Reference.MOD_ID + ":models/ModelMultiPanel.obj");
    private static final ResourceLocation CRYSTAL_1 = new ResourceLocation(Reference.MOD_ID + ":models/ModelCrystal1.obj");
    private static final ResourceLocation ESSENCE_DIFFUSER = new ResourceLocation(Reference.MOD_ID + ":models/ModelEssenceDiffuser.obj");
    private static final ResourceLocation CENTRIFUGE = new ResourceLocation(Reference.MOD_ID + ":models/ModelCentrifuge.obj");
    private static final ResourceLocation ELEMENTAL_REFINERY = new ResourceLocation(Reference.MOD_ID + ":models/ModelElementalRefinery.obj");
    private static final ResourceLocation FLAME_FOCUS = new ResourceLocation(Reference.MOD_ID + ":models/ModelFlameFocus.obj");
    private static final ResourceLocation FLAME_ALTAR = new ResourceLocation(Reference.MOD_ID + ":models/ModelFlameAltar.obj");
    public static final ResourceLocation GLASS_PIPE = new ResourceLocation(Reference.MOD_ID + ":models/ModelGlassPipe.obj");
    public static final ResourceLocation GYRO = new ResourceLocation(Reference.MOD_ID + ":models/ModelGyro.obj");
    public static final ResourceLocation FLAME_SHARD = new ResourceLocation(Reference.MOD_ID + ":models/ModelFlameShard.obj");
    public static final ResourceLocation FLAME_CONDUIT = new ResourceLocation(Reference.MOD_ID + ":models/ModelFlameConduit.obj");
    public static final ResourceLocation CRYSTAL_2 = new ResourceLocation(Reference.MOD_ID + ":models/ModelCrystal2.obj");
    public static final ResourceLocation RHOMBICUBOCTAHEDRON = new ResourceLocation(Reference.MOD_ID + ":models/ModelRhombicuboctahedron.obj");
    
    private Models() {
    }
    
    public static void initModels() {
        
        ModelMultiPanel = (WavefrontObject) AdvancedModelLoader.loadModel(Models.MULTI_PANEL);
        ModelCrystal1 = (WavefrontObject) AdvancedModelLoader.loadModel(Models.CRYSTAL_1);
        ModelEssenceDiffuser = (WavefrontObject) AdvancedModelLoader.loadModel(Models.ESSENCE_DIFFUSER);
        ModelCentrifgue = (WavefrontObject) AdvancedModelLoader.loadModel(Models.CENTRIFUGE);
        ModelElementalRefinery = (WavefrontObject) AdvancedModelLoader.loadModel(Models.ELEMENTAL_REFINERY);
        ModelFlameFocus = (WavefrontObject) AdvancedModelLoader.loadModel(Models.FLAME_FOCUS);
        ModelFlameAltar = (WavefrontObject) AdvancedModelLoader.loadModel(Models.FLAME_ALTAR);
        ModelGlassPipe = (WavefrontObject) AdvancedModelLoader.loadModel(Models.GLASS_PIPE);
        ModelGyro = (WavefrontObject) AdvancedModelLoader.loadModel(Models.GYRO);
        ModelElectisShard = (WavefrontObject) AdvancedModelLoader.loadModel(Models.FLAME_SHARD);
        ModelFlameConduit = (WavefrontObject) AdvancedModelLoader.loadModel(Models.FLAME_CONDUIT);
        ModelCrystal2 = (WavefrontObject) AdvancedModelLoader.loadModel(Models.CRYSTAL_2);
        ModelRhombicuboctahedron = (WavefrontObject) AdvancedModelLoader.loadModel(Models.RHOMBICUBOCTAHEDRON);
    }
}

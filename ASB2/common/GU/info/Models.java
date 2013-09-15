package GU.info;

import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public final class Models {

    public static IModelCustom ModelCreationTable;
    public static IModelCustom ModelCrystalItem;
    public static IModelCustom ModelBlockBreaker;
    public static IModelCustom ModelEssenceHandler;
    public static IModelCustom ModelRunicCube;
    public static IModelCustom ModelCulsterSender;
    public static IModelCustom ModelSender;
    public static IModelCustom ModelBloodStone;
    public static IModelCustom ModelSolarFocus;
    
    public static final String LAMP = Reference.RESOURCE_LOCATION + "models/ModelLamp.obj";
    public static final String CREATION_TABLE = Reference.RESOURCE_LOCATION + "models/ModelCreationTable.obj";
    public static final String CRYSTAL_ITEM = Reference.RESOURCE_LOCATION + "models/ModelCrystal.obj";
    public static final String BLOCK_BREAKER = Reference.RESOURCE_LOCATION + "models/ModelBlockBreaker.obj";
    public static final String ESSENCE_HANDLER = Reference.RESOURCE_LOCATION + "models/ModelEssenceHandler.obj";
    public static final String RUNIC_CUBE = Reference.RESOURCE_LOCATION + "models/ModelRunicCube.obj";
    public static final String CLUSTER_SENDER = Reference.RESOURCE_LOCATION + "models/ModelClusterSender.obj";
    public static final String SENDER = Reference.RESOURCE_LOCATION + "models/ModelSender.obj";
    public static final String BLOOD_STONE = Reference.RESOURCE_LOCATION + "models/ModelBloodStone.obj";
    public static final String SOLAR_FOCUS = Reference.RESOURCE_LOCATION + "models/ModelSolarFocus.obj";
    
    public static void initModels() {

        ModelCrystalItem = AdvancedModelLoader.loadModel(Models.CRYSTAL_ITEM);
        ModelCreationTable = AdvancedModelLoader.loadModel(Models.CREATION_TABLE);
        ModelBlockBreaker = AdvancedModelLoader.loadModel(Models.BLOCK_BREAKER);
        ModelEssenceHandler = AdvancedModelLoader.loadModel(Models.BLOCK_BREAKER);
        ModelRunicCube = AdvancedModelLoader.loadModel(Models.RUNIC_CUBE);
        ModelCulsterSender = AdvancedModelLoader.loadModel(Models.CLUSTER_SENDER);
        ModelSender = AdvancedModelLoader.loadModel(Models.SENDER);
        ModelBloodStone = AdvancedModelLoader.loadModel(Models.BLOOD_STONE);
        ModelSolarFocus = AdvancedModelLoader.loadModel(Models.SOLAR_FOCUS);
    }
}

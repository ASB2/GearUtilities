package GU.info;

import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public final class Models {

    public static IModelCustom ModelCreationTable;
    public static IModelCustom ModelCrystalItem;
    public static IModelCustom ModelBlockBreaker;
    public static IModelCustom ModelEssenceHandler;
    
    public static final String LAMP = Reference.RESOURCE_LOCATION + "models/ModelLamp.obj";
    public static final String CREATION_TABLE = Reference.RESOURCE_LOCATION + "models/ModelCreationTable.obj";
    public static final String CRYSTAL_ITEM = Reference.RESOURCE_LOCATION + "models/ModelCrystal.obj";
    public static final String BLOCK_BREAKER = Reference.RESOURCE_LOCATION + "models/ModelBlockBreaker.obj";
    public static final String ESSENCE_HANDLER = Reference.RESOURCE_LOCATION + "models/ModelEssenceHandler.obj";

    public static void initModels() {

        ModelCrystalItem = AdvancedModelLoader.loadModel(Models.CRYSTAL_ITEM);
        ModelCreationTable = AdvancedModelLoader.loadModel(Models.CREATION_TABLE);
        ModelBlockBreaker = AdvancedModelLoader.loadModel(Models.BLOCK_BREAKER);
        ModelEssenceHandler = AdvancedModelLoader.loadModel(Models.BLOCK_BREAKER);
    }
}

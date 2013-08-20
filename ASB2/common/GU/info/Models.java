package GU.info;

import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public final class Models {

    public static IModelCustom ModelCreationTable;
    public static IModelCustom ModelCrystalItem;
    public static IModelCustom ModelLamp;

    public static final String TETRA = Reference.RESOURCE_LOCATION
            + "models/Tetra.obj";
    public static final String ISOSPHERE = Reference.RESOURCE_LOCATION
            + "models/isosphere.obj";
    public static final String TORUS = Reference.RESOURCE_LOCATION
            + "models/torus.obj";
    public static final String GEAR = Reference.RESOURCE_LOCATION
            + "models/GearSingle.obj";
    public static final String LAMP = Reference.RESOURCE_LOCATION
            + "models/ModelLamp.obj";
    public static final String CREATION_TABLE = Reference.RESOURCE_LOCATION
            + "models/ModelCreationTable2.obj";
    public static final String CRYSTAL_ITEM = Reference.RESOURCE_LOCATION
            + "models/ModelCrystal.obj";

    public static final String TEST = Reference.RESOURCE_LOCATION
            + "models/ModelLamp2.obj";

    public static void initModels() {

        ModelCreationTable = AdvancedModelLoader
                .loadModel(Models.CREATION_TABLE);
        ModelCrystalItem = AdvancedModelLoader.loadModel(Models.CRYSTAL_ITEM);
        ModelLamp = AdvancedModelLoader.loadModel(Models.LAMP);
    }
}

package GUOLD.models;

import net.minecraft.client.model.ModelBase;
import net.minecraftforge.client.model.IModelCustom;

public class ModelTest extends ModelBase {

    private IModelCustom model;

    public ModelTest() {

//        model = AdvancedModelLoader.loadModel(Models.CRYSTAL_ITEM);s
    }

    public void render() {

        model.renderAll();
    }
}
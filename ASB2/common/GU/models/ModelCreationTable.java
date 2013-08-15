package GU.models;

import net.minecraft.client.model.ModelBase;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import GU.info.Models;

public class ModelCreationTable extends ModelBase {

    private IModelCustom model;

    public ModelCreationTable() {

        model = AdvancedModelLoader.loadModel(Models.CREATION_TABLE);
    }

    public void render() {

        model.renderAll();
    }
}

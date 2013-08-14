package GU.models;

import GU.info.Models;
import net.minecraft.client.model.ModelBase;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class ModelLamp extends ModelBase {

    private IModelCustom model;

    public ModelLamp() {

        model = AdvancedModelLoader.loadModel(Models.LAMP);
    }

    public void render() {

        model.renderAll();
    }
}

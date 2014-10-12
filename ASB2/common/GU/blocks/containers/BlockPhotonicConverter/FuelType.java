package GU.blocks.containers.BlockPhotonicConverter;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.util.ResourceLocation;
import ASB2.utils.UtilRender;
import GU.info.Reference;
import UC.color.Color4i;

public enum FuelType {
    
    NONE, ITEM, FLUID, SOLAR, ENDER, FOOD, REDSTONE;
    
    private static Map<Integer, ResourceLocation[]> textures = new HashMap<Integer, ResourceLocation[]>();
    
    static {
        
        for (FuelType type : FuelType.values()) {
            
            String folder = type.ordinal() == 0 ? "" : "/" + type.toString().toLowerCase();
            
            textures.put(type.ordinal(), new ResourceLocation[] { new ResourceLocation(Reference.MOD_ID + ":textures/models/ModelPhotonicConverter" + folder + "/ModelGyroBase.png"), new ResourceLocation(Reference.MOD_ID + ":textures/models/ModelPhotonicConverter" + folder + "/ModelGyroCenter.png"), new ResourceLocation(Reference.MOD_ID + ":textures/models/ModelPhotonicConverter" + folder + "/ModelGyroPanel.png") });
        }
    }
    
    public Color4i getPanelColor() {
        
        // switch (this) {
        //
        // case ITEMS: {
        //
        // return Color4i.GREEN;
        // }
        //
        // case FLUID: {
        //
        // return Color4i.BLUE;
        // }
        //
        // case SOLAR: {
        //
        // return Color4i.GOLD;
        // }
        //
        // case ENDER: {
        //
        // return Color4i.CYAN.darker(150);
        // }
        //
        // case FOOD: {
        //
        // return Color4i.PINK.darker(120);
        // }
        //
        // case REDSTONE: {
        //
        // return Color4i.RED;
        // }
        // default:
        // break;
        // }
        return Color4i.WHITE;
    }
    
    public Color4i getBaseColor() {
        
        return Color4i.WHITE;
    }
    
    public Color4i getCenterColor() {
        
        return Color4i.WHITE;
    }
    
    public void bindTextureBase() {
        
        UtilRender.renderTexture(textures.get(this.ordinal())[0]);
    }
    
    public void bindTextureCenter() {
        
        UtilRender.renderTexture(textures.get(this.ordinal())[1]);
    }
    
    public void bindTexturePanel() {
        
        UtilRender.renderTexture(textures.get(this.ordinal())[2]);
    }
}
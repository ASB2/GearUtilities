package GU.render;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;

public class TextureNoise extends TextureAtlasSprite {
    
    protected TextureNoise(String par1Str) {
        super(par1Str);
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public boolean hasCustomLoader(IResourceManager manager, ResourceLocation location) {
        return true;
    }
}

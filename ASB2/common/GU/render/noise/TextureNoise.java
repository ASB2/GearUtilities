package GU.render.noise;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.data.AnimationFrame;
import net.minecraft.client.resources.data.AnimationMetadataSection;
import net.minecraft.util.ResourceLocation;
import GU.info.Variables;

public class TextureNoise extends TextureAtlasSprite {
    
    public int[] data;
    
    protected TextureNoise(String par1Str) {
        super(par1Str);
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public boolean load(IResourceManager manager, ResourceLocation location) {
        
        BufferedImage[] imageArray = new BufferedImage[1 + Minecraft.getMinecraft().gameSettings.mipmapLevels];
        
        int[] data = NoiseManager.instance.imageDataArray.get(0);
        
        BufferedImage bufferedImage = new BufferedImage(Variables.NOISE_TEXTURE_SIZE, Variables.NOISE_TEXTURE_SIZE, BufferedImage.TYPE_INT_ARGB);
        bufferedImage.setRGB(0, 0, Variables.NOISE_TEXTURE_SIZE, Variables.NOISE_TEXTURE_SIZE, data, 0, Variables.NOISE_TEXTURE_SIZE);
        imageArray[0] = bufferedImage;
        super.loadSprite(imageArray, null, true);
        
        // BufferedImage[] imageArray = new BufferedImage[1 +
        // Minecraft.getMinecraft().gameSettings.mipmapLevels +
        // NoiseManager.instance.imageDataArray.size()];
        
        // List<AnimationFrame> frames = new ArrayList<AnimationFrame>();
        //
        // for (int index = 0; index <
        // NoiseManager.instance.imageDataArray.size(); index++) {
        //
        // int[] data = NoiseManager.instance.imageDataArray.get(index);
        //
        // BufferedImage bufferedImage = new
        // BufferedImage(Variables.NOISE_TEXTURE_SIZE,
        // Variables.NOISE_TEXTURE_SIZE, BufferedImage.TYPE_INT_ARGB);
        // bufferedImage.setRGB(0, 0, Variables.NOISE_TEXTURE_SIZE,
        // Variables.NOISE_TEXTURE_SIZE, data, 0, Variables.NOISE_TEXTURE_SIZE);
        // imageArray[index] = bufferedImage;
        //
        // frames.add(new AnimationFrame(index, 1));
        // }
        // super.loadSprite(imageArray, new AnimationMetadataSection(frames,
        // Variables.NOISE_TEXTURE_SIZE, Variables.NOISE_TEXTURE_SIZE, 1),
        // true);
        
        return false;
    }
    
    @Override
    public boolean hasCustomLoader(IResourceManager manager, ResourceLocation location) {
        
        return true;
    }
}

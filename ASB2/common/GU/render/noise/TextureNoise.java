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
        
        // BufferedImage[] imageArray = new BufferedImage[1 +
        // Minecraft.getMinecraft().gameSettings.mipmapLevels];
        //
        // int[] data = NoiseManager.instance.imageDataArray.get(0);
        //
        // BufferedImage bufferedImage = new
        // BufferedImage(Variables.NOISE_TEXTURE_SIZE,
        // Variables.NOISE_TEXTURE_SIZE, BufferedImage.TYPE_INT_ARGB);
        // bufferedImage.setRGB(0, 0, Variables.NOISE_TEXTURE_SIZE,
        // Variables.NOISE_TEXTURE_SIZE, data, 0, Variables.NOISE_TEXTURE_SIZE);
        // imageArray[0] = bufferedImage;
        // super.loadSprite(imageArray, null, true);
        
        BufferedImage[] imageArray = new BufferedImage[(1 + Minecraft.getMinecraft().gameSettings.mipmapLevels)];
        
        List<AnimationFrame> frames = new ArrayList<AnimationFrame>();
        final int FRAME_SIZE = NoiseManager.instance.imageDataArray.size();
        
        BufferedImage finalImage = new BufferedImage(Variables.NOISE_TEXTURE_SIZE, FRAME_SIZE * Variables.NOISE_TEXTURE_SIZE, BufferedImage.TYPE_INT_ARGB);
        
        for (int index = 0; index < FRAME_SIZE; index++) {
            
            int[] data = NoiseManager.instance.imageDataArray.get(index);
            
            finalImage.setRGB(0, index * Variables.NOISE_TEXTURE_SIZE, Variables.NOISE_TEXTURE_SIZE, Variables.NOISE_TEXTURE_SIZE, data, 0, Variables.NOISE_TEXTURE_SIZE);
            
            frames.add(new AnimationFrame(index, 1));
        }
        
        for (int index = 0; index < FRAME_SIZE; index++) {
            
            frames.add(new AnimationFrame((FRAME_SIZE - 1) - index, 1));
        }
        
        imageArray[0] = finalImage;
        super.loadSprite(imageArray, new AnimationMetadataSection(frames, Variables.NOISE_TEXTURE_SIZE, Variables.NOISE_TEXTURE_SIZE, 1), false);
        return false;
    }
    
    @Override
    public boolean hasCustomLoader(IResourceManager manager, ResourceLocation location) {
        
        return true;
    }
}

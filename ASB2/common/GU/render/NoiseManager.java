package GU.render;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureUtil;

import org.lwjgl.opengl.GL11;

import GU.GearUtilities;
import GU.info.Variables;
import UC.FastNoise;

public class NoiseManager {
    
    public static NoiseManager instance = new NoiseManager();
    
    public int GL_TEXTURE_ID;
    
    // Creation Thigns
    public BufferedImage longVinillaAnimationImage = null;
    public List<int[]> imageDataArray = new ArrayList<int[]>();
    public static final float maxDensity = .4f, minDensity = .1f, changePerTick = .0002f;
    
    public NoiseManager() {
        
    }
    
    public void initImage() {
        long startTime = Minecraft.getSystemTime();
        GL_TEXTURE_ID = GL11.glGenTextures();
        generateNoiseImage();
        TextureUtil.allocateTexture(GL_TEXTURE_ID, Variables.NOISE_TEXTURE_SIZE, Variables.NOISE_TEXTURE_SIZE);
        long endTime = Minecraft.getSystemTime();
        GearUtilities.log(endTime - startTime);
    }
    
    public static void bindImage() {
        
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, instance.GL_TEXTURE_ID);
    }
    
    public void generateNoiseImage() {
        
        imageDataArray.clear();
        this.longVinillaAnimationImage = null;
        
        for (float currentDensity = minDensity; currentDensity <= maxDensity; currentDensity += changePerTick) {
            
            int[] imageData = new int[Variables.NOISE_TEXTURE_SIZE * Variables.NOISE_TEXTURE_SIZE];
            
            for (int x = 0; x < Variables.NOISE_TEXTURE_SIZE; x++) {
                
                for (int y = 0; y < Variables.NOISE_TEXTURE_SIZE; y++) {
                    
                    int col = FastNoise.noise(x * currentDensity, y * currentDensity, 7);
                    
                    int red = col;
                    int green = col;
                    int blue = col;
                    
                    int RGB = red;
                    RGB = (RGB << 8) + green;
                    RGB = (RGB << 8) + blue;
                    RGB |= 0xFF000000;
                    
                    imageData[x + y * Variables.NOISE_TEXTURE_SIZE] = RGB;
                }
            }
            imageDataArray.add(imageData);
        }
        
        BufferedImage finalImage = new BufferedImage(Variables.NOISE_TEXTURE_SIZE, imageDataArray.size() * Variables.NOISE_TEXTURE_SIZE, BufferedImage.TYPE_INT_ARGB);
        
        for (int index = 0; index < imageDataArray.size(); index++) {
            
            int[] image = imageDataArray.get(index);
            finalImage.setRGB(0, index * Variables.NOISE_TEXTURE_SIZE, Variables.NOISE_TEXTURE_SIZE, Variables.NOISE_TEXTURE_SIZE, image, 0, Variables.NOISE_TEXTURE_SIZE);
        }
        this.longVinillaAnimationImage = finalImage;
    }
}

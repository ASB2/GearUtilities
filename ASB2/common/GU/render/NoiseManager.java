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
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;

public class NoiseManager {
    
    public static NoiseManager instance = new NoiseManager();
    
    public int GL_TEXTURE_ID;
    
    public List<int[]> imageDataArray = new ArrayList<int[]>(20000);
    public static final float maxDensity = .4f, minDensity = .1f, changePerTick = .0005f;
    
    boolean generateTextures = true;
    float currentDensity = 0;
    
    boolean moveAnimationDown = true;
    int animationPosition = 0;
    
    public NoiseManager() {
        
    }
    
    public void initImage() {
        
        GL_TEXTURE_ID = GL11.glGenTextures();
        
        long startTime = Minecraft.getSystemTime();
        GearUtilities.log("Noise Gen Has Begun");
        generateNoiseImage();
        GearUtilities.log("Noise Gen Has Ended");
        long endTime = Minecraft.getSystemTime();
        GearUtilities.log("Elapsed Time of Noise Gen: " + (endTime - startTime));
        
        TextureUtil.allocateTexture(GL_TEXTURE_ID, Variables.NOISE_TEXTURE_SIZE, Variables.NOISE_TEXTURE_SIZE);
    }
    
    public static void bindImage() {
        
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, instance.GL_TEXTURE_ID);
    }
    
    public void generateNoiseImage() {
        
        imageDataArray.clear();
        
        for (float currentDensity = minDensity; currentDensity <= maxDensity; currentDensity += changePerTick) {
            
            int[] imageData = new int[Variables.NOISE_TEXTURE_SIZE * Variables.NOISE_TEXTURE_SIZE];
            
            for (int x = 0; x < Variables.NOISE_TEXTURE_SIZE; x++) {
                
                for (int y = 0; y < Variables.NOISE_TEXTURE_SIZE; y++) {
                    
                    // int col = (int) (SimplexNoise.noise(x * currentDensity, y
                    // * currentDensity, 7) * 255);
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
    }
    
    @SubscribeEvent
    public void updateNoise(RenderTickEvent event) {
        //
        // if (generateTextures) {
        //
        // if (currentDensity == 0) {
        //
        // imageDataArray.clear();
        // }
        //
        // currentDensity += changePerTick;
        //
        // if (currentDensity > maxDensity) {
        // currentDensity = 0;
        // generateTextures = false;
        // return;
        // }
        //
        // int[] imageData = new int[Variables.NOISE_TEXTURE_SIZE *
        // Variables.NOISE_TEXTURE_SIZE];
        //
        // for (int x = 0; x < Variables.NOISE_TEXTURE_SIZE; x++) {
        //
        // for (int y = 0; y < Variables.NOISE_TEXTURE_SIZE; y++) {
        //
        // // int col = (int) (SimplexNoise.noise(x *
        // // currentDensity, y
        // // * currentDensity, 7) * 255);
        // int col = FastNoise.noise(x * currentDensity, y * currentDensity, 7);
        //
        // int red = col;
        // int green = col;
        // int blue = col;
        //
        // int RGB = red;
        // RGB = (RGB << 8) + green;
        // RGB = (RGB << 8) + blue;
        // RGB |= 0xFF000000;
        // imageData[x + y * Variables.NOISE_TEXTURE_SIZE] = RGB;
        // }
        // }
        // imageDataArray.add(imageData);
        // }
        // else
        {
            
            TextureUtil.uploadTexture(NoiseManager.instance.GL_TEXTURE_ID, imageDataArray.get(animationPosition), Variables.NOISE_TEXTURE_SIZE, Variables.NOISE_TEXTURE_SIZE);
            
            if (moveAnimationDown) {
                
                if (animationPosition >= imageDataArray.size() - 1) {
                    moveAnimationDown = false;
                    animationPosition = imageDataArray.size() - 1;
                }
                else {
                    // position = Math.round((position + (1 * (float)
                    // tickData[0])));
                    animationPosition++;
                }
                
            }
            else {
                
                if (animationPosition <= 0) {
                    moveAnimationDown = true;
                    animationPosition = 0;
                }
                else {
                    // position = Math.round((position - (1 * (float)
                    // tickData[0])));
                    animationPosition--;
                }
            }
        }
    }
}

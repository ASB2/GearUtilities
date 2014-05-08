package GU.render;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureUtil;

import org.lwjgl.opengl.GL11;

import ASB2.FastNoise;
import GU.info.Reference;
import GU.info.Variables;
import UC.SimplexNoise;
import GU.*;

public class NoiseManager {
    
    public static NoiseManager instance = new NoiseManager();
    
    public TextureNoise iconTexture = new TextureNoise(Reference.MOD_ID + ":Noise");
    public SimplexNoise noise = new SimplexNoise();
    
    public int GL_TEXTURE_ID;
    
    public int CURRENT_POSITION;
    
    // Creation Thigns
    public BufferedImage longVinillaAnimationImage = null;
    public List<int[]> imageDataArray = new ArrayList<int[]>();
    public static final float maxDensity = .4f, minDensity = .1f, changePerTick = .0005f;
    public static final int BOX_SIZE = 1;
    
    public NoiseManager() {
        
    }
    
    public void initImage() {
        long startTime = Minecraft.getSystemTime();
        GL_TEXTURE_ID = GL11.glGenTextures();
        initBufferedImage();
        TextureUtil.allocateTexture(GL_TEXTURE_ID, Variables.NOISE_TEXTURE_SIZE, Variables.NOISE_TEXTURE_SIZE);
        long endTime = Minecraft.getSystemTime();
        GearUtilities.log(endTime - startTime);
    }
    
    public static void bindImage() {
        
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, instance.GL_TEXTURE_ID);
    }
    
    public void initBufferedImage() {
        
        imageDataArray.clear();
        
        for (float currentDensity = minDensity; currentDensity <= maxDensity; currentDensity += changePerTick) {
            
            int[] imageData = new int[Variables.NOISE_TEXTURE_SIZE * Variables.NOISE_TEXTURE_SIZE];
            
            for (int x = 0; x < Variables.NOISE_TEXTURE_SIZE; x += BOX_SIZE) {
                
                for (int y = 0; y < Variables.NOISE_TEXTURE_SIZE; y += BOX_SIZE) {
                    
                    int col = FastNoise.noise(x * currentDensity, y * currentDensity, 7);
                    
                    int red = col;
                    int green = col;
                    int blue = col;
                    
                    int RGB = red;
                    RGB = (RGB << 8) + green;
                    RGB = (RGB << 8) + blue;
                    
                    imageData[x + y * Variables.NOISE_TEXTURE_SIZE] = RGB;
                }
            }
            
            imageDataArray.add(imageData);
            
        }
        
        BufferedImage finalImage = new BufferedImage(Variables.NOISE_TEXTURE_SIZE, imageDataArray.size() * Variables.NOISE_TEXTURE_SIZE, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = (Graphics2D) finalImage.createGraphics();
        
        for (int index = 0; index < imageDataArray.size(); index++) {
            
            int[] image = imageDataArray.get(index);
            graphics.setColor(new Color(image[index * Variables.NOISE_TEXTURE_SIZE]));
            graphics.fillRect(0, index * Variables.NOISE_TEXTURE_SIZE, Variables.NOISE_TEXTURE_SIZE, Variables.NOISE_TEXTURE_SIZE);
        }
        graphics.dispose();
        this.longVinillaAnimationImage = finalImage;
        writeImage(longVinillaAnimationImage, new File("C:/Users/AOJ/Desktop/Documents/Java Projects/image.png"), "PNG");
    }
    
    public static void modifyBufferedImage(int[] imageData, int x, int y, int xBoxSize, int yBoxSize) {
        
    }
    
    public static boolean writeImage(BufferedImage image, File outputFile, String format) {
        
        // if (outputFile.isFile()) {
        
        try {
            
            if (!outputFile.exists()) {
                
                outputFile.createNewFile();
            }
            
            ImageIO.write(image, format, outputFile);
            return true;
        }
        catch (IOException e) {
            
            e.printStackTrace();
        }
        // }
        return false;
    }
}

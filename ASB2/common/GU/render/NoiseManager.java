package GU.render;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import ASB2.FastNoise;
import GU.info.Reference;
import GU.info.Variables;

public class NoiseManager {
    
    public static NoiseManager instance = new NoiseManager();
    
    public DynamicTexture textureImage;
    
    public ResourceLocation textureLocation = new ResourceLocation(Reference.MOD_ID + ":textures/Noise.png");
    
    public TextureNoise iconTexture = new TextureNoise(Reference.MOD_ID + ":Noise");
    
    // Creation Thigns
    public BufferedImage longVinillaAnimationImage = null;
    public List<int[]> imageDataArray = new LinkedList<int[]>();
    public List<BufferedImage> bufferedImages = new LinkedList<BufferedImage>();
    public static final float maxDensity = .4f, minDensity = .1f, changePerTick = .0005f;
    public static final int BOX_SIZE = 1;
    
    public NoiseManager() {
    }
    
    public void initImage() {
        
        textureImage = new DynamicTexture(Variables.NOISE_TEXTURE_SIZE, Variables.NOISE_TEXTURE_SIZE);
        TextureUtil.allocateTexture(textureImage.getGlTextureId(), Variables.NOISE_TEXTURE_SIZE, Variables.NOISE_TEXTURE_SIZE);
        initBufferedImage();
    }
    
    public static void bindImage() {
        
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, NoiseManager.instance.textureImage.getGlTextureId());
    }
    
    public void initBufferedImage() {
        
        float currentDensity = minDensity;
        
        while (currentDensity <= maxDensity) {
            
            BufferedImage bufferedImage = new BufferedImage(Variables.NOISE_TEXTURE_SIZE, Variables.NOISE_TEXTURE_SIZE, BufferedImage.TYPE_INT_ARGB);
            
            if (currentDensity != minDensity) bufferedImages.add(bufferedImage);
            
            Graphics2D graphics = (Graphics2D) bufferedImage.createGraphics();
            
            for (int x = 0; x < bufferedImage.getWidth(); x += BOX_SIZE) {
                
                for (int y = 0; y < bufferedImage.getHeight(); y += BOX_SIZE) {
                    
                    int col = FastNoise.noise(x * currentDensity, y * currentDensity, 7);
                    
                    int red = col;
                    int green = col;
                    int blue = col;
                    
                    int RGB = red;
                    RGB = (RGB << 8) + green;
                    RGB = (RGB << 8) + blue;
                    graphics.setColor(new Color(RGB));
                    graphics.fillRect(x, y, BOX_SIZE, BOX_SIZE);
                }
            }
            
            graphics.dispose();
            int[] imageData = new int[bufferedImage.getWidth() * bufferedImage.getHeight()];
            bufferedImage.getRGB(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), imageData, 0, bufferedImage.getWidth());
            imageDataArray.add(imageData);
            currentDensity += changePerTick;
        }
        
        BufferedImage finalImage = new BufferedImage(Variables.NOISE_TEXTURE_SIZE, bufferedImages.size() * Variables.NOISE_TEXTURE_SIZE, BufferedImage.TYPE_INT_ARGB);
        
        int position = 0;
        
        for (BufferedImage image : bufferedImages) {
            
            Graphics2D graphics = (Graphics2D) finalImage.createGraphics();
            graphics.drawImage(image, 0, position * Variables.NOISE_TEXTURE_SIZE, null);
            graphics.dispose();
            position++;
        }
        
        this.longVinillaAnimationImage = finalImage;
    }
    
    public static void modifyBufferedImage(int[] imageData, int x, int y, int xBoxSize, int yBoxSize) {
        
    }
    
}

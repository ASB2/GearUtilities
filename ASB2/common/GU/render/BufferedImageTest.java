package GU.render;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;
import ASB2.utils.UtilRender;

public class BufferedImageTest {
    
    public static BufferedImage image;
    public static DynamicTexture textureImage = new DynamicTexture(BufferedImageTest.getImage());
    public static ResourceLocation textureLocation = Minecraft.getMinecraft().getTextureManager().getDynamicTextureLocation("TestImage", textureImage);
    
    public BufferedImageTest() {
        
    }
    
    public static BufferedImage init() {
        
        try {
            return image = ImageIO.read(new File("TestImage.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static BufferedImage getImage() {
        
        return image == null ? init() : image;
    }
    
    public static void bindImage() {
        // init();
        // textureImage = new DynamicTexture(BufferedImageTest.getImage());
        // textureLocation =
        // Minecraft.getMinecraft().getTextureManager().getDynamicTextureLocation("TestImage",
        // textureImage);
        UtilRender.renderTexture(textureLocation);
    }
}

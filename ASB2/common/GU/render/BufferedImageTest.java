package GU.render;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.util.ResourceLocation;
import GU.info.Reference;
import cpw.mods.fml.common.FMLCommonHandler;

public class BufferedImageTest {
    
    public static BufferedImage image = null;
    
    public static DynamicTexture textureImage;
    
    public static ResourceLocation textureLocation = new ResourceLocation(Reference.MODDID + ":textures/LargeBlankTexture.png");
    
    public BufferedImageTest() {
        
    }
    
    public static BufferedImage init() {
        
        try {
            
            image = ImageIO.read(Minecraft.getMinecraft().getResourceManager().getResource(textureLocation).getInputStream());
            textureImage = new DynamicTexture(BufferedImageTest.getImage());
            TextureUtil.allocateTexture(textureImage.getGlTextureId(), image.getWidth(), image.getHeight());
        } catch (IOException e) {
            
            FMLCommonHandler.instance().raiseException(e, "Can't read image at: " + textureLocation.getResourcePath(), true);
        }
        return image;
    }
    
    public static BufferedImage getImage() {
        
        return image == null ? init() : image;
    }
    
    public static void bindImage() {
        
        // UtilRender.renderTexture(textureLocation);
        // image.getRGB(0, 0, image.getWidth(), image.getHeight(),
        // textureImage.getTextureData(), 0, image.getWidth());
        TextureUtil.uploadTexture(textureImage.getGlTextureId(), textureImage.getTextureData(), image.getWidth(), image.getHeight());
    }
}

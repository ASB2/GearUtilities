package GU.render;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;
import ASB2.utils.UtilRender;
import GU.info.Reference;
import cpw.mods.fml.common.FMLCommonHandler;

public class BufferedImageTest {
    
    public static BufferedImage image = null;
    public static DynamicTexture textureImage = new DynamicTexture(BufferedImageTest.getImage());
    public static ResourceLocation textureLocation = new ResourceLocation(Reference.MODDID.toLowerCase() + ":textures/LargeBlankTexture.png");
    
    public BufferedImageTest() {
        
    }
    
    public static BufferedImage init() {
        
        try {
            image = ImageIO.read(Minecraft.getMinecraft().getResourceManager().getResource(textureLocation).getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            FMLCommonHandler.instance().raiseException(e, "Cant Read Image At: " + textureLocation.getResourcePath(), false);
        }
        return image;
    }
    
    public static BufferedImage getImage() {
        
        return image == null ? init() : image;
    }
    
    public static void bindImage() {
        // init();
        // textureImage = new DynamicTexture(BufferedImageTest.getImage());
        
        // Minecraft.getMinecraft().getTextureManager().getDynamicTextureLocation("LargeBlankTexture",
        // new DynamicTexture(BufferedImageTest.getImage()));
        UtilRender.renderTexture(textureLocation);
    }
}

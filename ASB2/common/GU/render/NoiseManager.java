package GU.render;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.util.Icon;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import GU.info.Reference;
import cpw.mods.fml.common.FMLCommonHandler;

public class NoiseManager {

    public static BufferedImage originalImage = null;

    public static int size = 16;

    public static DynamicTexture textureImage;

    public static ResourceLocation textureLocation = new ResourceLocation(Reference.MODDID + ":textures/Noise.png");

    public static TextureNoise iconTexture = new TextureNoise(Reference.MODDID + ":Noise");

    public NoiseManager() {

    }

    public static BufferedImage loadImage() {

        try {

            originalImage = ImageIO.read(Minecraft.getMinecraft().getResourceManager().getResource(textureLocation).getInputStream());
            textureImage = new DynamicTexture(NoiseManager.getImage());
            TextureUtil.allocateTexture(textureImage.getGlTextureId(), originalImage.getWidth(), originalImage.getHeight());
        } catch (IOException e) {

            FMLCommonHandler.instance().raiseException(e, "Can't read image at: " + textureLocation.getResourcePath(), true);
        }

        if (originalImage != null) {

            NoiseManager.size = originalImage.getWidth();
        }
        return originalImage;
    }

    public static BufferedImage getImage() {

        return originalImage == null ? loadImage() : originalImage;
    }

    public static void bindImage() {

        // UtilRender.renderTexture(textureLocation);
        // image.getRGB(0, 0, image.getWidth(), image.getHeight(),
        // textureImage.getTextureData(), 0, image.getWidth());
        // TextureUtil.uploadTexture(textureImage.getGlTextureId(),
        // textureImage.getTextureData(), image.getWidth(), image.getHeight());

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureImage.getGlTextureId());
    }

    public Icon getTexture() {

        return null;
    }
}

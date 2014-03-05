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

    public static NoiseManager instance = new NoiseManager();

    public BufferedImage originalImage = null;

    public int size = 16;

    public DynamicTexture textureImage;

    public ResourceLocation textureLocation = new ResourceLocation(Reference.MODDID + ":textures/Noise.png");

    public TextureNoise iconTexture = new TextureNoise(Reference.MODDID + ":Noise");

    public NoiseManager() {

    }

    public BufferedImage loadImage() {

        try {

            originalImage = ImageIO.read(Minecraft.getMinecraft().getResourceManager().getResource(textureLocation).getInputStream());
            textureImage = new DynamicTexture(NoiseManager.instance.getImage());
            TextureUtil.allocateTexture(textureImage.getGlTextureId(), originalImage.getWidth(), originalImage.getHeight());
        } catch (IOException e) {

            FMLCommonHandler.instance().raiseException(e, "Can't read image at: " + textureLocation.getResourcePath(), true);
        }

        NoiseManager.instance.size = originalImage.getWidth();
        return originalImage;
    }

    public BufferedImage getImage() {

        return originalImage == null ? loadImage() : originalImage;
    }

    public static void bindImage() {

        // UtilRender.renderTexture(textureLocation);
        // image.getRGB(0, 0, image.getWidth(), image.getHeight(),
        // textureImage.getTextureData(), 0, image.getWidth());
        // TextureUtil.uploadTexture(textureImage.getGlTextureId(),
        // textureImage.getTextureData(), image.getWidth(), image.getHeight());

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, NoiseManager.instance.textureImage.getGlTextureId());
    }

    public Icon getTexture() {

        return null;
    }
}

package GUOLD.render;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import UC.FastNoise;
import GUOLD.info.Reference;
import cpw.mods.fml.common.FMLCommonHandler;

public class NoiseManager {

    public static NoiseManager instance = new NoiseManager();

    public BufferedImage originalImage = null;

    public int size = 16;

    public DynamicTexture textureImage;

    public ResourceLocation textureLocation = new ResourceLocation(Reference.MODDID + ":textures/Noise.png");

    public TextureNoise iconTexture = new TextureNoise(Reference.MODDID + ":Noise");

    // Creation Thigns
    public BufferedImage longVinillaAnimationImage = null;
    public List<int[]> imageDataArray = new LinkedList<int[]>();
    public List<BufferedImage> bufferedImages = new LinkedList<BufferedImage>();
    public float maxDensity = .4f, minDensity = .1f, changePerTick = .0002f;
    public int BOX_SIZE = 1;

    public NoiseManager() {
        loadImage();
    }

    public BufferedImage loadImage() {

        try {

            originalImage = ImageIO.read(Minecraft.getMinecraft().getResourceManager().getResource(textureLocation).getInputStream());
            textureImage = new DynamicTexture(this.getImage());
            TextureUtil.allocateTexture(textureImage.getGlTextureId(), originalImage.getWidth(), originalImage.getHeight());
        } catch (IOException e) {

            FMLCommonHandler.instance().raiseException(e, "Can't read image at: " + textureLocation.getResourcePath(), true);
        }

        this.size = originalImage.getWidth();
        initBufferedImage();
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

    public void initBufferedImage() {

        float currentDensity = minDensity;

        while (currentDensity <= maxDensity) {

            BufferedImage bufferedImage = new BufferedImage(this.size, this.size, BufferedImage.TYPE_INT_ARGB);
            bufferedImages.add(bufferedImage);

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

            int[] imageData = new int[bufferedImage.getWidth() * bufferedImage.getHeight()];
            bufferedImage.getRGB(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), imageData, 0, bufferedImage.getWidth());
            imageDataArray.add(imageData);

            currentDensity += changePerTick;
        }

        BufferedImage finalImage = new BufferedImage(this.size, bufferedImages.size() * this.size, BufferedImage.TYPE_INT_ARGB);

        int position = 0;

        for (BufferedImage image : bufferedImages) {

            Graphics2D graphics = (Graphics2D) finalImage.createGraphics();
            graphics.drawImage(image, 0, position * this.size, null);
            graphics.dispose();
            position++;
        }

        this.longVinillaAnimationImage = finalImage;
    }
}

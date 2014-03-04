package GU.render;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.EnumSet;
import java.util.Random;

import net.minecraft.client.renderer.texture.TextureUtil;
import ASB2.FastNoise;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class NoiseRenderer implements ITickHandler {

    Random rand = new Random();

    public NoiseRenderer() {
        // TODO Auto-generated constructor stub
    }

    float[] renderDoubles = new float[10];

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {

        float maxDensity = .4f, minDensity = .1f, changePerTick = .0002f;
        BufferedImage newImage = BufferedImageTest.getImage();

        if (newImage != null) {

            if (type.equals(this.ticks())) {

                if (renderDoubles[0] == 0) {

                    renderDoubles[0] = 1;
                } else if (renderDoubles[1] == 0) {

                    renderDoubles[0] -= changePerTick;
                } else if (renderDoubles[1] == 1) {
                    renderDoubles[0] += changePerTick;
                }

                if (renderDoubles[0] >= maxDensity) {

                    renderDoubles[0] = maxDensity;
                    renderDoubles[1] = 0;
                } else if (renderDoubles[0] <= minDensity) {

                    renderDoubles[0] = minDensity;
                    renderDoubles[1] = 1;
                }

                float density = renderDoubles[0];
                int boxSize = 1;

                Graphics2D graphics = (Graphics2D) newImage.createGraphics();

                for (int x = 0; x < newImage.getWidth(); x += boxSize) {

                    for (int y = 0; y < newImage.getHeight(); y += boxSize) {

                        int col = FastNoise.noise(x * density, y * density, 7);

                        int red = col;
                        int green = col;
                        int blue = col;

                        int RGB = red;
                        RGB = (RGB << 8) + green;
                        RGB = (RGB << 8) + blue;
                        graphics.setColor(new Color(RGB));
                        graphics.fillRect(x, y, boxSize, boxSize);

                    }
                }
                newImage.getRGB(0, 0, newImage.getWidth(), newImage.getHeight(), BufferedImageTest.textureImage.getTextureData(), 0, newImage.getWidth());
                TextureUtil.uploadTexture(BufferedImageTest.textureImage.getGlTextureId(), BufferedImageTest.textureImage.getTextureData(), BufferedImageTest.image.getWidth(), BufferedImageTest.image.getHeight());
            }
        }
    }

    public int getNextColor(int lastColor, int minColorChange, int maxColorChange) {

        int randName = rand.nextInt(255);

        if (randName <= maxColorChange && randName >= minColorChange) {

            lastColor += randName;
        }
        return Math.abs(lastColor);
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData) {
        // TODO Auto-generated method stub

    }

    @Override
    public EnumSet<TickType> ticks() {

        return EnumSet.of(TickType.RENDER);
    }

    @Override
    public String getLabel() {

        return "Noise Renderer";
    }

}

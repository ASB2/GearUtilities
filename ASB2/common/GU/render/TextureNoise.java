package GU.render;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.Resource;
import net.minecraft.client.resources.ResourceManager;
import net.minecraft.client.resources.data.AnimationFrame;
import net.minecraft.client.resources.data.AnimationMetadataSection;
import net.minecraft.util.ResourceLocation;
import ASB2.FastNoise;

import com.google.common.collect.Lists;

import GU.*;

public class TextureNoise extends TextureAtlasSprite {

    public BufferedImage finalImage = null;
    public List<BufferedImage> bufferedImages = new ArrayList<BufferedImage>();
    public final static float maxDensity = .4f, minDensity = .1f, changePerTick = .0002f;
    public final static int BOX_SIZE = 1;

    // From SuperClass
    private AnimationMetadataSection animationMetadata;

    protected TextureNoise(String par1Str) {
        super(par1Str);
        initBufferedImage();
    }

    public boolean load(ResourceManager manager, ResourceLocation location) throws IOException {

        loadSprite(manager.getResource(location));
        return true;
    }

    public void initBufferedImage() {

        float currentDensity = minDensity;

        while (currentDensity < maxDensity) {

            GearUtilities.log("currentDesity: " + currentDensity);

            BufferedImage bufferedImage = new BufferedImage(NoiseManager.size, NoiseManager.size, BufferedImage.TYPE_INT_ARGB);
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
            bufferedImage.getRGB(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), new int[bufferedImage.getWidth() * bufferedImage.getHeight()], 0, bufferedImage.getWidth());

            currentDensity += changePerTick;
        }

        BufferedImage finalImage = new BufferedImage(NoiseManager.size, bufferedImages.size() * NoiseManager.size, BufferedImage.TYPE_INT_ARGB);

        int position = 1;

        for (BufferedImage image : bufferedImages) {

            Graphics2D graphics = (Graphics2D) finalImage.createGraphics();
            graphics.drawImage(image, 0, position * NoiseManager.size, null);
            graphics.dispose();
            position++;
        }

        this.finalImage = finalImage;
        bufferedImages.clear();

    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void loadSprite(Resource par1Resource) throws IOException {

        // From SuperClass
        AnimationMetadataSection animationmetadatasection = (AnimationMetadataSection) par1Resource.getMetadata("animation");
        this.height = finalImage.getHeight();
        this.width = finalImage.getWidth();
        int[] aint = new int[this.height * this.width];
        finalImage.getRGB(0, 0, this.width, this.height, aint, 0, this.width);

        if (animationmetadatasection == null) {
            if (this.height != this.width) {
                throw new RuntimeException("broken aspect ratio and not an animation");
            }

            this.framesTextureData.add(aint);
        } else {
            int i = this.height / this.width;
            int j = this.width;
            int k = this.width;
            this.height = this.width;
            int l;

            if (animationmetadatasection.getFrameCount() > 0) {
                Iterator iterator = animationmetadatasection.getFrameIndexSet().iterator();

                while (iterator.hasNext()) {
                    l = ((Integer) iterator.next()).intValue();

                    if (l >= i) {
                        throw new RuntimeException("invalid frameindex " + l);
                    }

                    this.allocateFrameTextureData(l);
                    this.framesTextureData.set(l, getFrameTextureData(aint, j, k, l));
                }

                this.animationMetadata = animationmetadatasection;
            } else {
                ArrayList arraylist = Lists.newArrayList();

                for (l = 0; l < i; ++l) {
                    this.framesTextureData.add(getFrameTextureData(aint, j, k, l));
                    arraylist.add(new AnimationFrame(l, -1));
                }

                this.animationMetadata = new AnimationMetadataSection(arraylist, this.width, this.height, animationmetadatasection.getFrameTime());
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void allocateFrameTextureData(int par1) {
        if (this.framesTextureData.size() <= par1) {
            for (int j = this.framesTextureData.size(); j <= par1; ++j) {
                this.framesTextureData.add((Object) null);
            }
        }
    }

    private static int[] getFrameTextureData(int[] par0ArrayOfInteger, int par1, int par2, int par3) {
        int[] aint1 = new int[par1 * par2];
        System.arraycopy(par0ArrayOfInteger, par3 * aint1.length, aint1, 0, aint1.length);
        return aint1;
    }

    public void updateAnimation() {
        ++this.tickCounter;

        if (this.tickCounter >= this.animationMetadata.getFrameTimeSingle(this.frameCounter)) {
            int i = this.animationMetadata.getFrameIndex(this.frameCounter);
            int j = this.animationMetadata.getFrameCount() == 0 ? this.framesTextureData.size() : this.animationMetadata.getFrameCount();
            this.frameCounter = (this.frameCounter + 1) % j;
            this.tickCounter = 0;
            int k = this.animationMetadata.getFrameIndex(this.frameCounter);

            if (i != k && k >= 0 && k < this.framesTextureData.size()) {
                TextureUtil.uploadTextureSub((int[]) this.framesTextureData.get(k), this.width, this.height, this.originX, this.originY, false, false);
            }
        }
    }
}

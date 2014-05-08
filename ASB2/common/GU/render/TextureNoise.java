package GU.render;

import java.awt.Image;
import java.awt.image.BufferedImage;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;

public class TextureNoise extends TextureAtlasSprite {
    
    protected TextureNoise(String par1Str) {
        super(par1Str);
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public boolean hasCustomLoader(IResourceManager manager, ResourceLocation location) {
        return true;
    }
    
    @Override
    public boolean load(IResourceManager manager, ResourceLocation location) {
        
        BufferedImage bl = NoiseManager.instance.longVinillaAnimationImage;
        
        BufferedImage[] images = new BufferedImage[1 + Minecraft.getMinecraft().gameSettings.mipmapLevels];
        images[0] = bl;
        for (int i = 0; i < images.length - 1; ++i) {
            int scaledWidth = bl.getWidth() / 2;
            int scaledHeight = bl.getHeight() / 2;
            Image scaled = bl.getScaledInstance(scaledWidth, scaledHeight, BufferedImage.SCALE_DEFAULT);
            // images[i + 1] = new BufferedImage(scaledWidth, scaledHeight,
            // BufferedImage.TYPE_INT_ARGB);
            // Graphics2D gfx = images[i + 1].createGraphics();
            // gfx.drawImage(scaled, 0, 0, null);
            // gfx.dispose();
        }
        super.loadSprite(images, null, true);
        return false;
    }
    
    @Override
    public void updateAnimation() {
        // TODO Auto-generated method stub
        super.updateAnimation();
    }
}

package GUOLD.render;

import java.awt.image.BufferedImage;
import java.util.List;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.data.AnimationFrame;
import net.minecraft.client.resources.data.AnimationMetadataSection;
import GUOLD.GearUtilities;

import com.google.common.collect.Lists;

public class TextureNoise extends TextureAtlasSprite {
    
    protected TextureNoise(String par1Str) {
        super(par1Str);
        
    }
    
    @Override
    public void loadSprite(BufferedImage[] p_147964_1_, AnimationMetadataSection p_147964_2_, boolean p_147964_3_) {
        
        BufferedImage finalImage = NoiseManager.instance.longVinillaAnimationImage;
        
        // From SuperClass
        this.height = finalImage.getHeight();
        this.width = finalImage.getWidth();
        int[] aint = new int[this.height * this.width];
        finalImage.getRGB(0, 0, this.width, this.height, aint, 0, this.width);
        
        int i = this.height / this.width;
        int j = this.width;
        int k = this.width;
        this.height = this.width;
        int l;
        
        List arraylist = Lists.newArrayList();
        
        for (l = 0; l < i; ++l) {
            this.framesTextureData.add(getFrameTextureData(aint, j, k, l));
            arraylist.add(new AnimationFrame(l, -1));
        }
    }
    
    public void updateAnimation() {
        // Covered in Errors
        ++this.tickCounter;
        GearUtilities.log("Ticks: " + tickCounter);
        // if (this.tickCounter >=
        // this.animationMetadata.getFrameTimeSingle(this.frameCounter)) {
        //
        // int i = this.animationMetadata.getFrameIndex(this.frameCounter);
        // int j = this.animationMetadata.getFrameCount() =cccccccccccccccccc= 0
        // ? this.framesTextureData.size() :
        // this.animationMetadata.getFrameCount();
        //
        // this.frameCounter = (this.frameCounter + 1) % j;
        // this.tickCounter = 0;
        //
        // int k = this.animationMetadata.getFrameIndex(this.frameCounter);
        //
        // if (i != k && k >= 0 && k < this.framesTextureData.size()) {
        // TextureUtil.uploadTextureSub((int[]) this.framesTextureData.get(k),
        // this.width, this.height, this.originX, this.originY, false,
        // false);
        // }
        // }
        // TextureUtil.uploadTexture(NoiseManager.instance.textureImage.getGlTextureId(),
        // NoiseManager.instance.textureImage.getTextureData(),
        // NoiseManager.instance.originalImage.getWidth(),
        // NoiseManager.instance.originalImage.getHeight());
    }
    
    private static int[] getFrameTextureData(int[] par0ArrayOfInteger, int par1, int par2, int par3) {
        int[] aint1 = new int[par1 * par2];
        System.arraycopy(par0ArrayOfInteger, par3 * aint1.length, aint1, 0, aint1.length);
        return aint1;
    }
}

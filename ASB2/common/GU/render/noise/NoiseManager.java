package GU.render.noise;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureUtil;

import org.lwjgl.opengl.GL11;

import GU.GearUtilities;
import GU.info.Reference;
import GU.info.Variables;
import UC.color.Color4i;
import UC.noise.libnoiseforjava.module.Voronoi;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class NoiseManager {
    
    public static NoiseManager instance = new NoiseManager();
    
    public Color4i ITERATED_COLOR = Color4i.WHITE.clone();
    public Color4i ITERATED_COLOR_INVERTED = Color4i.WHITE.invert();
    
    boolean moveRedColorValueDown = true, moveGreenColorValueDown = true, moveBlueColorValueDown = true;
    
    public TextureNoise blockNoiseIcon = new TextureNoise(Reference.MOD_ID.concat(":NoiseTexture"));
    public TextureNoise itemNoiseIcon = new TextureNoise(Reference.MOD_ID.concat(":NoiseTexture"));
    
    public int GL_TEXTURE_ID;
    
    public List<int[]> imageDataArray = new ArrayList<int[]>(20000);
    public static final float maxDensity = .2f, minDensity = .1f, changePerTick = .0002f;
    Voronoi noiseGen = new Voronoi();
    
    boolean moveAnimationDown = true;
    int animationPosition = 0;
    
    public NoiseManager() {
        
    }
    
    public void initImage() {
        
        GL_TEXTURE_ID = GL11.glGenTextures();
        
        long startTime = Minecraft.getSystemTime();
        GearUtilities.log("Noise Gen Has Begun");
        generateNoiseImage();
        GearUtilities.log("Noise Gen Has Ended");
        long endTime = Minecraft.getSystemTime();
        GearUtilities.log("Elapsed Time of Noise Gen: " + (endTime - startTime));
        
        TextureUtil.allocateTexture(GL_TEXTURE_ID, Variables.NOISE_TEXTURE_SIZE, Variables.NOISE_TEXTURE_SIZE);
        
        if (Variables.ANIMATE_NOISE_TEXTURE) {
            
            TextureUtil.uploadTexture(NoiseManager.instance.GL_TEXTURE_ID, imageDataArray.get(animationPosition), Variables.NOISE_TEXTURE_SIZE, Variables.NOISE_TEXTURE_SIZE);
        }
    }
    
    public static void bindImage() {
        
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, instance.GL_TEXTURE_ID);
    }
    
    public void generateNoiseImage() {
        
        imageDataArray.clear();
        
        for (float currentDensity = minDensity; currentDensity <= maxDensity; currentDensity += changePerTick) {
            
            if (Variables.ANIMATE_NOISE_TEXTURE && currentDensity != minDensity) {
                
                break;
            }
            
            double voroni2 = (noiseGen.getValue(currentDensity, currentDensity, currentDensity) * 255);
            
            int[] imageData = new int[Variables.NOISE_TEXTURE_SIZE * Variables.NOISE_TEXTURE_SIZE];
            
            for (int x = 0; x < Variables.NOISE_TEXTURE_SIZE; x++) {
                
                for (int y = 0; y < Variables.NOISE_TEXTURE_SIZE; y++) {
                    
                    double voroni = (noiseGen.getValue(x * currentDensity, y * currentDensity, currentDensity) * 255);
                    
                    // int perlin = FastNoise.noise(x * currentDensity, y *
                    // currentDensity, 7);
                    //
                    // int col = (int) ((voroni + voroni2 + perlin + perlin)
                    // / 3);
                    
                    int col = (int) (((voroni + voroni2 + (currentDensity * currentDensity)) / 4));
                    
                    // int col = (int) (((noiseGen.getValue(x *
                    // currentDensity, y * currentDensity, currentDensity) *
                    // 255) + (noiseGen.getValue(currentDensity,
                    // currentDensity, currentDensity) * 255) +
                    // FastNoise.noise(x * currentDensity, y *
                    // currentDensity, 7) + FastNoise.noise(x *
                    // currentDensity, y * currentDensity, 7)) / 4);
                    
                    // int col = (int) (((noiseGen.getValue(x *
                    // currentDensity, y * currentDensity, currentDensity) *
                    // 255) + FastNoise.noise(x * currentDensity, y *
                    // currentDensity, 5) + FastNoise.noise(x *
                    // currentDensity, y * currentDensity, 9)) / 3);
                    
                    // int col = (int) (noiseGen.getValue(x *
                    // currentDensity, y * currentDensity, currentDensity) *
                    // 255);
                    
                    // int col = FastNoise.noise((x + shift) * .1, (y +
                    // shift) *
                    // .1, 7);
                    
                    int red = col;
                    int green = col;
                    int blue = col;
                    
                    int RGB = red;
                    RGB = (RGB << 8) + green;
                    RGB = (RGB << 8) + blue;
                    RGB |= 0xFF000000;
                    imageData[x + y * Variables.NOISE_TEXTURE_SIZE] = RGB;
                }
            }
            imageDataArray.add(imageData);
        }
    }
    
    @SubscribeEvent
    public void updateNoise(RenderTickEvent event) {
        
        {
            if (!Variables.ANIMATE_NOISE_TEXTURE) {
                
                TextureUtil.uploadTexture(NoiseManager.instance.GL_TEXTURE_ID, imageDataArray.get(animationPosition), Variables.NOISE_TEXTURE_SIZE, Variables.NOISE_TEXTURE_SIZE);
                
                if (Minecraft.getSystemTime() % 20 == 0) {
                    
                    if (moveAnimationDown) {
                        
                        if (animationPosition >= imageDataArray.size() - 1) {
                            
                            moveAnimationDown = false;
                            animationPosition = imageDataArray.size() - 1;
                        } else /* if (Minecraft.getSystemTime() % 20 == 0) */{
                            
                            animationPosition++;
                        }
                        
                    } else {
                        
                        if (animationPosition <= 0) {
                            moveAnimationDown = true;
                            animationPosition = 0;
                        } else /* if (Minecraft.getSystemTime() % 20 == 0) */{
                            
                            animationPosition--;
                        }
                    }
                }
            }
        }
        
        {
            if (Minecraft.getSystemTime() % 20 == 0) {
                
                final float redModificationAmount = 1f, greenModificationAmount = 2f, blueModificationAmount = 3f;
                
                int red = ITERATED_COLOR.getRed(), green = ITERATED_COLOR.getGreen(), blue = ITERATED_COLOR.getBlue();
                
                if (moveRedColorValueDown) {
                    
                    if (red > 0) {
                        
                        red -= redModificationAmount;
                    } else if (red == 0) {
                        
                        moveRedColorValueDown = false;
                    }
                } else {
                    
                    if (red < 255) {
                        
                        red += redModificationAmount;
                    } else if (red == 255) {
                        
                        moveRedColorValueDown = true;
                    }
                }
                if (moveGreenColorValueDown) {
                    
                    if (green > 0) {
                        
                        green -= greenModificationAmount;
                    } else if (green == 0) {
                        
                        moveGreenColorValueDown = false;
                    }
                } else {
                    
                    if (green < 255) {
                        
                        green += greenModificationAmount;
                    } else if (green == 255) {
                        
                        moveGreenColorValueDown = true;
                    }
                }
                if (moveBlueColorValueDown) {
                    
                    if (blue > 0) {
                        
                        blue -= blueModificationAmount;
                    } else if (blue == 0) {
                        
                        moveBlueColorValueDown = false;
                    }
                } else {
                    
                    if (blue < 255) {
                        
                        blue += blueModificationAmount;
                    } else if (blue == 255) {
                        
                        moveBlueColorValueDown = true;
                    }
                }
                if (red > 255) {
                    
                    red = 255;
                } else if (red < 0) {
                    
                    red = 0;
                }
                if (green > 255) {
                    
                    green = 255;
                } else if (green < 0) {
                    
                    green = 0;
                }
                if (blue > 255) {
                    
                    blue = 255;
                } else if (blue < 0) {
                    
                    blue = 0;
                }
                ITERATED_COLOR.setRed(red).setGreen(green).setBlue(blue);
                ITERATED_COLOR_INVERTED.setRed(255 - red).setGreen(255 - green).setBlue(255 - blue);
                return;
            }
        }
    }
}

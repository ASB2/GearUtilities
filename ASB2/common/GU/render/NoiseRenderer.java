package GU.render;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.EnumSet;
import java.util.Random;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class NoiseRenderer implements ITickHandler {
    
    PerlinNoise noise = new PerlinNoise();
    Random rand = new Random();
    
    public NoiseRenderer() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {
        
        BufferedImage image = BufferedImageTest.getImage();
        
        if (image != null) {
            
            if (type.equals(this.ticks())) {
                
                Graphics2D graphics = (Graphics2D) image.createGraphics();
                
                int lastColor = 0, boxSize = 2;
                
                for (int x = 0; x < image.getWidth(); x+=boxSize) {
                    
                    for (int y = 0; y < image.getHeight(); y+=boxSize) {
                        
                        if (lastColor == 0) {
                            
                            Color color = Color.RED;
                            
                            graphics.setColor(color.darker());
                            graphics.fillRect(x, y, boxSize, boxSize);
                            lastColor = color.darker().getRGB();
                        } else {
                            
                            int alpha = (lastColor) & 0xFF;
                            int red = (lastColor) & 0xFF;
                            int green = (lastColor >> 8) & 0xFF;
                            int blue = lastColor & 0xFF;
                            int pixelColor = (alpha << 24) + (red << 16) + (green << 8) + blue;
                            
                            Color color = new Color(pixelColor);
                            
                            graphics.setColor(color.darker());
                            graphics.fillRect(x, y, boxSize, boxSize);
                            lastColor = color.darker().getRed();
                        }
                    }
                }
                
            }
        }
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

package GU.render;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.EnumSet;
import java.util.Random;

import net.minecraft.client.Minecraft;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class NoiseRenderer implements ITickHandler {
    
    Random rand = new Random();
    
    public NoiseRenderer() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {
        
        if (type.equals(this.ticks()) && Minecraft.getMinecraft().thePlayer != null) {
            
            Graphics2D graphics = (Graphics2D) BufferedImageTest.getImage().getGraphics();
            
            // if (graphics.getColor().equals(Color.WHITE)) {
            // graphics.setColor(Color.BLUE);
            // }
            graphics.setColor(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
            graphics.fillRect(rand.nextInt(BufferedImageTest.getImage().getWidth()), rand.nextInt(BufferedImageTest.getImage().getHeight()), rand.nextInt(BufferedImageTest.getImage().getWidth()), rand.nextInt(BufferedImageTest.getImage().getHeight()));
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

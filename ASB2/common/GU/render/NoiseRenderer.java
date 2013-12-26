package GU.render;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.EnumSet;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class NoiseRenderer implements ITickHandler {
    
    public NoiseRenderer() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {
        
        if (type.equals(this.ticks())) {
            
            Graphics2D graphics = (Graphics2D) BufferedImageTest.image.getGraphics();
            
            // if (graphics.getColor().equals(Color.WHITE)) {
            // graphics.setColor(Color.BLUE);
            // }
            graphics.setColor(Color.GRAY);
            graphics.clearRect(0, 0, BufferedImageTest.image.getWidth(), BufferedImageTest.image.getHeight());            
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

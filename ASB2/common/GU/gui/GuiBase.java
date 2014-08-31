package GU.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import GU.blocks.containers.TileBase;

public abstract class GuiBase extends GuiContainer {
    
    protected TileBase tile;
    
    public GuiBase(ContainerBase container, TileBase tile) {
        super(container);
        
        this.tile = tile;
    }
}

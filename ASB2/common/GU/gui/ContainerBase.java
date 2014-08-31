package GU.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import GU.blocks.containers.TileBase;

public class ContainerBase extends Container {
    
    TileBase tile;
    
    public ContainerBase(TileBase tile) {
        
        this.tile = tile;
    }
    
    @Override
    public boolean canInteractWith(EntityPlayer p_75145_1_) {
        
        return true;
    }
}

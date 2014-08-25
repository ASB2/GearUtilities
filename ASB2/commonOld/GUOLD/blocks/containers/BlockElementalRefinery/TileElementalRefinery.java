package GUOLD.blocks.containers.BlockElementalRefinery;

import GUOLD.blocks.containers.TileFluidBase;

public class TileElementalRefinery extends TileFluidBase {
    
    public TileElementalRefinery() {
        
        this.renderDoubles = new double[2];
    }
    
    /*
     * renderDoubles[1] == 0 Moving Down renderDoubles[1] == 1 Moving Up
     */
    
    @Override
    public void updateEntity() {
        
        if (renderDoubles[1] == 0) {
            
            renderDoubles[0] -= .04;
            
            if (renderDoubles[0] <= -.8) {
                
                renderDoubles[1] = 1;
            }
        } else {
            
            renderDoubles[0] += .04;
            
            if (renderDoubles[0] >= .8) {
                
                renderDoubles[1] = 0;
            }
        }
    }
}

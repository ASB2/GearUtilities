package GU.blocks.containers.BlockElementalRefinery;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import GU.blocks.containers.ContainerBase;

public class BlockElementalRefinery extends ContainerBase {
    
    public BlockElementalRefinery(int id, Material material) {
        super(id, material);
        
        this.registerTile(TileElementalRefinery.class);
        this.useStandardRendering = false;
        this.setLightOpacity(0);
    }
    
    @Override
    public TileEntity createNewTileEntity(World world) {
        
        return new TileElementalRefinery();
    }
}

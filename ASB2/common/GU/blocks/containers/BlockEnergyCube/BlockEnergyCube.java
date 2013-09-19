package GU.blocks.containers.BlockEnergyCube;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import GU.blocks.containers.*;

public class BlockEnergyCube extends ContainerBase {

    public BlockEnergyCube(int id, Material material) {
        super(id, material);
    
        this.registerTile(TileEnergyCube.class);
        this.useStandardRendering = false;
    }

    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TileEnergyCube();
    }
}

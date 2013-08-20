package GU.blocks.containers.BlockVortexControler;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import GU.blocks.containers.ContainerBase;

public class BlockVortexControler extends ContainerBase {

    public BlockVortexControler(int id, Material material) {
        super(id, material);
        this.registerTile(TileVortexControler.class);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        // TODO Auto-generated method stub
        return new TileVortexControler();
    }
}

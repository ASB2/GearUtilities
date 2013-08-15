package GU.blocks.containers.BlockCreationTable;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import GU.blocks.containers.ContainerBase;

public class BlockCreationTable extends ContainerBase {

    public BlockCreationTable(int id, Material material) {
        super(id, material);

        setLightValue(1.0F);
        this.registerTile(TileCreationTable.class);
        useStandardRendering = false;
    }

    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TileCreationTable();
    }
}

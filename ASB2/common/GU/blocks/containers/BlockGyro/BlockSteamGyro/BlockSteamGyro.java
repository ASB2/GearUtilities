package GU.blocks.containers.BlockGyro.BlockSteamGyro;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import GU.blocks.containers.ContainerBase;

public class BlockSteamGyro extends ContainerBase {

    public BlockSteamGyro(int id, Material material) {
        super(id, material);

        this.registerTile(TileSteamGyro.class);
        this.useStandardRendering = false;
    }

    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TileSteamGyro();
    }
}

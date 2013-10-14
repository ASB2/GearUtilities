package GU.blocks.containers.BlockUniversalConduit;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ASB2.vector.Vector3;
import GU.api.network.UniversalConduitNetwork;
import GU.blocks.containers.ContainerBase;

public class BlockUniversalConduit extends ContainerBase {

    public BlockUniversalConduit(int id, Material material) {
        super(id, material);
        
        this.registerTile(TileUniversalConduit.class);
        this.useStandardRendering = false;
    }

    public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
        

        TileEntity tile = world.getBlockTileEntity(x,y,z);
        if(tile != null && tile instanceof TileUniversalConduit) {

            TileUniversalConduit tileCasted = (TileUniversalConduit) tile;

            if(tileCasted.getNetwork() != null) {

                ((UniversalConduitNetwork)tileCasted.getNetwork()).onNetworkConductorBroken(world, new Vector3(x, y, z));
            }
        }
        super.breakBlock(world, x, y, z, par5, par6);
    }
    
    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TileUniversalConduit();
    }
}

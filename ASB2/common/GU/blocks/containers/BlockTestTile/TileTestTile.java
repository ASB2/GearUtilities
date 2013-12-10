package GU.blocks.containers.BlockTestTile;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import ASB2.utils.IBlockCycle;
import ASB2.utils.UtilBlock;
import ASB2.vector.Vector3;
import GU.api.cluster.ITileFinderSource;
import GU.api.wait.Wait;
import GU.blocks.containers.TileBase;
import GU.entity.EntityTileFinder.EntityTileFinder;

public class TileTestTile extends TileBase implements IBlockCycle, ITileFinderSource {

    public TileTestTile() {

        waitTimer = new Wait(20, this, 0);
    }

    @Override
    public void updateEntity() {

        waitTimer.update();
    }

    @Override
    public void trigger(int id) {

        UtilBlock.cycle3DBlock(null, worldObj, xCoord, yCoord + 10, zCoord, ForgeDirection.DOWN, 10, 10, this, 0);
    }

    @Override
    public boolean execute(EntityLivingBase player, World world, int x, int y, int z, ForgeDirection side, int id) {

        if(!world.isRemote) {

            if(world.getBlockId(x, y, z) == Block.sand.blockID) {
                
                Vector3 vector = new Vector3(this);

                EntityTileFinder finder = new EntityTileFinder(worldObj, vector.clone().add(ForgeDirection.DOWN), new Vector3(x, y, z), 20);

                world.spawnEntityInWorld(finder);
            }
        }
        return false;
    }

    @Override
    public void onCollision(World world, Vector3 position, EntityTileFinder tile, int id) {

        position.setBlock(world, 1);
        tile.setDead();
    }
}

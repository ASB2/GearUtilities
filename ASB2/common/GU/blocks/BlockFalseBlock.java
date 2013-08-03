package GU.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import GU.utils.UtilPlayers;

public class BlockFalseBlock extends BlockBase {

    public BlockFalseBlock(int par1, Material par3Material) {
        super(par1, par3Material);
        useStandardRendering = false;
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {

        if(!world.isBlockIndirectlyGettingPowered(x,y,z))
        return null;
        
        return super.getCollisionBoundingBoxFromPool(world, x, y, z);
    }

    public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity entity) {

        if(entity instanceof EntityPlayer) {

            if((UtilPlayers.isSpecialPlayer(((EntityPlayer)entity).username))) {

                entity.extinguish();
            }
        }
    }
}
package GU.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import GU.utils.UtilPlayers;

public class BlockFreezingFlower extends FlowerBase {

    public BlockFreezingFlower(int par1, Material par3Material) {
        super(par1, par3Material);
    }
    
    public void onEntityCollidedWithBlock(World par1World, int x, int y, int z, Entity entity) {
       
        if(entity instanceof EntityPlayer) {

            if(!(UtilPlayers.isSpecialPlayer(((EntityPlayer)entity).username))) {

                entity.setInWeb();  
            }
        }
        else {
            entity.setInWeb();
        }
    }
}
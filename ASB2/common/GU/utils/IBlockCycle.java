package GU.utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public interface IBlockCycle {

    public boolean execute(EntityPlayer player, World world, int x, int y,
            int z, ForgeDirection side, int id);
}

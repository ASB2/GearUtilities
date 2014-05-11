package GUOLD.api.multiblock;

import net.minecraft.world.IBlockAccess;

public interface ISpecialMultiBlockOpaque {

    boolean isTrueOpaqueCube(IBlockAccess world, int x, int y, int z);
}

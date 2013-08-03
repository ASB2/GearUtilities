package GU.worldGen;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import GU.BlockRegistry;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenBlockBurningFlower implements IWorldGenerator {
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        switch (world.provider.dimensionId)
        {
            case -1: generateNether(world, random, chunkX*16, chunkZ*16);
            case 0: generateSurface(world, random, chunkX*16, chunkZ*16);
        }
    }

    private void generateSurface(World world, Random random, int blockX, int blockZ)
    {
        for(int j=0;j<1;j++){
            int XCoordinate=blockX + random.nextInt(16);
            int ZCoordinate=blockZ + random.nextInt(16);
            int YCoordinate=random.nextInt(256);
            (new WorldGenFlowers(BlockRegistry.BlockBurningFlower.blockID)).generate(world, random, XCoordinate,YCoordinate,ZCoordinate);
        }
    }

    private void generateNether(World world, Random random, int blockX, int blockZ)
    {
    }
}

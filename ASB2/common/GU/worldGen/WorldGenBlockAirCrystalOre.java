package GU.worldGen;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import GU.BlockRegistry;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenBlockAirCrystalOre implements IWorldGenerator {
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        switch (world.provider.dimensionId)
        {
            case -1: generateNether(world, random, chunkX*16, chunkZ*16);
            case 0: generateSurface(world, random, chunkX*16, chunkZ*16);
        }
    }

    private void generateSurface(World world, Random random, int blockX, int blockZ){
        // Adds a Block
        int Xcoord = blockX + random.nextInt(16);
        int Ycoord = random.nextInt(100);
        int Zcoord = blockZ + random.nextInt(16);

        (new WorldGenMinable(BlockRegistry.BlockAirCrystalOre.blockID, 20)).generate(world, random, Xcoord, Ycoord, Zcoord);
    }

    private void generateNether(World world, Random random, int blockX, int blockZ)
    {
    }
}

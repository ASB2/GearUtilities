package GU.worldGen;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public abstract class WorldGenBase implements IWorldGenerator {
    
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        
        if (!world.provider.isHellWorld) {
            
            for (int index = 0; index < generationsPerChunk(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider); index++) {
                
                int x = (chunkX * 16) + random.nextInt(16);
                int y = random.nextInt(1000);
                int z = (chunkZ * 16) + random.nextInt(16);
                
                generate(world, random, x, y, z);
            }
        }
    }
    
    public abstract void generate(World world, Random random, int x, int y, int z);
    
    public abstract int generationsPerChunk(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider);
}

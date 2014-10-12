package GU.dimention;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;

public class ElectisVoidWorldProvider extends WorldProviderSurface {
    /**
     * creates a new world chunk manager for WorldProvider
     */
    @Override
    public void registerWorldChunkManager() {
        this.worldChunkMgr = new ElectisVoidChunkManager(BiomeGenBase.sky, 1.0F, 0.0F);
        this.isHellWorld = true;
        this.hasNoSky = true;
        this.dimensionId = 373;
    }
    
    @Override
    public IChunkProvider createChunkGenerator() {
        return new ElectisVoidChunkProvider(this.worldObj);
    }
    
    @Override
    public boolean canRespawnHere() {
        return false;
    }
    
    @Override
    public boolean doesXZShowFog(int par1, int par2) {
        return false;
    }
    
    @Override
    public int getRespawnDimension(EntityPlayerMP player) {
        
        return 0;
    }
}
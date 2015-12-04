package GU.dimention;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;

public class ElectisVoidChunkProvider implements IChunkProvider {
    
    /** Is the world that the nether is getting generated. */
    private World worldObj;
    
    public ElectisVoidChunkProvider(World par1World) {
        this.worldObj = par1World;
    }
    
    /**
     * loads or generates the chunk at the chunk location specified
     */
    @Override
    public Chunk loadChunk(int par1, int par2) {
        return this.provideChunk(par1, par2);
    }
    
    /**
     * Will return back a chunk, if it doesn't exist and its not a MP client it
     * will generates all the blocks for the specified chunk from the map seed
     * and chunk seed
     */
    @Override
    public Chunk provideChunk(int chunkX, int chunkZ) {
        
        Block[] lowerArray = new Block[32768];
        Chunk chunk = new ElectisVoidChunk(this.worldObj, lowerArray, chunkX, chunkZ);
        chunk.resetRelightChecks();
        return chunk;
    }
    
    /**
     * Checks to see if a chunk exists at x, y
     */
    @Override
    public boolean chunkExists(int par1, int par2) {
        return true;
    }
    
    /**
     * Populates chunk with ores etc etc
     */
    @Override
    public void populate(IChunkProvider par1IChunkProvider, int par2, int par3) {
        
    }
    
    /**
     * Two modes of operation: if passed true, save all Chunks in one go. If
     * passed false, save up to two chunks. Return true if all chunks have been
     * saved.
     */
    @Override
    public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate) {
        return true;
    }
    
    /**
     * Unloads chunks that are marked to be unloaded. This is not guaranteed to
     * unload every such chunk.
     */
    @Override
    public boolean unloadQueuedChunks() {
        return false;
    }
    
    /**
     * Returns if the IChunkProvider supports saving.
     */
    @Override
    public boolean canSave() {
        return true;
    }
    
    /**
     * Converts the instance data to a readable string.
     */
    @Override
    public String makeString() {
        return "FUCKSHAD";
    }
    
    /**
     * Returns the location of the closest structure of the specified type. If
     * not found returns null.
     */
    @Override
    public ChunkPosition func_147416_a(World par1World, String par2Str, int par3, int par4, int par5) {
        return null;
    }
    
    @Override
    public int getLoadedChunkCount() {
        return 0;
    }
    
    @Override
    public void recreateStructures(int par1, int par2) {
        
    }
    
    @Override
    public void saveExtraData() {
        // NYI
    }
    
    @Override
    public List getPossibleCreatures(EnumCreatureType p_73155_1_, int p_73155_2_, int p_73155_3_, int p_73155_4_) {
        
        return new ArrayList();
    }
}
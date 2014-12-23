package GU;

import java.util.List;

import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.LoadingCallback;
import net.minecraftforge.common.ForgeChunkManager.Ticket;

public class GUChunkLoadingCallback implements LoadingCallback {
    
    @Override
    public void ticketsLoaded(List<Ticket> tickets, World world) {
        
        for (Ticket ticket : tickets) {
            
            for (ChunkCoordIntPair chunk : ticket.getChunkList()) {
                
                ForgeChunkManager.forceChunk(ticket, chunk);
            }
        }
    }
}

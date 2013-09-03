package GU.worldGen;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;

import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.ChunkDataEvent;
import GU.info.Reference;
import GU.info.Variables;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.registry.GameRegistry;

public class RetroGenManager implements ITickHandler {

    ArrayList<Chunk> CACHEED_CHUNKS = new ArrayList<Chunk>();

    public RetroGenManager() {

    }

    public static void init() {

        MinecraftForge.EVENT_BUS.register(new RetroGenManager());
    }

    @EventHandler
    public void chunkDataLoad (ChunkDataEvent.Load event){

        if(Variables.DO_RETROGEN) {

            if(!event.getData().hasKey(Reference.MODDID)) {

                CACHEED_CHUNKS.add(event.getChunk());
            }
        }
    }

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {

        Iterator<Chunk> chunksIt = CACHEED_CHUNKS.iterator();

        while(chunksIt.hasNext()) {
            
            Chunk chunk = chunksIt.next();
            
            GameRegistry.generateWorld(chunk.xPosition, chunk.zPosition, chunk.worldObj, chunk.worldObj.getChunkProvider(), chunk.worldObj.getChunkProvider());
        }
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData) {
        // TODO Auto-generated method stub

    }

    @Override
    public EnumSet<TickType> ticks() {

        return EnumSet.of(TickType.SERVER);
    }

    @Override
    public String getLabel() {

        return "ASB2 Retrogen";
    }
}

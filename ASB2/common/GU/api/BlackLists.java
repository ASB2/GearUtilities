package GU.api;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;

public class BlackLists {

    private static BlackLists instance = new BlackLists();

    private Map<Block, Integer> BlockBreakerBlackList = new HashMap<Block, Integer>();

    public BlackLists() {
        
        this.addBlockBreakerBlackList(Block.bedrock, 0);
    }
    
    public void addBlockBreakerBlackList(Block block, int meta) {

        BlockBreakerBlackList.put(block, meta);
    }

    public boolean isOnBlockBreakerBlackList(Block block, int meta) {

        if(block == null) return false;
     
        return BlockBreakerBlackList.containsKey(block);
    }

    public static BlackLists getInstance() {

        return instance;
    }
}

package GU.proxy;

import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import GU.BlockRegistry;
import GU.ItemRegistry;
import GU.blocks.BlockBase;
import GU.info.Models;
import GU.items.ItemBase;
import GU.render.BlockSimpleRenderer;
import GU.render.noise.NoiseManager;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
    
    @Override
    public void register() {
        
        Models.initModels();
        NoiseManager.instance.initImage();
        
        RenderingRegistry.registerBlockHandler(BlockSimpleRenderer.instance);
        
        for (Entry<String, Item> entry : ItemRegistry.customItemMap.entrySet()) {
            
            if (entry.getValue() instanceof ItemBase) {
                
                ((ItemBase) entry.getValue()).postInitRender();
            }
        }
        
        for (Entry<String, Block> entry : BlockRegistry.customBlockMap.entrySet()) {
            
            if (entry.getValue() instanceof BlockBase) {
                
                ((BlockBase) entry.getValue()).postInitRender();
            }
        }
    }
}

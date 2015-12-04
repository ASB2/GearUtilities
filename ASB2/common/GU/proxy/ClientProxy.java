package GU.proxy;

import java.util.Map.Entry;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import GU.BlockRegistry;
import GU.ItemRegistry;
import GU.blocks.BlockBase;
import GU.info.Models;
import GU.items.ItemBase;
import GU.render.BlockSimpleRenderer;
import GU.render.noise.NoiseManager;

public class ClientProxy extends CommonProxy {
    
    @Override
    public void register() {
        
        Models.initModels();
        NoiseManager.instance.initImage();
        FMLCommonHandler.instance().bus().register(NoiseManager.instance);
        
    }
    
    public void registerRendereres() {
        
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
    
    public World getClientWorld() {
        
        return Minecraft.getMinecraft().theWorld;
    }
}

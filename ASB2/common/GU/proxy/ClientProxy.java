package GU.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import GU.BlockRegistry;
import GU.EntityRegistry;
import GU.ItemRegistry;
import GU.blocks.containers.BlockAdvancedPotionBrewery.GuiAdvancedPotionBrewery;
import GU.blocks.containers.BlockMultiPanel.GuiMultiPanel;
import GU.blocks.containers.BlockMultiPanel.MultiPanelRenderer;
import GU.blocks.containers.BlockMultiPanel.TileMultiPanel;
import GU.blocks.containers.BlockQuantaSender.QuantSenderRenderer;
import GU.blocks.containers.BlockQuantaSender.TileQuantaSender;
import GU.info.Gui;
import GU.info.Models;
import GU.items.ItemFocusGem.FocusGemRenderer;
import GU.items.ItemStorageCrystal.StorageCrystalRenderer;
import GU.render.BlockSimpleRenderer;
import GU.sounds.SoundHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
    
    @Override
    public void register() {
        
        Models.initModels();
        SoundHandler.init();
        EntityRegistry.initClient();
        
        MinecraftForgeClient.registerItemRenderer(ItemRegistry.ItemStorageCrystal.itemID, new StorageCrystalRenderer());
        RenderingRegistry.registerBlockHandler(new BlockSimpleRenderer());
        
        ClientRegistry.bindTileEntitySpecialRenderer(TileMultiPanel.class, new MultiPanelRenderer());
        MinecraftForgeClient.registerItemRenderer(BlockRegistry.BlockMultiPanel.blockID, new MultiPanelRenderer());
        
        ClientRegistry.bindTileEntitySpecialRenderer(TileQuantaSender.class, new QuantSenderRenderer());
        MinecraftForgeClient.registerItemRenderer(BlockRegistry.BlockQuantaSender.blockID, new QuantSenderRenderer());
        
        MinecraftForgeClient.registerItemRenderer(ItemRegistry.ItemFocusGem.itemID, new FocusGemRenderer());        
    }
    
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        
        TileEntity tile = world.getBlockTileEntity(x, y, z);
        
        if (tile != null) {
            
            switch (ID) {
            
                case Gui.ADVANCED_POTION_BREWERY:
                    return new GuiAdvancedPotionBrewery(player.inventory, tile);
                    
                case Gui.MULTI_PANEL:
                    return new GuiMultiPanel(player.inventory, tile);
            }
        }
        return null;
    }
}

package GU.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import GU.BlockRegistry;
import GU.EntityRegistry;
import GU.ItemRegistry;
import GU.blocks.containers.BlockAdvancedPotionBrewery.GuiAdvancedPotionBrewery;
import GU.blocks.containers.BlockCentrifuge.CentrifugeRenderer;
import GU.blocks.containers.BlockCentrifuge.TileCentrifuge;
import GU.blocks.containers.BlockElementalRefinery.ElementalRefineryRenderer;
import GU.blocks.containers.BlockElementalRefinery.TileElementalRefinery;
import GU.blocks.containers.BlockEssenceDiffuser.EssenceDiffuserRenderer;
import GU.blocks.containers.BlockEssenceDiffuser.TileEssenceDiffuser;
import GU.blocks.containers.BlockMultiPanel.GuiMultiPanel;
import GU.blocks.containers.BlockMultiPanel.MultiPanelRenderer;
import GU.blocks.containers.BlockMultiPanel.TileMultiPanel;
import GU.blocks.containers.BlockQuantaSender.QuantSenderRenderer;
import GU.blocks.containers.BlockQuantaSender.TileQuantaSender;
import GU.info.Gui;
import GU.info.Models;
import GU.items.ItemStorageCrystal.StorageCrystalRenderer;
import GU.render.BlockSimpleRenderer;
import GU.render.NoiseRenderer;
import GU.sounds.SoundHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ClientProxy extends CommonProxy {
    
    @Override
    public void register() {
        
        Models.initModels();
        SoundHandler.init();
        EntityRegistry.initClient();
        
        MinecraftForgeClient.registerItemRenderer(ItemRegistry.ItemStorageCrystal.itemID, new StorageCrystalRenderer());
        RenderingRegistry.registerBlockHandler(new BlockSimpleRenderer());
        
        ClientRegistry.bindTileEntitySpecialRenderer(TileMultiPanel.class, MultiPanelRenderer.instance);
        MinecraftForgeClient.registerItemRenderer(BlockRegistry.BlockMultiPanel.blockID, MultiPanelRenderer.instance);
        
        ClientRegistry.bindTileEntitySpecialRenderer(TileQuantaSender.class, QuantSenderRenderer.instance);
        MinecraftForgeClient.registerItemRenderer(BlockRegistry.BlockQuantaSender.blockID, QuantSenderRenderer.instance);
        
        ClientRegistry.bindTileEntitySpecialRenderer(TileEssenceDiffuser.class, EssenceDiffuserRenderer.instance);
        MinecraftForgeClient.registerItemRenderer(BlockRegistry.BlockEssenceDiffuser.blockID, EssenceDiffuserRenderer.instance);
        
        ClientRegistry.bindTileEntitySpecialRenderer(TileCentrifuge.class, CentrifugeRenderer.instance);
        MinecraftForgeClient.registerItemRenderer(BlockRegistry.BlockCentrifuge.blockID, CentrifugeRenderer.instance);
        
        ClientRegistry.bindTileEntitySpecialRenderer(TileElementalRefinery.class, ElementalRefineryRenderer.instance);
        MinecraftForgeClient.registerItemRenderer(BlockRegistry.BlockElementalRefinery.blockID, ElementalRefineryRenderer.instance);
        
        TickRegistry.registerTickHandler(new NoiseRenderer(), Side.CLIENT);
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

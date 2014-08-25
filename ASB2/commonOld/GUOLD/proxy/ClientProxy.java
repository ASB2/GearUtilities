package GUOLD.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import GUOLD.BlockRegistry;
import GUOLD.EntityRegistry;
import GUOLD.ItemRegistry;
import GUOLD.blocks.containers.BlockCentrifuge.CentrifugeRenderer;
import GUOLD.blocks.containers.BlockCentrifuge.TileCentrifuge;
import GUOLD.blocks.containers.BlockElementalRefinery.ElementalRefineryRenderer;
import GUOLD.blocks.containers.BlockElementalRefinery.TileElementalRefinery;
import GUOLD.blocks.containers.BlockEssenceDiffuser.EssenceDiffuserRenderer;
import GUOLD.blocks.containers.BlockEssenceDiffuser.TileEssenceDiffuser;
import GUOLD.blocks.containers.BlockFlameAltar.FlameAltarRenderer;
import GUOLD.blocks.containers.BlockFlameAltar.TileFlameAltar;
import GUOLD.blocks.containers.BlockFlameConduit.FlameConduitRenderer;
import GUOLD.blocks.containers.BlockFlameConduit.TileFlameConduit;
import GUOLD.blocks.containers.BlockGlassPipe.GlassPipeRenderer;
import GUOLD.blocks.containers.BlockGlassPipe.TileGlassPipe;
import GUOLD.blocks.containers.BlockMultiPanel.GuiMultiPanel;
import GUOLD.blocks.containers.BlockMultiPanel.MultiPanelRenderer;
import GUOLD.blocks.containers.BlockMultiPanel.TileMultiPanel;
import GUOLD.blocks.containers.BlockQuantaSender.QuantSenderRenderer;
import GUOLD.blocks.containers.BlockQuantaSender.TileQuantaSender;
import GUOLD.blocks.containers.BlockStructureCube.MultiCoreRenderer;
import GUOLD.info.Gui;
import GUOLD.info.Models;
import GUOLD.items.ItemFlameFocus.FlameFocusRenderer;
import GUOLD.items.ItemFlameShard.FlameShardRenderer;
import GUOLD.items.ItemStorageCrystal.StorageCrystalRenderer;
import GUOLD.render.BlockSimpleRenderer;
import GUOLD.render.NoiseRenderer;
import GUOLD.sounds.SoundHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;

public class ClientProxy extends CommonProxy {
    
    @Override
    public void register() {
        
        Models.initModels();
        SoundHandler.init();
        EntityRegistry.initClient();
        
        MinecraftForgeClient.registerItemRenderer(ItemRegistry.ItemStorageCrystal, new StorageCrystalRenderer());
        MinecraftForgeClient.registerItemRenderer(ItemRegistry.ItemFlameFocus, FlameFocusRenderer.instance);
        MinecraftForgeClient.registerItemRenderer(ItemRegistry.ItemFlameShard, FlameShardRenderer.instance);
        RenderingRegistry.registerBlockHandler(new BlockSimpleRenderer());
        
        ClientRegistry.bindTileEntitySpecialRenderer(TileMultiPanel.class, MultiPanelRenderer.instance);
        MinecraftForgeClient.registerItemRenderer(new ItemStack(BlockRegistry.BlockMultiPanel).getItem(), MultiPanelRenderer.instance);
        
        ClientRegistry.bindTileEntitySpecialRenderer(TileQuantaSender.class, QuantSenderRenderer.instance);
        MinecraftForgeClient.registerItemRenderer(new ItemStack(BlockRegistry.BlockQuantaSender).getItem(), QuantSenderRenderer.instance);
        
        ClientRegistry.bindTileEntitySpecialRenderer(TileEssenceDiffuser.class, EssenceDiffuserRenderer.instance);
        MinecraftForgeClient.registerItemRenderer(new ItemStack(BlockRegistry.BlockEssenceDiffuser).getItem(), EssenceDiffuserRenderer.instance);
        
        ClientRegistry.bindTileEntitySpecialRenderer(TileCentrifuge.class, CentrifugeRenderer.instance);
        MinecraftForgeClient.registerItemRenderer(new ItemStack(BlockRegistry.BlockCentrifuge).getItem(), CentrifugeRenderer.instance);
        
        ClientRegistry.bindTileEntitySpecialRenderer(TileElementalRefinery.class, ElementalRefineryRenderer.instance);
        MinecraftForgeClient.registerItemRenderer(new ItemStack(BlockRegistry.BlockElementalRefinery).getItem(), ElementalRefineryRenderer.instance);
        
        ClientRegistry.bindTileEntitySpecialRenderer(TileFlameAltar.class, FlameAltarRenderer.instance);
        MinecraftForgeClient.registerItemRenderer(new ItemStack(BlockRegistry.BlockFlameAltar).getItem(), FlameAltarRenderer.instance);
        
        ClientRegistry.bindTileEntitySpecialRenderer(TileFlameConduit.class, FlameConduitRenderer.instance);
        MinecraftForgeClient.registerItemRenderer(new ItemStack(BlockRegistry.BlockFlameConduit).getItem(), FlameConduitRenderer.instance);
        
        ClientRegistry.bindTileEntitySpecialRenderer(TileGlassPipe.class, new GlassPipeRenderer());
        MinecraftForgeClient.registerItemRenderer(new ItemStack(BlockRegistry.BlockGlassPipe).getItem(), new GlassPipeRenderer());
        
//        TickRegistry.registerTickHandler(new NoiseRenderer(), Side.CLIENT);
    }
    
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        
        TileEntity tile = world.getTileEntity(x, y, z);
        
        if (tile != null) {
            
            switch (ID) {
            
//                case Gui.ADVANCED_POTION_BREWERY:
//                    return new GuiAdvancedPotionBrewery(player.inventory, tile);
                    
                case Gui.MULTI_PANEL:
                    return new GuiMultiPanel(player.inventory, tile);
            }
        }
        return null;
    }
}

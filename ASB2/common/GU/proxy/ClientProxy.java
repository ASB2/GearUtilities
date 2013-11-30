package GU.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import GU.BlockRegistry;
import GU.EntityRegistry;
import GU.ItemRegistry;
import GU.blocks.BlockTestRender.TestRenderRenderer;
import GU.blocks.containers.BlockAdvancedPotionBrewery.GuiAdvancedPotionBrewery;
import GU.blocks.containers.BlockBlockBreaker.BlockBreakerRenderer;
import GU.blocks.containers.BlockBlockBreaker.GuiBlockBreaker;
import GU.blocks.containers.BlockBlockBreaker.TileBlockBreaker;
import GU.blocks.containers.BlockCamoBlock.GuiCamoBlock;
import GU.blocks.containers.BlockConduitInterface.ConduitInterfaceRenderer;
import GU.blocks.containers.BlockConduitInterface.TileConduitInterface;
import GU.blocks.containers.BlockConnectableTank.ConnectableTankRenderer;
import GU.blocks.containers.BlockCreationTable.CreationTableRenderer;
import GU.blocks.containers.BlockCreationTable.GuiCreationTable;
import GU.blocks.containers.BlockCreationTable.TileCreationTable;
import GU.blocks.containers.BlockDissolver.GuiDissolver;
import GU.blocks.containers.BlockEnergyCube.EnergyCubeRenderer;
import GU.blocks.containers.BlockEnergyCube.TileEnergyCube;
import GU.blocks.containers.BlockGlassPipe.GlassPipeRenderer;
import GU.blocks.containers.BlockGlassPipe.TileGlassPipe;
import GU.blocks.containers.BlockGyro.BlockSolarGyro.SolarGyroRenderer;
import GU.blocks.containers.BlockGyro.BlockSolarGyro.TileSolarGyro;
import GU.blocks.containers.BlockGyro.BlockSteamGyro.SteamGyroRenderer;
import GU.blocks.containers.BlockGyro.BlockSteamGyro.TileSteamGyro;
import GU.blocks.containers.BlockMasher.GuiMasher;
import GU.blocks.containers.BlockSender.GuiSender;
import GU.blocks.containers.BlockSender.SenderRenderer;
import GU.blocks.containers.BlockSender.TileSender;
import GU.blocks.containers.BlockSolarFocus.GuiSolarFocus;
import GU.blocks.containers.BlockSolarFocus.SolarFocusRenderer;
import GU.blocks.containers.BlockSolarFocus.TileSolarFocus;
import GU.blocks.containers.BlockUniversalConduit.TileUniversalConduit;
import GU.blocks.containers.BlockUniversalConduit.UniversalConduitRenderer;
import GU.info.Gui;
import GU.info.Models;
import GU.items.ItemBloodStone.BloodStoneRenderer;
import GU.items.ItemHandheldTank.HandheldTankRenderer;
import GU.items.ItemStorageCrystal.StorageCrystalRenderer;
import GU.models.BlockSimpleRenderer;
import GU.sounds.SoundHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

    @Override
    public void register() {

        Models.initModels();
        SoundHandler.init();
        EntityRegistry.initClient();

        ClientRegistry.bindTileEntitySpecialRenderer(TileCreationTable.class, new CreationTableRenderer());
        MinecraftForgeClient.registerItemRenderer(BlockRegistry.BlockCreationTable.blockID, new CreationTableRenderer());

        ClientRegistry.bindTileEntitySpecialRenderer(TileBlockBreaker.class, new BlockBreakerRenderer());
        MinecraftForgeClient.registerItemRenderer(BlockRegistry.BlockBlockBreaker.blockID, new BlockBreakerRenderer());

        ClientRegistry.bindTileEntitySpecialRenderer(TileSender.class, new SenderRenderer());
        MinecraftForgeClient.registerItemRenderer(BlockRegistry.BlockSender.blockID, new SenderRenderer());

        MinecraftForgeClient.registerItemRenderer(ItemRegistry.ItemBloodStone.itemID, new BloodStoneRenderer());

        MinecraftForgeClient.registerItemRenderer(ItemRegistry.ItemStorageCrystal.itemID, new StorageCrystalRenderer());

        ClientRegistry.bindTileEntitySpecialRenderer(TileSolarFocus.class, new SolarFocusRenderer());
        MinecraftForgeClient.registerItemRenderer(BlockRegistry.BlockSolarFocus.blockID, new SolarFocusRenderer());

        ClientRegistry.bindTileEntitySpecialRenderer(TileUniversalConduit.class, new UniversalConduitRenderer());
        MinecraftForgeClient.registerItemRenderer(BlockRegistry.BlockUniversalConduit.blockID, new UniversalConduitRenderer());

        ClientRegistry.bindTileEntitySpecialRenderer(TileConduitInterface.class, new ConduitInterfaceRenderer());
        MinecraftForgeClient.registerItemRenderer(BlockRegistry.BlockConduitInterface.blockID, new ConduitInterfaceRenderer());

        ClientRegistry.bindTileEntitySpecialRenderer(TileEnergyCube.class, new EnergyCubeRenderer());
        MinecraftForgeClient.registerItemRenderer(BlockRegistry.BlockEnergyCube.blockID, new EnergyCubeRenderer());

        MinecraftForgeClient.registerItemRenderer(ItemRegistry.ItemHandheldTank.itemID, new HandheldTankRenderer());

        ClientRegistry.bindTileEntitySpecialRenderer(TileGlassPipe.class, new GlassPipeRenderer());
        MinecraftForgeClient.registerItemRenderer(BlockRegistry.BlockGlassPipe.blockID, new GlassPipeRenderer());

        ClientRegistry.bindTileEntitySpecialRenderer(TileSolarGyro.class, new SolarGyroRenderer());
        MinecraftForgeClient.registerItemRenderer(BlockRegistry.BlockSolarGyro.blockID, new SolarGyroRenderer());

        ClientRegistry.bindTileEntitySpecialRenderer(TileSteamGyro.class, new SteamGyroRenderer());
        MinecraftForgeClient.registerItemRenderer(BlockRegistry.BlockSteamGyro.blockID, new SteamGyroRenderer());

        // MinecraftForgeClient.registerItemRenderer(ItemRegistry.ItemPurificationHelmet.itemID, new SolarFocusRenderer());

        RenderingRegistry.registerBlockHandler(new ConnectableTankRenderer());
        RenderingRegistry.registerBlockHandler(new TestRenderRenderer());
        RenderingRegistry.registerBlockHandler(new BlockSimpleRenderer());
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        TileEntity tile = world.getBlockTileEntity(x, y, z);

        if(tile != null) {

            switch(ID) {

                case Gui.CREATION_TABLE:
                    return new GuiCreationTable(player.inventory, tile);

                case Gui.BLOCK_BREAKER:
                    return new GuiBlockBreaker(player.inventory, tile);

                case Gui.CAMO_BLOCK:
                    return new GuiCamoBlock(player.inventory, tile);

                case Gui.ADVANCED_POTION_BREWERY:
                    return new GuiAdvancedPotionBrewery(player.inventory, tile);

                case Gui.SENDER:
                    return new GuiSender(player.inventory, tile);

                case Gui.SOLAR_FOCUS:
                    return new GuiSolarFocus(player.inventory, tile);

                case Gui.MASHER:
                    return new GuiMasher(player.inventory, tile);

                case Gui.DISSOLVER:
                    return new GuiDissolver(player.inventory, tile);
            }
        }
        return null;
    }
}

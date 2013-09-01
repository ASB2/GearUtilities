package GU.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import GU.BlockRegistry;
import GU.blocks.BlockTestRender.TestRenderRenderer;
import GU.blocks.containers.BlockAdvancedPotionBrewery.GuiAdvancedPotionBrewery;
import GU.blocks.containers.BlockBlockBreaker.BlockBreakerRenderer;
import GU.blocks.containers.BlockBlockBreaker.GuiBlockBreaker;
import GU.blocks.containers.BlockBlockBreaker.TileBlockBreaker;
import GU.blocks.containers.BlockCamoBlock.GuiCamoBlock;
import GU.blocks.containers.BlockConnectableTank.ConnectableTankRenderer;
import GU.blocks.containers.BlockCreationTable.CreationTableRenderer;
import GU.blocks.containers.BlockCreationTable.GuiCreationTable;
import GU.blocks.containers.BlockCreationTable.TileCreationTable;
import GU.blocks.containers.BlockRunicCube.RunicCubeRenderer;
import GU.blocks.containers.BlockRunicCube.TileRunicCube;
import GU.entity.EntityTest.EntityTestEntity;
import GU.entity.EntityTest.TestEntityRenderer;
import GU.info.Gui;
import GU.info.Models;
import GU.models.BlockSimpleRenderer;
import GU.sounds.SoundHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

    @Override
    public void register() {

        Models.initModels();
        new SoundHandler();

        RenderingRegistry.registerEntityRenderingHandler(EntityTestEntity.class, new TestEntityRenderer());

        ClientRegistry.bindTileEntitySpecialRenderer(TileCreationTable.class, new CreationTableRenderer());
        MinecraftForgeClient.registerItemRenderer(BlockRegistry.BlockCreationTable.blockID, new CreationTableRenderer());

        ClientRegistry.bindTileEntitySpecialRenderer(TileBlockBreaker.class, new BlockBreakerRenderer());
        MinecraftForgeClient.registerItemRenderer(BlockRegistry.BlockBlockBreaker.blockID, new BlockBreakerRenderer());
        
        ClientRegistry.bindTileEntitySpecialRenderer(TileRunicCube.class, new RunicCubeRenderer());
        MinecraftForgeClient.registerItemRenderer(BlockRegistry.BlockRunicCube.blockID, new RunicCubeRenderer());
        
        RenderingRegistry.registerBlockHandler(new ConnectableTankRenderer());
        RenderingRegistry.registerBlockHandler(new TestRenderRenderer());
        RenderingRegistry.registerBlockHandler(new BlockSimpleRenderer());
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        TileEntity tile = world.getBlockTileEntity(x, y, z);

        if (tile != null) {

            switch (ID) {

                case Gui.CREATION_TABLE:
                    return new GuiCreationTable(player.inventory, tile);

                case Gui.BLOCK_BREAKER:
                    return new GuiBlockBreaker(player.inventory, tile);

                case Gui.CAMO_BLOCK:
                    return new GuiCamoBlock(player.inventory, tile);

                case Gui.ADVANCED_POTION_BREWERY:
                    return new GuiAdvancedPotionBrewery(player.inventory, tile);
            }
        }
        return null;
    }
}

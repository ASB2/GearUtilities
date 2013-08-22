package GU.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import GU.BlockRegistry;
import GU.ItemRegistry;
import GU.blocks.BlockTestRender.TestRenderRenderer;
import GU.blocks.containers.BlockCanvas.CanvasRenderer;
import GU.blocks.containers.BlockConnectableTank.ConnectableTankRenderer;
import GU.blocks.containers.BlockCreationTable.CreationTableRenderer;
import GU.blocks.containers.BlockCreationTable.GuiCreationTable;
import GU.blocks.containers.BlockCreationTable.TileCreationTable;
import GU.blocks.containers.BlockLamp.LampRenderer;
import GU.blocks.containers.BlockLamp.TileLamp;
import GU.blocks.containers.BlockTestLaser.TestLaserRenderer;
import GU.blocks.containers.BlockTestLaser.TileTestLaser;
import GU.entity.EntityTest.EntityTestEntity;
import GU.entity.EntityTest.TestEntityRenderer;
import GU.info.Gui;
import GU.info.Models;
import GU.items.ItemStorageCrystal.StorageCrystalRenderer;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

    @Override
    public void register() {

        Models.initModels();

        RenderingRegistry.registerEntityRenderingHandler(EntityTestEntity.class, new TestEntityRenderer());

        ClientRegistry.bindTileEntitySpecialRenderer(TileLamp.class,
                new LampRenderer());
        MinecraftForgeClient.registerItemRenderer(
                BlockRegistry.BlockLamp.blockID, new LampRenderer());

        ClientRegistry.bindTileEntitySpecialRenderer(TileCreationTable.class,
                new CreationTableRenderer());
        MinecraftForgeClient.registerItemRenderer(
                BlockRegistry.BlockCreationTable.blockID,
                new CreationTableRenderer());

        MinecraftForgeClient.registerItemRenderer(
                ItemRegistry.ItemStorageCrystal.itemID,
                new StorageCrystalRenderer());

        RenderingRegistry.registerBlockHandler(new ConnectableTankRenderer());
        RenderingRegistry.registerBlockHandler(new TestRenderRenderer());
        RenderingRegistry.registerBlockHandler(new CanvasRenderer());

        ClientRegistry.bindTileEntitySpecialRenderer(TileTestLaser.class,
                new TestLaserRenderer());
        MinecraftForgeClient.registerItemRenderer(
                BlockRegistry.BlockTestLaser.blockID, new TestLaserRenderer());
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world,
            int x, int y, int z) {

        TileEntity tile = world.getBlockTileEntity(x, y, z);

        if (tile != null) {

            switch (ID) {

                case Gui.CREATION_TABLE:
                    return new GuiCreationTable(player.inventory, tile);
            }
        }
        return null;
    }
}

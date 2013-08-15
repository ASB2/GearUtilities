package GU.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import GU.BlockRegistry;
import GU.ItemRegistry;
import GU.blocks.containers.BlockConduit.ConduitRenderer;
import GU.blocks.containers.BlockConduit.TileConduit;
import GU.blocks.containers.BlockCreationTable.CreationTableRenderer;
import GU.blocks.containers.BlockCreationTable.TileCreationTable;
import GU.blocks.containers.BlockLamp.LampRenderer;
import GU.blocks.containers.BlockLamp.TileLamp;
import GU.blocks.containers.BlockTestLaser.TestLaserRenderer;
import GU.blocks.containers.BlockTestLaser.TileTestLaser;
import GU.blocks.containers.BlockTestRender.TestRenderRenderer;
import GU.blocks.containers.BlockTestTank.TestTankRenderer;
import GU.entity.EntityTest.EntityTestEntity;
import GU.entity.EntityTest.TestEntityRenderer;
import GU.info.Variables;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import GU.items.ItemCrystalShards.*;

public class ClientProxy extends CommonProxy {

    @Override
    public void register() {

        RenderingRegistry.registerEntityRenderingHandler(EntityTestEntity.class, new TestEntityRenderer());
       
        
        ClientRegistry.bindTileEntitySpecialRenderer(TileConduit.class, new ConduitRenderer());
        MinecraftForgeClient.registerItemRenderer(BlockRegistry.BlockConduit.blockID, new ConduitRenderer());
    
        ClientRegistry.bindTileEntitySpecialRenderer(TileLamp.class, new LampRenderer());
        MinecraftForgeClient.registerItemRenderer(BlockRegistry.BlockLamp.blockID, new LampRenderer());
       
        ClientRegistry.bindTileEntitySpecialRenderer(TileCreationTable.class, new CreationTableRenderer());
        MinecraftForgeClient.registerItemRenderer(BlockRegistry.BlockCreationTable.blockID, new CreationTableRenderer());
        
        MinecraftForgeClient.registerItemRenderer(ItemRegistry.ItemAirCrystalShard.itemID, new CrystalRenderer());
        MinecraftForgeClient.registerItemRenderer(ItemRegistry.ItemEarthCrystalShard.itemID, new CrystalRenderer());
        MinecraftForgeClient.registerItemRenderer(ItemRegistry.ItemFireCrystalShard.itemID, new CrystalRenderer());
        MinecraftForgeClient.registerItemRenderer(ItemRegistry.ItemWaterCrystalShard.itemID, new CrystalRenderer());
        MinecraftForgeClient.registerItemRenderer(ItemRegistry.ItemEnergyCrystalShard.itemID, new CrystalRenderer());
        
        if(Variables.TESTING_MODE) {
            
            RenderingRegistry.registerBlockHandler(new TestTankRenderer());
            RenderingRegistry.registerBlockHandler(new TestRenderRenderer());
            
            ClientRegistry.bindTileEntitySpecialRenderer(TileTestLaser.class, new TestLaserRenderer());
            MinecraftForgeClient.registerItemRenderer(BlockRegistry.BlockTestLaser.blockID, new TestLaserRenderer());
        }
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        // TODO Auto-generated method stub
        return null;
    }
}

package GU.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import GU.BlockRegistry;
import GU.blocks.containers.BlockConduit.ConduitRenderer;
import GU.blocks.containers.BlockConduit.TileConduit;
import GU.blocks.containers.BlockTestTank.TestTankRenderer;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import GU.entity.EntityTest.*;
import GU.blocks.containers.BlockTestLaser.*;

public class ClientProxy extends CommonProxy {

    @Override
    public void register() {

        RenderingRegistry.registerEntityRenderingHandler(EntityTest.class, new EntityTestRenderer());
        RenderingRegistry.registerBlockHandler(new TestTankRenderer());
        
        ClientRegistry.bindTileEntitySpecialRenderer(TileConduit.class, new ConduitRenderer());
        MinecraftForgeClient.registerItemRenderer(BlockRegistry.BlockConduit.blockID, new ConduitRenderer());
        
        ClientRegistry.bindTileEntitySpecialRenderer(TileTestLaser.class, new TestLaserRenderer());
        MinecraftForgeClient.registerItemRenderer(BlockRegistry.BlockTestLaser.blockID, new TestLaserRenderer());
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        // TODO Auto-generated method stub
        return null;
    }
}

package GU.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import GU.BlockRegistry;
import GU.blocks.containers.BlockConduit.ConduitRenderer;
import GU.blocks.containers.BlockConduit.TileConduit;
import GU.blocks.containers.BlockTestTank.TestTankRenderer;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

    public void register() {

        RenderingRegistry.registerBlockHandler(new TestTankRenderer());
        
        ClientRegistry.bindTileEntitySpecialRenderer(TileConduit.class, new ConduitRenderer());
        MinecraftForgeClient.registerItemRenderer(BlockRegistry.BlockConduit.blockID, (IItemRenderer)new ConduitRenderer());
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        // TODO Auto-generated method stub
        return null;
    }
}

package GU.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import GU.blocks.containers.BlockTestTank.TestTankRenderer;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

    public void register() {

        RenderingRegistry.registerBlockHandler(new TestTankRenderer());
        //ClientRegistry.bindTileEntitySpecialRenderer(TileTestTank.class, new TestTankRenderer());
        //MinecraftForgeClient.registerItemRenderer(BlockRegistry.BlockTCEnergySphere.blockID, (IItemRenderer)new ItemRendererMagicEnergySphere())
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        // TODO Auto-generated method stub
        return null;
    }
}

package GU.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import GU.tickHandler.*;

public class CommonProxy implements IGuiHandler {

    public GUTickHandler guTickHandler = new GUTickHandler();
    
    public void register() {
        
        TickRegistry.registerTickHandler(guTickHandler, Side.SERVER);        
    }
    
    public int addArmor(String string) {

        return 0;
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        // TODO Auto-generated method stub
        return null;
    }
}

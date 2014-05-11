package GUOLD.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import GUOLD.blocks.containers.BlockMultiPanel.ContainerMultiPanel;
import GUOLD.blocks.containers.BlockMultiPanel.TileMultiPanel;
import GUOLD.info.Gui;
import GUOLD.worldGen.RetroGenManager;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.relauncher.Side;

public class CommonProxy implements IGuiHandler {

    public void register() {

//        TickRegistry.registerTickHandler(new RetroGenManager(), Side.SERVER);
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        TileEntity tile = world.getTileEntity(x, y, z);

        switch(ID) {

//            case Gui.ADVANCED_POTION_BREWERY:
//                return new ContainerAdvancedPotionBrewery(player.inventory, (TileAdvancedPotionBrewery) tile);

            case Gui.MULTI_PANEL:
                return new ContainerMultiPanel(player.inventory, (TileMultiPanel) tile);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        // TODO Auto-generated method stub
        return null;
    }
}

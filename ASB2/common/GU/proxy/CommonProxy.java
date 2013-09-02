package GU.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import GU.GUTickHandler;
import GU.blocks.containers.BlockBlockBreaker.ContainerBlockBreaker;
import GU.blocks.containers.BlockBlockBreaker.TileBlockBreaker;
import GU.blocks.containers.BlockCamoBlock.ContainerCamoBlock;
import GU.blocks.containers.BlockCamoBlock.TileCamoBlock;
import GU.blocks.containers.BlockCreationTable.ContainerCreationTable;
import GU.blocks.containers.BlockCreationTable.TileCreationTable;
import GU.info.Gui;
import GU.sounds.SoundHandler;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import GU.blocks.containers.BlockAdvancedPotionBrewery.*;

public class CommonProxy implements IGuiHandler {

    public void register() {

        TickRegistry.registerTickHandler(new GUTickHandler(), Side.SERVER);
        new SoundHandler();
    }

    public int addArmor(String string) {

        return 0;
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        TileEntity tile = world.getBlockTileEntity(x, y, z);

        switch (ID) {

            case Gui.CREATION_TABLE:
                return new ContainerCreationTable(player.inventory, (TileCreationTable) tile);       
                
            case Gui.BLOCK_BREAKER:
                return new ContainerBlockBreaker(player.inventory, (TileBlockBreaker) tile);
        
            case Gui.CAMO_BLOCK:
                return new ContainerCamoBlock(player.inventory, (TileCamoBlock) tile);
                
            case Gui.ADVANCED_POTION_BREWERY:
                return new ContainerAdvancedPotionBrewery(player.inventory, (TileAdvancedPotionBrewery) tile);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        // TODO Auto-generated method stub
        return null;
    }
}

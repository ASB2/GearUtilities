package GU.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import GU.blocks.containers.BlockAdvancedPotionBrewery.ContainerAdvancedPotionBrewery;
import GU.blocks.containers.BlockAdvancedPotionBrewery.TileAdvancedPotionBrewery;
import GU.blocks.containers.BlockBlockBreaker.ContainerBlockBreaker;
import GU.blocks.containers.BlockBlockBreaker.TileBlockBreaker;
import GU.blocks.containers.BlockCamoBlock.ContainerCamoBlock;
import GU.blocks.containers.BlockCamoBlock.TileCamoBlock;
import GU.blocks.containers.BlockCreationTable.ContainerCreationTable;
import GU.blocks.containers.BlockCreationTable.TileCreationTable;
import GU.blocks.containers.BlockDissolver.ContainerDissolver;
import GU.blocks.containers.BlockDissolver.TileDissolver;
import GU.blocks.containers.BlockMasher.ContainerMasher;
import GU.blocks.containers.BlockMasher.TileMasher;
import GU.blocks.containers.BlockMultiPanel.ContainerMultiPanel;
import GU.blocks.containers.BlockMultiPanel.TileMultiPanel;
import GU.blocks.containers.BlockSolarFocus.ContainerSolarFocus;
import GU.blocks.containers.BlockSolarFocus.TileSolarFocus;
import GU.info.Gui;
import GU.worldGen.RetroGenManager;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class CommonProxy implements IGuiHandler {

    public void register() {

        TickRegistry.registerTickHandler(new RetroGenManager(), Side.SERVER);
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        TileEntity tile = world.getBlockTileEntity(x, y, z);

        switch(ID) {

            case Gui.CREATION_TABLE:
                return new ContainerCreationTable(player.inventory, (TileCreationTable) tile);

            case Gui.BLOCK_BREAKER:
                return new ContainerBlockBreaker(player.inventory, (TileBlockBreaker) tile);

            case Gui.CAMO_BLOCK:
                return new ContainerCamoBlock(player.inventory, (TileCamoBlock) tile);

            case Gui.ADVANCED_POTION_BREWERY:
                return new ContainerAdvancedPotionBrewery(player.inventory, (TileAdvancedPotionBrewery) tile);

            case Gui.SENDER:
                return new ContainerMultiPanel(player.inventory, (TileMultiPanel) tile);

            case Gui.SOLAR_FOCUS:
                return new ContainerSolarFocus(player.inventory, (TileSolarFocus) tile);

            case Gui.MASHER:
                return new ContainerMasher(player.inventory, (TileMasher) tile);
                
            case Gui.DISSOLVER:
                return new ContainerDissolver(player.inventory, (TileDissolver) tile);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        // TODO Auto-generated method stub
        return null;
    }
}

package GU;

import net.minecraft.block.material.Material;
import net.minecraftforge.common.Configuration;
import GU.blocks.BlockBase;
import GU.blocks.BlockBurningFlower;
import GU.blocks.BlockConnectedGlass;
import GU.blocks.BlockEtherealConnectedGlass;
import GU.blocks.BlockFalseBlock;
import GU.blocks.BlockFreezingFlower;
import GU.blocks.BlockPhantomBlock;
import GU.blocks.BlockSpeedyRoad;
import GU.blocks.BlockTestBlock;
import GU.blocks.BlockMetadataOre.BlockMetadataOre;
import GU.blocks.BlockTestRender.BlockTestRender;
import GU.blocks.containers.ContainerBase;
import GU.blocks.containers.BlockAdvancedPotionBrewery.BlockAdvancedPotionBrewery;
import GU.blocks.containers.BlockBlockBreaker.BlockBlockBreaker;
import GU.blocks.containers.BlockCamoBlock.BlockCamoBlock;
import GU.blocks.containers.BlockCanvas.BlockCanvas;
import GU.blocks.containers.BlockClusterSender.BlockClusterSender;
import GU.blocks.containers.BlockConduitInterface.BlockConduitInterface;
import GU.blocks.containers.BlockConnectableTank.BlockConnectableTank;
import GU.blocks.containers.BlockCreationTable.BlockCreationTable;
import GU.blocks.containers.BlockEnergyCube.BlockEnergyCube;
import GU.blocks.containers.BlockEnhancedBricks.BlockEnhancedBricks;
import GU.blocks.containers.BlockFluidProvider.BlockFluidProvider;
import GU.blocks.containers.BlockLamp.BlockLamp;
import GU.blocks.containers.BlockPowerTest.BlockPowerTest;
import GU.blocks.containers.BlockRunicCube.BlockRunicCube;
import GU.blocks.containers.BlockSender.BlockSender;
import GU.blocks.containers.BlockSolarFocus.BlockSolarFocus;
import GU.blocks.containers.BlockTestTile.BlockTestTile;
import GU.blocks.containers.BlockUniversalConduit.BlockUniversalConduit;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BlockRegistry {
    
    public static BlockMetadataOre BlockMetadataOre;
    public static BlockBase BlockTestBlock;
    public static ContainerBase BlockTestTile;
    public static BlockBase BlockBurningFlower;
    public static BlockBase BlockFreezingFlower;
    public static BlockBase BlockFalseBlock;
    public static BlockBase BlockSpeedyRoad;
    public static ContainerBase BlockConnectableTank;
    public static ContainerBase BlockTestLaser;
    public static ContainerBase BlockLamp;
    public static ContainerBase BlockCreationTable;
    public static BlockBase BlockTestRender;
    public static ContainerBase BlockFluidProvider;
    public static BlockBase BlockPhantomBlock;
    public static ContainerBase BlockCanvas;
    public static ContainerBase BlockPowerTest;
    public static BlockBase BlockConnectedGlass;
    public static BlockBase BlockEtherealConnectedGlass;
    public static ContainerBase BlockAdvancedPotionBrewery;
    public static ContainerBase BlockBlockBreaker;
    public static ContainerBase BlockCamoBlock;
    public static ContainerBase BlockEnhancedBricks;
    public static ContainerBase BlockRunicCube;
    public static ContainerBase BlockUniversalConduit;
    public static ContainerBase BlockClusterSender;
    public static ContainerBase BlockSender;
    public static ContainerBase BlockSolarFocus;
    public static ContainerBase BlockConduitInterface;
    public static ContainerBase BlockEnergyCube;
    
    private static int id = 500;

    public static void init(Configuration config) {

        BlockMetadataOre = new BlockMetadataOre(config.getBlock( "Metadata Blocks", BlockRegistry.getNextBaseID()).getInt(), Material.rock);

        BlockBurningFlower = new BlockBurningFlower(config.getBlock( "BlockBurningFlower", BlockRegistry.getNextBaseID()).getInt(), Material.leaves);
        BlockBurningFlower.setBlockName("BlockBurningFlower");
        LanguageRegistry.addName(BlockBurningFlower, "Burning Flower");

        BlockFreezingFlower = new BlockFreezingFlower(config.getBlock( "BlockFreezingFlower", BlockRegistry.getNextBaseID()).getInt(), Material.leaves);
        BlockFreezingFlower.setBlockName("BlockFreezingFlower");
        LanguageRegistry.addName(BlockFreezingFlower, "Freezing Flower");

        BlockFalseBlock = new BlockFalseBlock(config.getBlock( "BlockFalseBlock", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        BlockFalseBlock.setBlockName("BlockFalseBlock");
        LanguageRegistry.addName(BlockFalseBlock, "Ethereal Stone");

        BlockSpeedyRoad = new BlockSpeedyRoad(config.getBlock( "BlockSpeedyRoad", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        BlockSpeedyRoad.setBlockName("BlockSpeedyRoad");
        LanguageRegistry.addName(BlockSpeedyRoad, "Speedy Road");

        BlockLamp = new BlockLamp(config.getBlock("BlockLamp", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        BlockLamp.setBlockName("BlockLamp");
        LanguageRegistry.addName(BlockLamp, "Lamp");

        BlockCreationTable = new BlockCreationTable(config.getBlock( "BlockCreationTable", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        BlockCreationTable.setBlockName("BlockCreationTable");
        LanguageRegistry.addName(BlockCreationTable, "Creation Table");

        BlockFluidProvider = new BlockFluidProvider(config.getBlock( "BlockFluidProvider", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        BlockFluidProvider.setBlockName("BlockFluidProvider");
        LanguageRegistry.addName(BlockFluidProvider, "Fluid Provider");

        BlockTestBlock = new BlockTestBlock(config.getBlock("BlockTestBlock", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        BlockTestBlock.setBlockName("BlockTestBlock");
        LanguageRegistry.addName(BlockTestBlock, "Test Block");

        BlockTestTile = new BlockTestTile(config.getBlock("BlockTestTile", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        BlockTestTile.setBlockName("BlockTestTile");
        LanguageRegistry.addName(BlockTestTile, "Test Tile Block");

        BlockConnectableTank = new BlockConnectableTank(config.getBlock("BlockConnectableTank", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        BlockConnectableTank.setBlockName("BlockConnectableTank");
        LanguageRegistry.addName(BlockConnectableTank, "Connectable Tank");

        BlockTestRender = new BlockTestRender(config.getBlock( "BlockTestRender", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        BlockTestRender.setBlockName("BlockTestRender");
        LanguageRegistry.addName(BlockTestRender, "Test Render Block");

        BlockPhantomBlock = new BlockPhantomBlock(config.getBlock( "BlockPhantomBlock", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        BlockPhantomBlock.setBlockName("BlockPhantomBlock");
        LanguageRegistry.addName(BlockPhantomBlock, "Phantom Block");

        BlockCanvas = new BlockCanvas(config.getBlock("BlockCanvas", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        BlockCanvas.setBlockName("BlockCanvas");
        LanguageRegistry.addName(BlockCanvas, "Canvas");

        BlockPowerTest = new BlockPowerTest(config.getBlock("BlockPowerTest", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        BlockPowerTest.setBlockName("BlockPowerTest");
        LanguageRegistry.addName(BlockPowerTest, "Power Sink/Source");
        
        BlockConnectedGlass = new BlockConnectedGlass(config.getBlock("BlockConnectedGlass", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        BlockConnectedGlass.setBlockName("BlockConnectedGlass");
        LanguageRegistry.addName(BlockConnectedGlass, "Connected Glass");
        
        BlockEtherealConnectedGlass = new BlockEtherealConnectedGlass(config.getBlock("BlockEtherealConnectedGlass", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        BlockEtherealConnectedGlass.setBlockName("BlockEtherealConnectedGlass");
        LanguageRegistry.addName(BlockEtherealConnectedGlass, "Ethereal Connected Glass");
        
        BlockAdvancedPotionBrewery = new BlockAdvancedPotionBrewery(config.getBlock("BlockAdvancedPotionBrewery", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        BlockAdvancedPotionBrewery.setBlockName("BlockAdvancedPotionBrewery");
        LanguageRegistry.addName(BlockAdvancedPotionBrewery, "Advanced Potion Brewery");
        
        BlockBlockBreaker = new BlockBlockBreaker(config.getBlock("BlockBlockBreaker", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        BlockBlockBreaker.setBlockName("BlockBlockBreaker");
        LanguageRegistry.addName(BlockBlockBreaker, "Block Breaker");
        
        BlockCamoBlock = new BlockCamoBlock(config.getBlock("BlockCamoBlock", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        BlockCamoBlock.setBlockName("BlockCamoBlock");
        LanguageRegistry.addName(BlockCamoBlock, "Camo Block");
    
        BlockEnhancedBricks = new BlockEnhancedBricks(config.getBlock("BlockEnhancedBricks", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        BlockEnhancedBricks.setBlockName("BlockEnhancedBricks");
        LanguageRegistry.addName(BlockEnhancedBricks, "Enhanced Brick");
    
        BlockRunicCube = new BlockRunicCube(config.getBlock("BlockRunicCube", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        BlockRunicCube.setBlockName("BlockRunicCube");
        LanguageRegistry.addName(BlockRunicCube, "Runic Cube");
        
        BlockUniversalConduit = new BlockUniversalConduit(config.getBlock("BlockUniversalConduit", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        BlockUniversalConduit.setBlockName("BlockUniversalConduit");
        LanguageRegistry.addName(BlockUniversalConduit, "Universal Conduit");
        
        BlockClusterSender = new BlockClusterSender(config.getBlock("BlockClusterSender", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        BlockClusterSender.setBlockName("BlockClusterSender");
        LanguageRegistry.addName(BlockClusterSender, "Cluster Redirector");
        
        BlockSender = new BlockSender(config.getBlock("BlockSender", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        BlockSender.setBlockName("BlockSender");
        LanguageRegistry.addName(BlockSender, "Universal Sender");
        
        BlockSolarFocus = new BlockSolarFocus(config.getBlock("BlockSolarFocus", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        BlockSolarFocus.setBlockName("BlockSolarFocus");
        LanguageRegistry.addName(BlockSolarFocus, "Solar Focus");
        
        BlockConduitInterface = new BlockConduitInterface(config.getBlock("BlockConduitInterface", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        BlockConduitInterface.setBlockName("BlockConduitInterface");
        LanguageRegistry.addName(BlockConduitInterface, "Conduit Interface");
        
        BlockEnergyCube = new BlockEnergyCube(config.getBlock("BlockEnergyCube", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        BlockEnergyCube.setBlockName("BlockEnergyCube");
        LanguageRegistry.addName(BlockEnergyCube, "Energy Cube");
    }

    public static void initTestBlocks(Configuration config) {

    }

    public static int getNextBaseID() {

        return id++;
    }
}

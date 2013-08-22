package GU;

import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import GU.blocks.BlockBase;
import GU.blocks.BlockBurningFlower;
import GU.blocks.BlockConnectedGlass;
import GU.blocks.BlockEtherealConnectedGlass;
import GU.blocks.BlockFalseBlock;
import GU.blocks.BlockFreezingFlower;
import GU.blocks.BlockGarnetBlock;
import GU.blocks.BlockGarnetOre;
import GU.blocks.BlockPhantomBlock;
import GU.blocks.BlockSpeedyRoad;
import GU.blocks.BlockTestBlock;
import GU.blocks.BlockMetadataOre.BlockMetadataOre;
import GU.blocks.BlockTestRender.BlockTestRender;
import GU.blocks.containers.ContainerBase;
import GU.blocks.containers.BlockCanvas.BlockCanvas;
import GU.blocks.containers.BlockConnectableTank.BlockConnectableTank;
import GU.blocks.containers.BlockCreationTable.BlockCreationTable;
import GU.blocks.containers.BlockFluidProvider.BlockFluidProvider;
import GU.blocks.containers.BlockLamp.BlockLamp;
import GU.blocks.containers.BlockPowerTest.BlockPowerTest;
import GU.blocks.containers.BlockTestLaser.BlockTestLaser;
import GU.blocks.containers.BlockTestTile.BlockTestTile;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BlockRegistry {

    public static BlockMetadataOre BlockMetadataOre;
    public static BlockBase BlockTestBlock;
    public static ContainerBase BlockTestTile;
    public static BlockBase BlockGarnetOre;
    public static BlockBase BlockGarnetBlock;
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
    
    private static int id = 500;

    public static void init(Configuration config) {

        BlockMetadataOre = new BlockMetadataOre(config.getBlock( "Metadata Ores", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        BlockMetadataOre.setBlockName("BlockMetadataOre");
        LanguageRegistry.addName(new ItemStack(BlockMetadataOre, 1, 0), "Air Crystal Ore");
        LanguageRegistry.addName(new ItemStack(BlockMetadataOre, 1, 1), "Earth Crystal Ore");
        LanguageRegistry.addName(new ItemStack(BlockMetadataOre, 1, 2), "Fire Crystal Ore");
        LanguageRegistry.addName(new ItemStack(BlockMetadataOre, 1, 3), "Water Crystal Ore");
        LanguageRegistry.addName(new ItemStack(BlockMetadataOre, 1, 4), "Energy Crystal Ore");

        BlockGarnetOre = new BlockGarnetOre(config.getBlock("BlockGarnetOre", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        BlockGarnetOre.setBlockName("BlockGarnetOre");
        LanguageRegistry.addName(BlockGarnetOre, "Garnet Ore");

        BlockGarnetBlock = new BlockGarnetBlock(config.getBlock( "BlockGarnetBlock", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        BlockGarnetBlock.setBlockName("BlockGarnetBlock");
        LanguageRegistry.addName(BlockGarnetBlock, "Garnet Block");

        BlockBurningFlower = new BlockBurningFlower(config.getBlock( "BlockBurningFlower", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        BlockBurningFlower.setBlockName("BlockBurningFlower");
        LanguageRegistry.addName(BlockBurningFlower, "Burning Flower");

        BlockFreezingFlower = new BlockFreezingFlower(config.getBlock( "BlockFreezingFlower", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        BlockFreezingFlower.setBlockName("BlockFreezingFlower");
        LanguageRegistry.addName(BlockFreezingFlower, "Freezing Flower");

        BlockFalseBlock = new BlockFalseBlock(config.getBlock( "BlockFalseBlock", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        BlockFalseBlock.setBlockName("BlockFalseBlock");
        LanguageRegistry.addName(BlockFalseBlock, "Ghost Stone");

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

        BlockTestLaser = new BlockTestLaser(config.getBlock("BlockTestLaser", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        BlockTestLaser.setBlockName("BlockTestLaser");
        LanguageRegistry.addName(BlockTestLaser, "Test Laser");

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
    }

    public static void initTestBlocks(Configuration config) {

    }

    public static int getNextBaseID() {

        return id++;
    }
}

package GU;

import net.minecraft.block.material.Material;
import net.minecraftforge.common.Configuration;
import GU.blocks.BlockAirCrystalOre;
import GU.blocks.BlockBase;
import GU.blocks.BlockBurningFlower;
import GU.blocks.BlockEarthCrystalOre;
import GU.blocks.BlockEnergyCrystalOre;
import GU.blocks.BlockFalseBlock;
import GU.blocks.BlockFireCrystalOre;
import GU.blocks.BlockFreezingFlower;
import GU.blocks.BlockGarnetBlock;
import GU.blocks.BlockGarnetOre;
import GU.blocks.BlockSpeedyRoad;
import GU.blocks.BlockTestBlock;
import GU.blocks.BlockWaterCrystalOre;
import GU.blocks.containers.ContainerBase;
import GU.blocks.containers.BlockConduit.BlockConduit;
import GU.blocks.containers.BlockLamp.BlockLamp;
import GU.blocks.containers.BlockTestLaser.BlockTestLaser;
import GU.blocks.containers.BlockTestTank.BlockTestTank;
import GU.blocks.containers.BlockTestTile.BlockTestTile;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BlockRegistry {

    public static BlockBase BlockTestBlock;
    public static ContainerBase BlockTestTile;
    public static BlockBase BlockAirCrystalOre;
    public static BlockBase BlockEarthCrystalOre;
    public static BlockBase BlockFireCrystalOre;
    public static BlockBase BlockWaterCrystalOre;
    public static BlockBase BlockEnergyCrystalOre;
    public static BlockBase BlockGarnetOre;
    public static BlockBase BlockGarnetBlock;
    public static BlockBase BlockBurningFlower;
    public static BlockBase BlockFreezingFlower;
    public static BlockBase BlockFalseBlock;
    public static BlockBase BlockSpeedyRoad;
    public static ContainerBase BlockTestTank;
    public static ContainerBase BlockConduit;
    public static ContainerBase BlockTestLaser;
    public static ContainerBase BlockLamp;
    
    private static int id = 500;

    public static void init(Configuration config) {

        BlockTestBlock = new BlockTestBlock(config.getBlock("BlockTestBlock", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        BlockTestBlock.setBlockName("BlockTestBlock");
        LanguageRegistry.addName(BlockTestBlock, "Test Block");

        BlockTestTile = new BlockTestTile(config.getBlock("BlockTestTile", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        BlockTestTile.setBlockName("BlockTestTile");
        LanguageRegistry.addName(BlockTestTile, "Test Tile Block");

        BlockAirCrystalOre = new BlockAirCrystalOre(config.getBlock("BlockAirCrystalOre", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        BlockAirCrystalOre.setBlockName("BlockAirCrystalOre");
        LanguageRegistry.addName(BlockAirCrystalOre, "Air Crystal Ore");

        BlockEarthCrystalOre = new BlockEarthCrystalOre(config.getBlock("BlockEarthCrystalOre", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        BlockEarthCrystalOre.setBlockName("BlockEarthCrystalOre");
        LanguageRegistry.addName(BlockEarthCrystalOre, "Earth Crystal Ore");

        BlockFireCrystalOre = new BlockFireCrystalOre(config.getBlock("BlockFireCrystalOre", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        BlockFireCrystalOre.setBlockName("BlockFireCrystalOre");
        LanguageRegistry.addName(BlockFireCrystalOre, "Fire Crystal Ore");

        BlockWaterCrystalOre = new BlockWaterCrystalOre(config.getBlock("BlockWaterCrystalOre", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        BlockWaterCrystalOre.setBlockName("BlockWaterCrystalOre");
        LanguageRegistry.addName(BlockWaterCrystalOre, "Water Crystal Ore");

        BlockEnergyCrystalOre = new BlockEnergyCrystalOre(config.getBlock("BlockEnergyCrystalOre", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        BlockEnergyCrystalOre.setBlockName("BlockEnergyCrystalOre");
        LanguageRegistry.addName(BlockEnergyCrystalOre, "Energy Crystal Ore");

        BlockGarnetOre = new BlockGarnetOre(config.getBlock("BlockGarnetOre", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        BlockGarnetOre.setBlockName("BlockGarnetOre");
        LanguageRegistry.addName(BlockGarnetOre, "Garnet Ore");

        BlockGarnetBlock = new BlockGarnetBlock(config.getBlock("BlockGarnetBlock", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        BlockGarnetBlock.setBlockName("BlockGarnetBlock");
        LanguageRegistry.addName(BlockGarnetBlock, "Garnet Block");

        BlockBurningFlower = new BlockBurningFlower(config.getBlock("BlockBurningFlower", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        BlockBurningFlower.setBlockName("BlockBurningFlower");
        LanguageRegistry.addName(BlockBurningFlower, "Burning Flower");

        BlockFreezingFlower = new BlockFreezingFlower(config.getBlock("BlockFreezingFlower", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        BlockFreezingFlower.setBlockName("BlockFreezingFlower");
        LanguageRegistry.addName(BlockFreezingFlower, "Freezing Flower");

        BlockFalseBlock = new BlockFalseBlock(config.getBlock("BlockFalseBlock", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        BlockFalseBlock.setBlockName("BlockFalseBlock");
        LanguageRegistry.addName(BlockFalseBlock, "Ghost Stone");

        BlockSpeedyRoad = new BlockSpeedyRoad(config.getBlock("BlockSpeedyRoad", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        BlockSpeedyRoad.setBlockName("BlockSpeedyRoad");
        LanguageRegistry.addName(BlockSpeedyRoad, "Speedy Road");
        
        BlockTestTank = new BlockTestTank(config.getBlock("BlockTestTank", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        BlockTestTank.setBlockName("BlockTestTank");
        LanguageRegistry.addName(BlockTestTank, "Test Tank");
        
        BlockConduit = new BlockConduit(config.getBlock("BlockCable", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        BlockConduit.setBlockName("BlockConduit");
        LanguageRegistry.addName(BlockConduit, "Multi-Conduit");
    
        BlockTestLaser = new BlockTestLaser(config.getBlock("BlockTestLaser", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        BlockTestLaser.setBlockName("BlockTestLaser");
        LanguageRegistry.addName(BlockTestLaser, "Test Laser");
        
        BlockLamp = new BlockLamp(config.getBlock("BlockLamp", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        BlockLamp.setBlockName("BlockLamp");
        LanguageRegistry.addName(BlockLamp, "Lamp");
    }

    public static int getNextBaseID() {        

        return id++;
    }
}

package GU;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import GU.blocks.BlockBase;
import GU.blocks.BlockEtherealStone;
import GU.blocks.BlockPhantomBlock;
import GU.blocks.BlockMetadataOre.BlockMetadataOre;
import GU.blocks.containers.ContainerBase;
import GU.blocks.containers.BlockAdvancedPotionBrewery.BlockAdvancedPotionBrewery;
import GU.blocks.containers.BlockEnhancedBricks.BlockEnhancedBricks;
import GU.blocks.containers.BlockFluidProvider.BlockFluidProvider;
import GU.blocks.containers.BlockMultiPanel.BlockMultiPanel;
import GU.blocks.containers.BlockMultiPanel.ItemBlockMultiPanel;
import GU.blocks.containers.BlockPowerTest.BlockPowerTest;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BlockRegistry {
    
    public static BlockMetadataOre BlockMetadataOre;
    public static BlockBase BlockEtherealStone;
    public static ContainerBase BlockFluidProvider;
    public static BlockBase BlockPhantomBlock;
    public static ContainerBase BlockPowerTest;
    public static ContainerBase BlockAdvancedPotionBrewery;
    public static ContainerBase BlockEnhancedBricks;
    public static ContainerBase BlockMultiPanel;
    
    private static int id = 500;
    
    public static void init(Configuration config) {
        
        BlockMetadataOre = new BlockMetadataOre(config.getBlock("Metadata Ore", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        addBlock(BlockMetadataOre, "", "Metadata Ore");
        
        BlockEtherealStone = new BlockEtherealStone(config.getBlock("BlockEtherealStone", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        addBlock(BlockEtherealStone, "Ethereal Stone", "BlockEtherealStone");
        
        BlockFluidProvider = new BlockFluidProvider(config.getBlock("BlockFluidProvider", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        addBlock(BlockFluidProvider, "Fluid Provider", "BlockFluidProvider");
        
        BlockPhantomBlock = new BlockPhantomBlock(config.getBlock("BlockPhantomBlock", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        addBlock(BlockPhantomBlock, "Phantom Block", "BlockPhantomBlock");
        
        BlockPowerTest = new BlockPowerTest(config.getBlock("BlockPowerTest", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        addBlock(BlockPowerTest, "Power Sink/Source", "BlockPowerTest");
        
        BlockAdvancedPotionBrewery = new BlockAdvancedPotionBrewery(config.getBlock("BlockAdvancedPotionBrewery", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        addBlock(BlockAdvancedPotionBrewery, "Advanced Potion Brewery", "BlockAdvancedPotionBrewery");
        
        BlockEnhancedBricks = new BlockEnhancedBricks(config.getBlock("BlockEnhancedBrick", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        addBlock(BlockEnhancedBricks, "Enhanced Brick", "BlockEnhancedBrick");
        
        BlockMultiPanel = new BlockMultiPanel(config.getBlock("BlockMultiPanel", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        BlockMultiPanel.setUnlocalizedName("BlockMultiPanel");
        LanguageRegistry.addName(BlockMultiPanel, "Multi-Panel");
        MinecraftForge.setBlockHarvestLevel(BlockMultiPanel, "pickaxe", 2);
        GameRegistry.registerBlock(BlockMultiPanel, ItemBlockMultiPanel.class, "BlockMultiPanel");
    }
    
    public static Block addBlock(Block block, String ign, String unlocalizedName) {
        
        block.setUnlocalizedName(unlocalizedName);
        block.setTextureName(unlocalizedName);
        LanguageRegistry.addName(block, ign);
        MinecraftForge.setBlockHarvestLevel(block, "pickaxe", 2);
        GameRegistry.registerBlock(block, GUItemBlock.class, block.getUnlocalizedName());
        return block;
    }
    
    public static int getNextBaseID() {
        
        return id++;
    }
}

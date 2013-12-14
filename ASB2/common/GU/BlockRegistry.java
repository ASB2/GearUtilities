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
import GU.blocks.containers.BlockBasicElemental.BlockBasicElemental;
import GU.blocks.containers.BlockEnhancedBricks.BlockEnhancedBricks;
import GU.blocks.containers.BlockFluidProvider.BlockFluidProvider;
import GU.blocks.containers.BlockMultiPanel.BlockMultiPanel;
import GU.blocks.containers.BlockMultiPanel.ItemBlockMultiPanel;
import GU.blocks.containers.BlockPowerTest.BlockPowerTest;
import GU.blocks.containers.BlockSpacialProvider.BlockSpacialProvider;
import GU.info.GUItemBlock;
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
    public static ContainerBase BlockBasicElemental;
    public static BlockBase BlockStructureCube, BlockStructureCube2, BlockStructureCube3, BlockStructureCube4, BlockStructureGlass;
    public static ContainerBase BlockSpacialProvider;
    
    private static int id = 500;
    
    public static void init(Configuration config) {
        
        BlockMetadataOre = new BlockMetadataOre(BlockRegistry.getConfigID(config, "Metadata Ore"), Material.rock);
        addBlockNoLang(BlockMetadataOre, "", "Metadata Ore");
        
        BlockEtherealStone = new BlockEtherealStone(BlockRegistry.getConfigID(config, "BlockEtherealStone"), Material.rock);
        addBlock(BlockEtherealStone, "Ethereal Stone", "BlockEtherealStone");
        
        BlockFluidProvider = new BlockFluidProvider(BlockRegistry.getConfigID(config, "BlockFluidProvider"), Material.rock);
        addBlock(BlockFluidProvider, "Fluid Provider", "BlockFluidProvider");
        
        BlockPhantomBlock = new BlockPhantomBlock(BlockRegistry.getConfigID(config, "BlockPhantomBlock"), Material.rock);
        addBlock(BlockPhantomBlock, "Phantom Block", "BlockPhantomBlock");
        
        BlockPowerTest = new BlockPowerTest(BlockRegistry.getConfigID(config, "BlockPowerTest"), Material.rock);
        addBlock(BlockPowerTest, "Power Sink/Source", "BlockPowerTest");
        
        BlockAdvancedPotionBrewery = new BlockAdvancedPotionBrewery(BlockRegistry.getConfigID(config, "BlockAdvancedPotionBrewery"), Material.rock);
        addBlock(BlockAdvancedPotionBrewery, "Advanced Potion Brewery", "BlockAdvancedPotionBrewery");
        BlockAdvancedPotionBrewery.setTextureString(new String[] { "BlockAdvancedPotionBreweryBottom", "BlockAdvancedPotionBreweryTop", "BlockAdvancedPotionBrewerySide" });
        
        BlockEnhancedBricks = new BlockEnhancedBricks(BlockRegistry.getConfigID(config, "BlockEnhancedBrick"), Material.rock);
        addBlock(BlockEnhancedBricks, "Enhanced Brick", "BlockEnhancedBrick");
        
        BlockMultiPanel = new BlockMultiPanel(BlockRegistry.getConfigID(config, "BlockMultiPanel"), Material.rock);
        BlockMultiPanel.setUnlocalizedName("BlockMultiPanel");
        LanguageRegistry.addName(BlockMultiPanel, "Multi-Panel");
        MinecraftForge.setBlockHarvestLevel(BlockMultiPanel, "pickaxe", 2);
        GameRegistry.registerBlock(BlockMultiPanel, ItemBlockMultiPanel.class, "BlockMultiPanel");
        
        BlockBasicElemental = new BlockBasicElemental(BlockRegistry.getConfigID(config, "BlockBasicElemental"), Material.rock);
        addBlockNoLang(BlockBasicElemental, "BlockBasicElemental", "BlockBasicElemental");
        
        BlockStructureCube = new BlockBase(BlockRegistry.getConfigID(config, "BlockStructureCube"), Material.rock);
        addBlock(BlockStructureCube, "Structure Cube", "BlockStructureCube");
        BlockStructureCube.setResistance(Block.obsidian.blockResistance);
        
        BlockStructureCube2 = new BlockBase(BlockRegistry.getConfigID(config, "BlockStructureCube2"), Material.rock);
        addBlock(BlockStructureCube2, "Structure Cube 2", "BlockStructureCube2");
        BlockStructureCube2.setResistance(Block.obsidian.blockResistance);
        
        BlockStructureCube3 = new BlockBase(BlockRegistry.getConfigID(config, "BlockStructureCube3"), Material.rock);
        addBlock(BlockStructureCube3, "Structure Cube 3", "BlockStructureCube3");
        BlockStructureCube3.setResistance(Block.obsidian.blockResistance);
        
        BlockStructureCube4 = new BlockBase(BlockRegistry.getConfigID(config, "BlockStructureCube4"), Material.rock);
        addBlock(BlockStructureCube4, "Structure Cube 4", "BlockStructureCube4");
        BlockStructureCube4.setResistance(Block.obsidian.blockResistance);
        
        BlockStructureGlass = new BlockBase(BlockRegistry.getConfigID(config, "BlockStructureGlass"), Material.rock);
        addBlock(BlockStructureGlass, "Structure Glass", "BlockStructureGlass");
        BlockStructureGlass.setResistance(Block.obsidian.blockResistance);
        
        BlockSpacialProvider = new BlockSpacialProvider(BlockRegistry.getConfigID(config, "BlockSpacialProvider"), Material.rock);
        addBlock(BlockSpacialProvider, "Spacial Provider", "BlockSpacialProvider");
    }
    
    public static Block addBlock(Block block, String ign, String unlocalizedName) {
        
        LanguageRegistry.addName(block, ign);
        addBlockNoLang(block, ign, unlocalizedName);
        return block;
    }
    
    public static Block addBlockNoLang(Block block, String ign, String unlocalizedName) {
        
        block.setUnlocalizedName(unlocalizedName);
        block.setTextureName(unlocalizedName);
        MinecraftForge.setBlockHarvestLevel(block, "pickaxe", 1);
        GameRegistry.registerBlock(block, GUItemBlock.class, block.getUnlocalizedName());
        return block;
    }
    
    public static int getNextBaseID() {
        
        return id++;
    }
    
    public static int getConfigID(Configuration config, String string) {
        
        return config.getBlock(string, BlockRegistry.getNextBaseID()).getInt();
    }
}

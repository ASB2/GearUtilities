package GU;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import GU.blocks.BlockBase;
import GU.blocks.BlockBasicElemental;
import GU.blocks.BlockEtherealStone;
import GU.blocks.BlockPhantomBlock;
import GU.blocks.BlockMetadataOre.BlockMetadataOre;
import GU.blocks.containers.ContainerBase;
import GU.blocks.containers.BlockAdvancedPotionBrewery.BlockAdvancedPotionBrewery;
import GU.blocks.containers.BlockCentrifuge.BlockCentrifuge;
import GU.blocks.containers.BlockElementalRefinery.BlockElementalRefinery;
import GU.blocks.containers.BlockEnhancedBricks.BlockEnhancedBricks;
import GU.blocks.containers.BlockEssenceDiffuser.BlockEssenceDiffuser;
import GU.blocks.containers.BlockFlameAltar.BlockFlameAltar;
import GU.blocks.containers.BlockFlameConduit.BlockFlameConduit;
import GU.blocks.containers.BlockFluidProvider.BlockFluidProvider;
import GU.blocks.containers.BlockGlassPipe.BlockGlassPipe;
import GU.blocks.containers.BlockMultiInterface.BlockMultiInterface;
import GU.blocks.containers.BlockMultiPanel.BlockMultiPanel;
import GU.blocks.containers.BlockMultiPanel.ItemBlockMultiPanel;
import GU.blocks.containers.BlockPowerTest.BlockPowerTest;
import GU.blocks.containers.BlockQuantaSender.BlockQuantaSender;
import GU.blocks.containers.BlockSmeltingCube.BlockSmeltingCube;
import GU.blocks.containers.BlockSpacialProvider.BlockSpacialProvider;
import GU.blocks.containers.BlockStructureCube.BlockMultiCore;
import GU.blocks.containers.BlockStructureCube.BlockReplacementStructureCube;
import GU.blocks.containers.BlockStructureCube.BlockStructureAir;
import GU.blocks.containers.BlockStructureCube.BlockStructureCube;
import GU.items.GUItemBlock;
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
    public static BlockBase BlockBasicElemental;
    public static BlockStructureCube BlockStructureCube;
    public static BlockSpacialProvider BlockSpacialProvider;
    public static ContainerBase BlockSmeltingCube;
    public static ContainerBase BlockQuantaSender;
    public static ContainerBase BlockEssenceDiffuser;
    public static ContainerBase BlockCentrifuge;
    public static ContainerBase BlockElementalRefinery;
    public static BlockMultiInterface BlockMultiInterface;
    public static ContainerBase BlockStructureAir;
    public static ContainerBase BlockReplacementStructureCube;
    public static ContainerBase BlockFlameAltar;
    public static ContainerBase BlockFlameConduit;
    public static ContainerBase BlockGlassPipe;
    public static ContainerBase BlockMultiCore;
    
    private static int id = 500;

    public static void init(Configuration config) {

        BlockMetadataOre = new BlockMetadataOre(BlockRegistry.getConfigID(config, "Metadata Ore"), Material.rock);
        addBlock(BlockMetadataOre, "", "Metadata Ore");

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
        addBlock(BlockBasicElemental, "BlockBasicElemental", "BlockBasicElemental");

        BlockStructureCube = new BlockStructureCube(BlockRegistry.getConfigID(config, "BlockStructureCube"), Material.rock);
        BlockStructureCube.setResistance(Block.obsidian.blockResistance);

        BlockSpacialProvider = new BlockSpacialProvider(BlockRegistry.getConfigID(config, "BlockSpacialProvider"), Material.rock);
        addBlock(BlockSpacialProvider, "Spacial Provider", "BlockSpacialProvider");

        BlockSmeltingCube = new BlockSmeltingCube(BlockRegistry.getConfigID(config, "BlockSmeltingCube"), Material.rock);
        addBlock(BlockSmeltingCube, "Smelting Cube", "BlockSmeltingCube");

        BlockQuantaSender = new BlockQuantaSender(BlockRegistry.getConfigID(config, "BlockQuantaSender"), Material.rock);
        addBlock(BlockQuantaSender, "Quanta Sender", "BlockQuantaSender");

        BlockEssenceDiffuser = new BlockEssenceDiffuser(BlockRegistry.getConfigID(config, "BlockEssenceDiffuser"), Material.rock);
        addBlock(BlockEssenceDiffuser, "Essence Diffuser", "BlockEssenceDiffuser");

        BlockCentrifuge = new BlockCentrifuge(BlockRegistry.getConfigID(config, "BlockCentrifuge"), Material.rock);
        addBlock(BlockCentrifuge, "Centrifuge", "BlockCentrifuge");

        BlockElementalRefinery = new BlockElementalRefinery(BlockRegistry.getConfigID(config, "BlockElementalRefinery"), Material.rock);
        addBlock(BlockElementalRefinery, "Elemental Refinery", "BlockElementalRefinery");

        BlockMultiInterface = new BlockMultiInterface(BlockRegistry.getConfigID(config, "BlockMultiInterface"), Material.rock);
        BlockMultiInterface.setResistance(Block.obsidian.blockResistance);

        BlockStructureAir = new BlockStructureAir(BlockRegistry.getConfigID(config, "BlockStructureAir"), Material.air);
        addBlock(BlockStructureAir, "Structure Air", "BlockStructureAir");

        BlockReplacementStructureCube = new BlockReplacementStructureCube(BlockRegistry.getConfigID(config, "BlockReplacementStructureCube"), Material.rock);
        addBlock(BlockReplacementStructureCube, "Replacement Structure Cube", "BlockReplacementStructureCube");

        BlockFlameAltar = new BlockFlameAltar(BlockRegistry.getConfigID(config, "BlockFlameAltar"), Material.rock);
        addBlock(BlockFlameAltar, "Flame Altar", "BlockFlameAltar");

        BlockFlameConduit = new BlockFlameConduit(BlockRegistry.getConfigID(config, "BlockFlameConduit"), Material.rock);
        addBlock(BlockFlameConduit, "Flame Conduit", "BlockFlameConduit");
    
        BlockGlassPipe = new BlockGlassPipe(BlockRegistry.getConfigID(config, "BlockGlassPipe"), Material.rock);
        addBlock(BlockGlassPipe, "Glass Pipe", "BlockGlassPipe");
        
        BlockMultiCore = new BlockMultiCore(BlockRegistry.getConfigID(config, "BlockMultiCore"), Material.rock);
        addBlock(BlockMultiCore, "Multi Core", "BlockMultiCore");
    }

    public static Block addBlock(Block block, String ign, String unlocalizedName) {

        block.setUnlocalizedName(unlocalizedName);
        block.setTextureName(unlocalizedName);
        MinecraftForge.setBlockHarvestLevel(block, "pickaxe", 1);
        GameRegistry.registerBlock(block, GUItemBlock.class, block.getUnlocalizedName());
        LanguageRegistry.addName(block, ign);
        return block;
    }

    public static int getNextBaseID() {

        return id++;
    }

    public static int getConfigID(Configuration config, String string) {

        return config.getBlock(string, BlockRegistry.getNextBaseID()).getInt();
    }
}

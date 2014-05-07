package GU;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.config.Configuration;
import GU.blocks.BlockBase;
import GU.blocks.BlockBasicElemental;
import GU.blocks.BlockEtherealStone;
import GU.blocks.BlockPhantomBlock;
import GU.blocks.BlockMetadataOre.BlockMetadataOre;
import GU.blocks.containers.ContainerBase;
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
    public static ContainerBase BlockFlameAltar;
    public static ContainerBase BlockFlameConduit;
    public static ContainerBase BlockGlassPipe;
    
    public static void init(Configuration config) {
        
        BlockMetadataOre = new BlockMetadataOre(Material.rock);
        addBlock(BlockMetadataOre, "", "Metadata Ore");
        
        BlockEtherealStone = new BlockEtherealStone(Material.rock);
        addBlock(BlockEtherealStone, "Ethereal Stone", "BlockEtherealStone");
        
        BlockFluidProvider = new BlockFluidProvider(Material.rock);
        addBlock(BlockFluidProvider, "Fluid Provider", "BlockFluidProvider");
        
        BlockPhantomBlock = new BlockPhantomBlock(Material.rock);
        addBlock(BlockPhantomBlock, "Phantom Block", "BlockPhantomBlock");
        
        BlockPowerTest = new BlockPowerTest(Material.rock);
        addBlock(BlockPowerTest, "Power Sink/Source", "BlockPowerTest");
        
        BlockEnhancedBricks = new BlockEnhancedBricks(Material.rock);
        addBlock(BlockEnhancedBricks, "Enhanced Brick", "BlockEnhancedBrick");
        
        BlockMultiPanel = new BlockMultiPanel(Material.rock);
        addBlockNoRegistery(BlockMultiPanel, "Multi-Panel", "BlockMultiPanel");
        GameRegistry.registerBlock(BlockMultiPanel, ItemBlockMultiPanel.class, "BlockMultiPanel");
        
        BlockBasicElemental = new BlockBasicElemental(Material.rock);
        addBlock(BlockBasicElemental, "BlockBasicElemental", "BlockBasicElemental");
        
        BlockStructureCube = new BlockStructureCube(Material.rock);
        BlockStructureCube.setResistance(50);
        
        BlockSpacialProvider = new BlockSpacialProvider(Material.rock);
        addBlock(BlockSpacialProvider, "Spacial Provider", "BlockSpacialProvider");
        
        BlockSmeltingCube = new BlockSmeltingCube(Material.rock);
        addBlock(BlockSmeltingCube, "Smelting Cube", "BlockSmeltingCube");
        
        BlockQuantaSender = new BlockQuantaSender(Material.rock);
        addBlock(BlockQuantaSender, "Quanta Sender", "BlockQuantaSender");
        
        BlockEssenceDiffuser = new BlockEssenceDiffuser(Material.rock);
        addBlock(BlockEssenceDiffuser, "Essence Diffuser", "BlockEssenceDiffuser");
        
        BlockCentrifuge = new BlockCentrifuge(Material.rock);
        addBlock(BlockCentrifuge, "Centrifuge", "BlockCentrifuge");
        
        BlockElementalRefinery = new BlockElementalRefinery(Material.rock);
        addBlock(BlockElementalRefinery, "Elemental Refinery", "BlockElementalRefinery");
        
        BlockMultiInterface = new BlockMultiInterface(Material.rock);
        // BlockMultiInterface.setResistance(Blocks.obsidian.blockResistance);
        
        BlockFlameAltar = new BlockFlameAltar(Material.rock);
        addBlock(BlockFlameAltar, "Flame Altar", "BlockFlameAltar");
        
        BlockFlameConduit = new BlockFlameConduit(Material.rock);
        addBlock(BlockFlameConduit, "Flame Conduit", "BlockFlameConduit");
        
        BlockGlassPipe = new BlockGlassPipe(Material.rock);
        addBlock(BlockGlassPipe, "Glass Pipe", "BlockGlassPipe");
    }
    
    public static Block addBlock(Block block, String ign, String unlocalizedName) {
        
        block.setBlockName(unlocalizedName);
        block.setBlockTextureName(unlocalizedName);
        block.setHarvestLevel("pickaxe", 1);
        GameRegistry.registerBlock(block, GUItemBlock.class, block.getUnlocalizedName());
        LanguageRegistry.addName(block, ign);
        return block;
    }
    
    public static Block addBlockNoRegistery(Block block, String ign, String unlocalizedName) {
        
        block.setBlockName(unlocalizedName);
        block.setBlockTextureName(unlocalizedName);
        block.setHarvestLevel("pickaxe", 1);
        LanguageRegistry.addName(block, ign);
        return block;
    }
}

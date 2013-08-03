package GU;

import net.minecraft.block.material.Material;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import GU.info.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import GU.blocks.*;

public class BlockRegistry {

    public static BlockBase BlockTestBlock;

    private static int id = 500;

    public static void init(Configuration config) {

        BlockTestBlock = new BlockTestBlock(config.getBlock("BlockTestBlock", BlockRegistry.getNextBaseID()).getInt(), Material.rock);
        BlockTestBlock.setUnlocalizedName(Reference.UNIQUE_ID + "BlockTestBlock");
        GameRegistry.registerBlock(BlockTestBlock, GUItemBlock.class, Reference.UNIQUE_ID + "BlockTestBlock");
        LanguageRegistry.addName(BlockTestBlock, "Test Block");
        MinecraftForge.setBlockHarvestLevel(BlockTestBlock, "pickaxe", 1);
    }

    public static int getNextBaseID() {        

        return id++;
    }
}

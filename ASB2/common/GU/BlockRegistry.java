package GU;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import GU.blocks.BlockMetadata;
import GU.info.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import GU.blocks.*;

public class BlockRegistry {
    
    private static final Map<String, Block> customBlockMap = new HashMap<String, Block>();
    
    public static final BlockMetadata METADATA_ORE = new BlockMetadata(Material.rock) {
        
        public void postInit() {
            
            wrappers.put(0, new MetadataWrapper(new String[] { "BlockElectisStone" }).addDrop(ItemRegistry.ELECTIS_CRYSTAL).setDisplayName("Electis Stone"));
            wrappers.put(1, new MetadataWrapper(new String[] { "BlockGarnetOre" }).addDrop(new ItemStack(this, 4, 1)).setDisplayName("Garnet Infused Stone"));
        }
    };
    
    static {
        
        customBlockMap.put(Reference.MOD_ID.concat(":BlockMetadataOre"), METADATA_ORE.setBlockName("MetadataOre"));
    }
    
    public static void init(Configuration config) {
        
        for (Entry<String, Block> entry : customBlockMap.entrySet()) {
            
            if (entry.getValue() instanceof BlockBase) {
                
                ((BlockBase) entry.getValue()).registerBlock(entry.getKey());
            }
            else {
                
                GameRegistry.registerBlock(entry.getValue(), GUItemBlock.class, entry.getKey());
            }
        }
    }
}

package GU;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import GU.blocks.BlockBase;
import GU.blocks.BlockMetadata;
import GU.blocks.containers.BlockStructureCube.BlockStructureCube;
import GU.info.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockRegistry {
    
    private static final Map<String, Block> customBlockMap = new HashMap<String, Block>();
    
    public static final BlockMetadata METADATA_ORE = new BlockMetadata(Material.rock) {
        
        public void postInit() {
            
            wrappers.put(0, new MetadataWrapper(new String[] { "BlockElectisStone" }).addDrop(ItemRegistry.ELECTIS_CRYSTAL_SHARD).setDisplayName("Electis Stone"));
            wrappers.put(1, new MetadataWrapper(new String[] { "BlockGarnetOre" }).addDrop(new ItemStack(this, 4, 1)).setDisplayName("Garnet Infused Stone"));
        }
    };
    
    public static final BlockStructureCube STRUCTURE_CUBE = new BlockStructureCube(Material.rock) {
        
        public void postInit() {
            
            wrappers.put(0, new MetadataWrapper(new String[] { "BlockStructureCube0" }).addDrop(new ItemStack(STRUCTURE_CUBE, 1, 0)).setDisplayName("Structure Cube 0"));
            wrappers.put(1, new MetadataWrapper(new String[] { "BlockStructureCube1" }).addDrop(new ItemStack(STRUCTURE_CUBE, 1, 1)).setDisplayName("Structure Cube 1"));
            wrappers.put(2, new MetadataWrapper(new String[] { "BlockStructureCube2" }).addDrop(new ItemStack(STRUCTURE_CUBE, 1, 2)).setDisplayName("Structure Cube 2"));
            wrappers.put(3, new MetadataWrapper(new String[] { "BlockStructureCube3" }).addDrop(new ItemStack(STRUCTURE_CUBE, 1, 3)).setDisplayName("Structure Cube 3"));
            wrappers.put(4, new MetadataWrapper(new String[] { "BlockStructureCube4" }).addDrop(new ItemStack(STRUCTURE_CUBE, 1, 4)).setDisplayName("Structure Cube 4"));
            wrappers.put(5, new MetadataWrapper(new String[] { "BlockStructureCube5" }).addDrop(new ItemStack(STRUCTURE_CUBE, 1, 5)).setDisplayName("Structure Cube 5"));
            wrappers.put(6, new MetadataWrapper(new String[] { "BlockStructureCube6" }).addDrop(new ItemStack(STRUCTURE_CUBE, 1, 6)).setDisplayName("Structure Cube 6"));
        }
    };
    static {
        
        customBlockMap.put(Reference.MOD_ID.concat(":BlockMetadataOre"), METADATA_ORE.setBlockName("MetadataOre"));
        customBlockMap.put(Reference.MOD_ID.concat(":BlockStructureCube"), STRUCTURE_CUBE.setBlockName("StructureCube"));
    }
    
    public static void init(Configuration config) {
        
        for (Entry<String, Block> entry : customBlockMap.entrySet()) {
            
            if (entry.getValue() instanceof BlockBase) {
                
                ((BlockBase) entry.getValue()).registerBlock(entry.getKey());
                ((BlockBase) entry.getValue()).postInit();
            }
            else {
                
                GameRegistry.registerBlock(entry.getValue(), GUItemBlock.class, entry.getKey());
            }
        }
    }
}

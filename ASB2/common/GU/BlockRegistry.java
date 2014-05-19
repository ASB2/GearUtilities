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
import GU.blocks.BlockStructureCube;
import GU.blocks.containers.BlockCreativeMetadata.BlockCreativeMetadata;
import GU.blocks.containers.BlockElectisCrystal.BlockElectisCrystal;
import GU.blocks.containers.BlockMultiInterface.BlockMultiInterface;
import GU.blocks.containers.BlockSpacialProvider.BlockSpacialProvider;
import GU.info.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import GU.blocks.containers.BlockSpacialProvider.*;

public class BlockRegistry {
    
    private static final Map<String, Block> customBlockMap = new HashMap<String, Block>();
    
    public static final BlockMetadata METADATA_ORE = new BlockMetadata(Material.rock) {
        
        public void postInit() {
            
            this.addWrapper(new MetadataWrapper(new String[] { "BlockElectisCatchingStone" }).addDrop(new ItemStack(this, 1, 0)).setDisplayName("Electis Catching Stone"));
            this.addWrapper(new MetadataWrapper(new String[] { "BlockElectisInfusedStone" }).addDrop(ItemRegistry.ELECTIS_CRYSTAL_SHARD).setDisplayName("Electis Infused Stone"));
            this.addWrapper(new MetadataWrapper(new String[] { "BlockGarnetOre" }).addDrop(ItemRegistry.GARNET).setDisplayName("Garnet Infused Stone"));
        }
    };
    
    public static final BlockStructureCube STRUCTURE_CUBE = new BlockStructureCube(Material.rock) {
        
        public void postInit() {
            
            this.addWrapper(new MetadataWrapper(new String[] { "BlockStructureCube0" }).addDrop(new ItemStack(this, 1, 0)).setDisplayName("Structure Cube 0"));
            this.addWrapper(new MetadataWrapper(new String[] { "BlockStructureCube1" }).addDrop(new ItemStack(this, 1, 1)).setDisplayName("Structure Cube 1"));
            this.addWrapper(new MetadataWrapper(new String[] { "BlockStructureCube2" }).addDrop(new ItemStack(this, 1, 2)).setDisplayName("Structure Cube 2"));
            this.addWrapper(new MetadataWrapper(new String[] { "BlockStructureCube3" }).addDrop(new ItemStack(this, 1, 3)).setDisplayName("Structure Cube 3"));
            this.addWrapper(new MetadataWrapper(new String[] { "BlockStructureCube4" }).addDrop(new ItemStack(this, 1, 4)).setDisplayName("Structure Cube 4"));
            this.addWrapper(new MetadataWrapper(new String[] { "BlockStructureCube5" }).addDrop(new ItemStack(this, 1, 5)).setDisplayName("Structure Cube 5"));
            this.addWrapper(new MetadataWrapper(new String[] { "BlockStructureCube6" }).addDrop(new ItemStack(this, 1, 6)).setDisplayName("Structure Cube 6"));
        }
    };
    
    public static final BlockElectisCrystal ELECTIS_CRYSTAL = new BlockElectisCrystal(Material.rock);
    public static final BlockCreativeMetadata CREATIVE_METADATA = new BlockCreativeMetadata(Material.rock);
    
    public static final BlockSpacialProvider SPACIAL_PROVIDER = new BlockSpacialProvider(Material.rock) {
        
        public void postInit() {
            
            this.addWrapper(new StandardSpacialProviderWrapper().addDrop(new ItemStack(this, 1, 0)));
            this.addWrapper(new ChestSpacialProviderWrapper().addDrop(new ItemStack(this, 1, 1)));
            this.addWrapper(new FurnaceSpacialProviderWrapper().addDrop(new ItemStack(this, 1, 2)));
            this.addWrapper(new TankSpacialProviderWrapper().addDrop(new ItemStack(this, 1, 3)));
        }
    };
    
    public static final BlockMultiInterface MULTI_INTERFACE = new BlockMultiInterface(Material.rock) {
        
        public void postInit() {
            
            // wrappers.put(0, new MetadataWrapper(new String[] {
            // "BlockStructureCube0" }).addDrop(new ItemStack(this, 1,
            // 0)).setDisplayName("Structure Cube 0"));
            // wrappers.put(1, new MetadataWrapper(new String[] {
            // "BlockStructureCube1" }).addDrop(new ItemStack(this, 1,
            // 1)).setDisplayName("Structure Cube 1"));
            // wrappers.put(2, new MetadataWrapper(new String[] {
            // "BlockStructureCube2" }).addDrop(new ItemStack(this, 1,
            // 2)).setDisplayName("Structure Cube 2"));
        }
    };
    static {
        
        customBlockMap.put(Reference.MOD_ID.concat(":BlockMetadataOre"), METADATA_ORE.setBlockName("MetadataOre"));
        customBlockMap.put(Reference.MOD_ID.concat(":BlockStructureCube"), STRUCTURE_CUBE.setBlockName("StructureCube"));
        customBlockMap.put(Reference.MOD_ID.concat(":BlockElectisCrystal"), ELECTIS_CRYSTAL.setBlockName("Electis Crystal"));
        customBlockMap.put(Reference.MOD_ID.concat(":BlockCreativeMetadata"), CREATIVE_METADATA);
        customBlockMap.put(Reference.MOD_ID.concat(":BlockSpacialProvider"), SPACIAL_PROVIDER);
        customBlockMap.put(Reference.MOD_ID.concat(":BlockMultiInterface"), MULTI_INTERFACE);
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

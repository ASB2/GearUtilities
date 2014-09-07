package GU;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import GU.api.color.VanillaColor;
import GU.blocks.BlockBase;
import GU.blocks.BlockMetadata;
import GU.blocks.BlockStructureCube;
import GU.blocks.containers.BlockConduit.BlockConduit;
import GU.blocks.containers.BlockCreativeMetadata.BlockCreativeMetadata;
import GU.blocks.containers.BlockCreativeMetadata.CreativeMetadataWrapper;
import GU.blocks.containers.BlockDrill.BlockDrill;
import GU.blocks.containers.BlockElectisPolyhedron.BlockElectisPolyhedron;
import GU.blocks.containers.BlockFluidElectisPolyhedron.BlockFluidElectisPolyhedron;
import GU.blocks.containers.BlockItemElectisPolyhedron.BlockItemElectisPolyhedron;
import GU.blocks.containers.BlockMultiDirectionalConduit.BlockMultiDirectionalConduit;
import GU.blocks.containers.BlockMultiInterface.BlockMultiInterface;
import GU.blocks.containers.BlockMultiInterface.MultiInterfaceWrapper;
import GU.blocks.containers.BlockMultiInterface.RedstoneMultiInterfaceWrapper;
import GU.blocks.containers.BlockMultiPart.BlockMultiBlockPart;
import GU.blocks.containers.BlockMultiPart.BlockMultiBlockPartAir;
import GU.blocks.containers.BlockMultiPart.BlockMultiBlockPartGlass;
import GU.blocks.containers.BlockMultiPart.BlockMultiPartRender;
import GU.blocks.containers.BlockPhotonSender.BlockPhotonSender;
import GU.blocks.containers.BlockSpacialProvider.BlockSpatialProvider;
import GU.blocks.containers.BlockTeleportAltar.BlockTeleportAltar;
import GU.info.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockRegistry {
    
    public static final Map<String, Block> customBlockMap = new HashMap<String, Block>();
    
    public static final BlockMetadata METADATA_ORE = new BlockMetadata(Material.rock) {
        
        public void postInit() {
            
            this.addWrapper(new BlockMetadataWrapper(new String[] { "BlockElectisCatchingStone" }).addDrop(new ItemStack(this, 1, 0)).setDisplayName("Electis Catching Stone"));
            this.addWrapper(new BlockMetadataWrapper(new String[] { "BlockElectisInfusedStone" }).addDrop(ItemRegistry.ELECTIS_CRYSTAL_SHARD).setDisplayName("Electis Infused Stone"));
            this.addWrapper(new BlockMetadataWrapper(new String[] { "BlockGarnetOre" }).addDrop(ItemRegistry.GARNET).setDisplayName("Garnet Infused Stone"));
        }
    };
    
    public static final List<BlockStructureCube> STRUCTURE_CUBES = new ArrayList<BlockStructureCube>();
    
    public static final BlockCreativeMetadata CREATIVE_METADATA = new BlockCreativeMetadata(Material.rock) {
        
        public void postInit() {
            
            this.addWrapper(new CreativeMetadataWrapper(new String[] { "BlockCreativeMetadata0" }).addDrop(new ItemStack(this, 1, 0)).setDisplayName("Creative Energy"));
            this.addWrapper(new CreativeMetadataWrapper(new String[] { "BlockCreativeMetadata1" }).addDrop(new ItemStack(this, 1, 1)).setDisplayName("Creative Fluid"));
            this.addWrapper(new CreativeMetadataWrapper(new String[] { "BlockCreativeMetadata2" }).addDrop(new ItemStack(this, 1, 2)).setDisplayName("Creative Item"));
        }
    };
    
    public static final BlockSpatialProvider SPATIAL_PROVIDER = new BlockSpatialProvider(Material.rock) {
        
        public void postInit() {
            
            this.addWrapper(new BlockMetadataWrapper(new String[] { "BlockSpatialProvider" }).addDrop(new ItemStack(this, 1, 0)).setDisplayName("Standard Spatial Provider"));
            this.addWrapper(new BlockMetadataWrapper(new String[] { "BlockSpatialProvider" }).addDrop(new ItemStack(this, 1, 1)).setDisplayName("Chest Spatial Provider"));
            this.addWrapper(new BlockMetadataWrapper(new String[] { "BlockSpatialProvider" }).addDrop(new ItemStack(this, 1, 2)).setDisplayName("Furnace Spatial Provider"));
            this.addWrapper(new BlockMetadataWrapper(new String[] { "BlockSpatialProvider" }).addDrop(new ItemStack(this, 1, 3)).setDisplayName("Tank Spatial Provider"));
            this.addWrapper(new BlockMetadataWrapper(new String[] { "BlockSpatialProvider" }).addDrop(new ItemStack(this, 1, 4)).setDisplayName("Flame Spatial Provider"));
            this.addWrapper(new BlockMetadataWrapper(new String[] { "BlockSpatialProvider" }).addDrop(new ItemStack(this, 1, 5)).setDisplayName("Triturator Spatial Provider"));
        }
    };
    
    public static final BlockMultiInterface MULTI_INTERFACE = new BlockMultiInterface(Material.rock) {
        
        public void postInit() {
            
            this.addWrapper(new MultiInterfaceWrapper(new String[] { "BlockMultiInterface" }).addDrop(new ItemStack(this, 1, 0)).setDisplayName("Item Interface"));
            this.addWrapper(new MultiInterfaceWrapper(new String[] { "BlockMultiInterface" }).addDrop(new ItemStack(this, 1, 1)).setDisplayName("Fluid Interface"));
            this.addWrapper(new MultiInterfaceWrapper(new String[] { "BlockMultiInterface" }).addDrop(new ItemStack(this, 1, 2)).setDisplayName("Power Interface"));
            this.addWrapper(new MultiInterfaceWrapper(new String[] { "BlockMultiInterface" }).addDrop(new ItemStack(this, 1, 3)).setDisplayName("Data Interface"));
            this.addWrapper(new RedstoneMultiInterfaceWrapper(new String[] { "BlockMultiInterface" }).addDrop(new ItemStack(this, 1, 4)).setDisplayName("Redstone Interface"));
            this.addWrapper(new MultiInterfaceWrapper(new String[] { "BlockMultiInterface" }).addDrop(new ItemStack(this, 1, 5)).setDisplayName("Gui Interface"));
        }
    };
    
    public static final BlockMultiBlockPart MULTI_BLOCK_PART = new BlockMultiBlockPart(Material.rock) {
        
        public void postInit() {
            
            this.addWrapper(new BlockMetadataWrapper(new String[] { "BlockMultiBlockInner" }).setDisplayName("Multi Block Inner"));
            this.addWrapper(new BlockMetadataWrapper(new String[] { "BlockMultiBlockEdge" }).setDisplayName("Multi Block Edge"));
            this.addWrapper(new BlockMetadataWrapper(new String[] { "BlockMultiBlockCorner" }).setDisplayName("Multi Block Corner"));
        }
    };
    
    public static final BlockMultiBlockPartGlass MULTI_BLOCK_PART_GLASS = new BlockMultiBlockPartGlass(Material.rock) {
        
        public void postInit() {
            
            this.addWrapper(new BlockMetadataWrapper(new String[] { "BlockMultiBlockPartGlass" }).setDisplayName("Multi Block Glass"));
        }
    };
    
    public static final BlockMultiBlockPartAir MULTI_BLOCK_PART_AIR = new BlockMultiBlockPartAir(Material.air);
    
    public static final BlockMultiPartRender MULTI_BLOCK_PART_RENDER = new BlockMultiPartRender(Material.rock);
    
    public static final BlockPhotonSender PHOTON_SENDER = new BlockPhotonSender(Material.rock);
    
    public static final BlockElectisPolyhedron ELECTIS_POLYHEDRON = new BlockElectisPolyhedron(Material.rock);
    
    public static final BlockItemElectisPolyhedron ITEM_ELECTIS_POLYHEDRON = new BlockItemElectisPolyhedron(Material.rock);
    
    public static final BlockFluidElectisPolyhedron FLUID_ELECTIS_POLYHEDRON = new BlockFluidElectisPolyhedron(Material.rock);
    
    public static final BlockConduit CONDUIT = new BlockConduit(Material.rock);
    
    public static final BlockMultiDirectionalConduit MULTI_DIRECTIONAL_CONDUIT = new BlockMultiDirectionalConduit(Material.rock);
    
    public static final BlockTeleportAltar TELEPORT_ALTAR = new BlockTeleportAltar(Material.rock);
    
    public static final BlockDrill DRILL = new BlockDrill(Material.rock);
    
    static {
        
        customBlockMap.put(Reference.MOD_ID.concat(":BlockMetadataOre"), METADATA_ORE.setBlockName("MetadataOre"));
        
        for (VanillaColor color : VanillaColor.values()) {
            
            if (color != VanillaColor.NONE) {
                
                BlockStructureCube cube = new BlockStructureCube(Material.rock, color);
                STRUCTURE_CUBES.add(cube);
                customBlockMap.put(Reference.MOD_ID.concat(":BlockStructureCubeColor" + color.ordinal()), cube.setBlockName("StructureCubeColor" + color.ordinal()));
            }
        }
        
        customBlockMap.put(Reference.MOD_ID.concat(":BlockCreativeMetadata"), CREATIVE_METADATA);
        customBlockMap.put(Reference.MOD_ID.concat(":BlockSpatialProvider"), SPATIAL_PROVIDER);
        customBlockMap.put(Reference.MOD_ID.concat(":BlockMultiInterface"), MULTI_INTERFACE);
        customBlockMap.put(Reference.MOD_ID.concat(":BlockMultiBlockPart"), MULTI_BLOCK_PART);
        customBlockMap.put(Reference.MOD_ID.concat(":BlockMultiBlockPartGlass"), MULTI_BLOCK_PART_GLASS);
        customBlockMap.put(Reference.MOD_ID.concat(":BlockMultiBlockPartAir"), MULTI_BLOCK_PART_AIR.setBlockName("Multi Block Air"));
        customBlockMap.put(Reference.MOD_ID.concat(":BlockMultiPartRender"), MULTI_BLOCK_PART_RENDER.setBlockName("Multi Block Render"));
        customBlockMap.put(Reference.MOD_ID.concat(":BlockPhotonSender"), PHOTON_SENDER.setBlockName("Photon Sender"));
        customBlockMap.put(Reference.MOD_ID.concat(":BlockElectisPolyhedron"), ELECTIS_POLYHEDRON.setBlockName("Electis Polyhedron"));
        customBlockMap.put(Reference.MOD_ID.concat(":BlockItemElectisPolyhedron"), ITEM_ELECTIS_POLYHEDRON.setBlockName("Item Electis Polyhedron"));
        customBlockMap.put(Reference.MOD_ID.concat(":BlockFluidElectisPolyhedron"), FLUID_ELECTIS_POLYHEDRON.setBlockName("Fluid Electis Polyhedron"));
        customBlockMap.put(Reference.MOD_ID.concat(":BlockConduit"), CONDUIT.setBlockName("Conduit"));
        customBlockMap.put(Reference.MOD_ID.concat(":BlockMultiDirectionalConduit"), MULTI_DIRECTIONAL_CONDUIT.setBlockName("Multi Directional Conduit"));
        customBlockMap.put(Reference.MOD_ID.concat(":BlockTeleportAltar"), TELEPORT_ALTAR.setBlockName("Teleport Altar"));
        customBlockMap.put(Reference.MOD_ID.concat(":BlockDrill"), DRILL.setBlockName("Drill"));
    }
    
    public static void init(Configuration config) {
        
        for (Entry<String, Block> entry : customBlockMap.entrySet()) {
            
            if (entry.getValue() instanceof BlockBase) {
                
                ((BlockBase) entry.getValue()).registerBlock(entry.getKey());
                ((BlockBase) entry.getValue()).postInit();
            } else {
                
                GameRegistry.registerBlock(entry.getValue(), GUItemBlock.class, entry.getKey());
            }
        }
    }
}

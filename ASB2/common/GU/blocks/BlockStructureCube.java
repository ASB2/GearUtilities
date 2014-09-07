package GU.blocks;

import java.util.List;
import java.util.Map.Entry;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.oredict.OreDictionary;
import GU.BlockRegistry;
import GU.GearUtilities;
import GU.api.color.VanillaColor;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlockMarker;
import GU.render.BlockSimpleRenderer.INoiseBlockRender;
import UC.color.Color4i;

public class BlockStructureCube extends BlockMetadata implements IMultiBlockMarker, INoiseBlockRender {
    
    VanillaColor color;
    
    public BlockStructureCube(Material material, int ordinal) {
        this(material, VanillaColor.values()[ordinal]);
        
    }
    
    public BlockStructureCube(Material material, VanillaColor ordinal) {
        super(material);
        this.color = ordinal;
        this.setCreativeTab(GearUtilities.tabGUStructureCubes);
    }
    
    public void postInit() {
        
        this.addWrapper(new BlockMetadataWrapper(new String[] { "BlockStructureCube0" }).addDrop(new ItemStack(this, 1, 0)).setDisplayName("Structure Cube 0"));
        this.addWrapper(new BlockMetadataWrapper(new String[] { "BlockStructureCube1" }).addDrop(new ItemStack(this, 1, 1)).setDisplayName("Structure Cube 1"));
        this.addWrapper(new BlockMetadataWrapper(new String[] { "BlockStructureCube2" }).addDrop(new ItemStack(this, 1, 2)).setDisplayName("Structure Cube 2"));
        this.addWrapper(new BlockMetadataWrapper(new String[] { "BlockStructureCube3" }).addDrop(new ItemStack(this, 1, 3)).setDisplayName("Structure Cube 3"));
        this.addWrapper(new BlockMetadataWrapper(new String[] { "BlockStructureCube4" }) {
            
            public int getLightOpacity(net.minecraft.world.IBlockAccess world, int x, int y, int z) {
                return 2000;
            };
        }.addDrop(new ItemStack(this, 1, 4)).setDisplayName("Structure Cube 4"));
        this.addWrapper(new BlockMetadataWrapper(new String[] { "BlockStructureCube5" }).addDrop(new ItemStack(this, 1, 5)).setDisplayName("Structure Cube 5"));
        this.addWrapper(new BlockMetadataWrapper(new String[] { "BlockStructureCube6" }).addDrop(new ItemStack(this, 1, 6)).setDisplayName("Structure Cube 6"));
        
        for (Entry<Integer, BlockMetadataWrapper> meta : wrappers.entrySet()) {
            
            OreDictionary.registerOre(GU.info.Reference.STRUCTURE_CUBE_OREDIC, new ItemStack(this, 1, meta.getKey()));
        }
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List par3List, boolean par4) {
        super.addInformation(stack, player, par3List, par4);
        par3List.add("Color: " + color.name());
        par3List.add("To see crafting recipes, ");
        par3List.add("look at the white structure cube");
         }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float xHit, float yHit, float zHit) {
        
        VanillaColor color = VanillaColor.getItemColorValue(player.inventory.getCurrentItem());
        
        if (color != VanillaColor.NONE) {
            
            world.setBlock(x, y, z, BlockRegistry.STRUCTURE_CUBES.get(color.ordinal()), world.getBlockMetadata(x, y, z), 3);
        }
        return false;
    }
    
    @Override
    public boolean isValid(World world, int x, int y, int z) {
        
        return world.isBlockIndirectlyGettingPowered(x, y, z);
    }
    
    @Override
    public boolean isOpaqueCube() {
        
        return false;
    }
    
    @Override
    public Color4i getColor(int metadata) {
        
        return color.getRGBValue();
    }
    
    @Override
    public Color4i getColor(IBlockAccess world, int x, int y, int z, ForgeDirection direction) {
        
        return getColor(world.getBlockMetadata(x, y, z));
    }
    
    @Override
    public boolean canRender(int metadata) {
        
        return true;
    }
    
    @Override
    public boolean canRender(IBlockAccess world, int x, int y, int z, ForgeDirection direction) {
        
        return true;
    }
}

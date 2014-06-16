package GU.blocks;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import GU.api.color.VanillaColor;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlockMarker;
import GU.render.BlockSimpleRenderer.INoiseBlockRender;
import UC.color.Color4i;
import GU.*;

public class BlockStructureCube extends BlockMetadata implements IMultiBlockMarker, INoiseBlockRender {
    
    VanillaColor color;
    
    public BlockStructureCube(Material material, int ordinal) {
        super(material);
        this.color = VanillaColor.values()[ordinal];
        this.setCreativeTab(GearUtilities.tabGUStructureCubes);
    }
    
    public void postInit() {
        
        this.addWrapper(new MetadataWrapper(new String[] { "BlockStructureCube0" }).addDrop(new ItemStack(this, 1, 0)).setDisplayName("Structure Cube 0"));
        this.addWrapper(new MetadataWrapper(new String[] { "BlockStructureCube1" }).addDrop(new ItemStack(this, 1, 1)).setDisplayName("Structure Cube 1"));
        this.addWrapper(new MetadataWrapper(new String[] { "BlockStructureCube2" }).addDrop(new ItemStack(this, 1, 2)).setDisplayName("Structure Cube 2"));
        this.addWrapper(new MetadataWrapper(new String[] { "BlockStructureCube3" }).addDrop(new ItemStack(this, 1, 3)).setDisplayName("Structure Cube 3"));
        this.addWrapper(new MetadataWrapper(new String[] { "BlockStructureCube4" }) {
            
            public int getLightOpacity(net.minecraft.world.IBlockAccess world, int x, int y, int z) {
                return 2000;
            };
        }.addDrop(new ItemStack(this, 1, 4)).setDisplayName("Structure Cube 4"));
        this.addWrapper(new MetadataWrapper(new String[] { "BlockStructureCube5" }).addDrop(new ItemStack(this, 1, 5)).setDisplayName("Structure Cube 5"));
        this.addWrapper(new MetadataWrapper(new String[] { "BlockStructureCube6" }).addDrop(new ItemStack(this, 1, 6)).setDisplayName("Structure Cube 6"));
    }
    
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List par3List, boolean par4) {
           super.addInformation(stack, player, par3List, par4);
           par3List.add("Color: " + color.name());
           
    }
    
    @Override
    public boolean isValid(World world, int x, int y, int z) {
        
        return true;
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
        
        return metadata != 4;
    }
    
    @Override
    public boolean canRender(IBlockAccess world, int x, int y, int z, ForgeDirection direction) {
        
        return world.getBlockMetadata(x, y, z) != 4;
    }
}

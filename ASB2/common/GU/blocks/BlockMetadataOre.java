package GU.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import GU.ItemRegistry;
import GU.render.BlockSimpleRenderer.INoiseBlockRender;
import UC.color.Color4i;

public class BlockMetadataOre extends BlockMetadata implements INoiseBlockRender {
    
    public BlockMetadataOre(Material material) {
        super(material);
        // TODO Auto-generated constructor stub
    }
    
    public void postInit() {
        
        this.addWrapper(new BlockMetadataWrapper(new String[] { "BlockElectisCatchingStone" }).addDrop(new ItemStack(this, 1, 0)).setDisplayName("Electis Catching Stone"));
        this.addWrapper(new BlockMetadataWrapper(new String[] { "BlockElectisInfusedStone" }).addDrop(ItemRegistry.ELECTIS_CRYSTAL_SHARD).setDisplayName("Electis Infused Stone"));
        this.addWrapper(new BlockMetadataWrapper(new String[] { "BlockGarnetOre" }).addDrop(ItemRegistry.GARNET).setDisplayName("Garnet Infused Stone"));
    }
    
    @Override
    public Color4i getNoiseColor(int metadata) {
        
        return metadata == 1 ? Color4i.WHITE : metadata == 2 ? Color4i.GOLD : null;
    }
    
    @Override
    public Color4i getNoiseColor(IBlockAccess world, int x, int y, int z, ForgeDirection direction) {
        
        return getNoiseColor(world.getBlockMetadata(x, y, z));
    }
    
    @Override
    public boolean canRenderNoise(int metadata) {
        
        return metadata == 1 || metadata == 2;
    }
    
    @Override
    public boolean canRenderNoise(IBlockAccess world, int x, int y, int z, ForgeDirection direction) {
        
        return canRenderNoise(world.getBlockMetadata(x, y, z));
    }
    
}

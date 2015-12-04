package GU.blocks.containers.BlockMultiPart;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import GU.api.color.AbstractColorable.IColorableTile;
import GU.api.color.VanillaColor;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlock;
import GU.blocks.containers.BlockMultiMetadataContainerBase;
import GU.multiblock.MultiBlockBase;
import GU.render.BlockSimpleRenderer.INoiseBlockRender;
import GU.render.noise.NoiseManager;
import UC.color.Color4i;

public class BlockMultiBlockPart extends BlockMultiMetadataContainerBase implements INoiseBlockRender {
    
    public BlockMultiBlockPart(Material material) {
        super(material);
        
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float xHit, float yHit, float zHit) {
        
        TileEntity tile = world.getTileEntity(x, y, z);
        
        if (tile != null && tile instanceof TileMultiPart) {
            
            if (tile instanceof IColorableTile && player.getHeldItem() != null && VanillaColor.isItemDye(player.getHeldItem())) {
                
                if (!player.isSneaking()) {
                    
                    return ((IColorableTile) tile).setColor(VanillaColor.getItemColorValue(player.getHeldItem()).getRGBValue(), ForgeDirection.getOrientation(side));
                } else {
                    
                    for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
                        
                        ((IColorableTile) tile).setColor(VanillaColor.getItemColorValue(player.getHeldItem()).getRGBValue(), direction);
                    }
                    return true;
                }
            } else {
                
                List<IMultiBlock> multiList = ((TileMultiPart) tile).getMultiBlocks();
                
                if (multiList.size() == 1) {
                    
                    IMultiBlock block = multiList.get(0);
                    
                    if (block instanceof MultiBlockBase) {
                        
                        ((MultiBlockBase) block).onBlockActivated(world, x, y, z, player, side, xHit, yHit, zHit);
                    }
                }
            }
        }
        return false;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List par3List, boolean par4) {
        super.addInformation(stack, player, par3List, par4);
        par3List.add("Colorable");
    }
    
    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        
        return new TileMultiPart();
    }
    
    @Override
    public Color4i getNoiseColor(int metadata) {
        
        // return Minecraft.getSystemTime() % 2 == 0 ?
        // NoiseManager.instance.ITERATED_COLOR :
        // NoiseManager.instance.ITERATED_COLOR_INVERTED;
        
        return NoiseManager.instance.ITERATED_COLOR;
    }
    
    @Override
    public Color4i getNoiseColor(IBlockAccess world, int x, int y, int z, ForgeDirection direction) {
        
        return ((TileMultiPart) world.getTileEntity(x, y, z)).getColor(direction);
    }
    
    @Override
    public boolean canRenderNoise(int metadata) {
        // TODO Auto-generated method stub
        return true;
    }
    
    @Override
    public boolean canRenderNoise(IBlockAccess world, int x, int y, int z, ForgeDirection direction) {
        // TODO Auto-generated method stub
        return true;
    }
}

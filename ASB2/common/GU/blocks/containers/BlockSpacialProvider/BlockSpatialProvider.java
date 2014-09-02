package GU.blocks.containers.BlockSpacialProvider;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import ASB2.utils.UtilEntity;
import GU.api.color.AbstractColorable.IColorableBlock;
import GU.api.color.VanillaColor;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlock;
import GU.blocks.containers.BlockMultiMetadataContainerBase;
import GU.blocks.containers.TileMultiBase;
import GU.multiblock.EnumMultiBlockType;
import GU.multiblock.MultiBlockBase;
import GU.render.BlockSimpleRenderer.INoiseBlockRender;
import UC.color.Color4i;
import UC.math.vector.Vector3i;

public class BlockSpatialProvider extends BlockMultiMetadataContainerBase implements INoiseBlockRender, IColorableBlock {
    
    public BlockSpatialProvider(Material material) {
        super(material);
        
        this.registerTile(TileSpatialProvider.class);
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float xHit, float yHit, float zHit) {
        
        TileEntity tile = world.getTileEntity(x, y, z);
        
        if (tile != null && tile instanceof TileMultiBase) {
            
            List<IMultiBlock> multiList = ((TileMultiBase) tile).getMultiBlocks();
            
            if (multiList.isEmpty()) {
                
                if (player.getHeldItem() == null) {
                    
                    int metadata = world.getBlockMetadata(x, y, z);
                    
                    if (metadata < EnumMultiBlockType.values().length) {
                        
                        EnumMultiBlockType type = EnumMultiBlockType.values()[metadata];
                        UtilEntity.sendChatToPlayer(player, type.createMultiBlock(world, new Vector3i(x, y, z)));
                    }
                    return true;
                }
            } else {
                for (int index = 0; index < multiList.size(); index++) {
                    
                    IMultiBlock multi = multiList.get(index);
                    
                    if (multi instanceof MultiBlockBase) {
                        
                        ((MultiBlockBase) multi).onBlockActivated(world, x, y, z, player, side, xHit, yHit, zHit);
                    }
                }
            }
        }
        return false;
    }
    
    @Override
    public Color4i getColor(int metadata) {
        
        switch (metadata) {
        
            case 0:
                return Color4i.WHITE;
            case 1:
                return Color4i.GREEN;
            case 2:
                return Color4i.RED.brighter();
            case 3:
                return Color4i.BLUE.brighter();
            case 4:
                return VanillaColor.MAGENTA.getRGBValue().darker(150);
            case 5:
                return Color4i.RUST;
        }
        return null;
    }
    
    @Override
    public Color4i getColor(IBlockAccess world, int x, int y, int z, ForgeDirection direction) {
        
        return getColor(world.getBlockMetadata(x, y, z));
    }
    
    @Override
    public Color4i getColor(World world, int x, int y, int z, ForgeDirection direction) {
        
        return this.getColor(world.getBlockMetadata(x, y, z));
    }
    
    @Override
    public boolean setColor(World world, int x, int y, int z, Color4i color, ForgeDirection direction) {
        
        return false;
    }
    
    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        
        return new TileSpatialProvider();
    }
    
    @Override
    public boolean canRender(int metadata) {
        // TODO Auto-generated method stub
        return true;
    }
    
    @Override
    public boolean canRender(IBlockAccess world, int x, int y, int z, ForgeDirection direction) {
        // TODO Auto-generated method stub
        return true;
    }
}

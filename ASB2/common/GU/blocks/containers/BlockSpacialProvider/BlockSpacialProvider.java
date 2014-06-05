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
import GU.api.multiblock.MultiBlockAbstract.IMultiBlock;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlockMarker;
import GU.blocks.containers.BlockMultiMetadataContainerBase;
import GU.blocks.containers.TileMultiBase;
import GU.multiblock.EnumMultiBlockType;
import GU.multiblock.MultiBlockBase;
import GU.render.BlockSimpleRenderer.INoiseBlockRender;
import UC.color.Color4i;
import UC.math.vector.Vector3i;

public class BlockSpacialProvider extends BlockMultiMetadataContainerBase implements INoiseBlockRender, IMultiBlockMarker, IColorableBlock {
    
    public BlockSpacialProvider(Material material) {
        super(material);
        
        this.registerTile(TileSpacialProvider.class);
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float xHit, float yHit, float zHit) {
        
        TileEntity tile = world.getTileEntity(x, y, z);
        
        if (tile != null && tile instanceof TileMultiBase) {
            
            List<IMultiBlock> multiList = ((TileMultiBase) tile).getMultiBlocks();
            
            if (multiList.isEmpty()) {
                
                if (player.getHeldItem() == null) {
                    
                    switch (world.getBlockMetadata(x, y, z)) {
                    
                        case 0: {
                            
                            UtilEntity.sendChatToPlayer(player, EnumMultiBlockType.STANDARD.createMultiBlock(world, new Vector3i(x, y, z)));
                            break;
                        }
                        
                        case 1: {
                            
                            UtilEntity.sendChatToPlayer(player, EnumMultiBlockType.CHEST.createMultiBlock(world, new Vector3i(x, y, z)));
                            break;
                        }
                        case 2: {
                            
                            UtilEntity.sendChatToPlayer(player, EnumMultiBlockType.FURNACE.createMultiBlock(world, new Vector3i(x, y, z)));
                            break;
                        }
                        case 3: {
                            
                            UtilEntity.sendChatToPlayer(player, EnumMultiBlockType.TANK.createMultiBlock(world, new Vector3i(x, y, z)));
                            break;
                        }
                    }
                    return true;
                }
            }
            else {
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
        }
        return null;
    }
    
    @Override
    public Color4i getColor(IBlockAccess world, int x, int y, int z, ForgeDirection direction) {
        
        return getColor(world.getBlockMetadata(x, y, z));
    }
    
    @Override
    public boolean isValid(World world, int x, int y, int z) {
        
        return true;
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
        
        return new TileSpacialProvider();
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

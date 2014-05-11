package GUOLD.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import GUOLD.render.BlockSimpleRenderer;

public class BlockEtherealStone extends BlockBase {
    
    public BlockEtherealStone(Material material) {
        super(material);
        useStandardRendering = false;
    }
    
    @Override
    public int getRenderType() {
        
        return BlockSimpleRenderer.renderID;
    }
    
    @Override
    public IIcon getIcon(int side, int metadata) {
        
        return Blocks.stone.getIcon(side, metadata);
    }
    
    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        
    }
    
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        
        if (!world.isBlockIndirectlyGettingPowered(x, y, z)) return null;
        
        return super.getCollisionBoundingBoxFromPool(world, x, y, z);
    }
    
    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        
        float movementFactor = .3F;
        entity.fallDistance = 0;
        
        if (entity instanceof EntityLivingBase) {
            
            entity.extinguish();
            
            if (((EntityLivingBase) entity).isSneaking()) {
                
                entity.motionY -= movementFactor;
            }
            else {
                
                entity.motionY += movementFactor;
            }
        }
        else {
            
            entity.motionY += movementFactor;
        }
    }
}
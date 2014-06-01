package GU.multiblock;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidContainerRegistry;
import ASB2.utils.UtilVector;
import GU.GearUtilities;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlockPart;
import GU.packets.abstractPacket.MutliBlockTankPacket;
import UC.color.Color4i;
import UC.math.vector.Vector3i;

public class MultiBlockTank extends MultiBlockFluidHandler {
    
    public MultiBlockTank(World world, Vector3i positionRelativeTo, Vector3i size, Vector3i updater) {
        super(world, positionRelativeTo, size, updater);
    }
    
    public MultiBlockTank(World world) {
        super(world);
    }
    
    @Override
    public Color4i getDefaultBlockColor() {
        
        return Color4i.BLUE;
    }
    
    @Override
    public void update(Object... objects) {
        
        if (isConstructing) {
            
            for (int x = 0; x <= size.getX(); x++) {
                
                for (int y = 0; y <= size.getY(); y++) {
                    
                    for (int z = 0; z <= size.getZ(); z++) {
                        
                        Vector3i vec = positionRelativeTo.subtract(x, y, z);
                        
                        if (x == 1 && y == 1 && z == 1) {
                            
                            if (!placeRenderBlock(vec)) {
                                
                                deconstruct();
                                return;
                            }
                        }
                        else if (((x == 0 || x == size.getX()) && (y == 0 || y == size.getY())) && (z == 0 || z == size.getZ())) {
                            
                            if (!placeCornerBlock(vec)) {
                                
                                deconstruct();
                                return;
                            }
                        }
                        else if (((x == 0 || x == size.getX()) && (y == 0 || y == size.getY())) || ((y == 0 || y == size.getY()) && (z == 0 || z == size.getZ())) || ((x == 0 || x == size.getX()) && (z == 0 || z == size.getZ()))) {
                            
                            if (!placeEdgeBlock(vec)) {
                                
                                deconstruct();
                                return;
                            }
                        }
                        else if (((x == 0 || x == size.getX()) && (y != 0 && y != size.getY())) && (z != 0 && z != size.getZ())) {
                            
                            if (!placeGlassBlock(vec)) {
                                
                                deconstruct();
                                return;
                            }
                        }
                        else if (((x != 0 && x != size.getX()) && (y == 0 || y == size.getY())) && (z != 0 && z != size.getZ())) {
                            
                            if (!placeGlassBlock(vec)) {
                                
                                deconstruct();
                                return;
                            }
                        }
                        else if (((x != 0 && x != size.getX()) && (y != 0 && y != size.getY())) && (z == 0 || z == size.getZ())) {
                            
                            if (!placeGlassBlock(vec)) {
                                
                                deconstruct();
                                return;
                            }
                        }
                        else if (!placeAirBlock(vec)) {
                            
                            deconstruct();
                            return;
                        }
                        // else if (!placeAirBlock(vec)) {
                        //
                        // deconstruct();
                        // return;
                        // }
                    }
                }
            }
            isConstructing = false;
            isValid = true;
        }
        else if (forceLoad) {
            
            for (int x = 0; x <= size.getX(); x++) {
                
                for (int y = 0; y <= size.getY(); y++) {
                    
                    for (int z = 0; z <= size.getZ(); z++) {
                        
                        checkBlock(positionRelativeTo.subtract(x, y, z));
                    }
                }
            }
            forceLoad = false;
            isValid = true;
        }
    }
    
    public boolean startCreation() {
        
        if (!isValid && !isConstructing) {
            
            if (size.getX() >= 2 && size.getY() >= 2 && size.getZ() >= 2) {
                
                isConstructing = true;
                
                TileEntity tile = UtilVector.getTileAtPostion(world, updater);
                
                if (tile != null && tile instanceof IMultiBlockPart) {
                    
                    if (((IMultiBlockPart) tile).addMultiBlock(this)) {
                        
                        fluidTank.setCapacity((size.getX() - 1) * (size.getY() - 1) * (size.getZ() - 1) * 16 * FluidContainerRegistry.BUCKET_VOLUME);
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    @Override
    public void sendPacket() {
        
        if (!world.isRemote) {
            
            GearUtilities.getPipeline().sendToDimension(new MutliBlockTankPacket(this.positionRelativeTo.subtract(1), size, fluidTank), world.provider.dimensionId);
        }
    }
}

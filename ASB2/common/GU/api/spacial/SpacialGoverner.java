package GU.api.spacial;

import java.util.Set;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;
import ASB2.vector.Vector3;

public class SpacialGoverner implements ISpacialGoverner {
    
    World worldObj;
    Set<Vector3> compitition;
    
    public SpacialGoverner(World world, Set<Vector3> composit) {

        worldObj = world;
        compitition = composit;
    }    
    
    public void distributeFluid(FluidStack fluid) {
        
        for(Vector3 vector : compitition) {
            
            TileEntity tile = vector.getTileEntity(worldObj);
            
            if(tile != null) {
                
                if(tile instanceof IFluidHandler) {
                    
                    
                }
            }
        }
    }
}

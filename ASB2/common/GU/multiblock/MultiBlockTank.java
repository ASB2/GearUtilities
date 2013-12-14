package GU.multiblock;

import net.minecraft.world.World;
import ASB2.vector.Vector3;
import GU.api.multiblock.MultiBlockManager;

public class MultiBlockTank extends MultiBlockManager {

    public MultiBlockTank(World worldObj, Vector3 multiBlockCore, int relativeXPlus, int relativeYPlus, int relativeZPlus) {
        super(worldObj, multiBlockCore, relativeXPlus, relativeYPlus, relativeZPlus);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean isMultiBlockValid() {

        return false;
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        
    }
    
}

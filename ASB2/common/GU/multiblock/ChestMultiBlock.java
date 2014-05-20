package GU.multiblock;

import net.minecraft.world.World;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlockStructure;
import UC.math.vector.Vector3i;

public class ChestMultiBlock extends MultiBlockBase {
    
    public ChestMultiBlock(World world, Vector3i positionRelativeTo, Vector3i size) {
        super(world, positionRelativeTo, size);
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public IMultiBlockStructure getStructure() {
        // TODO Auto-generated method stub
        return null;
    }
}

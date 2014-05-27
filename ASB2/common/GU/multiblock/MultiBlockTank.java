package GU.multiblock;

import net.minecraft.world.World;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlockStructure;
import UC.color.Color4i;
import UC.math.vector.Vector3i;

public class MultiBlockTank extends MultiBlockBase {
    
    public MultiBlockTank(World world, Vector3i positionRelativeTo, Vector3i size, Vector3i updater) {
        super(world, positionRelativeTo, size, updater);
        // TODO Auto-generated constructor stub
    }
    
    public MultiBlockTank(World world) {
        super(world);
        
    }
    
    @Override
    public IMultiBlockStructure getStructure() {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Color4i getDefaultBlockColor() {
        
        return Color4i.BLUE;
    }
}

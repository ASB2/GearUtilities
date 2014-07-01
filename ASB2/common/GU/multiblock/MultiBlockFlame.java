package GU.multiblock;

import net.minecraft.world.World;
import GU.api.color.VanillaColor;
import GU.multiblock.construction.ConstructionManager;
import GU.multiblock.construction.FlameConstructionManager;
import UC.color.Color4i;
import UC.math.vector.Vector3i;

public class MultiBlockFlame extends MultiBlockBase {
    
    public MultiBlockFlame(World world, Vector3i positionRelativeTo, Vector3i size, Vector3i updater) {
        super(world, positionRelativeTo, size, updater);
    }
    
    public MultiBlockFlame(World world) {
        super(world);
    }
    
    @Override
    public Color4i getDefaultBlockColor() {
        
        return VanillaColor.MAGENTA.getRGBValue().darker(150);
    }
    
    @Override
    public ConstructionManager getConstructionManager() {
        
        return new FlameConstructionManager(world, this, positionRelativeTo, size);
    }
    
    public boolean startCreation() {
        
        return size.getX() >= 2 && size.getY() >= 2 && size.getZ() >= 2 && (size.getX() == size.getY() && size.getY() == size.getZ()) && super.startCreation();
    }
}

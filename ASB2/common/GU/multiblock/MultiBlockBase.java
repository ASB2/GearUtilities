package GU.multiblock;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlock;
import UC.AbstractUpdateable;
import UC.math.vector.Vector3i;

public abstract class MultiBlockBase implements IMultiBlock, AbstractUpdateable {
    
    World world;
    Vector3i positionRelativeTo;
    Vector3i size;
    
    boolean isValid, isConstructing;
    
    public MultiBlockBase(World world, Vector3i positionRelativeTo, Vector3i size) {
        
        this.world = world;
        this.positionRelativeTo = positionRelativeTo;
        this.size = size;
    }
    
    @Override
    public void update(Object... objects) {
        
        if (isConstructing) {
            
        }
    }
    
    public boolean startCreation() {
        
        if (!isValid && !isConstructing) {
            
            isConstructing = true;
            return true;
        }
        return false;
    }
    
    public boolean createAfterLoad() {
        
        return false;
    }
    
    public NBTTagCompound save(NBTTagCompound tag) {
        
        return tag;
    }
    
    public void load(NBTTagCompound tag) {
        
    }
}

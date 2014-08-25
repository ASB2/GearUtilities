package GU.multiblock;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ASB2.utils.UtilVector;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlock;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlockPart;
import GU.multiblock.construction.ConstructionManager;
import UC.IAbstractUpdateable;
import UC.color.Color4i;
import UC.math.vector.Vector3i;

public abstract class MultiBlockBase implements IMultiBlock, IAbstractUpdateable {
    
    protected World world;
    protected Vector3i positionRelativeTo;
    protected Vector3i size;
    protected Vector3i updater;
    
    protected boolean isValid = false, isConstructing = false, isDeconstructing = false, forceLoad = false, hasTriedConstructionManagerCreation = false, calledSize = false;
    
    protected ConstructionManager constructionManager;
    
    public MultiBlockBase(World world, Vector3i positionRelativeTo, Vector3i size, Vector3i updater) {
        this(world);
        this.positionRelativeTo = positionRelativeTo;
        setSize(size);
        this.updater = updater;
    }
    
    public MultiBlockBase(World world) {
        
        this.world = world;
    }
    
    @Override
    public void update(Object... objects) {
        
        if (!calledSize) {
            
            onSetSize();
            calledSize = true;
        }
        
        if (!hasTriedConstructionManagerCreation) {
            
            constructionManager = this.getConstructionManager();
            hasTriedConstructionManagerCreation = true;
            
            if (isConstructing) {
                
                constructionManager.startConstruction();
            }
        }
        
        if (constructionManager != null) {
            
            constructionManager.update();
        }
        
        if (isConstructing) {
            
            if (constructionManager != null) {
                
                if (constructionManager.isFinishedConstructing()) {
                    
                    isConstructing = false;
                    isValid = true;
                }
            }
        }
        else if (isDeconstructing) {
            
            if (constructionManager != null) {
                
                if (constructionManager.isFinishedDeconstruction()) {
                    
                    isConstructing = false;
                    isValid = false;
                }
            }
        }
        else if (forceLoad) {
            
            if (constructionManager != null) {
                
                if (constructionManager.checkAfterLoad()) {
                    
                    forceLoad = false;
                    isValid = true;
                }
            }
        }
        
        if (isValid) {
            
            logicUpdate();
        }
    }
    
    public void logicUpdate() {
        
    }
    
    public void onSetSize() {
        
    }
    
    private void setSize(Vector3i newSize) {
        
        this.size = newSize;
    }
    
    public boolean startCreation() {
        
        if (!isValid && !isConstructing) {
            
            isConstructing = true;
            
            TileEntity tile = UtilVector.getTileAtPostion(world, updater);
            
            if (tile != null && tile instanceof IMultiBlockPart) {
                
                return ((IMultiBlockPart) tile).addMultiBlock(this);
            }
        }
        return false;
    }
    
    public void deconstruct() {
        
        if (constructionManager != null) {
            
            constructionManager.startDestruction();
        }
    }
    
    public Color4i getDefaultBlockColor() {
        
        return Color4i.WHITE;
    }
    
    public ConstructionManager getConstructionManager() {
        
        return null;
    }
    
    public Vector3i getCore() {
        
        return positionRelativeTo;
    }
    
    public Vector3i getSpacialProvider() {
        
        return updater;
    }
    
    public boolean isValid() {
        
        return isValid;
    }
    
    @Override
    public void onBlockBreak(int x, int y, int z) {
        
        this.isValid = false;
        
        if (!isDeconstructing) {
            
            deconstruct();
        }
    }
    
    public void onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        
        if (!isValid) {
            
            if (constructionManager != null) {
                
                constructionManager.deconstructBlock(new Vector3i(x, y, z));
            }
        }
    }
    
    public void sendPacket() {
        
    }
    
    public NBTTagCompound save(NBTTagCompound tag) {
        
        tag.setTag("core", UtilVector.saveVector(new NBTTagCompound(), positionRelativeTo));
        tag.setTag("size", UtilVector.saveVector(new NBTTagCompound(), size));
        tag.setTag("updater", UtilVector.saveVector(new NBTTagCompound(), updater));
        tag.setBoolean("isValid", isValid);
        return tag;
    }
    
    public void load(NBTTagCompound tag) {
        
        positionRelativeTo = UtilVector.loadVector3i(tag.getCompoundTag("core"));
        updater = UtilVector.loadVector3i(tag.getCompoundTag("updater"));
        this.setSize(UtilVector.loadVector3i(tag.getCompoundTag("size")));
        this.forceLoad = tag.getBoolean("isValid");
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (forceLoad ? 1231 : 1237);
        result = prime * result + (isConstructing ? 1231 : 1237);
        result = prime * result + (isDeconstructing ? 1231 : 1237);
        result = prime * result + (isValid ? 1231 : 1237);
        result = prime * result + ((positionRelativeTo == null) ? 0 : positionRelativeTo.hashCode());
        result = prime * result + ((size == null) ? 0 : size.hashCode());
        result = prime * result + ((updater == null) ? 0 : updater.hashCode());
        result = prime * result + ((world == null) ? 0 : world.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof MultiBlockBase)) return false;
        MultiBlockBase other = (MultiBlockBase) obj;
        if (forceLoad != other.forceLoad) return false;
        if (isConstructing != other.isConstructing) return false;
        if (isDeconstructing != other.isDeconstructing) return false;
        if (isValid != other.isValid) return false;
        if (positionRelativeTo == null) {
            if (other.positionRelativeTo != null) return false;
        }
        else if (!positionRelativeTo.equals(other.positionRelativeTo)) return false;
        if (size == null) {
            if (other.size != null) return false;
        }
        else if (!size.equals(other.size)) return false;
        if (updater == null) {
            if (other.updater != null) return false;
        }
        else if (!updater.equals(other.updater)) return false;
        if (world == null) {
            if (other.world != null) return false;
        }
        else if (!world.equals(other.world)) return false;
        return true;
    }
    
}

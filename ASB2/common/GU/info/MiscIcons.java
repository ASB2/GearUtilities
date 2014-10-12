package GU.info;

import net.minecraft.util.IIcon;

public enum MiscIcons {
    
    RUNE_SQUARE("miscIcons/RunicSquare"), INPUT("miscIcons/sides/BlockInput"), OUTPUT("miscIcons/sides/BlockOutput"), BOTH("miscIcons/sides/BlockBoth"), NONE("miscIcons/sides/BlockNone");
    
    String path;
    IIcon icon;
    
    private MiscIcons(String path) {
        
        this.path = path;
    }
    
    public String getPath() {
        
        return path;
    }
    
    public MiscIcons setIcon(IIcon icon) {
        
        this.icon = icon;
        return this;
    }
    
    public IIcon getIcon() {
        
        return icon;
    }
}

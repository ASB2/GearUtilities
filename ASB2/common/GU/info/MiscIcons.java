package GU.info;

import net.minecraft.util.IIcon;

public enum MiscIcons {
    
    RUNE_SQUARE("miscIcon/RunicSquare");
    
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

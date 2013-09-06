package GU.api.module.block;

import java.util.ArrayList;

public interface IBlockModuleAccepter {
    
    public boolean addModule(IBlockModule module);
    public boolean removeModule(IBlockModule module);
    public ArrayList<IBlockModule> getModules();
}

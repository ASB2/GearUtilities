package GU.api.module;

import java.util.ArrayList;

public interface IModuleUser {
    
    public boolean addModule(IModuleProvider module);
    public boolean removeModule(IModuleProvider module);
    public ArrayList<IModuleProvider> getModules();
}

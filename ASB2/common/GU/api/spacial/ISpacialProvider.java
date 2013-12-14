package GU.api.spacial;

import java.util.Set;

import ASB2.vector.Vector3;

public interface ISpacialProvider {
    
    Set<Vector3> getProvidedTiles();
    
    void setSpacialGoverner(ISpacialGoverner newGoverner);
    ISpacialGoverner getGoverner();
    
    void invalidate();
}

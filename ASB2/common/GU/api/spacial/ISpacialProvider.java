package GU.api.spacial;

import java.util.Set;

import ASB2.vector.Vector3;
import GU.api.multiblock.*;

public interface ISpacialProvider extends IMultiBlockPart {
    
    Set<Vector3> getProvidedTiles();
}

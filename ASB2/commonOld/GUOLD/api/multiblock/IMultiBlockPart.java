package GUOLD.api.multiblock;

import java.util.List;

public interface IMultiBlockPart {

    boolean addMultiBlock(IMultiBlock multiBlock);

    void removeMultiBlock(IMultiBlock multiBlock);

    List<IMultiBlock> getComprisedMultiBlocks();
}

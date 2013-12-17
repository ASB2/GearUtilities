package GU.api.multiblock;

public interface IMultiBlockPart {
    
    boolean setStructure(MultiBlockManager multiBlock);    
    //TODO Remove Parameter
    void removeStructure(MultiBlockManager multiBlock);
    MultiBlockManager getCurrentStructure();
}

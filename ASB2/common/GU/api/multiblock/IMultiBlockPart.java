package GU.api.multiblock;

public interface IMultiBlockPart {
    
    boolean setStructure(MultiBlockManager multiBlock);
    void removeStructure();
    MultiBlockManager getCurrentStructure();
}

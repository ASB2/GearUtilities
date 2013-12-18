package GU.api.multiblock;

public interface IMultiBlockPart {
    
    boolean setStructure(MultiBlockBase multiBlock);
    void removeStructure();
    MultiBlockBase getCurrentStructure();
}

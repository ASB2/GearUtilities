package GU.api.embryo;

public enum EnumGrowthStage {

    DEAD, INFANT, ALMOST_NOT_INFANT, HIGHER_INFANT, ADOLESENT, MATURE;

    public EnumGrowthStage getNextGrowthStage() {

        switch(this) {

            case DEAD:
                return INFANT;
            case INFANT:
                return ALMOST_NOT_INFANT;
            case ALMOST_NOT_INFANT:
                return HIGHER_INFANT;
            case HIGHER_INFANT:
                return ADOLESENT;
            case ADOLESENT:
                return MATURE;
            case MATURE:
                return MATURE;
            default:
                break;
        }
        return DEAD;
    }
}

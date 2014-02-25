package GU.api.flame;

public enum EnumFlameType {

    RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET;

    public String getIGN() {

        switch (this) {

            case RED: {
                return "Storm";
            }

            case ORANGE: {
                return "Sun";
            }

            case YELLOW: {
                return "Sky";
            }

            case GREEN: {
                return "Lightning";
            }

            case BLUE: {
                return "Rain";
            }

            case INDIGO: {
                return "Cloud";
            }

            case VIOLET: {
                return "Mist";
            }
        }
        return "Somethings Wrong";
    }
}

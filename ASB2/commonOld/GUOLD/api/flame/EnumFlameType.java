package GUOLD.api.flame;

import java.awt.Color;

public enum EnumFlameType {

    STORM, SUN, SKY, LIGHTNING, RAIN, CLOUD, MIST, DARK, LIGHT, CLEAR;

    public String getIGN() {

        switch (this) {

            case STORM: {
                return "Storm";
            }

            case SUN: {
                return "Sun";
            }

            case SKY: {
                return "Sky";
            }

            case LIGHTNING: {
                return "Lightning";
            }

            case RAIN: {
                return "Rain";
            }

            case CLOUD: {
                return "Cloud";
            }

            case MIST: {
                return "Mist";
            }

            case DARK: {
                return "Dark";
            }

            case LIGHT: {
                return "Light";
            }

            case CLEAR: {
                return "Clear";
            }
        }
        return "Somethings Wrong";
    }

    public Color getFlameColor() {

        switch (this) {

            case STORM: {
                return Color.RED;
            }

            case SUN: {
                return new Color(255, 150, 0);
            }

            case SKY: {
                return new Color(255, 255, 0);
            }

            case LIGHTNING: {
                return Color.GREEN;
            }

            case RAIN: {
                return Color.BLUE;
            }

            case CLOUD: {
                return new Color(75, 0, 130);
            }

            case MIST: {
                return new Color(143, 0, 255).brighter();
            }

            case DARK: {
                return Color.DARK_GRAY;
            }

            case LIGHT: {
                return Color.LIGHT_GRAY.darker();
            }

            case CLEAR: {
                return Color.WHITE;
            }
        }
        return Color.WHITE;
    }
}
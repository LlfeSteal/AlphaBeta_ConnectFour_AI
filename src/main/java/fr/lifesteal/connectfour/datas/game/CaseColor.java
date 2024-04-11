package fr.lifesteal.connectfour.datas.game;

public enum CaseColor {

    RED("R"),
    YELLOW("J"),
    WHITE("B");

    private final String color;

    CaseColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return color;
    }
}

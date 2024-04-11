package fr.lifesteal.connectfour.datas.game;

public class Case {

    private CaseColor value = CaseColor.WHITE;
    private int rowPosition;

    public Case(int rowPosition) {
        this.rowPosition = rowPosition;
    }

    public CaseColor getValue() {
        return value;
    }

    public int getRowPosition() {
        return rowPosition;
    }

    public void setValue(CaseColor value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Case n°" + this.rowPosition + " -> " + this.value;
    }
}

package fr.lifesteal.connectfour.datas.game;

import java.util.ArrayList;
import java.util.List;

public class Column {

    private int columnID;
    private List<Case> cases = new ArrayList<Case>();

    public Column(int columnID) {
        this.columnID = columnID;
    }

    public int getColumnID() {
        return columnID;
    }

    public List<Case> getCases() {
        return cases;
    }

    public boolean addCase(Case newCase) {
        return this.cases.add(newCase);
    }

    public Case setCaseColor(CaseColor color) {
        for (int i = this.cases.size()-1; i >= 0 ; --i) {
            Case currentCase = this.cases.get(i);
            if (currentCase.getValue() == CaseColor.WHITE) {
                currentCase.setValue(color);
                return currentCase;
            }

        }
        return null;
    }

    public void unsetCaseColor() {
        for (int i = this.cases.size()-1; i >= 0 ; --i) {
            Case currentCase = this.cases.get(i);
            Case caseToRemove = null;
            if (i == 0) {
                caseToRemove = this.cases.get(i);
            }
            else if (currentCase.getValue() == CaseColor.WHITE) {
                caseToRemove = this.cases.get(i+1);
            }
            if (caseToRemove != null) {
                caseToRemove.setValue(CaseColor.WHITE);
                break;
            }
        }
    }

    public boolean isFull() {
        System.out.println(this);
        return this.cases.get(0).getValue() != CaseColor.WHITE;
    }

    @Override
    public String toString() {
        return "Column{" +
                "columnID=" + columnID +
                ", cases=" + cases +
                '}';
    }
}

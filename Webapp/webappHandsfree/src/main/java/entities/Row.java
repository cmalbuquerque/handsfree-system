/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Objects;

/**
 *
 * @author nunos
 */
public class Row {
    
    private int selectAction;
    private int selectedVoice;

    public void setSelectAction(int selectAction) {
        this.selectAction = selectAction;
    }

    public void setSelectedVoice(int selectedVoice) {
        this.selectedVoice = selectedVoice;
    }

    public int getSelectAction() {
        return selectAction;
    }

    public int getSelectedVoice() {
        return selectedVoice;
    }

    public Row(int selectedAction, int selectedVoice) {
        this.selectAction = selectAction;
        this.selectedVoice = selectedVoice;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.selectAction);
        hash = 53 * hash + Objects.hashCode(this.selectedVoice);
        return hash;
    }

    @Override
    public String toString() {
        return "Row{" + "selectAction=" + selectAction + ", nome=" + selectedVoice + '}';
    }

}

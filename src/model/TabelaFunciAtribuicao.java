/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author User
 */
public class TabelaFunciAtribuicao {
    
    
    private String funci;
    private String estado;
    private int cd;
    private boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
    

    public int getCd() {
        return cd;
    }

    public void setCd(int cd) {
        this.cd = cd;
    }
    
    
    

    public String getFunci() {
        return funci;
    }

    public void setFunci(String funci) {
        this.funci = funci;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
}

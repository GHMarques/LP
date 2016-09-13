/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Aluno
 */
public class InputIntValue extends IntValue{
    private String stringValue;

    public InputIntValue(String stringValue, int Line){
        super(Line);
        this.stringValue = stringValue;
    }
    
    @Override
    public Integer value() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

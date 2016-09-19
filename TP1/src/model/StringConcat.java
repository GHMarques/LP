/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author wendell
 */
public class StringConcat  extends StringValue{
    private Value<?> left, right;

    public StringConcat(Value<?> left, Value<?> right, int line) {
        super(line);
        this.left = left;
        this.right = right;
    }

    @Override
    public String value() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}

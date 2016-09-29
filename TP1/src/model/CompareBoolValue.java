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
public class CompareBoolValue extends BoolValue{
    RelOp op;
    Value<?> left, right;

    public CompareBoolValue(RelOp op, Value<?> left, Value<?> right, int l) {
        super(l);
        this.op = op;
        this.left = left;
        this.right = right;
    }
    
    
    
    @Override
    public Boolean value() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
            
}

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
public class OpposedMatrixValue extends MatrixValue {
    
    private Value<?> value;

    public OpposedMatrixValue(Value<?> value, int line) {
        super(line);
        
        this.value = value;
    }

    @Override
    public Matrix value() {
        Value<?> v = (value instanceof Variable ? (Variable) value.value() : value);
        
        if (v instanceof MatrixValue){
            Matrix x = ((MatrixValue) v).value();
            return x.opposed();
        } else {
            // FIXME: Erro de tipos!
            return null;
        }
    }
    
    
}

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
public class ValueIntMatrixValue extends IntMatrixValue{
    private Value<?>v1,v2;

    public ValueIntMatrixValue(Value<?> v1, Value<?> v2, Matrix m, int line) {
        super(m, line);
        this.v1 = v1;
        this.v2 = v2;
    }
    
    @Override
    public Integer value() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

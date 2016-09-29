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
public class RowsIntMatrixValue extends IntMatrixValue{

    public RowsIntMatrixValue(Value<?> m, int line) {
        super(m, line);
    }

    @Override
    public Integer value() {
        Value<?> v = (m instanceof Variable ? (Variable) m.value() : m);
        
        if (v instanceof MatrixValue){
            Matrix x = ((MatrixValue) v).value();
            return x.rows();
        } else {
            // FIXME: Erro de tipos!
            return null;
        }
    }
    
}

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
public class MulMatrixValue extends MatrixValue{
    private Value<?> m;
    private Value<?> v;
    
    public MulMatrixValue(Value<?> m, Value<?> v, int line) {
        super(line);
        this.m = m;
        this.v = v;
    }
    

    @Override
    public Matrix value() {
        Value<?> v1 = (m instanceof Variable ? (Variable) m.value() : m);
        Value<?> v2 = (v instanceof Variable ? (Variable) v.value() : v);
        
        if (v1 instanceof MatrixValue && v2 instanceof MatrixValue){
            Matrix x = ((MatrixValue) v1).value();
            Matrix y = ((MatrixValue) v2).value();
            return x.mul(x, y);
        }else if (v1 instanceof MatrixValue && v2 instanceof ConstIntValue){
            Matrix x = ((MatrixValue) v1).value();
            int y = ((ConstIntValue) v2).value();
            return x.mul(x, y);
        }else {
            // FIXME: Erro de tipos!
            return null;
        }
    }
}

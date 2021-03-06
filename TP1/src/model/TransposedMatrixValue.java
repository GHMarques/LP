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
public class TransposedMatrixValue extends MatrixValue{
    private Value<?> m;

    public TransposedMatrixValue(Value<?> m, int line) {
        super(line);
        this.m = m;
    }

    @Override
    public Matrix value() {
        Value<?> v = (m instanceof Variable ?((Variable)this.m).value() : m);
        
        if (v instanceof MatrixValue){
            Matrix x = ((MatrixValue) v).value();
            return x.transposed();
        } else {
            System.out.println(this.line()+": Tipos inválidos");
            System.exit(1);
            return null;
        }
    }
}

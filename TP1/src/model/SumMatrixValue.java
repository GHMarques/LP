/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import static java.lang.System.exit;

/**
 *
 * @author wendell
 */
public class SumMatrixValue extends MatrixValue{
    private Value<?> m1, m2;

    public SumMatrixValue(Value<?> m1, Value<?> m2, int line) {
        super(line);
        this.m1 = m1;
        this.m2 = m2;
    }

    @Override
    public Matrix value() {
        Value<?> v1 = (m1 instanceof Variable ? ((Variable)this.m1).value() : m1);
        Value<?> v2 = (m2 instanceof Variable ? ((Variable)this.m2).value() : m2);
        
        if (v1 instanceof MatrixValue && v2 instanceof MatrixValue){
            Matrix x = ((MatrixValue) v1).value();
            Matrix y = ((MatrixValue) v2).value();
            return x.sum(x, y);
        } else {
            System.out.println(this.line()+": Tipos inválidos");
            System.exit(1);
            return null;
        }
    }
    
}

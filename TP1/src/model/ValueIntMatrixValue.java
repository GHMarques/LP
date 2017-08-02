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

    public ValueIntMatrixValue(Value<?> v1, Value<?> v2, Value<?> m, int line) {
        super(m, line);
        this.v1 = v1;
        this.v2 = v2;
    }
    
    @Override
    public Integer value() {
        Value<?> row = (v1 instanceof Variable ? ((Variable)this.v1).value(): v1);
        Value<?> col = (v2 instanceof Variable ? ((Variable)this.v2).value(): v2);
        Value<?> v = (m instanceof Variable ?((Variable)m).value()  : m);
        
        if (row instanceof IntValue && col instanceof IntValue && (v instanceof MatrixValue)){
            int i = ((IntValue) row).value();
            int j = ((IntValue) col).value();
            Matrix x = ((MatrixValue) v).value();
            return x.value(i, j);
        } else {
            System.out.println(this.line()+": Tipos inválidos");
            System.exit(1);
            return null;
        }
    }
    
}


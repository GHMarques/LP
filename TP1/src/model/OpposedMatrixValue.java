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
public class OpposedMatrixValue extends MatrixValue {
    
    private Value<?> value;

    public OpposedMatrixValue(Value<?> value, int line) {
        super(line);
        
        this.value = value;
    }

    @Override
    public Matrix value() {
        Value<?> v = (value instanceof Variable ? ((Variable)this.value).value() : value);
        
        if (v instanceof MatrixValue){
            Matrix x = ((MatrixValue) v).value();
            return x.opposed();
        } else {
            System.out.println(this.line()+": Tipos inválidos");
            System.exit(1);
            return null;
        }
    }
    
    
}

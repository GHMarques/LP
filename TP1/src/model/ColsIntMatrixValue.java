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
public class ColsIntMatrixValue extends IntMatrixValue{

    public ColsIntMatrixValue(Value<?> m, int line) {
        super(m, line);
    }

    @Override
    public Integer value() {
        Value<?> v = (m instanceof Variable ? ((Variable)this.m).value() : m);
        
        if (v instanceof MatrixValue){
            Matrix x = ((MatrixValue) v).value();
            return x.cols();
        } else {
            System.out.println(this.line()+": Tipos inválidos");
            System.exit(1);
            return null;
        }
    }
    
}

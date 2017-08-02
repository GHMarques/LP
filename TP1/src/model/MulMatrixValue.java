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
        Value<?> v1 = (m instanceof Variable ? ((Variable)this.m).value() : m);
        Value<?> v2 = (v instanceof Variable ? ((Variable)this.v).value() : v);
        
        if (v1 instanceof MatrixValue && v2 instanceof MatrixValue){
            Matrix x = ((MatrixValue) v1).value();
            Matrix y = ((MatrixValue) v2).value();
            
            if(x.cols() != y.rows()){
                System.out.println("Operacao invalida na linha "+line());
                exit(1);
            }
            return x.mul(x, y);
        }else if (v1 instanceof MatrixValue && v2 instanceof IntValue){
            Matrix x = ((MatrixValue) v1).value();
            int y = ((IntValue) v2).value();
            return x.mul(x, y);
        }else {
            System.out.println(this.line()+": Tipos inválidos");
            System.exit(1);
            return null;
        }
    }
}

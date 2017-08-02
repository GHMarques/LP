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
public class FillMatrixValue extends MatrixValue{
    private Value<?> rows,cols,v;

    public FillMatrixValue(Value<?> r, Value<?> c, Value<?> v, int line) {
        super(line);
        this.rows = r;
        this.cols = c;
        this.v = v;
    }
    
    
    
    @Override
    public Matrix value() {
        Value<?> v1 = (rows instanceof Variable ? (Variable) rows.value() : rows);
        Value<?> v2 = (cols instanceof Variable ? (Variable) cols.value() : cols);
        Value<?> v3 = (v instanceof Variable ? (Variable) v.value() : v);
        
        if (v1 instanceof IntValue && v2 instanceof IntValue){
                int r = ((IntValue)v1).value();
                int c = ((IntValue)v2).value();
                int v = ((IntValue)v3).value();
                
                return Matrix.fill(r, c, v);
        }else{
            System.out.println(this.line()+": Tipos inválidos");
            System.exit(1);
            return null;
        }
    }
    
}

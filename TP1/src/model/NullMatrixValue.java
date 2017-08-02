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
public class NullMatrixValue extends MatrixValue{
    private Value<?> rows,cols;

    public NullMatrixValue(Value<?> rows, Value<?> cols, int line) {
        super(line);
        this.rows = rows;
        this.cols = cols;
    }

    @Override
    public Matrix value() {
        Value<?> v1 = (rows instanceof Variable ? ((Variable)this.rows).value() : rows);
        Value<?> v2 = (cols instanceof Variable ? ((Variable)this.cols).value() : cols);
        
        if (v1 instanceof IntValue && v2 instanceof IntValue){
                int r = ((IntValue)v1).value();
                int c = ((IntValue)v2).value();
                return Matrix._null(r, c);
        }else{
            System.out.println(this.line()+": Tipos inválidos");
            System.exit(1);
            return null;
        }
    }
    
}

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
public class IdMatrixValue extends MatrixValue{
    Value<?> rows,cols;

    public IdMatrixValue(Value<?> r, Value<?> c, int line) {
        super(line);
        this.rows = r;
        this.cols = c;
    }

    @Override
    public Matrix value() {
        Value<?> v1 = (rows instanceof Variable ? (Variable) rows.value() : rows);
        Value<?> v2 = (cols instanceof Variable ? (Variable) cols.value() : cols);
        
        if (v1 instanceof ConstIntValue && v2 instanceof ConstIntValue){
                int r = ((ConstIntValue)v1).value();
                int c = ((ConstIntValue)v2).value();
                return Matrix.id(r, c);
        }else{
            // FIXME: Erro de tipos!
            return null;
        }
    }
    
}

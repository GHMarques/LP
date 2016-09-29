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
public class SeqMatrixValue extends MatrixValue{
    Value<?> from,to;
    boolean inverted;

    public SeqMatrixValue(Value<?> from, Value<?> to, boolean inverted, int line) {
        super(line);
        this.from = from;
        this.to = to;
        this.inverted = inverted;
    }
    
    @Override
    public Matrix value() {
        Value<?> v1 = (from instanceof Variable ? (Variable) from.value() : from);
        Value<?> v2 = (to instanceof Variable ? (Variable) to.value() : to);
        
        if (v1 instanceof ConstIntValue && v2 instanceof ConstIntValue){
            if(inverted){
                int r = ((ConstIntValue)v1).value();
                int c = ((ConstIntValue)v2).value();
                return Matrix.seq(r, c);
            }
        }else{
            // FIXME: Erro de tipos!
            return null;
        }
        return null;
    }
}

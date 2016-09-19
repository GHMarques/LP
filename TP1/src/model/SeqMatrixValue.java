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
    IntValue from,to;
    boolean inverted;

    public SeqMatrixValue(IntValue from, IntValue to, boolean inverted, int line) {
        super(line);
        this.from = from;
        this.to = to;
        this.inverted = inverted;
    }
    
    @Override
    public Matrix value() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

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
public class MulMatrixValue extends MatrixValue{
    private MatrixValue m;
    private Value<?> v;
    
    public MulMatrixValue(MatrixValue m, Value<?> v, int line) {
        super(line);
        this.m = m;
        this.v = v;
    }
    

    @Override
    public Matrix value() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

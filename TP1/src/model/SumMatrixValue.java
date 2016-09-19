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
public class SumMatrixValue extends MatrixValue{
    private MatrixValue m1, m2;

    public SumMatrixValue(MatrixValue m1, MatrixValue m2, int line) {
        super(line);
        this.m1 = m1;
        this.m2 = m2;
    }

    @Override
    public Matrix value() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

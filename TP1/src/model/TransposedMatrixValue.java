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
public class TransposedMatrixValue extends MatrixValue{
    private MatrixValue m;

    public TransposedMatrixValue(MatrixValue m, int line) {
        super(line);
        this.m = m;
    }

    @Override
    public Matrix value() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

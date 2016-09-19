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
public class NullMatrixValue extends MatrixValue{
    private int rows,cols;

    public NullMatrixValue(int rows, int cols, int line) {
        super(line);
        this.rows = rows;
        this.cols = cols;
    }

    @Override
    public Matrix value() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

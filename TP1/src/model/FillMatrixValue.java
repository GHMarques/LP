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
public class FillMatrixValue extends MatrixValue{
    private int r,c,v;

    public FillMatrixValue(int r, int c, int v, int line) {
        super(line);
        this.r = r;
        this.c = c;
        this.v = v;
    }
    
    
    
    @Override
    public Matrix value() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

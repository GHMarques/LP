/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.util.Random;
/**
 *
 * @author alunoccc
 */
public class Matrix {
    private int rows, cols;
    int[][] matrix;
    
    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }
    
    public void setValue(int r, int c, int v){
        matrix[r][c] = v;
    }
    
    public void show(){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.println(matrix[i][j]+' ');
            }
            System.out.println('\n');
        }
    }

    public int rows() {
        return rows;
    }

    public int cols() {
        return cols;
    }
    
    public int value(int r, int c)
    {
        return matrix[r][c];
    }    
    
    public Matrix opposed(Matrix m){
        return mul(m,-1);
    }
    
    public Matrix transposed(){
       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Matrix size(){
       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Matrix sum(Matrix x, Matrix y){
        if(x.rows == y.rows && x.cols == y.cols){
            Matrix m = new Matrix(x.rows,y.cols);
            for (int i = 0; i < rows; i++) {
               for (int j = 0; j < cols; j++) {
                  m.matrix[i][j] = x.matrix[i][j] + y.matrix[i][j];
               }
            }
            return m;
        }
        return null;
    }
    
    public Matrix mul(Matrix x, Matrix y){
        if(x.rows() == y.cols())
            if(x.cols == y.rows){
                Matrix m = new Matrix(x.cols(),y.rows());
                //implementar
            }
        return null;
    }
    
    public Matrix mul(Matrix m,int n){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                m.matrix[i][j]*=n;
            }
        }
        return m;
    }
    
    public Matrix _null(int r, int c){
        Matrix m = new Matrix(r,c);
        return m;
    }
    
    public Matrix fill(int r, int c, int v){
        Matrix matrix2 = new Matrix(r,c);
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                matrix2.matrix[i][j] = v;
            }
        }
        return matrix2;
    }
    
    public Matrix rand(int r, int c){
        Matrix matrix2 = new Matrix(r,c);
        Random rand = new Random();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                matrix2.matrix[i][j] = rand.nextInt(100);
            }
        }
        return matrix2;
    }
    
    public Matrix id(int r, int c){
        Matrix matrix2 = new Matrix(r,c);
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
               matrix2.matrix[i][j] = (i==j) ? 1 : 0;
            }
        }
        return matrix2;
    }
    
    public Matrix seq(int x, int y){
        if(y > x){
            Matrix m = new Matrix(1,y-x+1);
            for (int i = x; i < y; i++) {
                m.matrix[1][i-1] = i;
            }
            return m;
        }
        return null;
    }
    
    public Matrix iseq(int x, int y){
        if(y < x){
            Matrix m = new Matrix(1,x-y+1);
            for (int i = x; i > y; i--) {
                m.matrix[1][i-1] = i;
            }
            return m;
        }
        return null;
    }
}

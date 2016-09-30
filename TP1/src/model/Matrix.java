/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.util.Random;
/**
 *
 * @author wendell
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
    
    //retorna valor da posicao [r][c]
    public int value(int r, int c)
    {
        return matrix[r][c];
    }    
    
    //Inverte sinal dos elementos
    public Matrix opposed(){
        Matrix m = new Matrix(rows,cols);
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j]*=-1;
            }
        }
        
        m.matrix = this.matrix;
        return m;
    }
    
    //matrix transposta
    public Matrix transposed(){
       Matrix m = new Matrix(rows,cols);
       int[][] m2 = matrix;
       
       for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j]=m2[j][i];
            }
        }
       
       m.matrix = this.matrix;
       return m;
    }
    
    public int size(){
        return rows*cols;
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
        if(x.rows() == y.cols())//Na especificacao isso tem q bater
            if(x.cols == y.rows){//e isso tbm
                Matrix m = new Matrix(x.rows(),y.cols());
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
    
    //cria uma matrix nula
    public static Matrix _null(int r, int c){
        Matrix m = new Matrix(r,c);
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                m.matrix[i][j]=0;
            }
        }
        return m;
    }
    
    //cria uma matrix[r][c] preenchida com v's
    public static Matrix fill(int r, int c, int v){
        Matrix matrix2 = new Matrix(r,c);
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                matrix2.matrix[i][j] = v;
            }
        }
        return matrix2;
    }
    
    //cria uma matrix com valores aleatorios
    public static Matrix rand(int r, int c){
        Matrix matrix2 = new Matrix(r,c);
        Random rand = new Random();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                matrix2.matrix[i][j] = rand.nextInt(100);
            }
        }
        return matrix2;
    }
    
    public static Matrix id(int r, int c){
        Matrix matrix2 = new Matrix(r,c);
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
               matrix2.matrix[i][j] = (i==j) ? 1 : 0;
            }
        }
        return matrix2;
    }
    
    public static Matrix seq(int x, int y){
        if(y > x){
            Matrix m = new Matrix(1,y-x+1);
            int c = x;
            for (int i = 0; i < y-x; i++) {
                m.matrix[1][i] = c++;
            }
            return m;
        }
        return null;
    }
    
    public static Matrix iseq(int x, int y){
        if(y < x){
            Matrix m = new Matrix(1,x-y+1);
            int c = x;
            for (int i = x; i > x-y+1; i--) {
                m.matrix[1][i-1] = c--;
            }
            return m;
        }
        return null;
    }
}

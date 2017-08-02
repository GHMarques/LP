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
public class StringConcat  extends StringValue{
    private Value<?> left, right;

    public StringConcat(Value<?> left, Value<?> right, int line) {
        super(line);
        this.left = left;
        this.right = right;
    }

    @Override
    public String value() {
        Value<?> expr1 = (this.left instanceof Variable ? ((Variable)this.left).value() : this.left);
        Value<?> expr2 = (this.right instanceof Variable ? ((Variable)this.right).value() : this.right);
        
        String str = "";

        if (expr1 instanceof IntValue) {
            str = Integer.toString(((IntValue) expr1).value());
        } else if (expr1 instanceof StringValue) {
            str = ((StringValue) expr1).value();
        }
        else if(expr1 instanceof RefMatrixValue){
            Matrix m;
            m = ((MatrixValue) expr1).value();
            str = m.showString();
        } else {
            System.out.println(this.line()+": Tipos inválidos");
            System.exit(1);
            return null;
        }

        if (expr2 instanceof IntValue) {
            str += Integer.toString(((IntValue) expr2).value());
        } else if (expr2 instanceof StringValue) {
            str += ((StringValue) expr2).value();
        }
        else if(expr2 instanceof RefMatrixValue){
            Matrix m;
            m = ((MatrixValue) expr2).value();
            str = m.showString();
        } else {
            System.out.println(this.line()+": Tipos inválidos");
            System.exit(1);
            return null;
        }

        return str;
    }
}

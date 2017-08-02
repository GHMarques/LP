/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import static java.lang.System.exit;

/**
 *
 * @author wendell
 */
public class DualIntExpr extends IntValue{
    private IntOp op;
    private Value<?> left, right;

    public DualIntExpr(IntOp op, Value<?> left, Value<?> right, int line) {
        super(line);
        this.op = op;
        this.left = left;
        this.right = right;
    }

    @Override
    public Integer value() {
        Value<?> v1 = (left instanceof Variable ? ((Variable) left).value() : left);
        Value<?> v2 = (right instanceof Variable ? ((Variable) right).value() : right);
        
        if (v1 instanceof IntValue && v2 instanceof IntValue) {
            int n1 = ((IntValue) v1).value();
            int n2 = ((IntValue) v2).value();
            
            switch (op) {
                case Add:
                    return n1+n2;
                case Sub:
                    return n1-n2;
                case Div:
                    return n1/n2;
                case Mul:
                    return n1*n2;
                case Mod:
                    return n1%n2;
                default:
                    System.out.println("Operacao invalida na linha "+line());
                    exit(1);
            }
        } else {
            System.out.println(this.line()+": Tipos inválidos");
            System.exit(1);
        }
        return null;
    }
}

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
public class CompareBoolValue extends BoolValue{
    RelOp op;
    Value<?> left, right;

    public CompareBoolValue(RelOp op, Value<?> left, Value<?> right, int l) {
        super(l);
        this.op = op;
        this.left = left;
        this.right = right;
    }
    
    
    
    @Override
    public Boolean value() {
        Value<?> v1 = (this.left instanceof Variable ? ((Variable)this.left).value() : this.left);
        Value<?> v2 = (this.right instanceof Variable ? ((Variable)this.right).value() : this.right);
                
        if (!(v1 instanceof IntValue && v2 instanceof IntValue)) {
            System.out.println(this.line()+": Tipos inválidos");
            System.exit(1);
            return null;
        }
        
        int i1 = ((IntValue) v1).value();
        int i2 = ((IntValue) v2).value();
        
        switch (this.op) {
            case Equal :
                return (i1 == i2);
            case NotEqual:
                return (i1 != i2);
            case GreaterThan:
                return (i1 > i2);
            case GreaterEqual:
                return (i1 >= i2);
            case LowerThan:
                return (i1 < i2);
            case LowerEqual:
                return (i1 <= i2);
            default:
                System.out.println("Operacao invalida na linha "+line());
                exit(1);
        }
        return null;
    }
            
}

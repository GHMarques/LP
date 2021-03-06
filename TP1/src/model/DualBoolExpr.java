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
public class DualBoolExpr extends BoolValue{
    private BoolOp op;
    private BoolValue left, right;

    public DualBoolExpr(BoolOp op, BoolValue left, BoolValue right, int l) {
        super(l);
        this.op = op;
        this.left = left;
        this.right = right;
    }

    @Override
    public Boolean value() {
        if(op == BoolOp.And){
            return this.left.value() & this.right.value();
        }else if(op == BoolOp.Or){
            return this.left.value() | this.right.value();
        }else {
            System.out.println(this.line()+": Tipos inválidos");
            System.exit(1);
            return null;
        }
    }

    
}

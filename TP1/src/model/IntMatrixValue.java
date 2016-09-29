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
public abstract class IntMatrixValue extends IntValue{
    protected Value<?> m;

    public IntMatrixValue(Value<?> m, int line) {
        super(line);
        this.m = m;
    }

    @Override
    public abstract Integer value();
    
}

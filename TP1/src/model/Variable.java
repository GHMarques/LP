/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author alunoccc
 */
public class Variable extends Value<Value<?>> {

    private String name;
    private Value<?> value;

    public Variable(String name) {
        super(-1);
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
    
    public void setValue(Value<?> v){
        this.value = v;
    }
    
    public Value<?> value(){
        return value;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.util.Scanner;
/**
 *
 * @author Aluno
 */
public class InputIntValue extends IntValue{
    
    private Value<?> val;
    private static Scanner ler;
    
    static {
        ler = new Scanner(System.in);
    }

    public InputIntValue(Value<?> val, int line){
        super(line);
        
        this.val = val;
    }
    
    @Override
    public Integer value() {
        Value<?> v = (val instanceof Variable ? (Variable) val.value() : val);
        
        if (v instanceof IntValue){
            int n = ((IntValue) v).value();
            System.out.print(n);
        }else if (v instanceof StringValue){
            String s = ((StringValue) v).value();
            System.out.print(s);
        } else if (v instanceof MatrixValue){
            Matrix m = ((MatrixValue) v).value();
            m.show();
        }else{
            //Tipos invalidos e aborta
        }

        int num = ler.nextInt();
        return num;
    }
    
}

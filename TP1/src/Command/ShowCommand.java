/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import model.*;

/**
 *
 * @author alunoccc
 */
public class ShowCommand extends Command {
    private Value<?> value;
    public ShowCommand(Value<?> value, int line ){
        super(line);
        this.value = value;
    }
    public void execute()
    {
        Value<?> v = (value instanceof Variable ? ((Variable) value).value() : value);
        
        if (v instanceof IntValue){
            int n = ((IntValue) v).value();
            System.out.println(n);
        }else if (v instanceof StringValue){
            String s = ((StringValue) v).value();
            System.out.println(s);
        } else if (v instanceof MatrixValue){
            Matrix m = ((MatrixValue) v).value();
            m.show();
        }else{
            //Tipos invalidos e aborta
        }
    }
}

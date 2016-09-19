/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import model.IntValue;
import model.Matrix;
import model.MatrixValue;
import model.StringValue;
import model.Value;
import model.Variable;

/**
 *
 * @author alunoccc
 */
public class ShowCommand extends Command{
    private Value<?> value;
    public ShowCommand(Value<?> value, int line ){
        super(line);
        this.value = value;
    }
    public void execute()
    {
        Value<?> val = value;
        if(val instanceof Variable){
            Variable var = (Variable) val;
            val = val.value();
        }
        if(val instanceof IntValue){
            IntValue iv = (IntValue) val;
            int n = iv.value();
            System.out.println(n);
        }else if(val instanceof StringValue){
            String sv = (StringValue) val;
            String S = sv.value();
            System.out.println(S);
        }
        else if(val instanceof MatrixValue){
            MatrixValue mv = (MatrixValue) val;
            Matrix m = mv.value();
            m.show();
        }else{
            //imprime tipos invalidos
            System.exit(1);
        }
        
    }
}

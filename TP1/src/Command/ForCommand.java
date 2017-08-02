/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import model.ConstIntValue;
import model.Matrix;
import model.MatrixValue;
import model.Value;
import model.Variable;

/**
 *
 * @author wendell
 */
public class ForCommand extends Command{
    private Variable var;
    private Value<?> value;
    private CommandBlock cmdblock;

    public ForCommand(Variable var, Value<?> value, CommandBlock cmdblock, int line) {
        super(line);
        this.var = var;
        this.value = value;
        this.cmdblock = cmdblock;
    }
    
    @Override
    public void execute(){
        Value<?> v = (this.value instanceof Variable? ((Variable)this.value).value() : this.value);
        
        if(!(v instanceof MatrixValue)){
            System.out.println(this.line()+": Tipos inválidos");
            System.exit(1);
        }
        
        Matrix m = ((MatrixValue)v).value();
        for(int i = 0; i < m.rows(); i++)
            for(int j = 0; j < m.cols(); j++){
                ConstIntValue civ = new ConstIntValue(m.value(i, j),this.line());
                this.var.setValue(civ);
                this.cmdblock.execute();
            }        
        
    }
}

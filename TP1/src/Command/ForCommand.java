/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import model.Value;
import model.Variable;

/**
 *
 * @author wendell
 */
public class ForCommand extends Command{
    private Variable var;
    private Value<?> value;
    private Command cmd;

    public ForCommand(Variable var, Value<?> value, Command cmd, int line) {
        super(line);
        this.var = var;
        this.value = value;
        this.cmd = cmd;
    }
    
    public void execute(){
        
    }
}

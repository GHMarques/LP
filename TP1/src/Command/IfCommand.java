/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import model.BoolValue;

/**
 *
 * @author wendell
 */
public class IfCommand extends Command{
    private BoolValue expr;
    private Command then, _else;

    public IfCommand(BoolValue expr, Command then, int line){
        super(line);
        this.expr = expr;
        this.then = then;
        this._else = null;
    }

    public IfCommand(BoolValue expr, Command then, Command _else, int line) {
        super(line);
        this.expr = expr;
        this.then = then;
        this._else = _else;
    }
    
    public void execute(){
        if(this.expr.value())
            this.then.execute();
        else
            if(this._else != null)
                  this._else.execute();
    }
    
}

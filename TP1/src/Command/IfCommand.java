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
public class IfCommand {
    private BoolValue expr;
    private Command then, _else;

    public IfCommand(Command then) {
        this.then = then;
    }

    public IfCommand(Command then, Command _else) {
        this.then = then;
        this._else = _else;
    }
    
    public void execute(){
        
    }
    
}

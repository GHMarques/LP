/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author wendell
 */
public class CommandBlock extends Command{
    private List<Command> commands;

    public CommandBlock() {
        super(-1);
        
        commands = new ArrayList<Command>();
    }

    
   public void addCommand(Command c){
        commands.add(c);
    }
    
    public void execute(){
        for (int i = 0; i < this.commands.size(); i++) {
            Command c = this.commands.get(i);
            c.execute();
        }
    }
}

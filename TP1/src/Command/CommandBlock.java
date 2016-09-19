/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import java.util.List;


/**
 *
 * @author wendell
 */
public class CommandBlock extends Command{
    private List commands;

    public CommandBlock(int line) {
        super(line);
    }

    
    void addCommand(Command c){
        commands.add(c);
    }
    
    void execute(){
    
    }
}

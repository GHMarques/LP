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
public class AssignCommand extends Command{
    private Variable var;
    private Value<?> val;

    public AssignCommand(Variable var, Value<?> val, int line) {
        super(line);
        this.var = var;
        this.val = val;
    }
    
    public void execute(){
        Value<?> v = (val instanceof Variable ? (Variable) val.value():val );
        if( v instanceof IntValue){
            ConstIntValue c = new ConstIntValue( ((IntValue) v).value(), val.line());//olhar de novo se e esse val.line()
            var.setValue(c);
        }else if(v instanceof MatrixValue){
            RefMatrixValue ref = new RefMatrixValue( ((MatrixValue) v).value(), val.line()/*...*/);
            
        }else{
            //Tipos invalidos e aborta
        }
        
    }
    
}

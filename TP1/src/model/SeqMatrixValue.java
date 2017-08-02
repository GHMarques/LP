/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author wendell
 */
public class SeqMatrixValue extends MatrixValue{
   private final boolean inverted;
    private Value<?> from;
    private Value<?> to;


    public SeqMatrixValue(Value<?> from, Value<?> to, boolean inverted, int line) {
        super(line);
        this.inverted = inverted;
        this.from = from;
        this.to = to;
    }
   

    @Override
    public Matrix value() {
        Value<?> v1 = (this.from instanceof Variable ? ((Variable)this.from).value() : this.from);
        Value<?> v2 = (this.to instanceof Variable ? ((Variable)this.to).value() : this.to);
                
        if(!(v1 instanceof IntValue && v2 instanceof IntValue)){
            System.out.println(this.line()+": Tipos inválidos");
            System.exit(1);
            return null;
        }
        int i = ((IntValue) v1).value();
        int j = ((IntValue) v2).value();

        if(i > j & !inverted){
            System.out.println(this.line()+": Operação inválida");
            System.exit(1);
            return null;
        }
        if(i < j & inverted){
            System.out.println(this.line()+": Operação inválida");
            System.exit(1);
            return null;
        }

        Matrix retorno = (inverted) ? Matrix.iseq(i,j) : Matrix.seq(i,j);
        return retorno;
    }
}

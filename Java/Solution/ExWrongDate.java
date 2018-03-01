public class ExWrongDate extends Exception {
 
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public ExWrongDate(){
        super("Wrong Date.  System date is already "+SystemDate.getInstance().toString()+"!");
    }
    public ExWrongDate(String message){
        super(message);
    }
}
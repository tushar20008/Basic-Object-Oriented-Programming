public class ExEmployeeNotFound extends Exception {
 
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public ExEmployeeNotFound(){
        super("Employee not found!");
    }
    public ExEmployeeNotFound(Team t, String emp){
        super("Employee ("+emp+") not found for "+t.getTName()+"!");
    }
    public ExEmployeeNotFound(String message){
        super(message);
    }
}
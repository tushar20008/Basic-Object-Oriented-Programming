public class ExInsufficientLeaves extends Exception {
 
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public ExInsufficientLeaves(int leaves){
        super("Insufficient balance.  "+leaves+" days left only!");
    }
    public ExInsufficientLeaves(String message){
        super(message);
    }
}
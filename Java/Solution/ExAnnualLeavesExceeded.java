public class ExAnnualLeavesExceeded extends Exception {
 
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public ExAnnualLeavesExceeded(){
        super("Annual leaves out of range (0-300)!");
    }
    public ExAnnualLeavesExceeded(String message){
        super(message);
    }
}
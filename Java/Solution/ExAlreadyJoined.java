public class ExAlreadyJoined extends Exception {
 
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public ExAlreadyJoined(){
        super("Employee has already joined the team!");
    }
    public ExAlreadyJoined(String message){
        super(message);
    }
}
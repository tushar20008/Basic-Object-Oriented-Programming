public class ExActingHeadNotAssigned extends Exception {
 
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public ExActingHeadNotAssigned(Team t){
        super("Please name a member to be the acting head of "+t.getTName());
    }
    public ExActingHeadNotAssigned(String message){
        super(message);
    }
}
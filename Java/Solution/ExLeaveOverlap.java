public class ExLeaveOverlap extends Exception {
 
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public ExLeaveOverlap(Leaves l){
        super("Overlap with leave from " + l.toString()+"!");
    }
    public ExLeaveOverlap(Employee emp, Day sD, Day oD){
        super(emp.getName()+" is on leave during "+sD.toString()+" to "+oD.toString()+"!");
    }
    public ExLeaveOverlap(Employee emp, Day sD, Day lD, Team t){
        super("Cannot take leave.  "+emp.getName()+" is the acting head of "+t.getTName()+" during "+sD.toString()+" to "+lD.toString()+"!");
    }
    public ExLeaveOverlap(String message){
        super(message);
    }
}
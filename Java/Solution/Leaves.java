
public class Leaves implements Comparable<Leaves>{

	private Employee emp;
	private Day startDate, overDate;
	private int leaves;
	
	public Leaves(Employee emp, Day startvac, Day overvac, int leave)
	{
		this.emp=emp;
		this.startDate=startvac;
		this.overDate=overvac;
		this.leaves=leave;
	}
	public Employee getEmp(){
		return emp;
	}
	public static void checkOverLap(Leaves leave, Leaves l, char overlapType) throws ExLeaveOverlap
	{
		if(l.startDate.dateFormat().compareTo(leave.overDate.dateFormat())>0)
			return;
		else if(l.overDate.dateFormat().compareTo(leave.startDate.dateFormat())<0)
			return;
		else
		{
			if(overlapType=='s')
				throw new ExLeaveOverlap(leave);
			else if(overlapType=='a')
				throw new ExLeaveOverlap(leave.getEmp(),leave.startDate,leave.overDate);
		}
	}
	public int getLeaves(){
		return leaves;
	}
	public String toString()
	{
		return startDate.toString() + " to " + overDate.toString();
	}
	@Override
	public int compareTo(Leaves another) {
		// TODO Auto-generated method stub
		return this.startDate.dateFormat().compareTo(another.startDate.dateFormat());
	}

}


public class ActingHead implements Comparable<ActingHead>{

	private Team t;
	private Day startDate, lastDate;
	private Employee emp;
	public ActingHead(Team t,Day startDate, Day lastDate, Employee emp)
	{
		this.t=t;
		this.startDate=startDate;
		this.lastDate=lastDate;
		this.emp=emp;
	}
	public Team getTeam()
	{
		return t;
	}
	public Employee getEmp()
	{
		return emp;
	}
	
	public String toString()
	{
		return startDate.toString()+" to "+lastDate.toString()+": "+emp.getName();
	}
	public void checkOverlap(Day sD, Day lD) throws ExLeaveOverlap
	{
		if(sD.dateFormat().compareTo(lastDate.dateFormat())>0)
			return;
		else if(lD.dateFormat().compareTo(startDate.dateFormat())<0)
			return;
		else
			throw new ExLeaveOverlap(this.emp,this.startDate,this.lastDate,this.t);
	}
	@Override
	public int compareTo(ActingHead another) {
		// TODO Auto-generated method stub
		return this.startDate.dateFormat().compareTo(another.startDate.dateFormat());
	}
}

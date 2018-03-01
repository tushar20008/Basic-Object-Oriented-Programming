import java.util.ArrayList;


public class Team implements Comparable<Team>{
	
	private String teamName;
	private char role='m';
	private Employee emp;
	private Day dateSetup; 
	public Team(String n, Employee hd)
	{
		teamName=n;
		emp=hd;
		dateSetup=SystemDate.getInstance().clone();
	}
	public static void list(ArrayList<Team> list)
	{
		System.out.printf("%-30s%-10s%-13s\n", "Team Name", "Leader", "Setup Date");
		for (Team t : list)
			if(t.role=='h')
				System.out.printf("%-30s%-10s%-13s\n",t.teamName,t.emp.getName(),t.dateSetup.toString()); //display t.teamName, etc..
	}
	public String getTName()
	{
		return teamName;
	}
	public Employee getEmp()
	{
		return emp;
	}
	public char getRole()
	{
		return role;
	}
	public void changeRole(char role)
	{
		this.role=role;
	}
	@Override
	public int compareTo(Team another) {
		// TODO Auto-generated method stub
		if(this.teamName.compareTo(another.teamName)==0)
			return this.emp.getName().compareTo(another.emp.getName());
		return this.teamName.compareTo(another.teamName);
	}
}

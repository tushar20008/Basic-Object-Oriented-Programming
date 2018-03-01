import java.util.ArrayList;
import java.util.Collections;
public class Company {

	private ArrayList<Employee> allEmployees;
	private ArrayList<Team> allTeams;
	private ArrayList<Leaves> allLeaves;
	private ArrayList<ActingHead> allActingHeads;
	 private static Company instance = new Company();
	 private Company() 
	 {
		 allEmployees=new ArrayList<>();
		 allTeams=new ArrayList<>();
		 allLeaves=new ArrayList<>();
		 allActingHeads=new ArrayList<>();
	 }
	 public static Company getInstance() { return instance; }
	 public void listTeams()
	 {
		 Team.list(allTeams); 
	 }
	 public Employee createEmployee(String name, int leaves) throws ExEmployeeAlreadyExists, ExAnnualLeavesExceeded
	 {
		 Employee emp=null;
		 try{
			 emp=Employee.searchEmployee(allEmployees,name);
			 throw new ExEmployeeAlreadyExists();
		 } catch(ExEmployeeNotFound e)
		 {
			 if(leaves > 300)
				 throw new ExAnnualLeavesExceeded();
			 emp = new Employee(name,leaves);
			 allEmployees.add(emp);
			 Collections.sort(allEmployees);
			 return emp;	
		 }
	 }
	 public Team createTeam(String tName, String head) throws ExEmployeeNotFound , ExTeamAlreadyExists
	 {
		 Employee emp = Employee.searchEmployee(allEmployees,head);
		 
		 for(Team t: allTeams)
			 if(t.getTName().equals(tName))
				 throw new ExTeamAlreadyExists();
		 Team t = new Team(tName, emp);
		 t.changeRole('h');
		 allTeams.add(t);
		 Collections.sort(allTeams); 
		 return t;	
	 }
	 public void addEmployee(Employee e) {
		 allEmployees.add(e);
		 Collections.sort(allEmployees);
		}
		public void removeEmployee(Employee e) {
		 allEmployees.remove(e); 
		}
	public void listEmp()
	{
		for(Employee e: allEmployees)
			System.out.println(e.getName()+" (Entitled Annual Leaves: "+e.getLeaves() +" days)");
	}
	public void addTeam(Team t) 
	{
		allTeams.add(t);
		Collections.sort(allTeams);
	}
	public void removeTeam(Team t)
	{
		allTeams.remove(t);
	}
	public Leaves takeLeave(String name, String startDate, String overDate) throws ExInsufficientLeaves, ExWrongDate, ExLeaveOverlap, ExEmployeeNotFound
	{
		Employee emp=Employee.searchEmployee(allEmployees,name);
		Day startDay=new Day(startDate);
		Day lastDay=new Day(overDate);
		
		SystemDate currentDay=SystemDate.getInstance();
		int length=Day.calVacationLength(currentDay, startDay);
		if(length < 1)
			throw new ExWrongDate();
		length=Day.calVacationLength(startDay,lastDay);
		if(length>emp.getLeaves())
			throw new ExInsufficientLeaves(emp.getLeaves());
		Leaves l=new Leaves(emp,startDay,lastDay,length);
		for(Leaves leave: allLeaves)
			if(leave.getEmp().getName().equals(name))
			{
				Leaves.checkOverLap(leave, l, 's');
			}
		for(ActingHead actHead: allActingHeads)
			if(actHead.getEmp().getName().equals(name))
				actHead.checkOverlap(startDay,lastDay);
		addLeave(l);
		emp.changeLeaves(length);
		return l;
	}
	public void undoLeaves(Leaves leave)
	{
		Employee emp=leave.getEmp();
			emp.changeLeaves(-leave.getLeaves());
	}
	public void redoLeaves(Leaves leave)
	{
		Employee emp=leave.getEmp();
			emp.changeLeaves(leave.getLeaves());
	}
	public int calActingHeads(String head)
	{
		int noActingHeads=0;
		for(Team t: allTeams)
			if(t.getEmp().getName().equals(head) && t.getRole()=='h')
				noActingHeads++;
		return noActingHeads;
	}
	public ActingHead createActingHead(String head,String tName, String actHead, String startDate, String overDate) throws ExTeamNotFound, ExEmployeeNotFound, ExActingHeadNotAssigned, ExLeaveOverlap
	{
		if(tName==null)
		{
			Team notFound=null;
			boolean foundActingHead=true;
			for(Team t: allTeams)
				if(t.getEmp().getName().equals(head) && t.getRole()=='h')
				{
					foundActingHead=false;
					notFound=t;
					for(Team t2: allTeams)
						if(t2.getRole()=='a' && t2.getTName().equals(t.getTName()))
						{
							foundActingHead=true;
							break;
						}
					if(!foundActingHead)		
						throw new ExActingHeadNotAssigned(notFound);
				}
		}
		Team team=null;
		for(Team t: allTeams)
			if(t.getTName().equals(tName))
				team=t;
		if(team==null)
		{
			throw new ExTeamNotFound();
		}
		Employee emp=null;
		for(Team t: allTeams)
			if(t.getEmp().getName().equals(actHead) && t.getTName().equals(tName))
				emp=t.getEmp();
		if(emp==null)
			throw new ExEmployeeNotFound(team,actHead); 
		ActingHead actH=new ActingHead(team,new Day(startDate),new Day(overDate),emp);
		for(Leaves leave: allLeaves)
			if(leave.getEmp().getName().equals(actHead))
				Leaves.checkOverLap(leave,new Leaves(emp,new Day(startDate),new Day(overDate),0),'a');
		allActingHeads.add(actH);
		Collections.sort(allActingHeads);
		return actH;
	}
	public Team changeRole(String tName, String eName, char role)
	{
		for(Team t: allTeams)
			if(t.getEmp().getName().equals(eName) && t.getTName().equals(tName))
			{
				t.changeRole(role);
				return t;
			}
		return null;
	}
	public void addActingHead(ActingHead actHead) 
	{
		allActingHeads.add(actHead);
		Collections.sort(allActingHeads);
	}
	public void removeActingHead(ActingHead actHead)
	{
		allActingHeads.remove(actHead);
	}
	
	public void addLeave(Leaves leave) 
	{
		allLeaves.add(leave);
		Collections.sort(allLeaves);
	}
	public void removeLeaves(Leaves leave)
	{
		allLeaves.remove(leave);
	}
	public void listLeaves()
	{
		for(Employee e: allEmployees)
		{
			System.out.println(e.getName()+":");
			listLeaves(e.getName());
		}
	}
	public void listLeaves(String name)
	{
		boolean recordFound=false;
		for(Leaves leave: allLeaves)
			if(leave.getEmp().getName().equals(name))
			{
				System.out.println(leave.toString());
				recordFound=true;
			}
		if(!recordFound)
			System.out.println("No leave record");
	}
	public Team addTeamMember(String tName, String eName) throws ExTeamNotFound, ExEmployeeNotFound, ExAlreadyJoined
	{
		Team team=null;
		for(Team t: allTeams)
			if(t.getTName().equals(tName))
			{
				team=t;
				if(team.getEmp().getName().equals(eName))
					throw new ExAlreadyJoined();
			}
		if(team==null)
			throw new ExTeamNotFound();
		Employee emp=Employee.searchEmployee(allEmployees,eName);
		team = new Team(tName,emp);
		addTeam(team);
		return team;
	}
	public void listTeamMembers()
	{
		Team team=null;
		for(Team t: allTeams)
		{
			if(team==null)
			{
				System.out.println(t.getTName()+":");
				team=t;
			}
			if(!team.getTName().equals(t.getTName()))
			{
				listActingHeads(team);
				System.out.println("\n"+t.getTName()+":");
				team=t;
			}
			if(t.getRole()=='h')
				System.out.println(t.getEmp().getName()+" (Head of Team)");
			else
				System.out.println(t.getEmp().getName());
		}
		listActingHeads(team);
	}
	public void listActingHeads(Team t)
	{
		boolean hasActingHeads=false;
		for(ActingHead actHead: allActingHeads)
			if(actHead.getTeam().getTName().equals(t.getTName()))
			{
				if(!hasActingHeads)
				{
					System.out.println("Acting heads:");
					hasActingHeads=true;
				}
				System.out.println(actHead.toString());
			}
	}
	public void listRoles(String name) throws ExEmployeeNotFound
	{
		boolean noRole=false;
		Employee emp = Employee.searchEmployee(allEmployees,name);
		for(Team t: allTeams)
			if(t.getEmp().getName().equals(emp.getName()))
			{
				noRole=true;
				if(t.getRole()=='h')
					System.out.println(t.getTName()+" (Head of Team)");
				else
					System.out.println(t.getTName());
			}
		if(!noRole)
			System.out.println("No role");
	}
}

import java.awt.List;
import java.util.ArrayList;


public class Employee implements Comparable<Employee>{

	private String name;
	private int yrLeavesEntitled;
	
	public Employee(String n, int yle){
		name=n;
		yrLeavesEntitled=yle;
	}
	public String getName() { return name;}
	public int getLeaves(){ return yrLeavesEntitled;}
	public void changeLeaves(int vacationLength)
	{
		yrLeavesEntitled-=vacationLength;
	}
	 public static Employee searchEmployee(ArrayList<Employee> list,String nameToSearch) throws ExEmployeeNotFound
	 {
		 for(Employee e: list)
			 if(e.getName().equals(nameToSearch))
				 return e;
		 throw new ExEmployeeNotFound();
	 }
	@Override
	public int compareTo(Employee another) {
		// TODO Auto-generated method stub
		return this.name.compareTo(another.name);
	}
}

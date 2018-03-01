
public class CmdListLeaves implements Command{

	public void execute(String[] cmdParts)
	{
		Company company = Company.getInstance();
		if(cmdParts.length==1)
			company.listLeaves();
		else
		{
			company.listLeaves(cmdParts[1]);
		}
	}
}

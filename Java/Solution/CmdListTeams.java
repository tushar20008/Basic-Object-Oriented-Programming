
public class CmdListTeams implements Command{

	public void execute(String[] cmdParts)
	{
		Company company = Company.getInstance();
		company.listTeams();
	}

}
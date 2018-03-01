
public class CmdListRoles implements Command{

	public void execute(String[] cmdParts)
	{
		Company company = Company.getInstance();
		try {
			company.listRoles(cmdParts[1]);
		} catch (ExEmployeeNotFound e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

}
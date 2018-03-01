
public class CmdSetupTeam extends RecordedCommand{

	Team t;
	public void execute(String[] cmdParts)
	{
		try {
			if(cmdParts.length<3)
                throw new ExInsufficientArguments();
		Company company = Company.getInstance();
		t=company.createTeam(cmdParts[1],cmdParts[2]);
		addUndoCommand(this); //<====== store this command (addUndoCommand is implemented in RecordedCommand.java)
		clearRedoList(); //<====== There maybe some commands stored in the redo list.  Clear them.
		System.out.println("Done.");
		}catch (ExInsufficientArguments e) {
            System.out.println(e.getMessage());
        }catch(ExEmployeeNotFound e){
			 System.out.println(e.getMessage());
		}catch(ExTeamAlreadyExists e){
			System.out.println(e.getMessage());
		}
	}
	@Override
	public void undoMe() {
		// TODO Auto-generated method stub
		Company company = Company.getInstance();
		company.removeTeam(t);
		addRedoCommand(this);
	}

	@Override
	public void redoMe() {
		// TODO Auto-generated method stub
		Company company = Company.getInstance();
		company.addTeam(t);
		addUndoCommand(this); 
	}

}


public class CmdAddTeamMember extends RecordedCommand{

	Company company = Company.getInstance();
	Team t;
	public void execute(String[] cmdParts)
	{
		try {
			if(cmdParts.length<3)
                throw new ExInsufficientArguments();
		
		t=company.addTeamMember(cmdParts[1],cmdParts[2]);

		addUndoCommand(this); //<====== store this command (addUndoCommand is implemented in RecordedCommand.java)
		clearRedoList(); //<====== There maybe some commands stored in the redo list.  Clear them.
		System.out.println("Done.");
		} catch (ExInsufficientArguments e) {
            System.out.println(e.getMessage());
        } catch (ExTeamNotFound e) {
			// TODO Auto-generated catch block
        	System.out.println(e.getMessage());
		} catch (ExEmployeeNotFound e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (ExAlreadyJoined e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	@Override
	public void undoMe() {
		// TODO Auto-generated method stub
		company.removeTeam(t);
		addRedoCommand(this);
	}

	@Override
	public void redoMe() {
		// TODO Auto-generated method stub
		company.addTeam(t);
		addUndoCommand(this); 
	}

}

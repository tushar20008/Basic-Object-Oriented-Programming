
public class CmdHire  extends RecordedCommand{

	Company company = Company.getInstance();
	Employee emp;
	int leave; 
	
	public void execute(String[] cmdParts)
	{
		try {
			if(cmdParts.length<3)
                throw new ExInsufficientArguments();
		
		emp=company.createEmployee(cmdParts[1],Integer.parseInt(cmdParts[2]));
		addUndoCommand(this); //<====== store this command (addUndoCommand is implemented in RecordedCommand.java)
		clearRedoList(); //<====== There maybe some commands stored in the redo list.  Clear them.
		System.out.println("Done.");
		} catch (ExEmployeeAlreadyExists e) {
			 System.out.println(e.getMessage());
		}catch(ExAnnualLeavesExceeded e){
			System.out.println(e.getMessage());
		}catch (ExInsufficientArguments e) {
            System.out.println(e.getMessage());
        }
	}

	@Override
	public void undoMe() {
		// TODO Auto-generated method stub
		company.removeEmployee(emp);
		addRedoCommand(this);
	}

	@Override
	public void redoMe() {
		// TODO Auto-generated method stub
		company.addEmployee(emp);
		addUndoCommand(this); 
	}

}

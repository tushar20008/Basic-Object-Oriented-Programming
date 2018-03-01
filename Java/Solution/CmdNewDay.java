
public class CmdNewDay extends RecordedCommand{

	String oldDate, newDate;
	public void execute(String[] cmdParts)
	{
		oldDate=SystemDate.getInstance().toString();
		newDate=cmdParts[1];
		SystemDate.createTheInstance(newDate);
		addUndoCommand(this); //<====== store this command (addUndoCommand is implemented in RecordedCommand.java)
		clearRedoList(); //<====== There maybe some commands stored in the redo list.  Clear them.
		System.out.println("Done.");
	}
	@Override
	public void undoMe() {
		// TODO Auto-generated method stub
		SystemDate.createTheInstance(oldDate);
		addRedoCommand(this);
	}

	@Override
	public void redoMe() {
		// TODO Auto-generated method stub
		SystemDate.createTheInstance(newDate);
		addUndoCommand(this); 
	}

}

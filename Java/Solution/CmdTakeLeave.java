import java.util.ArrayList;


public class CmdTakeLeave extends RecordedCommand{

	Company company = Company.getInstance();
	int leavesTaken;
	ArrayList<ActingHead> allActingHeads=new ArrayList<>();
	ArrayList<Team> allActingHeadTeams=new ArrayList<>();
	Leaves leave;
	public void execute(String[] cmdParts)
	{
		boolean leaveexecuted=false, actingHeadexecuted=false;
		try {
			if(cmdParts.length<4)
                throw new ExInsufficientArguments();
		
			else 
			{
				leave=company.takeLeave(cmdParts[1],cmdParts[2],cmdParts[3]);
				leaveexecuted=true;
				int noActingHeads=company.calActingHeads(cmdParts[1]);
				for(int i=0; i<noActingHeads*2; i+=2)
				{
					ActingHead actH=null;
					Team t=null;
					if(i+4<cmdParts.length)
					{
						actH=company.createActingHead(cmdParts[1],cmdParts[i+4], cmdParts[i+5], cmdParts[2],cmdParts[3]);
						allActingHeads.add(actH);
						t=company.changeRole(cmdParts[i+4], cmdParts[i+5], 'a');
						allActingHeadTeams.add(t);
					}
					else
						actH=company.createActingHead(cmdParts[1],null, null, cmdParts[2],cmdParts[3]);
				}	
			}
			
		addUndoCommand(this); //<====== store this command (addUndoCommand is implemented in RecordedCommand.java)
		clearRedoList(); //<====== There maybe some commands stored in the redo list.  Clear them.
		System.out.println("Done.");
		actingHeadexecuted=true;
		} catch (ExInsufficientArguments e) {
            System.out.println(e.getMessage());
        } catch (ExEmployeeNotFound e) {
			// TODO Auto-generated catch block
        	System.out.println(e.getMessage());
		} catch (ExInsufficientLeaves e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (ExWrongDate e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (ExLeaveOverlap e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (ExTeamNotFound e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (ExActingHeadNotAssigned e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		finally{
			if(leaveexecuted && !actingHeadexecuted)
				undoMe();
		}
	}
	@Override
	public void undoMe() {
		// TODO Auto-generated method stub
		company.undoLeaves(leave);
		company.removeLeaves(leave);
		for(ActingHead actHead: allActingHeads)
			company.removeActingHead(actHead);
		for(Team t: allActingHeadTeams)
			company.changeRole(t.getTName(), t.getEmp().getName(), 'm');
		addRedoCommand(this);
	}

	@Override
	public void redoMe() {
		// TODO Auto-generated method stub
		company.addLeave(leave);
		company.redoLeaves(leave);
		for(ActingHead actHead: allActingHeads)
			company.addActingHead(actHead);
		for(Team t: allActingHeadTeams)
			company.changeRole(t.getTName(), t.getEmp().getName(), 'a');
		addUndoCommand(this); 
	}

}

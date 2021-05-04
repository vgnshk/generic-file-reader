package designtest.striim.genericfilereader.operations;

import designtest.striim.genericfilereader.model.Data;

/**
 * Operation is anything that processes the file data
 * Next operation in the sequence is linked
 * Concrete classes should define the process() and setStatus() methods
 */
public abstract class Operation {
	private Operation next;
	
	public Operation addNext(Operation nextOperation) {
		this.next = nextOperation;
		return nextOperation;
	}
	
	public abstract Data process(Data incoming);
	public abstract void setStatus(Data data);
	
	protected Data processNext(Data incoming) {
		setStatus(incoming);
		if(next == null) {
			return incoming;
		}
		return next.process(incoming);
	}
}

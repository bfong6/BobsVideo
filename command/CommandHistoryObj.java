package shop.command;
import java.util.Stack;

final class CommandHistoryObj implements CommandHistory {
	Stack<UndoableCommand> _undoStack = new Stack<UndoableCommand>();
	Stack<UndoableCommand> _redoStack = new Stack<UndoableCommand>();
	RerunnableCommand _undoCmd = new RerunnableCommand () {
		public boolean run () {
			boolean result = !_undoStack.empty();
			if (result) {
				UndoableCommand cmd = _undoStack.pop();
				_redoStack.add(cmd);
				cmd.undo();
			}
			return result;
		}
	};
	RerunnableCommand _redoCmd = new RerunnableCommand () {
		public boolean run () {
			boolean result = !_redoStack.empty();
			if (result) {
				UndoableCommand cmd = _redoStack.pop();
				_undoStack.add(cmd);
				cmd.redo();
			}
			return result;
		}
	};

	public void add(UndoableCommand cmd) {
		_undoStack.add(cmd);
		_redoStack.clear();
	}

	public RerunnableCommand getUndo() {
		//if (_undoStack.empty()) throw new EmptyStackException();
		return _undoCmd;
	}

	public RerunnableCommand getRedo() {
		//if (_redoStack.empty()) throw new EmptyStackException();
		return _redoCmd;
	}

	// For testing
	UndoableCommand topUndoCommand() {
		if (_undoStack.empty())
			return null;
		else
			return _undoStack.peek();
	}
	// For testing
	UndoableCommand topRedoCommand() {
		if (_redoStack.empty())
			return null;
		else
			return _redoStack.peek();
	}
}

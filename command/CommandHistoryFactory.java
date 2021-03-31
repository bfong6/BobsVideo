package shop.command;


public class CommandHistoryFactory {
	private CommandHistoryFactory() {}
	static public CommandHistory newCommandHistory() {
		CommandHistoryObj history = new CommandHistoryObj();
		return history;
	}
}

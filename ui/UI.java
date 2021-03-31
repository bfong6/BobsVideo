package shop.ui;

/**
 * 
 * A UI interface.
 */
public interface UI {
	/**
	 * Takes a UIMenu and uses user input to run the corresponding action in the menu, or
	 * the default action if input is invalid.
	 * @param menu the menu
	 */
	public void processMenu(UIUseInt menu);
	/**
	 * Takes a UIForm and parses user input for each field in the form.
	 * @param form the form
	 * @return a string array with entries for each field in the form.
	 */
	public String[] processForm(UIUseInt form);
	/**
	 * Displays a system message.
	 * @param message the message to be displayed.
	 */
	public void displayMessage(String message);
	/**
	 * Displays a system error.
	 * @param message the error message to be displayed.
	 */
	public void displayError(String message);
}

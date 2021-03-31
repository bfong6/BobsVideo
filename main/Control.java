package shop.main;

import shop.ui.UI;
import shop.ui.UIError;
import shop.data.Inventory;

class Control {
	private MState state;
	private static final MState StartState = MState.START;
	private static final MState ExitState = MState.EXIT;
	private static final MState ExitedState = MState.EXITED;
	private Inventory _inventory;
	private UI _ui;

	Control(Inventory inventory, UI ui) {
		_inventory = inventory;
		_ui = ui;
		state = StartState;
	}

	void run() {
		try {
			while (state != ExitedState) {
				_ui.processMenu(state.build(this, _ui, _inventory));
			}
		} catch (UIError e) {
			_ui.displayError("UI closed");
		}
	}

	void setStart() {
		this.state = StartState;
	}
	void setExit() {
		this.state = ExitState;
	}
	void setExited() {
		this.state = ExitedState;
	}
}

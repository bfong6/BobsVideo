package shop.ui;

import java.util.ArrayList;
import java.util.List;

class UIMenuBuilder implements MenuBuilder {
	private final List _menu;
	public UIMenuBuilder() {
		_menu = new ArrayList();
	}
	public UIMenu toUIMenu(String heading) {
		if (null == heading)
			throw new IllegalArgumentException();
		if (_menu.size() <= 1)
			throw new IllegalStateException();
		MenuPair[] array = new MenuPair[_menu.size()];
		for (int i = 0; i < _menu.size(); i++)
			array[i] = (MenuPair) (_menu.get(i));
		return new UIMenu(heading, array);
	}
	public void add(String prompt, UIMenuAction action) {
		if (null == action)
			throw new IllegalArgumentException();
		_menu.add(new MenuPair(prompt, action));
	}
}

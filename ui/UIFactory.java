package shop.ui;

import java.util.HashMap;

import shop.data.Video;

public class UIFactory {
	private UIFactory() {}
	static private UI _UI = new PopupUI();
	//static private UI _UI = new TextUI();
	public static HashMap<Video, Video> vidPool = new HashMap<Video, Video>();

	static public UI ui () {
		return _UI;
	}
	
	public static FormBuilder formBuilder() {
		return new UIFormBuilder();
	}
	
	public static MenuBuilder menuBuilder() {
		return new UIMenuBuilder();
	}
}

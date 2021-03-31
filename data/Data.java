package shop.data;

import shop.command.RerunnableCommand;
import shop.command.UndoableCommand;
import shop.ui.UIFactory;

/**
 * A static class for accessing data objects.
 */
public class Data {
	private Data() {}
	/**
	 * Returns a new Inventory.
	 */
	static public final Inventory newInventory() {
		return new InventorySet();
	}

	/**
	 * Factory method for Video objects.
	 * Title and director are "trimmed" to remove leading and final space.
	 * @throws IllegalArgumentException if Video invariant violated.
	 */
	static public Video newVideo(String title, int year, String director) {
		if (title == null) throw new IllegalArgumentException("Invalid title");
		if (director == null) throw new IllegalArgumentException("Invalid director");
		String t = title.trim();
		String d = director.trim();
		if (t.length() == 0) throw new IllegalArgumentException("Invalid title");
		if (year < 1801 | year > 4999) throw new IllegalArgumentException("Invalid year");
		if (d.length() == 0) throw new IllegalArgumentException("Invalid director name");
		VideoObj v = new VideoObj(t, year, d);
		if (v.equals(UIFactory.vidPool.get(v))) {
			return UIFactory.vidPool.get(v);
		}
		UIFactory.vidPool.put(v, v);
		return v;
	}

	/**
	 * Returns a command to add or remove copies of a video from the inventory.
	 * <p>The returned command has the following behavior:</p>
	 * <ul>
	 * <li>If a video record is not already present (and change is
	 * positive), a record is created.</li>
	 * <li>If a record is already present, <code>numOwned</code> is
	 * modified using <code>change</code>.</li>
	 * <li>If <code>change</code> brings the number of copies to be less
	 * than one, the record is removed from the inventory.</li>
	 * </ul>
	 * @param video the video to be added.
	 * @param change the number of copies to add (or remove if negative).
	 * @throws IllegalArgumentException if <code>inventory<code> not created by a call to <code>newInventory</code>.
	 */
	static public UndoableCommand newAddCmd(Inventory inventory, Video video, int change) {
		if (!(inventory instanceof InventorySet))
			throw new IllegalArgumentException();
		return new CmdAdd((InventorySet) inventory, video, change);
	}

	/**
	 * Returns a command to check out a video.
	 * @param video the video to be checked out.
	 */
	static public UndoableCommand newOutCmd(Inventory inventory, Video video) {
		return new CmdOut((InventorySet) inventory, video);
	}

	/**
	 * Returns a command to check in a video.
	 * @param video the video to be checked in.
	 */
	static public UndoableCommand newInCmd(Inventory inventory, Video video) {
		return new CmdIn((InventorySet) inventory, video);
	}

	/**
	 * Returns a command to remove all records from the inventory.
	 */
	static public UndoableCommand newClearCmd(Inventory inventory) {
		return new CmdClear((InventorySet) inventory);
	}

	/**
	 * Returns a command to undo that will undo the last successful UndoableCommand. 
	 */
	static public RerunnableCommand newUndoCmd(Inventory inventory) {
		InventorySet inv = (InventorySet) inventory;
		RerunnableCommand cmd = inv.getHistory().getUndo();
		return cmd;
	}

	/**
	 * Returns a command to redo that last successfully undone command. 
	 */
	static public RerunnableCommand newRedoCmd(Inventory inventory) {
		InventorySet inv = (InventorySet) inventory;
		RerunnableCommand cmd = inv.getHistory().getRedo();
		return cmd;
	}
}  

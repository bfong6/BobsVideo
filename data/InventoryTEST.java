package shop.data;

import junit.framework.TestCase;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class InventoryTEST extends TestCase {
	public InventoryTEST(String name) {
		super(name);
	}

	public void testSize() {
		InventorySet x = new InventorySet();
		Video a = new VideoObj("X", 2009, "Y");
		assertEquals(x.size(), 0);
		x.addNumOwned(a, 1);
		assertEquals(x.size(), 1);
	}

	public void testAddNumOwned() {
		InventorySet x = new InventorySet();
		Video a = new VideoObj("X", 2009, "Y");
		x.addNumOwned(a, 1);
		assertEquals(x.size(), 1);
		x.addNumOwned(a, 2);
		assertEquals(x.size(), 1);
		assertEquals(x.get(a).toString(), "X (2009) : Y [3,0,0]"); 
	}

	public void testCheckOutCheckIn() {
		InventorySet x = new InventorySet();
		Video a = new VideoObj("X", 2009, "Y");
		Video b = new VideoObj("A", 2016, "B");
		try {
			x.checkOut(a);
			fail();
		} catch (IllegalArgumentException e) { }
		x.addNumOwned(a, 1);
		assertEquals(x.get(a).toString(), "X (2009) : Y [1,0,0]");
		x.checkOut(a);
		assertEquals(x.get(a).toString(), "X (2009) : Y [1,1,1]");
		try {
			x.checkOut(a);
			fail();
		} catch (IllegalArgumentException e) { }
		try {
			x.checkIn(b);
			fail();
		} catch (IllegalArgumentException e) { }
		x.checkIn(a);
		assertEquals(x.get(a).toString(), "X (2009) : Y [1,0,1]");
		try {
			x.checkIn(a);
			fail();
		} catch (IllegalArgumentException e) { } 
	}

	public void testClear() {
		InventorySet x = new InventorySet();
		Video a = new VideoObj("X", 2009, "Y");
		x.addNumOwned(a, 1);
		assertEquals(x.size(), 1);
		x.clear();
		assertEquals(0, x.size()); 
	}

	public void testGet() {
		InventorySet x = new InventorySet();
		Video a = new VideoObj("X", 2009, "Y");
		x.addNumOwned(a, 1);
		assertEquals(x.get(a).toString(), "X (2009) : Y [1,0,0]");
	}

	public void testIterator1() {
		InventorySet x = new InventorySet();
		HashSet<Video> h = new HashSet<Video>();
		Video a = new VideoObj("X", 2009, "Y");
		Video b = new VideoObj("A", 2016, "B");
		x.addNumOwned(a, 1);
		x.addNumOwned(b, 1);
		h.add(a);
		h.add(b);
		Iterator<Record> i = x.iterator();
		while (i.hasNext()) {
			Record r = i.next();
			assertTrue(h.contains(r.video()));
			h.remove(r.video());
		}
		assertTrue(h.isEmpty());
	}
	public void testIterator2() {
		InventorySet x = new InventorySet();
		LinkedList<Video> l = new LinkedList<Video>();
		Video a = new VideoObj("X", 2009, "Y");
		Video b = new VideoObj("A", 2016, "B");
		Video c = new VideoObj("F", 2011, "G");
		x.addNumOwned(a, 2);
		x.addNumOwned(b, 1);
		x.addNumOwned(c, 3);
		l.add(c);
		l.add(a);
		l.add(b);
		Iterator<Record> i1 = x.iterator(new NumOwnedComparator());
		Iterator<Video> i2 = l.iterator();
		while (i1.hasNext()) {
			Record r1 = i1.next();
			Video v = i2.next();
			assertEquals(r1.video(), v);
		}
	}

}

class NumOwnedComparator implements java.util.Comparator<Record> {
	public int compare (Record o1, Record o2) {
		Record r1 = o1;
		Record r2 = o2;
		return r2.numOwned() - r1.numOwned();
	}
}

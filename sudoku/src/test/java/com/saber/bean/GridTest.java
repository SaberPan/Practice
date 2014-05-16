package com.saber.bean;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GridTest {

	@Test
	public void testIsOnlyOneEmptyOnX() throws Exception {
		Grid grid = new Grid();
		assertEquals(false, grid.isOnlyOneEmptyOnX(0, 0));

		grid.setValue(1, 0, 1);
		assertEquals(true, grid.isOnlyOneEmptyOnX(0, 0));

		grid.setValue(2, 0, 2);
		assertEquals(false, grid.isOnlyOneEmptyOnX(0, 0));

		grid = new Grid();
		grid.setValue(1, 0, 0);
		grid.setValue(8, 2, 0);
		grid.setValue(4, 0, 1);
		grid.setValue(2, 0, 2);
		assertEquals(false, grid.isOnlyOneEmptyOnX(1, 0));
		assertEquals(true, grid.isOnlyOneEmptyOnX(2, 1));
	}

	@Test
	public void testIsOnlyOneEmptyOnY() throws Exception {
		Grid grid = new Grid();
		assertEquals(false, grid.isOnlyOneEmptyOnY(0, 0));

		grid.setValue(1, 1, 0);
		assertEquals(true, grid.isOnlyOneEmptyOnY(0, 0));

		grid.setValue(2, 2, 0);
		assertEquals(false, grid.isOnlyOneEmptyOnY(0, 0));

		grid = new Grid();
		grid.setValue(1, 0, 0);
		grid.setValue(8, 2, 0);
		grid.setValue(4, 0, 1);
		grid.setValue(2, 0, 2);
		assertEquals(false, grid.isOnlyOneEmptyOnY(1, 0));
		assertEquals(true, grid.isOnlyOneEmptyOnY(2, 0));
	}

	@Test
	public void testIsNotEmptyOnY() throws Exception {
		Grid grid = new Grid();
		assertEquals(false, grid.isNotEmptyOnY(0, 0));

		grid.setValue(1, 1, 0);
		assertEquals(false, grid.isNotEmptyOnY(0, 0));

		grid.setValue(2, 2, 0);
		assertEquals(true, grid.isNotEmptyOnY(0, 0));
	}

	@Test
	public void testIsNotEmptyOnX() throws Exception {
		Grid grid = new Grid();
		assertEquals(false, grid.isNotEmptyOnX(0, 0));

		grid.setValue(1, 0, 1);
		assertEquals(false, grid.isNotEmptyOnX(0, 0));

		grid.setValue(2, 0, 2);
		assertEquals(true, grid.isNotEmptyOnX(0, 0));
	}

	@Test
	public void testFindEmptyIndexOnX() throws Exception {
		Grid grid = new Grid();
		assertEquals(1, grid.findEmptyIndexOnX(0, 0));

		grid.setValue(1, 0, 1);
		assertEquals(2, grid.findEmptyIndexOnX(0, 0));

		grid.setValue(2, 0, 2);
		assertEquals(-1, grid.findEmptyIndexOnX(0, 0));
	}

	@Test
	public void testFindEmptyIndexOnY() throws Exception {
		Grid grid = new Grid();
		assertEquals(1, grid.findEmptyIndexOnY(0, 0));

		grid.setValue(1, 1, 0);
		assertEquals(2, grid.findEmptyIndexOnY(0, 0));

		grid.setValue(2, 2, 0);
		assertEquals(-1, grid.findEmptyIndexOnY(0, 0));
	}

	@Test
	public void testIsFullOnX() throws Exception {
		Grid grid = new Grid();
		assertEquals(false, grid.isFullOnX(0));
		grid.setValue(1, 0, 0);
		grid.setValue(2, 0, 1);
		grid.setValue(3, 0, 2);

		assertEquals(true, grid.isFullOnX(0));
	}

	@Test
	public void testIsFullOnY() throws Exception {
		Grid grid = new Grid();
		assertEquals(false, grid.isFullOnY(0));
		grid.setValue(1, 0, 0);
		grid.setValue(2, 1, 0);
		grid.setValue(3, 2, 0);

		assertEquals(true, grid.isFullOnY(0));
	}

}

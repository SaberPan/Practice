package com.saber.bean;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.saber.service.InputService;
import com.saber.util.PathUtils;

public class SudokuGridTest {

	@Test
	public void testGetAnotherTwoGridOnY() throws Exception {
		String filePath = PathUtils.getPath("com/saber/bean/testGetAnotherTwoGridOnY.xlsx");
		SudokuGrid sdkGrid = new InputService().inputByFile(filePath);
		int x = 5;
		int y = 3;

		List<Grid> list = sdkGrid.getAnotherTwoGridOnY(x, y);

		assertEquals("{1,2,3,<null>,<null>,<null>,<null>,<null>,<null>}", list.get(0).toString());
		assertEquals("{<null>,<null>,<null>,6,<null>,<null>,<null>,<null>,<null>}", list.get(1).toString());
	}

	@Test
	public void testGetAnotherTwoGridOnX() throws Exception {
		String filePath = PathUtils.getPath("com/saber/bean/testGetAnotherTwoGridOnX.xlsx");
		SudokuGrid sdkGrid = new InputService().inputByFile(filePath);
		int x = 5;
		int y = 3;

		List<Grid> list = sdkGrid.getAnotherTwoGridOnX(x, y);

		assertEquals("{<null>,3,<null>,<null>,4,<null>,<null>,5,<null>}", list.get(0).toString());
		assertEquals("{<null>,<null>,2,<null>,<null>,<null>,<null>,<null>,<null>}", list.get(1).toString());

	}

}

package crew82.gameoflife.simple;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestGameOfLife
{
	@Test
	public void testCountNeighborsAll()
	{
		GameOfLife life = new GameOfLife(new boolean[][]
		{
			{true , true , true },
			{true , true , true },
			{true , true , true },
		});
		assertEquals("All corner", 3, life.countNeighbors(0, 0));
		assertEquals("All border", 5, life.countNeighbors(0, 1));
		assertEquals("All corner", 3, life.countNeighbors(0, 2));
		assertEquals("All border", 5, life.countNeighbors(1, 0));
		assertEquals("All middle", 8, life.countNeighbors(1, 1));
		assertEquals("All border", 5, life.countNeighbors(1, 2));
		assertEquals("All corner", 3, life.countNeighbors(2, 0));
		assertEquals("All border", 5, life.countNeighbors(2, 1));
		assertEquals("All corner", 3, life.countNeighbors(2, 2));
	}

	@Test
	public void testWillBeBorn3()
	{
		GameOfLife life = new GameOfLife(new boolean[][]
		{
			{false, false, false},
			{false, false, false},
			{true , true , true },
		});
		assertEquals("3 neighbors is born", true, life.willBeAliveNextTurn(1, 1));
	}
}

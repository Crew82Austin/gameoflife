package crew82.gameoflife.deluxe;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;

import org.junit.Test;

public class TestGameOfLifeBoard
{
	@Test
	public void testCountNeighbors0()
	{
		GameOfLifeBoard life = new GameOfLifeBoard(new boolean[][]
		{
			{false, false, false},
			{false, false, false},
			{false, false, false},
		});
		for (int i = 0 ; i < 3 ; ++i)
		{
			for (int j = 0 ; j < 3 ; ++j)
			{
				assertEquals("at " + i + "," + j, 0, life.countNeighbors(i, j));
			}
		}
	}

	@Test
	public void testCountNeighborsCenter()
	{
		GameOfLifeBoard life = new GameOfLifeBoard(new boolean[][]
		{
			{false, false, false},
			{false, true , false},
			{false, false, false},
		});
		for (int i = 0 ; i < 3 ; ++i)
		{
			for (int j = 0 ; j < 3 ; ++j)
			{
				if (i == 1 && j == 1)
				{
					assertEquals("at " + i + "," + j, 0, life.countNeighbors(i, j));
				}
				else
				{
					assertEquals("at " + i + "," + j, 1, life.countNeighbors(i, j));
				}
			}
		}
	}

	@Test
	public void testCountNeighborsAll()
	{
		GameOfLifeBoard life = new GameOfLifeBoard(new boolean[][]
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
	public void testWillBeAliveUnderpopulated0()
	{
		GameOfLifeBoard life = new GameOfLifeBoard(new boolean[][]
		{
			{false, false, false},
			{false, true , false},
			{false, false, false},
		});
		assertEquals("No neighbors dies", false, life.willBeAliveNextTurn(1, 1));
	}

	@Test
	public void testWillBeAliveUnderpopulated1()
	{
		GameOfLifeBoard life = new GameOfLifeBoard(new boolean[][]
		{
			{false, false, false},
			{false, true , true },
			{false, false, false},
		});
		assertEquals("1 neighbor dies", false, life.willBeAliveNextTurn(1, 1));
	}

	@Test
	public void testWillBeAlivePopulated2()
	{
		GameOfLifeBoard life = new GameOfLifeBoard(new boolean[][]
		{
			{true , false, false},
			{false, true , true },
			{false, false, false},
		});
		assertEquals("2 neighbors lives", true, life.willBeAliveNextTurn(1, 1));
	}

	@Test
	public void testWillBeAlivePopulated3()
	{
		GameOfLifeBoard life = new GameOfLifeBoard(new boolean[][]
		{
			{true , true , false},
			{false, true , true },
			{false, false, false},
		});
		assertEquals("3 neighbors lives", true, life.willBeAliveNextTurn(1, 1));
	}

	@Test
	public void testWillBeAliveOverpopulated4()
	{
		GameOfLifeBoard life = new GameOfLifeBoard(new boolean[][]
		{
			{true , true , true },
			{false, true , true },
			{false, false, false},
		});
		assertEquals("4 neighbors dies", false, life.willBeAliveNextTurn(1, 1));
	}

	@Test
	public void testWillBeAliveOverpopulated5()
	{
		GameOfLifeBoard life = new GameOfLifeBoard(new boolean[][]
		{
			{true , true , true },
			{false, true , true },
			{true , false, false},
		});
		assertEquals("4 neighbors dies", false, life.willBeAliveNextTurn(1, 1));
	}

	@Test
	public void testWillStayDead0()
	{
		GameOfLifeBoard life = new GameOfLifeBoard(new boolean[][]
		{
			{false, false, false},
			{false, false, false},
			{false, false, false},
		});
		assertEquals("No neighbors stays dead", false, life.willBeAliveNextTurn(1, 1));
	}

	@Test
	public void testWillStayDead1()
	{
		GameOfLifeBoard life = new GameOfLifeBoard(new boolean[][]
		{
			{false, false, false},
			{false, false, false},
			{false, false, true },
		});
		assertEquals("1 neighbors stays dead", false, life.willBeAliveNextTurn(1, 1));
	}

	@Test
	public void testWillStayDead2()
	{
		GameOfLifeBoard life = new GameOfLifeBoard(new boolean[][]
		{
			{false, false, false},
			{false, false, false},
			{false, true , true },
			
		});
		assertEquals("2 neighbors stays dead", false, life.willBeAliveNextTurn(1, 1));
	}

	@Test
	public void testWillBeBorn3()
	{
		GameOfLifeBoard life = new GameOfLifeBoard(new boolean[][]
		{
			{false, false, false},
			{false, false, false},
			{true , true , true },
		});
		assertEquals("3 neighbors is born", true, life.willBeAliveNextTurn(1, 1));
	}

	@Test
	public void testWillStayDead4()
	{
		GameOfLifeBoard life = new GameOfLifeBoard(new boolean[][]
		{
			{false, false, false},
			{false, false, true },
			{true , true , true },
		});
		assertEquals("4 neighbors stays dead", false, life.willBeAliveNextTurn(1, 1));
	}

	@Test
	public void testPrintBoard()
	{
		GameOfLifeBoard life = new GameOfLifeBoard(new boolean[][]
		{
			{false, false, false, true },
			{false, false, true , false},
			{true , true , true , false},
		});
		
		ByteArrayOutputStream outBytes = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(outBytes);
		life.print(out);
		assertEquals("+----+\n"
				+ "|   X|\n"
				+ "|  X |\n"
				+ "|XXX |\n"
				+ "+----+\n",
				outBytes.toString());
	}

	@Test
	public void testCopyCells()
	{
		GameOfLifeBoard life = new GameOfLifeBoard(new boolean[][]
		{
			{false, false, false, true },
			{false, false, true , false},
			{true , true , true , false},
		});
		GameOfLifeBoard life2 = new GameOfLifeBoard(life.copyCells());

		ByteArrayOutputStream outBytes = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(outBytes);
		life2.print(out);
		assertEquals("+----+\n"
				+ "|   X|\n"
				+ "|  X |\n"
				+ "|XXX |\n"
				+ "+----+\n",
				outBytes.toString());
	}

	@Test
	public void testUpdate()
	{
		GameOfLifeBoard life = new GameOfLifeBoard(new boolean[][]
		{
			{false, false, false, true },
			{false, false, true , false},
			{true , true , true , false},
		});
		life.update();

		ByteArrayOutputStream outBytes = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(outBytes);
		life.print(out);
		assertEquals("+----+\n"
				+ "|    |\n"
				+ "|  XX|\n"
				+ "| XX |\n"
				+ "+----+\n",
				outBytes.toString());
	}

	@Test
	public void testReadCells() throws IOException
	{
		GameOfLifeBoard life = new GameOfLifeBoard(new boolean[][]
		{
			{false, false, false, true },
			{false, false, true , false},
			{true , true , true , false},
		});

		BufferedReader input = new BufferedReader(new StringReader(
				"+-----+\n"
						+ "|X X X|\n"
						+ "|XXXXX|\n"
						+ "|X   X|\n"
						+ "|  X  |\n"
						+ "|XXX  |\n"
						+ "|  XXX|\n"
						+ "+-----+\n"));
		life.readCells(input);

		ByteArrayOutputStream outBytes = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(outBytes);
		life.print(out);
		assertEquals("+-----+\n"
						+ "|X X X|\n"
						+ "|XXXXX|\n"
						+ "|X   X|\n"
						+ "|  X  |\n"
						+ "|XXX  |\n"
						+ "|  XXX|\n"
						+ "+-----+\n",
				outBytes.toString());
	}

	@Test
	public void testGetCell()
	{
		GameOfLifeBoard life = new GameOfLifeBoard(new boolean[][]
		{
			{false, false, false, true },
			{false, false, true , false},
			{true , true , true , false},
		});

		assertEquals(3,  life.getRows());
		assertEquals(4,  life.getColumns());
		assertTrue(life.getCell(0, 3));
		assertTrue(life.getCell(2, 1));
		assertFalse(life.getCell(2, 3));
	}

	@Test
	public void testSetCell()
	{
		GameOfLifeBoard life = new GameOfLifeBoard(new boolean[][]
		{
			{false, false, false, true },
			{false, false, true , false},
			{true , true , true , false},
		});

		life.setCell(0, 3, false);
		life.setCell(1, 0, true);
		life.setCell(1, 3, false);
		life.setCell(2, 1, true);

		ByteArrayOutputStream outBytes = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(outBytes);
		life.print(out);
		assertEquals("+----+\n"
				+ "|    |\n"
				+ "|X X |\n"
				+ "|XXX |\n"
				+ "+----+\n",
				outBytes.toString());
	}
}

package org.crew82austin.gameoflife.simple;


public class GameOfLife
{
	public static void main(String[] args) throws Exception
	{
		GameOfLife board = new GameOfLife();
		do
		{
			board.print();
			board.update();
			System.out.println("Hit <ENTER> to continue to next turn");
		}
		while(System.in.read() > 0);
	}

	private boolean[][] cells;

	public GameOfLife()
	{
		cells = new boolean[30][70];
		randomizeCells();
	}

	public GameOfLife(boolean[][] cellArray)
	{
		cells = cellArray;
	}

	public void randomizeCells()
	{
		for(int i = 0 ; i < cells.length; ++i)
		{
			for(int j = 0 ; j < cells[0].length ; ++j)
			{
				cells[i][j] = (Math.random() >= .5);
			}
		}
	}

	public void print()
	{
		for (int i = 0 ; i < cells.length ; ++i)
		{
			for (int j = 0 ; j < cells[i].length ; ++j)
			{
				if (cells[i][j])
				{
					System.out.print('X');
				}
				else
				{
					System.out.print(' ');
				}
			}
			System.out.println();
		}
	}

	public void update()
	{
		boolean[][] newcells = new boolean[cells.length][cells[0].length];
		for (int i = 0 ; i < newcells.length; ++i)
		{
			for(int j = 0 ; j < newcells[i].length; ++j)
			{
				newcells[i][j] = willBeAliveNextTurn(i, j);
			}
		}
		cells = newcells;
	}

	public boolean willBeAliveNextTurn(int i, int j)
	{
		int neighbors = countNeighbors(i, j);
		switch(neighbors)
		{
		case 2:
			boolean isAlive = cells[i][j];
			return isAlive;
		case 3:
			return true;
		default:
			return false;	
		}
	}

	public int countNeighbors(int row, int col)
	{
		int neighbors = 0;
		for (int i = Math.max(0, row - 1) ; i <= Math.min(cells.length - 1, row + 1) ; ++i)
		{
			for (int j = Math.max(0, col - 1) ; j <= Math.min(cells[0].length - 1, col + 1) ; ++j)
			{
				if (i != row || j != col)
				{
					if (cells[i][j])
					{
						++neighbors;
					}
				}
			}
		}
		return neighbors;
	}

	public boolean getCell(int row, int col)
	{
		return cells[row][col];
	}

	public void setCell(int row, int col, boolean isOn)
	{
		cells[row][col] = isOn;
	}
}

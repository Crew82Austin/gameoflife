package crew82.gameoflife.deluxe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

public class GameOfLifeBoard
{
	private boolean[][] cells;

	public GameOfLifeBoard(boolean[][] cellArray)
	{
		cells = cellArray;
		cells = copyCells();
	}

	public void setCell(int row, int col, boolean value)
	{
		cells[row][col] = value;
	}

	public boolean getCell(int row, int col)
	{
		return cells[row][col];
	}

	public int getColumns() {
		return cells[0].length;
	}

	public int getRows() {
		return cells.length;
	}

	public boolean[][] copyCells()
	{
		boolean[][] copyCells = new boolean[getRows()][getColumns()];
		for (int i = 0 ; i < getRows() ; ++i)
		{
			for (int j = 0 ; j < getColumns() ; ++j)
			{
				copyCells[i][j] = cells[i][j];
			}
		}
		return copyCells;
	}

	public void print(PrintStream out)
	{
		out.print('+');
		for (int j = 0 ; j < getColumns() ; ++j)
		{
			out.print('-');
		}
		out.print('+');
		out.println();
		for (int i = 0 ; i < getRows() ; ++i)
		{
			out.print('|');
			for (int j = 0 ; j < cells[i].length ; ++j)
			{
				if (cells[i][j])
				{
					out.print('X');
				}
				else
				{
					out.print(' ');
				}
			}
			out.print('|');
			out.println();
		}
		out.print('+');
		for (int j = 0 ; j < getColumns() ; ++j)
		{
			out.print('-');
		}
		out.print('+');
		out.println();
	}

	public void readCells(BufferedReader input) throws IOException
	{
		String firstLine = input.readLine();
		if (firstLine == null)
		{
			throw new IOException("No first line");
		}
		if (! firstLine.matches("[+][-]*[+]"))
		{
			throw new IOException("Read invalid first line: " + firstLine);
		}
		int ncols = firstLine.length() - 2;
		ArrayList<boolean[]> cellsRead = new ArrayList<boolean[]>();
		while(true)
		{
			String line = input.readLine();
			if (line == null)
			{
				throw new IOException("Missing last line");
			}
			if (line.matches("[+][-]*[+]"))
			{
				break;
			}
			if (line.length() != ncols + 2)
			{
				throw new IOException("Read invalid row: " + line);
			}
			if (! line.matches("[|][X ]*[|]"))
			{
				throw new IOException("Read invalid row: " + line);
			}
			boolean[] row = new boolean[ncols];
			for(int i = 0 ; i < line.length() - 2 ; ++i)
			{
				row[i] = line.charAt(i + 1) == 'X';
			}
			cellsRead.add(row);
		}
		cells = cellsRead.toArray(new boolean[cellsRead.size()][]);
	}

	public void randomizeCells()
	{
		for(int i = 0 ; i < getRows(); ++i)
		{
			for(int j = 0 ; j < getColumns() ; ++j)
			{
				cells[i][j] = Math.random() >= .5;
			}
		}
	}

	public void update()
	{
		boolean[][] newcells = new boolean[getRows()][getColumns()];
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
		for (int i = Math.max(0, row - 1) ; i <= Math.min(getRows() - 1, row + 1) ; ++i)
		{
			for (int j = Math.max(0, col - 1) ; j <= Math.min(getColumns() - 1, col + 1) ; ++j)
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
}

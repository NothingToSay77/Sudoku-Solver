import java.util.*; 
import java.io.*;

public class Backtracker
{
	private int[][] Taken;
	private int[][] currsol;

	Backtracker(Vector<Integer> input)
	{
		Taken=new int[9][9];
		currsol=new int[9][9];
		int i=0;
		int j=0;
		for(int k=0;k<81;k++)
		{
			if(input.get(k)>0)
			{
				Taken[i][j]=1;
				currsol[i][j]=input.get(k);
			}
			j++;
			if(j==9)
			{
				j=0;
				i++;
			}
		}
	}


	private Boolean checkRow(int i,int j)
	{
		int nums=currsol[i][j];
		
		for(int k=0;k<9;k++)
		{
			if(j!=k)
			{
				if(currsol[i][k]==currsol[i][j])
				{
					return false;
				}
			}	
		}
		return true;
	}

	private Boolean checkCol(int i,int j)
	{
		int nums=currsol[i][j];

		int col=(i+9)%9;
		for(int k=col;k<81;k++)
		{
			if(i!=k)
			{
				if(currsol[k][j]==currsol[i][j])
				{
					return false;
				}
			}
		}
		return true;
	}

	private Boolean checkBox(int i,int j)
	{
		int a=(i+9)%9;
		while((a+3)%3!=0)
		{
			a--;
		}
		int b=(j+9)%9;
		while((b+3)%3!=0)
		{
			b--;
		}

		int nums=currsol[i][j];

		for(int k=a;k<a+3;k++)
		{
			for(int l=b;l<b+3;l++)
			{
				if((k!=i)&(l!=j))
				{
					if(currsol[a][b]==nums)
					{
						return false;
					}
				}
			}
		}
		return true;
	}

	private Boolean checkLoc(int i,int j)
	{
		return checkRow(i,j)&checkBox(i,j)&checkCol(i,j);
	}
}
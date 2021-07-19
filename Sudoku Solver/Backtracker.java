import java.util.*; 
import java.io.*;

public class Backtracker
{
	private int[][] Taken;
	private int[][] currsol;
	private static int cnt=0;

	Backtracker(Vector<Integer> input)
	{
		Taken=new int[9][9];
		currsol=new int[9][9];
		int i=0;
		int j=0;
		for(int k=0;k<81;k++)
		{
			if(input.get(k)>0&&input.get(k)<10)
			{
				Taken[i][j]=1;
			}
			else if(input.get(k)!=0)
			{
				Taken[i][j]=2;
				
			}
			else
			{
				Taken[i][j]=0;
			}
			
			currsol[i][j]=input.get(k);
			j++;
			if(j==9)
			{
				j=0;
				i++;
			}
		}

	}

	Boolean check() 
	{
		Boolean flag=true;
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)
			{
				int temp=currsol[i][j];
				if(temp==-1)
				{
					flag=false;
				}
				Boolean Valid=checkRow(i,j)&checkCol(i,j)&checkBox(i,j);
				if(Valid==false)
				{
					//System.out.println(i+" "+j);
					flag=false;
				}
			}
		}
		//System.out.println(flag);
		return flag;
	}

	private Boolean checkRow(int i,int j)
	{
		//System.out.println("AAAAAAAAAAAAAAAA");
		int nums=currsol[i][j];
		if(nums==0)
		{
			return true;
		}
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
		//System.out.println("BBBBBBBBBBBB");
		int nums=currsol[i][j];

			if(nums==0)
		{
			return true;
		}
		for(int k=0;k<9;k++)
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
	//	System.out.println("CCCCCCCCCCCCCCCCC"+" "+i+" "+j+" "+currsol[i][j]);
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
		//System.out.println(a+" "+b);
		int nums=currsol[i][j];
		if(nums==0)
		{
			return true;
		}
		for(int k=a;k<a+3;k++)
		{
			for(int l=b;l<b+3;l++)
			{
				if((k!=i)|(l!=j))
				{
					if(currsol[k][l]==nums)
					{
						//System.out.println(k+" "+l);
						return false;
					}
				}
			}
		}
		
		return true;
	}

	private Boolean checkLoc(int i,int j)
	{
		//System.out.println(checkRow(i,j)+" "+checkBox(i,j)+" "+checkCol(i,j));
		return checkRow(i,j)&checkBox(i,j)&checkCol(i,j);
	}

	public Boolean Track(int i,int j) 
	{	
		//System.out.println(i+" "+j);
		if(check()==false)
		{
			return false;
		}
		cnt++;
		if(cnt%10000==0)
		{
			System.out.println("Solving");
		}
		int start=1;
		if(Taken[i][j]==0)
		{
			while(start<10)
			{
				//output();
				currsol[i][j]=start;
				if(checkLoc(i,j))
				{
					//System.out.println("DONE:");
					if((j+9)%9!=8)
					{
						if(Track(i,j+1))
						{
							break;
						}
						else
						{
							start++;
						}
					}
					else if(i<8)
					{
						if(Track(i+1,0))
						{
							break;
						}
						else
						{
							start++;
						}
					}
					else
					{
						return true;
					}
					
				}
				else
				{
					start++;
				}
			}
			if(start<10)
			{
				return true;
			}
			else
			{
				currsol[i][j]=0;
				return false;
			}
			
		}
		else
		{
			if((j+9)%9!=8)
			{
				return Track(i,j+1);
			}
			else if(i<8)
			{
				return Track(i+1,0);
			}
			else
			{
				return true;
			}
		}
		
	}

	public int output()
	{
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)
			{
				int curr=currsol[i][j];
				System.out.print(curr+" ");
			}
			System.out.println();
		}
		return 0;
	}

	public int[][] ans()
	{
		int[][] curr=new int[9][9];
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)
			{
				curr[i][j]=currsol[i][j];
			}
		}
		return curr;
	}

	public int[][] took()
	{
		int[][] curr=new int[9][9];
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)
			{
				curr[i][j]=Taken[i][j];
			}
		}
		return curr;

	}

	public static void main(String[] args)
	{
		Vector<Integer> in=new Vector<Integer>();
		Scanner In=new Scanner(System.in);
		for(int i=0;i<81;i++)
		{
			int a;
			a=In.nextInt();
			in.add(a);
			
			
		}
		Backtracker solve=new Backtracker(in);
		solve.Track(0,0);
		solve.output();
	}
}
import java.util.*; 
import java.io.*;

public class Sudoku
{
	int Variables;
	//1458
	public int DefaultEdges(CNF myCNF)
	{
		Vector<Integer> Rows=new Vector<Integer>();;
		Vector<Integer> Col=new Vector<Integer>();
		Vector<Clause> Clauses=new Vector<Clause>();
		for(int i=0;i<81;i+=9)
		{

			for(int j=i;j<i+9;j++)
			{

				Rows.clear();
				for(int k=0;k<9;k++)
				{
					Col.clear();
					Rows.add(18*j+k);
					if(i<j)
					{
						Col.add((-1)*(18*i+k));
						Col.add((-1)*(18*j+k));
						Clause myClause2=new Clause(Col);
						Clauses.add(myClause2);
					}
				}
				Clause myClause=new Clause(Rows);
				Clauses.add(myClause);
			}
		
		}


		for(int i=0;i<9;i++)
		{
		
			for(int j=i;j<81;j+=9)
			{
				Rows.clear();
				for(int k=0;k<9;k++)
				{
					Col.clear();
					Rows.add(18*j+k);
					if(i<j)
					{
						Col.add((-1)*(18*i+k));
						Col.add((-1)*(18*j+k));
						Clause myClause2=new Clause(Col);
						Clauses.add(myClause2);
					}
				}
				Clause myClause=new Clause(Rows);
				Clauses.add(myClause);
				
			}
		}
	
		for(int i=0;i<9;i+=3)
		{
			for(int k=0;k<9;k++)
			{	
				Rows.clear();
				
				for(int j=i;j<i+3;j++)
				{
					Col.clear();
					if(i<j)
					{
						Col.add((-1)*(18*i+k));
						Col.add((-1)*(18*j+k));
						Clause myClause2=new Clause(Col);
						Clauses.add(myClause2);
					}
					Rows.add(18*j+k);
				}
				for(int j=i+9;j<i+12;j++)
				{
					Col.clear();
					if(i<j)
					{
						Col.add((-1)*(18*i+k));
						Col.add((-1)*(18*j+k));
						Clause myClause2=new Clause(Col);
						Clauses.add(myClause2);
					}
					Rows.add(18*j+k);
				}
				for(int j=i+18;j<i+21;j++)
				{
					Col.clear();
					if(i<j)
					{
						Col.add((-1)*(18*i+k));
						Col.add((-1)*(18*j+k));
						Clause myClause2=new Clause(Col);
						Clauses.add(myClause2);
					}
					Rows.add(18*j+k);
				}
				Clause myClause=new Clause(Rows);
				Clauses.add(myClause);
			}

		}
		
		for(int i=27;i<36;i+=3)
		{
			for(int k=0;k<9;k++)
			{	
				Rows.clear();
				
				for(int j=i;j<i+3;j++)
				{
					Col.clear();
					if(i<j)
					{
						Col.add((-1)*(18*i+k));
						Col.add((-1)*(18*j+k));
						Clause myClause2=new Clause(Col);
						Clauses.add(myClause2);
					}
					Rows.add(18*j+k);
				}
				for(int j=i+9;j<i+12;j++)
				{
					Col.clear();
					if(i<j)
					{
						Col.add((-1)*(18*i+k));
						Col.add((-1)*(18*j+k));
						Clause myClause2=new Clause(Col);
						Clauses.add(myClause2);
					}
					Rows.add(18*j+k);
				}
				for(int j=i+18;j<i+21;j++)
				{
					Col.clear();
					if(i<j)
					{
						Col.add((-1)*(18*i+k));
						Col.add((-1)*(18*j+k));
						Clause myClause2=new Clause(Col);
						Clauses.add(myClause2);
					}
					Rows.add(18*j+k);
				}
				Clause myClause=new Clause(Rows);
				Clauses.add(myClause);
			}
		}

		for(int i=54;i<63;i+=3)
		{
			for(int k=0;k<9;k++)
			{	
				Rows.clear();
				
				for(int j=i;j<i+3;j++)
				{
					Col.clear();
					if(i<j)
					{
						Col.add((-1)*(18*i+k));
						Col.add((-1)*(18*j+k));
						Clause myClause2=new Clause(Col);
						Clauses.add(myClause2);
					}
					Rows.add(18*j+k);
				}
				for(int j=i+9;j<i+12;j++)
				{
					Col.clear();
					if(i<j)
					{
						Col.add((-1)*(18*i+k));
						Col.add((-1)*(18*j+k));
						Clause myClause2=new Clause(Col);
						Clauses.add(myClause2);
					}
					Rows.add(18*j+k);
				}
				for(int j=i+18;j<i+21;j++)
				{
					Col.clear();
					if(i<j)
					{
						Col.add((-1)*(18*i+k));
						Col.add((-1)*(18*j+k));
						Clause myClause2=new Clause(Col);
						Clauses.add(myClause2);
					}
					Rows.add(18*j+k);
				}
				Clause myClause=new Clause(Rows);
				Clauses.add(myClause);
			}
		}

		for(int i=0;i<81;i++)
		{
			for(int j=0;j<9;j++)
			{
				for(int k=0;k<j;k++)
				{
					Rows.clear();
					Rows.add((-1)*(18*i+k));
					Rows.add((-1)*(18*i+j));
					Clause myClause=new Clause(Rows);
					Clauses.add(myClause);
				}
			}
		}

		for(int i=0;i<Clauses.size();i++)
		{
			//System.out.println(Clauses.get(i).Variable().size());
			myCNF.add(Clauses.get(i));
		}

		return 0;
	}


	public static void main(String[] args)
	{
		CNF curr=new CNF(729);
		Sudoku temp=new Sudoku();
		temp.DefaultEdges(curr);
		curr.DLL(0,true,curr);
	}
}
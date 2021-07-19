import java.util.ArrayList;

import java.util.*; 
import java.io.*;

public class Clause{
	private Vector<Variable> Variables;
	private int sz;
	private Boolean satisfied;


	Clause(Vector<Integer> input)
	{
		sz=input.size();
		Variables=new Vector<Variable>();
		satisfied=false;
		for(int i=0;i<sz;i++)
		{
			int a=input.get(i);
			Variable temp = new Variable(a);
			Variables.add(temp);
		}
	}

	public Vector<Variable> Variable()
	{
		Vector<Variable> curr=new Vector<Variable>();
		curr=Variables;
		return curr;
	}

	public int Add(int newvar)
	{
		
			Variable temp = new Variable(newvar);
			Variables.add(temp);
		return 0;
	}

	public Variable UnitPropogate()
	{
		if(Variables.size()==1&&satisfied==false)
		{
			//System.out.println("DOINGIT");
			Variable temp=new Variable(0);
			temp=Variables.get(0);
			Variables.remove(0);
			satisfied=true;
			return temp;
		}
		else
		{
			return new Variable(0);
		}
	}
	public int update(int ToUpdate,Boolean val)
	{
		for(int i=0;i<Variables.size();i++)
		{
			if(Variables.get(i).idx()==ToUpdate)
			{
				Boolean temp=!(val^Variables.get(i).Neg()); 
				if(temp==false)
				{
					//System.out.println(ToUpdate+"GAAAAAAAAAAAAAAAAAAAAA "+val+" "+i);
					satisfied=true;
					
				}
				//System.out.println(ToUpdate+"GAAAAAAAAAAAAAAAAAAAAA "+val+" "+i);
				//Variables.remove(i);
			}
		}
		int j=0;
		while(j<Variables.size())
		{
			if(Variables.get(j).idx()==ToUpdate)
			{
				Variables.remove(j);

			}
			else
				{
					j++;
				}
		}
		return 1;

	}

	public Boolean satisfied()
	{
		return satisfied;
	}

	public Vector<Integer> Varval()
	{
		Vector<Integer> temp=new Vector<Integer>();
		for(int i=0;i<Variables.size();i++)
		{
			temp.add(Variables.get(i).idx());
		}
		return temp;
	}

	public int output()
	{
		for(int i=0;i<Variables.size();i++)
		{
			System.out.println(Variables.get(i).idx()+"This is output"+Variables.get(i).Neg());
		}
		return 0;
	}

	public int UpdateSat(Boolean isSat)
	{
		satisfied=isSat;
		return 0; 
	}


}

import java.util.*; 
import java.io.*;

public class CNF implements Cloneable{
	private Vector<Clause> Equations;
	private Vector<Integer> present;
	private Vector<Integer> Assignments; // 0 is false 1 is true
	public int Total;
	static int solved=0;

	CNF(int total){
		Equations=new Vector<Clause>();
		present= new Vector<Integer>();
		Assignments=new Vector<Integer>();
		for(int i=0;i<total+1;i++)
		{
			Assignments.add(-1);;
		}
		Total=total;
	}

	CNF(CNF copy)
	{
		Equations=new Vector<Clause>();
		present= new Vector<Integer>();
		Assignments=new Vector<Integer>();
		Total=copy.Total;
		//System.out.println(copy.Total+"NO.OF ELEMTNS");
		for(int i=0;i<copy.Total+1;i++)
		{
			Assignments.add(copy.Assignments().get(i));
		}
		for(int i=0;i<copy.Equations().size();i++)
		{
			Vector<Integer> maker=new  Vector<Integer>();
			Clause curr=new Clause(maker);

			for(int j=0;j<copy.Equations().get(i).Variable().size();j++)
			{
				int a=copy.Equations().get(i).Variable().get(j).idx();
				Boolean b=copy.Equations().get(i).Variable().get(j).Neg();
				if(b)
				{
					a*=-1;
				}
				Variable temp=new Variable(a);
				curr.Add(a);

			}
			curr.UpdateSat(copy.Equations().get(i).satisfied());
			add(curr);
		}
	}

	public Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	}

	public Vector<Clause> Equations(){
		return Equations;
	}

	public Vector<Integer> present()
	{
		return present;

	}

	public Vector<Integer> Assignments()
	{
		return Assignments;
	}

	public int add(Clause nextClause)
	{
		Equations.add(nextClause);
		Vector<Integer> Next=nextClause.Varval();
		for(int i=0;i<Next.size();i++)
		{
			if(present.indexOf(Next.get(i))==-1)
			{
				present.add(Next.get(i));

			}
		}
		return 0;
	}
	public int Assign(int var,Boolean assignment)
	{
		if(assignment==true)
		{
			Assignments.set(var,1);
		}
		else
		{
			Assignments.set(var,0);
		}
		for(int i=0;i<Equations.size();i++)
		{
			Equations.get(i).update(var,assignment);
			if(Equations.get(i).satisfied()==true)
			{
				//Equations.remove(i);
			}
		}

		return 0;
	}

	public int UnitPropogate()
	{
		int flag=0;
		int flag2=1;
		do
		{
			flag=0;	
			for(int i=1;i<Assignments.size();i++)
			{
				//System.out.println(i+" "+Assignments.get(i));
			}
			for(int i=0;i<Equations.size();i++)
			{
				//Boolean update;
				//System.out.println(i+" "+Equations.get(i).satisfied());
					if(Equations.get(i).satisfied()==false)
					{
						Variable k=Equations.get(i).UnitPropogate();
						if(k.idx()!=0)
						{
							int temp=Assignments.get(k.idx());
							//System.out.println(k.idx()+"KIDX"+k.Neg()+" "+temp+" "+Equations.get(i).satisfied());;
							if(temp==-1)
							{
								if(k.Neg()==false)
								{
									Assignments.set(k.idx(),1);
									Equations.get(i).update(k.idx(),true);
									Assign(k.idx(),true);
									flag=1;
								}
								else
								{
									Assignments.set(k.idx(),0);
									Equations.get(i).update(k.idx(),false);
									Assign(k.idx(),false);
									flag=1;
								}
							}
							else 
							{
								if(k.Neg()==false)
								{
									if(temp==0)
									{	
										flag=0;
										flag2=0;
									}
								}
								else if(k.Neg()==true)
								{
									if(temp==1)
									{
										flag=0;
										flag2=0;						
									}
								}
							}
						
						}

						for(int j=0;j<Equations.size();j++)
						{
							if(Assignments.get(k.idx())==1)
							{
								Equations.get(j).update(k.idx(),true);
							}
							else
							{
								Equations.get(j).update(k.idx(),false);
							}
						}

					
					//System.out.println(k.idx()+"KIDX"+k.Neg()+" "+Assignments.get(k.idx())+" "+Equations.get(i).satisfied());;
					//System.out.println(k.idx()+"K"+k.Neg()+" "+i+" "+Equations.size());
					break;
			}
		}
		}while(flag==1);	
		//System.out.println(flag+"B");
		return flag2;
	}

	public int DLL(int variable,Boolean assignment,CNF currEq)
	{
		System.out.println(variable+" "+assignment+" "+solved+" "+currEq.Assignments().size()+" "+currEq.Equations().size());
		/*for(int i=1;i<currEq.Assignments.size();i++)
		{
			System.out.println(currEq.Assignments.get(i));
				
		}
		Vector<Clause> A=new Vector<Clause>();
			A=currEq.Equations();
			for(int j=0;j<A.size();j++)
			{
				if(A.get(j).satisfied()==true)
				{
					System.out.println("POSSIBLE"+j);
				}
				else
				{
					System.out.println("NOT"+j);
				}
			}*/
		if(solved==0)
		{
			CNF currCNF=new CNF(currEq);
			
			if(currCNF.UnitPropogate()==0)
			{
				//System.out.println("PROPOGATEOVER");
				return 0;
			}
			
			if(currCNF.Assignments().get(variable)==1&&assignment==true)
			{
				currCNF.Assign(variable,assignment);
			}
			else if(currCNF.Assignments().get(variable)==0&&assignment==false)
			{
				currCNF.Assign(variable,assignment);
			}
			else if(currCNF.Assignments().get(variable)==-1)
			{
				currCNF.Assign(variable,assignment);
			}
			else
			{
				//System.out.println(variable+"OVER"+assignment+" "+currCNF.Assignments().get(variable));
				return 0;
			}


				Boolean flag=true;
				Vector<Clause> checker= new Vector<Clause>();
				checker=currCNF.Equations();
				for(int i=0;i<checker.size();i++)
				{
					if(checker.get(i).satisfied()==false)
					{	
						flag=false;
						break;
					}
					
				}

				if(flag)
				{
					solved=1;
					Vector<Integer> Ans=new Vector<Integer>();
					Ans=currCNF.Assignments();
					for(int i=1;i<Total+1;i++)
					{
						if(Ans.get(i)==-1)
						{
							Ans.set(i,1);
						}
						System.out.println(Ans.get(i)+" BBBB"+i);
					}
					return 1;
				}
			
	
			int i=1;
			while(i<=Total)
			{
				Vector<Integer> Ans=new Vector<Integer>();
					Ans=currCNF.Assignments();
				if(Ans.get(i)==-1)
				{
					break;
				}
				else
				{
					i++;
				}
			}
			if(i>Total)
			{
				
				return 0;
			}
			//System.out.println(i+"GAAA"+assignment+Equations.size());
			for(int j=1;j<Total+1;j++)
			{
				Vector<Integer> Ans=new Vector<Integer>();
					Ans=currCNF.Assignments();
				//System.out.println(Ans.get(j)+" ");
			}
			CNF currCNF2=new CNF(currCNF);
			
			CNF currCNF3=new CNF(currCNF);
			
			//System.out.println(i+"Variableupdate"+solved);
	
			//currCNF2.Assign(i,true);
	
				DLL(i,true,currCNF2);
			
			
		
			//currCNF3.Assign(i,false);
		

				DLL(i,false,currCNF3);

		}

			
		return 0;
	}

	public static void main(String[] args)
	{
		CNF myCNF=new CNF(4);
		Vector<Integer> First=new Vector<Integer>();
		Vector<Integer> Second=new Vector<Integer>();
		Vector<Integer> Third=new Vector<Integer>();
		Vector<Integer> Four=new Vector<Integer>();
		First.add(1);
		First.add(-2);
		First.add(4);
		Second.add(2);
		Second.add(-1);
		Third.add(-3);
		Third.add(4);
		Four.add(-4);
		Four.add(-1);
		Four.add(-3);

		Clause Clause1=new Clause(First);
		Clause Clause2=new Clause(Second);
		Clause Clause3=new Clause(Third);
		Clause Clause4=new Clause(Four);
		myCNF.add(Clause1);
		myCNF.add(Clause2);
		myCNF.add(Clause3);
		myCNF.add(Clause4);
		myCNF.DLL(1,true,myCNF);
		myCNF.DLL(1,false,myCNF);
	}
}  
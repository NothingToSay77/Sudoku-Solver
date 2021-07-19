import java.util.ArrayList;

import java.util.*; 
import java.io.*;

public class Variable
{
	private int value;
	private Boolean Negated;
	Variable(int val)
	{
		if(val>0)
		{
			value=val;
			Negated=false;
		}
		else 
		{
			value=(-1)*val;
			Negated=true;
		}
	}

	public int idx(){
		return value;
	}

	public Boolean Neg()
	{
		return Negated;
	} 
}
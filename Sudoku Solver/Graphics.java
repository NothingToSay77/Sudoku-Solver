import java.util.*; 
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

public class Graphics implements ActionListener
{
	private Vector<JTextField> input;
	private JButton solver;
	private Vector<Integer> values;
	private JLabel solvable,solvable2;
	private JButton reset;
	public static void main(String[] args)
	{
		Graphics myWindow=new Graphics();
		myWindow.go();
	}
	public void go()
	{
		input=new Vector<JTextField>();
		JFrame Frame=new JFrame();
		JPanel Panel=new JPanel();
		Frame.setSize(910,900);
		
		
		Frame.add(Panel);
		Panel.setLayout(null);
		//GridLayout experimentLayout = new GridLayout(0,9);
	//Panel.setLayout(experimentLayout);
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)
			{
				JTextField curr=new JTextField(15);
				curr.setHorizontalAlignment(JTextField.CENTER);
				//curr.setPreferredSize(new Dimension(77,77));
				curr.setBounds(60*i+50,70*j,50,50);
				input.add(curr);
				Panel.add(input.get((i)*9+j));
			}
		}
		solvable= new JLabel("Enter values and click on solve");
		solvable2= new JLabel("");
		solvable.setBounds(400,700,250,40);
		solvable2.setBounds(400,750,250,40);
		Panel.add(solvable);
		Panel.add(solvable2);
		solver=new JButton("Solve");
		reset=new JButton("Reset");
		solver.setBounds(700,400,150,70);
		reset.setBounds(700,500,150,70);
		reset.addActionListener(this);
		solver.addActionListener(this);
		Panel.add(solver);
		Panel.add(reset);
		Frame.setVisible(true);
		
		
		
		
		

		
		
	}

	public void actionPerformed (ActionEvent event)
	{
		//System.out.println("GAAAAAA");
		if(event.getSource()==solver)
		{
			values=new Vector<Integer>();
			for(int i=0;i<81;i++)
			{
				String curr = input.get(i).getText();
				if(curr.length()==0)
				{
					values.add(0);
				}
				else if(curr.length()==1)
				{
					char c=curr.charAt(0);
					int a=c-'0';
					values.add(a);
				}
				else
				{
					values.add(-1);
				}
			}
			Backtracker answer=new Backtracker(values);
			int[][] taken=new int[9][9];
			taken=answer.took();
			for(int i=0;i<9;i++)
			{
				for(int j=0;j<9;j++)
				{
					if(taken[i][j]==1)
					{
						JTextField curr2=new JTextField(15);
						curr2=input.get((9*(i))+j);
						curr2.setBackground(Color.YELLOW);
					}
					else if(taken[i][j]==2)
					{
						JTextField curr2=new JTextField(15);
						curr2=input.get((9*(i))+j);
						curr2.setBackground(Color.RED);
					
					}
				}
			}
			
			solvable2.setText("Solving...");
				if(answer.Track(0,0))
				{
					int[][] out=new int[9][9];
					out=answer.ans();
					//answer.output();
					int cnt=0;
					//System.out.println("GAA");
					for(int i=0;i<9;i++)
					{
						for(int j=0;j<9;j++)
						{
							JTextField curr2=new JTextField(15);
							curr2=input.get(cnt);
							//System.out.println(out[i][j]);
							cnt++;
							curr2.setText(String.valueOf(out[i][j]));
						}
					}
					solvable.setText("Solved, reenter to solve again");
				}
				else
				{
					solvable.setText("Invalid input");
				}
				solvable2.setText("");
			
			
			
		}
		if(event.getSource()==reset)
		{
			for(int i=0;i<9;i++)
			{
				for(int j=0;j<9;j++)
				{
					
						JTextField curr2=new JTextField(15);
						curr2=input.get((9*(i))+j);
						curr2.setBackground(Color.WHITE);
					
				}
			}
			int cnt=0;
					//System.out.println("GAA");
				for(int i=0;i<9;i++)
				{
					for(int j=0;j<9;j++)
					{
						JTextField curr2=new JTextField(15);
						curr2=input.get(cnt);
						//System.out.println(out[i][j]);
						cnt++;
						curr2.setText("");
						}
				}
		}
		//button.setText("clicked");
	}

}
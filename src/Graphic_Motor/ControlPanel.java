package Graphic_Motor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class ControlPanel extends JPanel implements ActionListener{
	
	private JPanel controlButtons, changeTurn;
	private JButton play, fast, slow, pause, exit, goForTurn;
	private JLabel enterTurnLabel;
	private JTextField turnNum;
	
	private GameFrame frame;
	
	public ControlPanel(GameFrame gf) {
		setLayout(new BorderLayout());
		controlButtons = new JPanel(new BorderLayout());
		changeTurn = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		play = new JButton("Play");
		play.addActionListener(this);
		
		pause = new JButton("Pause");
		pause.addActionListener(this);
		
		fast= new JButton(">>>");
		fast.addActionListener(this);
		
		slow = new JButton("<<<");
		slow.addActionListener(this);
		
		exit = new JButton("Exit") ;
		exit.addActionListener(this);
		
		controlButtons.add(play,BorderLayout.CENTER);
		controlButtons.add(pause,BorderLayout.NORTH);
		controlButtons.add(exit,BorderLayout.SOUTH);
		controlButtons.add(fast,BorderLayout.EAST);
		controlButtons.add(slow,BorderLayout.WEST);
		
		add(controlButtons,BorderLayout.NORTH);
		
		enterTurnLabel = new JLabel("Enter the number of turn which you want move to :");
		turnNum = new JTextField(4);
		goForTurn = new JButton("Move to turn");
		goForTurn.addActionListener(this);
		
		changeTurn.add(enterTurnLabel);
		changeTurn.add(turnNum);
		changeTurn.add(goForTurn);
		
		add(changeTurn,BorderLayout.SOUTH);
		
		frame = gf;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == play)
			frame.play();
		if(e.getSource() == pause)
			frame.pause();
		if(e.getSource() == exit)
			frame.turnOff();
		if(e.getSource() == fast)
			frame.speedUp();
		if (e.getSource() == slow)
			frame.speedDown();
		if(e.getSource() == goForTurn) {
			if(Integer.parseInt(turnNum.getText())<=1000) {
				frame.moveToTurn(Integer.parseInt(turnNum.getText()));
			}
		}
		
	}


}

package barricades.player;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Scanner;

import framework.player.Action;
import framework.player.Player;


public class HumanPlayer extends Player{

	
	protected BufferedReader _in;
	protected PrintWriter  _out;
	
	public HumanPlayer(String name, int color, int type, String symbol,
			int score) {
		super(name, color, type, symbol, score, new Scanner(System.in));
		
	}
	
	public HumanPlayer listenSocket(Socket socket) throws UnknownHostException{
		//Create socket connection
		   try{
		     socket = new Socket(InetAddress.getLocalHost(), 4321);
		     _out = new PrintWriter(socket.getOutputStream(), 
		                 true);
		     _in = new BufferedReader(new InputStreamReader(
		                socket.getInputStream()));
		     System.out.println("player connected");
		   } catch (UnknownHostException e) {
		     System.out.println("Player connected: "+InetAddress.getLocalHost());
		     System.exit(1);
		   } catch  (IOException e) {
		     System.out.println("No I/O");
		     System.exit(1);
		   }
		return this;
		}


	@Override
	public Action getAction(List<Action> listActions) {
		String strActions = "";
		
		for (int i=0; i<listActions.size(); i++)
			strActions += listActions.get(i) + " ";
		
		Action action = listActions.get(0);
		action.getParameters();

		return action;
	}

	

}

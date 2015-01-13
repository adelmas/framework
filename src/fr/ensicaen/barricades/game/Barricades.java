package fr.ensicaen.barricades.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import javax.swing.JButton;
import javax.swing.event.UndoableEditEvent;
import javax.swing.undo.UndoManager;

import fr.ensicaen.barricades.board.*;
import fr.ensicaen.barricades.graphics.FrameBarricades;
import fr.ensicaen.barricades.graphics.PanelBarricades;
import fr.ensicaen.barricades.player.ActionMOVE;
import fr.ensicaen.barricades.player.ActionPUT;
import fr.ensicaen.barricades.player.ActionREMOVE;
import fr.ensicaen.barricades.player.HumanPlayer;
import fr.ensicaen.framework.board.Coordinates;
import fr.ensicaen.framework.game.*;
import fr.ensicaen.framework.player.*;

public class Barricades extends Game implements ActionListener {
	private UndoManager _undoManager = new UndoManager();
	private UndoableListener _undoListener = new UndoableListener(_undoManager);
	private int _phase = 0; // 0->init	1->renforts	2->move
	private int _countI = 5, _countL = 5;
	private boolean _choice = false;
	private static BoardBarricades _boardB;
	private static LinkedList<Node> _quartier = new LinkedList<Node>();
	private PanelBarricades _pBarricades;
	private int _nbCoups = 0;
	private static int _score = 5; //nb initial de pions par joueur
	
	@Override
	public void init() {
		FrameBarricades f = new FrameBarricades(this);
		_pBarricades = f.getPanel();
		LinkedList<JButton> list = f.getButtons();
		for(JButton b : list){
			b.addActionListener(this);
		}
		BoardBarricades board = (BoardBarricades) getBoard();
		loadMapFromFile("resources/map.txt");
		board.setNeighbor(1, true);
		board.setNeighbor(2, true);
		board.setNeighbor(3, true);
		board.setNeighbor(4, true);
		System.out.println(board);
	}
	
	public void loadMapFromFile(String file) {
		BoardBarricades b = (BoardBarricades) getBoard();
		try {
			BufferedReader buf = new BufferedReader(new FileReader(file));
			try {
					String line = "";
					int phase = 0;
					while ((line = buf.readLine()) != null) {
						line = line.trim();
						if (line.equals("")) {
							phase = 1;
							continue;
						}
						String[] split = line.split("\t");
						if (phase == 0) {
							b.addNode(Integer.parseInt(split[0]), new Coordinates(Integer.parseInt(split[1]), Integer.parseInt(split[2])));
						}
						else {
							int numFather = 0, numChild = 0;
							for (int i=0; i<split.length; i++) {
								if (i == 0) {
									numFather = Integer.parseInt(split[i]);
								}
								else {
									numChild = Integer.parseInt(split[i]);
									b.addChild(numFather, numChild);
								}
							}
						}
					}
			} finally {
				buf.close();
			}
		} catch (IOException ioe) {
			System.out.println("Error loadMapFromFile : File not found");
		}
	}	
	
	@Override
	public boolean isGameOver() {
		for (Player player : getPlayers()) {
			if (player.getScore() == 0)
				return true;
		}
		return quartierPris(_boardB);
	}

	public static void initQuartier(BoardBarricades boardB) {
		LinkedList<Node> nodeList = boardB.getGraph().getList();
		for(int i=0;i<nodeList.size();i++){
			if(nodeList.get(i).isNeighbor()){
				_quartier.add(nodeList.get(i));
				System.out.println(_quartier.get(i));
			}
		}
	}
	
	public boolean quartierPris(BoardBarricades boardB) {
		int nbCarrefoursPris = 0;
		for(int i=0;i<_quartier.size();i++){
			if(!_boardB.getGraph().getNode(_quartier.get(i).getNum()).getCase().isEmpty()){
				if(_boardB.getGraph().getNode(_quartier.get(i).getNum()).getCase().getFirstPiece().getPlayer().getColor()==2){ //Si pion appartient au loyaliste
					nbCarrefoursPris++;
				}
			}
		}
		if(nbCarrefoursPris==_quartier.size()){
			return true;
		}
		else{
			return false;
		}
	}	
	
	@Override
	public void play() {
		while (true) {
			coup();
			if (isGameOver()) {
				gameOver();
				break;
			}
		}
	}
	
	public void choice(Player player){
		if(player.getScanner().equals("oui")){
			System.out.println("cool!");
			_choice = true;
		}
		else{
			_choice = false;
		}
	}
	
	public void coup(){
		Player player = getCurrentPlayer();
		int playerType = player.getColor();
		List<Action> actions = new LinkedList<Action>();
		boolean valide = false;
		ActionPUT act_put;

		if (_phase == 0) {
			System.out.println("Phase "+_phase+" joueur "+player.getName()+" : pose");
			actions = new LinkedList<Action>();
			//Scanner scan = player.getScanner();
			act_put = new ActionPUT("PUT", 0, player, _boardB, player.getScanner());
			//numNode = scan.nextInt();
			//System.out.println(numNode);
			actions.add(act_put);
		}
		
		else if (_phase == 1) {
			if(playerType==1){
				/*System.out.println("Voulez-vous placer des renforts ? (oui/non)");
				choice(player);
				if(_choice){*/
					System.out.println("Phase "+_phase+" joueur "+player.getName()+" : renfort");
					actions = new LinkedList<Action>();
					act_put = new ActionPUT("PUT", 0, player, _boardB, player.getScanner());
					actions.add(act_put);
				//}
			}
			else{
				System.out.println("Phase "+_phase+" joueur "+player.getName()+" : renfort");
				actions = new LinkedList<Action>();
				act_put = new ActionPUT("PUT", 0, player, _boardB, player.getScanner());
				actions.add(act_put);
			}
		}
		
		else{ //(_phase == 2)
			/*System.out.println("Voulez-vous déplacer des pions ? (oui/non)");
			choice(player);
			if(_choice){*/
				System.out.println("Phase "+_phase+" joueur "+player.getName()+" : deplacement");
				actions = new LinkedList<Action>();
				ActionMOVE act_mov;
				act_mov = new ActionMOVE("MOVE", 0, player, _boardB, player.getScanner());
				actions.add(act_mov);
			//}
		}
			
		if(actions != null){
			Action action = player.getAction(actions);		
				
			/* ----- Controle des actions ----- */
			if ((_phase == 0 || _phase == 1) && playerType==2) {
				valide = !distance(_boardB.getGraph().getNode(action.getCoordinate(0)),1);
			}
			if(_phase == 1 && playerType == 1){
				valide = !encercle(_boardB.getGraph().getNode(action.getCoordinate(0)));
			}
			if (valide == true) {
				System.out.println("Action invalide (Game)");
				return;
			}
			
			/* Execution de l'action si elle est valide */
			if (!action.doAction()) {
				System.out.println("Action invalide (Action) " + action.toString());
				//System.out.println(toString());
				return;
			}
			
			/* Action registration */
			_undoListener.undoableEditHappened(new UndoableEditEvent(this, action));
		}
		
		setChanged();
		notifyObservers();
		//System.out.println(toString());

		//Contrôle des compteurs pour la partie initialisation
		if (_phase==0){ 
			if(playerType==1){
				if(_countI>0){
					_countI--;
					System.out.println(_countI);
				}
			}
			else{
				if(_countL>0){
					_countL--;
					System.out.println(_countL);
				}
			}
		}

		_nbCoups++;
		if(!_choice){
			if(_phase==2){
				fightManager(player);
			}
			updatePhase();
		}
	}
	
	public void updatePhase() {
		if (_countI == 0 && _countL == 5){
			nextPlayer();
		}
		if (_countI == 0 && _countL == 0) {
			if(_phase == 0){
				nextPlayer();
				_phase = 1;
			}
			else if(_phase == 1){
				_phase = 2;
			}
			else if(_phase == 2){
				nextPlayer();
				_phase = 1;
			}
		}
	}
	
	//gère tous les combats d'un joueur :)
	public void fightManager(Player player)
	{
		LinkedList<Node> fightPosition = new LinkedList<Node>();
		LinkedList<Integer> fightResult = new LinkedList<Integer>();
		LinkedList<Node> position = _boardB.getGraph().getList();
		int type = player.getColor();
		ActionREMOVE act_rem;
		ActionMOVE act_mov;
		
		//parcours de la carte pour trouver les pions du joueur
	    for (Node n : position) {
	    	if(n.getCase().getPieces().size() != 0)
	    	{
	        if (n.getCase().getFirstPiece().getPlayer().equals(player))
	        {
	        	if(voisins(n, player))
	        	{
	        		//gestion de tous les combats et de leur résultat pour le joueur
	        		for(Node sonOfWar : n.getChildrenList())
	        		{
	        			if(sonOfWar.getCase().getPieces().size() != 0)
	        			{
	        			Player opponent = sonOfWar.getCase().getFirstPiece().getPlayer(); 
	        			if(!opponent.equals(player) && (opponent.getColor() == 1 || opponent.getColor() == 2))
	        			{
	        				if(!fightPosition.contains(sonOfWar))
	        				{
	        					fightPosition.push(sonOfWar);
	        					fightResult.push(fight(sonOfWar));
	        				}
	        			}
	        			}
	        		}
	        	}
	        }
	    	}
	      }
	    
	    //conséquences combats
		for(int index = 0 ; index < fightPosition.size(); index++)
		{
			LinkedList<Node> involved = fightPosition.get(index).getChildrenList();
			involved.push(fightPosition.get(index));
			
			switch(fightResult.get(index))
			{
			case 1 :
				if(type == 1)
				{
					for (Node n : involved)
					{
						if(n.getCase().getPieces().size() != 0)
						{
						if(n.getCase().getFirstPiece().getPlayer().getColor() == 2)
						{
							if(encercle(n))
							{
								//action virer pion + décrémenter score
								act_rem = new ActionREMOVE("REMOVE", 0, n.getCase().getFirstPiece().getPlayer(), _boardB, null);
								act_rem.setCoordinates(n.getCoordinates());
								act_rem.doAction();
								n.getCase().getFirstPiece().getPlayer().decreaseScore(1);
								System.out.println("Votre sacrifice n'aura pas été vain...");
							}
							
							else
							{
								//action deplacer
								act_mov = new ActionMOVE("MOVE", 0, n.getCase().getFirstPiece().getPlayer(), _boardB, null);
								act_mov.setoldNum(n.getNum());
								for(Node nn : n.getChildrenList())
								{
									if(nn.getCase().getPieces().size() != 0)
									{
										act_mov.setCoordinates(nn.getCoordinates());
										break;
									}
								}
								act_mov.doAction();
								System.out.println("Repli stratégique !");
							}
						}
						}
					}
				}
				else
				{
					for (Node n : involved)
					{
						if(n.getCase().getPieces().size() != 0)
						{
						if(n.getCase().getFirstPiece().getPlayer().getColor() == 1)
						{
							if(encercle(n))
							{
								//action virer pion + décrémenter score
								act_rem = new ActionREMOVE("REMOVE", 0, player, _boardB, null);
								act_rem.setCoordinates(n.getCoordinates());
								act_rem.doAction();
								player.decreaseScore(1);
								System.out.println("Votre sacrifice n'aura pas été vain...");
							}
							
							else
							{
								//action deplacer
								act_mov = new ActionMOVE("MOVE", 0, player, _boardB, null);
								act_mov.setoldNum(n.getNum());
								for(Node nn : n.getChildrenList())
								{
									if(nn.getCase().getPieces().size() != 0)
									{
										act_mov.setCoordinates(nn.getCoordinates());
										break;
									}
								}
								act_mov.doAction();
								System.out.println("Repli stratégique !");
							}
						}
						}
					}
				}
				break;
				
			case 2 :
				if(type == 1)
				{
					for (Node n : involved)
					{
						if(n.getCase().getPieces().size() != 0)
						{
						if(n.getCase().getFirstPiece().getPlayer().getColor() == 1)
						{
							if(encercle(n))
							{
								//action virer pion + décrémenter score
								act_rem = new ActionREMOVE("REMOVE", 0, player, _boardB, null);
								act_rem.setCoordinates(n.getCoordinates());
								act_rem.doAction();
								player.decreaseScore(1);
								System.out.println("Votre sacrifice n'aura pas été vain...");
							}
							
							else
							{
								//action deplacer
								act_mov = new ActionMOVE("MOVE", 0, player, _boardB, null);
								act_mov.setoldNum(n.getNum());
								for(Node nn : n.getChildrenList())
								{
									if(nn.getCase().getPieces().size() != 0)
									{
										act_mov.setCoordinates(nn.getCoordinates());
										break;
									}
								}
								act_mov.doAction();
								System.out.println("Repli stratégique !");
							}
						}
						}
					}
				}
				else
				{
					for (Node n : involved)
					{
						if(n.getCase().getPieces().size() != 0)
						{
						if(n.getCase().getFirstPiece().getPlayer().getColor() == 2)
						{
							if(encercle(n))
							{
								//action virer pion + décrémenter score
								act_rem = new ActionREMOVE("REMOVE", 0, n.getCase().getFirstPiece().getPlayer(), _boardB, null);
								act_rem.setCoordinates(n.getCoordinates());
								act_rem.doAction();
								n.getCase().getFirstPiece().getPlayer().decreaseScore(1);
								System.out.println("Votre sacrifice n'aura pas été vain...");
							}
							
							else
							{
								//action deplacer
								act_mov = new ActionMOVE("MOVE", 0, n.getCase().getFirstPiece().getPlayer(), _boardB, null);
								act_mov.setoldNum(n.getNum());
								for(Node nn : n.getChildrenList())
								{
									if(nn.getCase().getPieces().size() != 0)
									{
										act_mov.setCoordinates(nn.getCoordinates());
										break;
									}
								}
								act_mov.doAction();
								System.out.println("Repli stratégique !");
							}
						}
						}
					}
				}
				break;
				
			case 3 :
				
				if(type == 1)
				{
					for (Node n : involved)
					{
						if(n.getCase().getPieces().size() != 0)
						{
						if(n.getCase().getFirstPiece().getPlayer().getColor() == 2)
						{
							//action virer pion + décrémenter score
							act_rem = new ActionREMOVE("REMOVE", 0, n.getCase().getFirstPiece().getPlayer(), _boardB, null);
							act_rem.setCoordinates(n.getCoordinates());
							act_rem.doAction();
							n.getCase().getFirstPiece().getPlayer().decreaseScore(1);
							System.out.println("Dur de se remettre d'un tel coup...");
						}
						}
					}
				}
				else
				{
					for (Node n : involved)
					{
						if(n.getCase().getPieces().size() != 0)
						{
						if(n.getCase().getFirstPiece().getPlayer().getColor() == 1)
						{
							//action virer pion + décrémenter score
							act_rem = new ActionREMOVE("REMOVE", 0, player, _boardB, null);
							act_rem.setCoordinates(n.getCoordinates());
							act_rem.doAction();
							player.decreaseScore(1);
							System.out.println("Dur de se remettre d'un tel coup...");
						}
						}
					}
				}
				break;
				
			case 4:
				if(type == 1)
				{
					for (Node n : involved)
					{
						if(n.getCase().getPieces().size() != 0)
						{
						if(n.getCase().getFirstPiece().getPlayer().getColor() == 1)
						{
							//action virer pion + décrémenter score
							act_rem = new ActionREMOVE("REMOVE", 0, player, _boardB, null);
							act_rem.setCoordinates(n.getCoordinates());
							act_rem.doAction();
							player.decreaseScore(1);
							System.out.println("Dur de se remettre d'un tel coup...");
						}
						}
					}
				}
				else
				{
					for (Node n : involved)
					{
						if(n.getCase().getPieces().size() != 0)
						{
						if(n.getCase().getFirstPiece().getPlayer().getColor() == 2)
						{
							//action virer pion + décrémenter score
							act_rem = new ActionREMOVE("REMOVE", 0, n.getCase().getFirstPiece().getPlayer(), _boardB, null);
							act_rem.setCoordinates(n.getCoordinates());
							act_rem.doAction();
							n.getCase().getFirstPiece().getPlayer().decreaseScore(1);
							System.out.println("Dur de se remettre d'un tel coup...");
						}
						}
					}
				}
				break;
				
			}
		}
	}
	
	public int fight(Node Intersection){
		//0 : égalité, 1 : insurgé simple, 2 : loyaliste simple, 3 : insurgé coup critique, 4 : loyaliste coup critique
		
		int nbInsurges = 0, nbLoyalistes = 0, tmp, combat;
		LinkedList<Node> listFils = (LinkedList<Node>) Intersection.getChildrenList().clone();
		listFils.push(Intersection);
		Node n;
		
		System.out.println("C'est l'heure du DUDUDUDUDUUUUEL !!");
			
		while(listFils.size() != 0)
		{
			n = listFils.pop();
			
			if(n.getCase().getPieces().size() != 0)
			{
			tmp = n.getCase().getFirstPiece().getPlayer().getColor();
			
			if( tmp == 1)
			{
				nbInsurges++;
			}
			
			else if( tmp == 2)
			{
				nbLoyalistes++;
			}
			}
		}	

		nbInsurges += dice();
		nbLoyalistes += dice();
		System.out.println("Insurgé lance le dé .... " + nbInsurges);
		System.out.println("Loyaliste lance le dé .... " + nbLoyalistes);
		combat = nbInsurges - nbLoyalistes;
		
		if( combat > 0)
		{
			if( combat > 1)
			{
				System.out.println("Les loyalistes subissent de lourdes pertes...");
				return 3;
			}		
			System.out.println("Les loyalistes perdent cette bataille, mais pas la gueurre !");
			return 1;
		}
		
		else if(combat < 0)
		{
			if( combat < -1)
			{
				System.out.println("Les insurgé auront du mal à s'en remettre...");
				return 4;
			}	
			System.out.println("Les insurgés reviendront !");
			return 2;
		}
		
		return 0;
	}
	
	public static int dice(){
		Random rand = new Random();
		return rand.nextInt(6)+1;
	}
	
	//renvoie vrai si pas d'insurgé à une distance <= distMax
	public boolean distance(Node depart, int distMax){
		LinkedList<Node> f = new LinkedList<Node>();
		LinkedList<Node> marquage = new LinkedList<Node>();
		LinkedList<Node> listFils = new LinkedList<Node>();
		f.push(depart);
		marquage.add(depart);
		Node tmp, fils;
		
		for(int i = distMax ; i > 0 ; i--)
		{			
			tmp = f.getLast();
			
			do
			{
				depart = f.pop();

				if(depart.getCase().getPieces().size() != 0)
				{
					if( depart.getCase().getFirstPiece().getPlayer().getColor() == 1 )
					{
						return false;
					}	
				}
					
				listFils = (LinkedList<Node>) depart.getChildrenList().clone();
			
				while(listFils.size() != 0)
				{
					fils = listFils.pop();
					
					if(!marquage.contains(fils))
					{
						marquage.add(fils);
						f.push(fils);
					}
				}
			}
			while(!depart.equals(tmp));					
		}		
		return true;
	}
	
	//renvoie vrai si ennemi en contact
	public boolean voisins(Node depart, Player player){
		LinkedList<Node> listChildren;
		Player tmp;
		//System.out.println(depart+"----------"+depart.getChildrenList());
		listChildren = depart.getChildrenList();
			
		for (Node n : listChildren)
		{
			if(n.getCase().getPieces().size() != 0)
			{
			tmp = n.getCase().getFirstPiece().getPlayer();
			
			if(!tmp.equals(player) && (tmp.getColor() == 1 || tmp.getColor() == 2))
			{
				return true;
			}
			}
		}
						
		return false;
	}
	
	//renvoie vrai si une case est encerclée
	public boolean encercle(Node depart)
	{
		LinkedList<Node> listChildren;
		
		listChildren = depart.getChildrenList();
			
		for (Node n : listChildren)
		{
			if(n.getCase().getPieces().size() == 0)
			{
				return false;
			}
		}	
		
		return true;
	}
	
	public static void main(String[] args) {
		Game g = new Barricades();
		List<Player> listPlayers = new LinkedList<Player>();
		listPlayers.add(new HumanPlayer("Insurgé", 1, 1, "X", _score));
		listPlayers.add(new HumanPlayer("Loyaliste", 2, 2, "O", _score));
		BoardBarricades b = new BoardBarricades();
		g.setBoard(b);
		g.init();
		_boardB = b;
		initQuartier(b);
		g.setPlayers(listPlayers);
		g.play();
	}

	@Override
	public void gameOver() {
		System.out.println("---------------\nGAME OVER !\n" + getCurrentPlayer().getName() + " remporte la partie !\nScores :");
		for (Player p : getPlayers())
			System.out.println(p.getName() + " : " + p.getScore());
	}

	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if((b.getText().equals("New game"))){
			resetPlayers(_score);
			BoardBarricades board = (BoardBarricades) getBoard();
			board.reset();
			_undoManager.discardAllEdits();
			_nbCoups = 0;
			_phase = 0;
			_choice = false;
			_countI = 5;
			_countL = 5;
			
		}
		else if((b.getText().equals("Undo"))){
			if (_undoManager.canUndo()) {
				_undoManager.undo();
				prevPlayer();
				if (_nbCoups != 0) {
					_nbCoups--;
				}
				updatePhase();	
			}
		}
		else{
			if (_undoManager.canRedo()) {
				_undoManager.redo();
				nextPlayer();
				_nbCoups++;
				updatePhase();
			}
		}
		
		
		setChanged();
		notifyObservers();
	}
}

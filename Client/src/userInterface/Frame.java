package userInterface;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import deck.Card;
import deck.Deck;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLayeredPane;

public class Frame extends JFrame {

	private JPanel contentPane;
	int tablePanelwidth;
	int tablePanelheight;
	int cardToPrint = 0;
	BackgroundPanel panel_dealer;
	int playerFocus = 4;
	final Hand Clients[] = new Hand[5];
	int player = 4;
	final JLabel lblTurn[] = new JLabel[5];
	final GridBagConstraints gbc_lblTurn[] = new GridBagConstraints[5];
	JButton btnStand;
	JButton btnHit;
	//Communication communication;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Frame() {
	//public Frame(Communication communication) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{220, 220, 220, 220, 0};
		gbl_contentPane.rowHeights = new int[]{313, 0, 164, 19, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		//this.communication = communication;
		
		btnHit = newButton(1,5,"HIT");
		btnHit.setEnabled(false);
		btnStand = newButton(2,5,"STAND");
		btnStand.setEnabled(false);
	
		//HIT BUTTON
		//*********************
					
			btnHit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if(cardToPrint<Clients[playerFocus].getCountLabels())
					{
						Deck deck = new Deck();
						Card card = deck.getCard();
						
						//sendHit();
						addCard(card);
						setTotal(card.getValue());
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Hai gata...", "Error",JOptionPane.ERROR_MESSAGE);
					}
				}});
			
			
		//HIT BUTTON
		//*********************
			
		//STAND BUTTON
		//*********************
			btnStand.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					//objectInputStream("Stand"); ceva de gen
				}
			});
		//STAND BUTTON
		//*********************

		enableButtons("Enter option: HIT/STAND");
		
	//DECK ICON
	//*********************
		JPanel panelDeck = new JPanel();
		panelDeck = newPanel(0,0);
		JLabel lblDeckImage = new JLabel();
		lblDeckImage = imageToLabel("/userInterface/other/Card-Back.png",190,280);
		lblDeckImage.setBounds(9, 5, 190, 280);
		panelDeck.add(lblDeckImage);
	//*********************
	//DECK ICONS
		
	//DEALER TABLE
	//*********************
		tablePanelwidth = gbl_contentPane.columnWidths[1]+gbl_contentPane.columnWidths[2]+gbl_contentPane.columnWidths[3];
		tablePanelheight = gbl_contentPane.rowHeights[0];
		
		panel_dealer = new BackgroundPanel(tablePanelwidth, tablePanelheight);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridwidth = 3;
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 0;
		contentPane.add(panel_dealer, gbc_panel_2);
		panel_dealer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		Deck deck = new Deck();
		Card card = deck.getCard();
		
		addDealerCard(card);
		addInvisibleCard();	
	//*********************
	//DEALER TABLE
		
	//CLIENT 1-4 TURN
	//*********************
		for(int i = 1; i<5; i++)
		{
			lblTurn[i] = new JLabel("Client " + i + " Turn");
			gbc_lblTurn[i] = new GridBagConstraints();
			gbc_lblTurn[i].insets = new Insets(0, 0, 5, 5);
			gbc_lblTurn[i].gridx = i-1;
			gbc_lblTurn[i].gridy = 1;
			contentPane.add(lblTurn[i], gbc_lblTurn[i]);
		}
	//CLIENT 1-4 TURN
	//*********************
		
	//CLIENTS HAND
	//*********************
		Clients[1] = new Hand(newPanel(0,2), 1);
		Clients[2] = new Hand(newPanel(1,2), 2);
		Clients[3] = new Hand(newPanel(2,2), 3);
		Clients[4] = new Hand(newPanel(3,2), 4);
	//*********************
	//CLIENTS HANDS
		
	//CLIENT LABELS
	//*********************
		JLabel Client1Label = newLabel(0,3,"Client1Label");
		JLabel Client2Label	= newLabel(1,3,"Client2Label");
		JLabel Client3Label = newLabel(2,3,"Client3Label");
		JLabel Client4Label = newLabel(3,3,"Client4Label");
	//*********************
	//CLIENT LABELS
		
		
	}
	
	public void addDealerCard(Card card){
		
		JLabel cardDealer = new JLabel();
		ImageIcon cardOfDealer = new ImageIcon(getClass().getResource("/userInterface/cards/" + card.toString() + ".png"));
		cardDealer.setIcon(new ImageIcon(cardOfDealer.getImage().getScaledInstance(100, 150, java.awt.Image.SCALE_SMOOTH)));
		panel_dealer.add(cardDealer);
	}
	
	public void addInvisibleCard(){
		JLabel otherCard = new JLabel();
		ImageIcon backOfCard = new ImageIcon(getClass().getResource("/userInterface/other/Card-Back.png"));
		otherCard.setIcon(new ImageIcon(backOfCard.getImage().getScaledInstance(100, 150, java.awt.Image.SCALE_SMOOTH)));
		panel_dealer.add(otherCard);
	}
	
	public JLabel imageToLabel(String path, int sizex, int sizey){
		ImageIcon cardBack = new ImageIcon(getClass().getResource(path));
		JLabel lblDeckimage = new JLabel(new ImageIcon(cardBack.getImage().getScaledInstance(sizex, sizey, java.awt.Image.SCALE_SMOOTH)));
		return lblDeckimage;
	}
	
	public JPanel newPanel (int pozx, int pozy){

		CustomPanel panel = new CustomPanel(pozx, pozy);
		contentPane.add(panel.getPane(), panel.getGBC());
		return panel.getPane();
	}
	
	private JLabel newLabel(int pozx, int pozy, String string) {
		JLabel temp = new JLabel(string);
		GridBagConstraints gbc_temp = new GridBagConstraints();
		gbc_temp.insets = new Insets(0, 0, 5, 5);
		gbc_temp.gridx = pozx;
		gbc_temp.gridy = pozy;
		contentPane.add(temp, gbc_temp);
		return temp;
	}
	
	private JButton newButton(int pozx, int pozy, String string){
		JButton btn = new JButton(string);
		btn.setEnabled(false);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 0, 5);
		gbc.gridx = pozx;
		gbc.gridy = pozy;
		contentPane.add(btn, gbc);
		return btn;
	}
	
	public void setPlayerFocus (int x){
		playerFocus = x;
	}
	
	public void setPlayer(int playerNumber){
		player = playerNumber;
	}

	public void addCard(Card card){
		ImageIcon cardBacktemp = new ImageIcon(getClass().getResource("/userInterface/cards/" + card.toString() + ".png"));
		Clients[playerFocus].getLabel(cardToPrint).setIcon(new ImageIcon(cardBacktemp.getImage().getScaledInstance(100, 150, java.awt.Image.SCALE_SMOOTH)));
		Clients[playerFocus].getLabel(Clients[playerFocus].getCountLabels() - 1).setText("");
		cardToPrint++;
	}
	
	public void setTotal(int total){
		lblTurn[playerFocus].setText("Total " + total);
		contentPane.add(lblTurn[playerFocus], gbc_lblTurn[playerFocus]);
	}
	
	public void setStatus(String status){
		lblTurn[playerFocus].setText(status);
		contentPane.add(lblTurn[playerFocus], gbc_lblTurn[playerFocus]);
		cardToPrint++;
	}
	
	public void enableButtons(String string){
		if(string.equals("Enter option: HIT/STAND")){
			btnHit.setEnabled(true);
			btnStand.setEnabled(true);
		}
	}
};
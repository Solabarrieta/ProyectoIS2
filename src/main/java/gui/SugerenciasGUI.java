package gui;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import businessLogic.BLFacade;
import domain.Event;
import domain.Question;
import domain.Quote;
import domain.Sport;
import domain.User;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.Color;
import java.awt.Font;

public class SugerenciasGUI extends JFrame{
	private BLFacade businessLogic = MainGUI.getBusinessLogic();

	private static final long serialVersionUID = 1L;
	private User user;
	private JFrame thisw;

	private JLabel lblSugerencia;

	private JLabel lblDeporte;

	private JScrollPane scrollBar;

	private JList list;
	private DefaultListModel<Event> eventLista = new DefaultListModel<Event>();
	private JLabel lblEvent;
	private JButton btnApostar;
	private JButton btnClose;

	private Sport s;

	private JLabel lblQuestion;

	private JScrollPane scrollBar_1;

	private JList list_1;
	private DefaultListModel<Question> questionLista = new DefaultListModel<Question>();
	private JLabel lblQuote;
	private JList list_2;
	private DefaultListModel<Quote> quoteLista = new DefaultListModel<Quote>();
	private JScrollPane scrollBar_2;
	private JLabel lblErrorQuestion;
	private JLabel lblErrorQuote;
	
	public SugerenciasGUI(User u) {
		thisw=this;
		getContentPane().setLayout(null);
		this.setSize(new Dimension(550, 820));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("Sugerencia"));
		
		lblSugerencia = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SugerenciaDeporte"));
		lblSugerencia.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblSugerencia.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSugerencia.setBounds(10, 45, 344, 68);
		getContentPane().add(lblSugerencia);
		
		lblDeporte = new JLabel();
		lblDeporte.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblDeporte.setBounds(364, 47, 162, 64);
		getContentPane().add(lblDeporte);
		user=u;
		
		lblErrorQuestion = new JLabel(); //$NON-NLS-1$ //$NON-NLS-2$
		lblErrorQuestion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblErrorQuestion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblErrorQuestion.setBounds(127, 314, 382, 31);
		getContentPane().add(lblErrorQuestion);
		
		lblErrorQuote = new JLabel(); //$NON-NLS-1$ //$NON-NLS-2$
		lblErrorQuote.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblErrorQuote.setHorizontalAlignment(SwingConstants.RIGHT);
		lblErrorQuote.setBounds(127, 504, 382, 30);
		getContentPane().add(lblErrorQuote);
		
		s = businessLogic.gehiengoaLortu(user);
		lblDeporte.setText(s.getIzena());
		if(s.getIzena().equals("Futbol")) {
			ImageIcon imageIcon = new ImageIcon(".\\src/main/resources\\data\\futbol.png"); // load the image to a imageIcon
		    Image image = imageIcon.getImage(); // transform it 
		    Image newimg = image.getScaledInstance(40, 40, Image.SCALE_SMOOTH); // scale it the smooth way  
		    imageIcon = new ImageIcon(newimg);
	        lblDeporte.setIcon(imageIcon);
		}else if(s.getIzena().equals("Baloncesto")) {
			ImageIcon imageIcon = new ImageIcon(".\\src/main/resources\\data\\basker.png"); // load the image to a imageIcon
		    Image image = imageIcon.getImage(); // transform it 
		    Image newimg = image.getScaledInstance(40, 40, Image.SCALE_SMOOTH); // scale it the smooth way  
		    imageIcon = new ImageIcon(newimg);
	        lblDeporte.setIcon(imageIcon);
		}else if(s.getIzena().equals("Tennis")) {
			ImageIcon imageIcon = new ImageIcon(".\\src/main/resources\\data\\tennis.png"); // load the image to a imageIcon
		    Image image = imageIcon.getImage(); // transform it 
		    Image newimg = image.getScaledInstance(40, 40, Image.SCALE_SMOOTH); // scale it the smooth way  
		    imageIcon = new ImageIcon(newimg);
	        lblDeporte.setIcon(imageIcon);
		}
		
		lblErrorQuestion.setVisible(false);
		
		btnApostar = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ApustuaEgin")); //$NON-NLS-1$ //$NON-NLS-2$
		btnApostar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnApostar.setBackground(Color.PINK);
		btnApostar.setForeground(Color.DARK_GRAY);
		list = new JList();
		list.setFont(new Font("Tahoma", Font.PLAIN, 18));
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				btnApostar.setEnabled(false);
				questionLista.removeAllElements();
				for(domain.Question question : businessLogic.findQuestion((Event)list.getSelectedValue()))
					questionLista.addElement(question); 
				if(questionLista.size()==0) {
					lblErrorQuestion.setVisible(true);
					lblErrorQuestion.setText(ResourceBundle.getBundle("Etiquetas").getString("NoQueries"));
				}else {
					lblErrorQuestion.setVisible(false);
					lblErrorQuote.setVisible(false);
				}
			}
		});
		list.setModel(eventLista);
		list.setBounds(10, 39, 1, 1);
		getContentPane().add(list);
		
		scrollBar = new JScrollPane(list);
		scrollBar.setBounds(23, 174, 486, 130);
		getContentPane().add(scrollBar);
		
		lblEvent = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Event")); //$NON-NLS-1$ //$NON-NLS-2$
		lblEvent.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEvent.setBounds(23, 138, 130, 26);
		getContentPane().add(lblEvent);
		
		
		btnApostar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a =new ApustuaEginGUI(new Vector<Event>(),user, (Quote)list_2.getSelectedValue());
				a.setVisible(true);
				thisw.setVisible(false);
			}
		});
		btnApostar.setBounds(23, 708, 140, 53);
		getContentPane().add(btnApostar);
		
		btnClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close")); //$NON-NLS-1$ //$NON-NLS-2$
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnClose.setForeground(Color.WHITE);
		btnClose.setBackground(Color.DARK_GRAY);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonClose_actionPerformed(e);
				JFrame a= new DestacadosGUI(user);
				a.setVisible(true);
			}
		});
		btnClose.setBounds(355, 708, 154, 53);
		getContentPane().add(btnClose);
		
		lblQuestion = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Queries"));
		lblQuestion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblQuestion.setBounds(23, 314, 89, 31);
		getContentPane().add(lblQuestion);
		
		lblErrorQuote.setVisible(false);
		
		list_1 = new JList();
		list_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		list_1.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				btnApostar.setEnabled(false);
				quoteLista.removeAllElements();
				for(domain.Quote quote : businessLogic.findQuote((Question)list_1.getSelectedValue()))
					quoteLista.addElement(quote);
				if(quoteLista.size()==0) {
					btnApostar.setEnabled(false);
					lblErrorQuote.setVisible(true);
					lblErrorQuote.setText(ResourceBundle.getBundle("Etiquetas").getString("NoQuote"));
				}else {
					btnApostar.setEnabled(true);
					lblErrorQuote.setVisible(false);
				}
				if(list_2.getSelectedValue()==null) {
					btnApostar.setEnabled(false);
				}
			}
		});
		list_1.setModel(questionLista);
		list_1.setBounds(48, 212, 1, 1);
		getContentPane().add(list_1);
		
		btnApostar.setEnabled(false);
		scrollBar_1 = new JScrollPane(list_1);
		scrollBar_1.setBounds(23, 355, 486, 139);
		getContentPane().add(scrollBar_1);
		
		lblQuote = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Quote")); //$NON-NLS-1$ //$NON-NLS-2$
		lblQuote.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblQuote.setBounds(23, 504, 89, 30);
		getContentPane().add(lblQuote);
		
		list_2 = new JList();
		list_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		list_2.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				btnApostar.setEnabled(true);
			}
		});
		list_2.setModel(quoteLista);
		list_2.setBounds(59, 360, 1, 1);
		getContentPane().add(list_2);
		
		scrollBar_2 = new JScrollPane(list_2);
		scrollBar_2.setBounds(23, 544, 486, 141);
		getContentPane().add(scrollBar_2);
		
		eventLista.addAll(s.getEvents());
	}
	private void jButtonClose_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}

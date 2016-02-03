package org.capfifix.client.ihm;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle.Control;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import org.capfifix.client.entities.Order;
import org.capfifix.client.entities.ServerCapFIX;
import javax.swing.SpinnerNumberModel;
import javax.print.attribute.TextSyntax;
import javax.swing.DefaultComboBoxModel;
import org.capfifix.client.enumurate.OrderSide;

public class IHM implements Observer {

	private JFrame frame;
	private JTextField adressServerField;
	private JTextField portServerField;
	private JTextField nameServerField;

	private Controller controler;
	private JTextPane logConsole;
	private JTable ordersTable;
	private JList<String> serversConnected;

	private Map<String, ServerCapFIX> servers = new HashMap<String, ServerCapFIX>();
	private JList<String> listServers;
	private JTextField loginField;

	private JTextField idOrder;
	private JTextField securityOrder;
	private JComboBox<OrderSide> sideCombox;
	private JSpinner quantitySpinner;
	private JTextField priceField;
	
	private DefaultListModel<String> serversModel = new DefaultListModel<String>();
	private DefaultListModel<String> serversConnectedModel = new DefaultListModel<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AbstractModel model = new Model();
					Controller control = new Controller(model);
					IHM window = new IHM(control);
					window.frame.setVisible(true);
					model.addObserver(window);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public IHM(Controller controler) {
		this.controler = controler;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 750, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("CapFIX Market- Client");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));

		JButton btnNewButton = new JButton("Quitter");
		panel.add(btnNewButton, BorderLayout.EAST);

		JSeparator separator = new JSeparator();
		panel.add(separator, BorderLayout.NORTH);

		JPanel container = new JPanel();
		frame.getContentPane().add(container, BorderLayout.CENTER);
		container.setLayout(null);

		JLabel lblClientParameters = new JLabel("Client Parameters");
		lblClientParameters.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblClientParameters.setBounds(6, 6, 134, 16);
		container.add(lblClientParameters);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(139, 10, 605, 12);
		container.add(separator_1);

		JLabel lblId = new JLabel("ID");
		lblId.setBounds(16, 34, 40, 16);
		container.add(lblId);

		loginField = new JTextField();
		loginField.setBounds(68, 29, 130, 26);
		container.add(loginField);
		loginField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Servers");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblNewLabel_1.setBounds(6, 91, 63, 16);
		container.add(lblNewLabel_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(68, 98, 676, 16);
		container.add(separator_2);

		listServers = new JList<String>();
		listServers.setModel(this.serversModel);
		listServers.setBounds(16, 154, 361, 115);
		container.add(listServers);

		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(389, 116, 9, 193);
		container.add(separator_3);

		JLabel lblAjouterUnServeur = new JLabel("Ajouter un serveur");
		lblAjouterUnServeur.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblAjouterUnServeur.setBounds(410, 126, 134, 16);
		container.add(lblAjouterUnServeur);

		JLabel lblIp = new JLabel("Adresse IP");
		lblIp.setBounds(420, 169, 71, 16);
		container.add(lblIp);

		JLabel lblNewLabel_2 = new JLabel("Port");
		lblNewLabel_2.setBounds(420, 197, 61, 16);
		container.add(lblNewLabel_2);

		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(420, 225, 61, 16);
		container.add(lblNom);

		adressServerField = new JTextField();
		adressServerField.setBounds(503, 164, 130, 26);
		container.add(adressServerField);
		adressServerField.setColumns(10);

		portServerField = new JTextField();
		portServerField.setBounds(503, 192, 130, 26);
		container.add(portServerField);
		portServerField.setColumns(10);

		nameServerField = new JTextField();
		nameServerField.setBounds(503, 220, 130, 26);
		container.add(nameServerField);
		nameServerField.setColumns(10);

		JButton btnNewButton_1 = new JButton("Ajouter");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// ajout de server
				ServerCapFIX server = new ServerCapFIX(nameServerField.getText(), adressServerField.getText(),
						Integer.valueOf(portServerField.getText()));

				servers.put(server.getName(), server);
				serversModel.addElement(server.getName());

			}
		});
		btnNewButton_1.setBounds(410, 280, 90, 29);
		container.add(btnNewButton_1);

		JLabel lblChoisirLesServeurs = new JLabel("Choisir les serveurs");
		lblChoisirLesServeurs.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblChoisirLesServeurs.setBounds(16, 126, 134, 16);
		container.add(lblChoisirLesServeurs);

		JButton btnNewButton_2 = new JButton("Se connecter");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// connect to server
				ServerCapFIX server = servers.get(listServers.getModel().getElementAt(listServers.getSelectedIndex()));
				controler.connectToserver(server, loginField.getText());
			}
		});
		btnNewButton_2.setBounds(16, 280, 117, 29);
		container.add(btnNewButton_2);

		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(6, 317, 738, 12);
		container.add(separator_4);

		serversConnected = new JList<String>();
		serversConnected.setModel(serversConnectedModel);
		serversConnected.setBounds(514, 366, 217, 168);
		container.add(serversConnected);

		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(6, 321, 738, 12);
		container.add(separator_5);

		idOrder = new JTextField();
		idOrder.setBounds(16, 366, 71, 26);
		container.add(idOrder);
		idOrder.setColumns(10);

		securityOrder = new JTextField();
		securityOrder.setBounds(99, 366, 99, 26);
		container.add(securityOrder);
		securityOrder.setColumns(10);

		quantitySpinner = new JSpinner();
		quantitySpinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		quantitySpinner.setBounds(210, 366, 63, 26);
		container.add(quantitySpinner);

		priceField = new JTextField();
		priceField.setBounds(285, 366, 71, 26);
		container.add(priceField);
		priceField.setColumns(10);

		sideCombox = new JComboBox<OrderSide>();
		sideCombox.setModel(new DefaultComboBoxModel<OrderSide>(OrderSide.values()));
		sideCombox.setMaximumRowCount(2);
		sideCombox.setBounds(368, 366, 71, 27);
		container.add(sideCombox);

		JLabel lblNewLabel_3 = new JLabel("Id");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(16, 345, 71, 16);
		container.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Security");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(99, 345, 99, 16);
		container.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Quantity");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(210, 345, 63, 16);
		container.add(lblNewLabel_5);

		JLabel lblPrice = new JLabel("Price");
		lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrice.setBounds(285, 345, 71, 16);
		container.add(lblPrice);

		JLabel lblSide = new JLabel("Side");
		lblSide.setHorizontalAlignment(SwingConstants.CENTER);
		lblSide.setBounds(368, 345, 71, 16);
		container.add(lblSide);

		JButton btnNewButton_3 = new JButton("Envoyer");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// add order
				controler.addOrder(idOrder.getText(), (OrderSide) sideCombox.getSelectedItem(), securityOrder.getText(),
						(int) quantitySpinner.getValue(), priceField.getText(),
						serversConnected.getModel().getElementAt(serversConnected.getSelectedIndex()));

				idOrder.setText("");
				sideCombox.setSelectedItem(OrderSide.BUY);
				securityOrder.setText("");
				quantitySpinner.setValue(1);
				priceField.setText("");

			}
		});
		btnNewButton_3.setBounds(16, 404, 99, 29);
		container.add(btnNewButton_3);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 445, 450, 133);
		container.add(scrollPane);

		ordersTable = new JTable();
		ordersTable
				.setModel(new DefaultTableModel(
						new Object[][] {},
						new String[] { "Server", "Order Id", "Security", "Side", "Price", "Quantity" }));
		scrollPane.setViewportView(ordersTable);

		JLabel lblMessagesConsole = new JLabel("Messages console");
		lblMessagesConsole.setBounds(16, 590, 117, 16);
		container.add(lblMessagesConsole);

		logConsole = new JTextPane();
		logConsole.setBounds(16, 618, 716, 109);
		container.add(logConsole);

		JButton btnSeDconnecter = new JButton("Se deconnecter");
		btnSeDconnecter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// deconnexion
				controler.disconnectToServer(
						serversConnected.getModel().getElementAt(serversConnected.getSelectedIndex()));
			}
		});
		btnSeDconnecter.setBounds(514, 546, 130, 29);
		container.add(btnSeDconnecter);
		
		JButton btnDeleteOrder = new JButton("delete Order");
		btnDeleteOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//delete order
				
				String selectedOrder = String.valueOf(ordersTable.getValueAt(ordersTable.getSelectedRow(), 1));
				
				controler.cancelOrder(selectedOrder, serversConnected.getModel().getElementAt(serversConnected.getSelectedIndex()));
			}
		});
		btnDeleteOrder.setBounds(368, 408, 107, 25);
		container.add(btnDeleteOrder);
	}

	@Override
	public void updateLog(String str) {
		try {
			Document doc = this.logConsole.getDocument();
			doc.insertString(doc.getLength(), str + "\n", null);
		} catch (BadLocationException exc) {
			exc.printStackTrace();
		}

	}

	@Override
	public void addOrder(Order order, String server) {
		DefaultTableModel model = (DefaultTableModel) this.ordersTable.getModel();
		// Server - Order Id - Security - Side - Price - Quantity
		model.addRow(new Object[] { server, order.getClientOrderId(), order.getSecurityCode(), order.getOrderSide(),
				String.valueOf(order.getPrice()), String.valueOf(order.getQuantity()) });
	}

	@Override
	public void updateOrder(Order order, String server) {
		DefaultTableModel model = (DefaultTableModel) this.ordersTable.getModel();

		for (int i = 0; i < model.getRowCount(); i++) {

			if (String.valueOf(model.getValueAt(i, 1)).equals(order.getClientOrderId())
					&& String.valueOf(model.getValueAt(i, 0)).equals(server)) {
				model.setValueAt(String.valueOf(order.getQuantity()), i, 5);
				break;
			}
		}

	}

	@Override
	public void deleteOrder(String idOrder, String server) {

		DefaultTableModel model = (DefaultTableModel) this.ordersTable.getModel();

		for (int i = 0; i < model.getRowCount(); i++) {

			if (String.valueOf(model.getValueAt(i, 1)).equals(idOrder)
					&& String.valueOf(model.getValueAt(i, 0)).equals(server)) {

				model.removeRow(i);
				break;
			}
		}

	}

	@Override
	public void disconnectFromServer(String server) {
		for (int i = 0; i < this.serversConnected.getModel().getSize(); i++) {
			if (server.equals(this.serversConnected.getModel().getElementAt(i))) {
				this.serversConnected.remove(i);
				DefaultListModel<String> model = (DefaultListModel<String>) (listServers.getModel());
				model.addElement(server);
			}

		}

	}

	@Override
	public void connectToServer(String server) {
		serversConnectedModel.addElement(server);


	}

	@Override
	public void alertDialog(String str) {
		JOptionPane optionPane = new JOptionPane(str, JOptionPane.WARNING_MESSAGE);
		JDialog dialog = optionPane.createDialog("Warning!");
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);

	}
}

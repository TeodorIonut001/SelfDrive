package gui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.awt.Choice;


public class Input extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField wallNumberTextField;
	private JTextField mapTextField;
	private JTextField wallSizeTextField;
	private JTextField vehiclePosXTextField;
	private JTextField vehiclePosYTextField;
	private JTextField endPosXTextField;
	private JTextField endPosYTextField;
	/**
	 * Launch the application.
	 */
	public void inputPage() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Input frame = new Input();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Input() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titleLabel = new JLabel("MAP v0.5");
		titleLabel.setForeground(new Color(32, 178, 170));
		titleLabel.setFont(new Font("Adobe Caslon Pro", Font.PLAIN, 25));
		titleLabel.setBounds(10, 11, 142, 40);
		contentPane.add(titleLabel);
		
		JLabel betaLabel = new JLabel("Beta");
		betaLabel.setForeground(new Color(128, 128, 128));
		betaLabel.setBounds(10, 50, 142, 14);
		contentPane.add(betaLabel);
		
		JLabel iconLabel = new JLabel("");
		iconLabel.setIcon(new ImageIcon(Input.class.getResource("/inputPictures/mapLogo.png")));
		iconLabel.setBounds(134, 11, 50, 50);
		contentPane.add(iconLabel);
		
		JLabel wallLabel = new JLabel("Numar ziduri");
		wallLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		wallLabel.setBounds(10, 147, 132, 25);
		contentPane.add(wallLabel);
		
		wallNumberTextField = new JTextField();
		wallNumberTextField.setToolTipText("Leave 0 to generate a random number of walls");
		wallNumberTextField.setText("0");
		wallNumberTextField.setBounds(10, 172, 132, 20);
		contentPane.add(wallNumberTextField);
		wallNumberTextField.setColumns(10);
		
		Choice mapChoice = new Choice();
		mapChoice.setBounds(187, 116, 147, 14);
		mapChoice.add("Generare automata");
		mapChoice.add("Predefinit");
		contentPane.add(mapChoice);
		
		JButton finishButton = new JButton("Finish");
		finishButton.setToolTipText("Create map");
		finishButton.addMouseListener(new MouseAdapter() {
			@Override
			//Parte importanta
			public void mouseClicked(MouseEvent arg0) {
				String wallNumber = wallNumberTextField.getText();
				String mapSize    = mapTextField.getText();
				String wallSize   = wallSizeTextField.getText();
				String vehicleX   = vehiclePosXTextField.getText();
				String vehicleY   = vehiclePosYTextField.getText();
				String endX       = endPosXTextField.getText();
				String endY       = endPosYTextField.getText();
				String mapGenerationChoice;
				InputMethods input = new InputMethods();
				try {
					mapGenerationChoice = mapChoice.getSelectedItem();
					//Model de "asa nu" v0.5 update
					input.finishButton(wallNumber, mapSize, wallSize,  mapGenerationChoice, 
							           vehicleX, vehicleY, endX, endY);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.exit(0);
				}
				
			}
		});
		finishButton.setBackground(new Color(32, 178, 170));
		finishButton.setForeground(new Color(255, 255, 255));
		finishButton.setBounds(10, 418, 174, 32);
		contentPane.add(finishButton);
		
		JLabel mapSizeLabel = new JLabel("Dimensiune");
		mapSizeLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		mapSizeLabel.setBounds(10, 92, 142, 25);
		contentPane.add(mapSizeLabel);
		
		mapTextField = new JTextField();
		
		mapTextField.setToolTipText("Insert a number to create 2D map array");
		mapTextField.setText("100");
		mapTextField.setBounds(10, 116, 132, 20);
		contentPane.add(mapTextField);
		mapTextField.setColumns(10);
		
		JLabel wallSizeLabel = new JLabel("Marimea unui zid");
		wallSizeLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		wallSizeLabel.setBounds(10, 206, 132, 20);
		contentPane.add(wallSizeLabel);
		
		wallSizeTextField = new JTextField();
		wallSizeTextField.setToolTipText("Wall length on map");
		wallSizeTextField.setText("10");
		wallSizeTextField.setBounds(10, 227, 132, 20);
		contentPane.add(wallSizeTextField);
		wallSizeTextField.setColumns(10);
		
		JLabel lblFillMethod = new JLabel("Format");
		lblFillMethod.setFont(new Font("Arial", Font.PLAIN, 12));
		lblFillMethod.setBounds(187, 94, 147, 20);
		contentPane.add(lblFillMethod);
		
		JLabel vehiclePositionLabel = new JLabel("Pozitia de start");
		vehiclePositionLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		vehiclePositionLabel.setBounds(10, 258, 142, 25);
		contentPane.add(vehiclePositionLabel);
		
		vehiclePosXTextField = new JTextField();
		vehiclePosXTextField.setToolTipText("Leave 0 to generate a random number of walls");
		vehiclePosXTextField.setText("0");
		vehiclePosXTextField.setColumns(10);
		vehiclePosXTextField.setBounds(34, 279, 31, 20);
		contentPane.add(vehiclePosXTextField);
		
		vehiclePosYTextField = new JTextField();
		vehiclePosYTextField.setToolTipText("Leave 0 to generate a random number of walls");
		vehiclePosYTextField.setText("0");
		vehiclePosYTextField.setColumns(10);
		vehiclePosYTextField.setBounds(98, 279, 31, 20);
		contentPane.add(vehiclePosYTextField);
		
		JLabel xLabel = new JLabel("x : ");
		xLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		xLabel.setBounds(10, 281, 14, 14);
		contentPane.add(xLabel);
		
		JLabel yLabel = new JLabel("y : ");
		yLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		yLabel.setBounds(74, 281, 14, 14);
		contentPane.add(yLabel);
		
		JLabel lblLeave = new JLabel("Lasa \"0\" pentru generare automata");
		lblLeave.setFont(new Font("Arial", Font.ITALIC, 12));
		lblLeave.setForeground(Color.LIGHT_GRAY);
		lblLeave.setBounds(10, 393, 210, 14);
		contentPane.add(lblLeave);
		
		JLabel lblRouteEndPosition = new JLabel("Pozitia de stop");
		lblRouteEndPosition.setFont(new Font("Arial", Font.PLAIN, 12));
		lblRouteEndPosition.setBounds(10, 302, 142, 25);
		contentPane.add(lblRouteEndPosition);
		
		JLabel xRouteLabel = new JLabel("x : ");
		xRouteLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		xRouteLabel.setBounds(10, 324, 14, 14);
		contentPane.add(xRouteLabel);
		
		endPosXTextField = new JTextField();
		endPosXTextField.setToolTipText("Leave 0 to generate a random number of walls");
		endPosXTextField.setText("0");
		endPosXTextField.setColumns(10);
		endPosXTextField.setBounds(34, 322, 31, 20);
		contentPane.add(endPosXTextField);
		
		JLabel yRouteLabel = new JLabel("y : ");
		yRouteLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		yRouteLabel.setBounds(74, 325, 14, 14);
		contentPane.add(yRouteLabel);
		
		endPosYTextField = new JTextField();
		endPosYTextField.setToolTipText("Leave 0 to generate a random number of walls");
		endPosYTextField.setText("0");
		endPosYTextField.setColumns(10);
		endPosYTextField.setBounds(98, 322, 31, 20);
		contentPane.add(endPosYTextField);
	}
}

package newtonraphson;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewtonRaphson extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField funcion;
	private JTextField puntoInicial;
	private JTextField error;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewtonRaphson frame = new NewtonRaphson();
					frame.setTitle("Newton-Raphson");
					frame.setVisible(true);
					frame.setSize(800,460);
					frame.setLocationRelativeTo(null);
					frame.setResizable(false);
					
					ImageIcon icono = new ImageIcon("logo.png");
					frame.setIconImage(icono.getImage());
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	JRadioButton nr1;
	JRadioButton nr2;
	
	public NewtonRaphson() {
		setBackground(new Color(255, 240, 245));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 460);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 240, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel menu = new JLabel("Newton-Raphson");
		menu.setFont(new Font("Cambria Math", Font.BOLD, 20));
		menu.setHorizontalAlignment(SwingConstants.CENTER);
		menu.setBounds(313, 46, 181, 40);
		contentPane.add(menu);
		
		JLabel lblNewLabel = new JLabel("Elige una opción");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		lblNewLabel.setBounds(61, 106, 197, 40);
		contentPane.add(lblNewLabel);

		nr1 = new JRadioButton("Newton-Raphson");
		nr1.setFont(new Font("Times New Roman", Font.BOLD, 11));
		nr1.setBounds(77, 173, 181, 21);
		contentPane.add(nr1);
		
		nr2 = new JRadioButton("Newton-Raphson Mejorado");
		nr2.setFont(new Font("Times New Roman", Font.BOLD, 11));
		nr2.setBounds(77, 209, 181, 23);
		contentPane.add(nr2);
		
		ButtonGroup group = new ButtonGroup();
		group.add(nr1);
		group.add(nr2);
		
		getContentPane().add(nr1);
		getContentPane().add(nr2);
		this.pack();		
		
		JLabel errores = new JLabel("");
		errores.setForeground(new Color(255, 0, 0));
		errores.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		errores.setHorizontalAlignment(SwingConstants.CENTER);
		errores.setBounds(327, 235, 410, 14);
		contentPane.add(errores);
		
		
		JLabel errorgeneral = new JLabel("");
		errorgeneral.setForeground(new Color(255, 0, 0));
		errorgeneral.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		errorgeneral.setHorizontalAlignment(SwingConstants.CENTER);
		errorgeneral.setBounds(165, 275, 500, 14);
		contentPane.add(errorgeneral);
		
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					String expresion = funcion.getText();
					double valor = Double.parseDouble(puntoInicial.getText());
					double margen = Double.parseDouble(error.getText());
					

					if (!nr1.isSelected() && !nr2.isSelected()) {
						errores.setText("Debe seleccionar una opción.");
		                return;
	                }
					
					if(nr1.isSelected()) {
						nr1 f1 = new nr1();
						f1.setFuncion(expresion);
						f1.setPuntoInicial(valor);
						f1.setError(margen);
						System.out.println(f1.iteracionesraiz());
					}
					
					if(nr2.isSelected()) {
						nr2 f2 = new nr2();
						f2.setFuncion(expresion);
						//f2.setPuntoInicial(valor);
						f2.setError(margen);
						
					}
					
					if(funcion.getText().isEmpty()) {
						errorgeneral.setText("Complete todos los campos para continuar.");
						errores.setText("");
						btnNewButton.setEnabled(false);
					}else{
						Resultados r = new Resultados();
						r.setVisible(true);
						r.setTitle("Newton-Raphson");
						r.setLocationRelativeTo(null);
						r.setResizable(false);
						
						ImageIcon icono = new ImageIcon("logo.png");
						r.setIconImage(icono.getImage());
						dispose();
					}
										

				}catch(Exception a) {
					errores.setText("El punto inicial debe ser entero y el margen de error decimal");
					errorgeneral.setText("");
				}
	
				if(funcion.getText().isEmpty() && puntoInicial.getText().isEmpty() && error.getText().isEmpty()) {
					errorgeneral.setText("Complete todos los campos para continuar.");
					errores.setText("");
				}else {
					btnNewButton.setEnabled(true);
				}

				
			}
		});
		

		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnNewButton.setBounds(230, 315, 117, 40);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Función");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 15));
		lblNewLabel_1.setBounds(333, 130, 75, 14);
		contentPane.add(lblNewLabel_1);
		
		funcion = new JTextField();
		funcion.setBounds(446, 120, 296, 30);
		contentPane.add(funcion);
		funcion.setColumns(10);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				errorgeneral.setText("");
				errores.setText("");
				funcion.setText("");
				puntoInicial.setText("");
				error.setText("");
				group.clearSelection();

			}
		});
		
		btnLimpiar.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnLimpiar.setBounds(446, 315, 117, 40);
		contentPane.add(btnLimpiar);
		
		JLabel lblNewLabel_1_1 = new JLabel("Punto inicial");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1.setFont(new Font("Verdana", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(326, 190, 117, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Margen de error");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_2.setFont(new Font("Verdana", Font.BOLD, 15));
		lblNewLabel_1_2.setBounds(527, 185, 132, 30);
		contentPane.add(lblNewLabel_1_2);
		
		puntoInicial = new JTextField();
		puntoInicial.setColumns(10);
		puntoInicial.setBounds(446, 185, 60, 30);
		contentPane.add(puntoInicial);
		
		error = new JTextField();
		error.setColumns(10);
		error.setBounds(674, 185, 68, 30);
		contentPane.add(error);
		
		
	}
}

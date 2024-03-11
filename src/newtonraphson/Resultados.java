package newtonraphson;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Resultados extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableu;
	private DefaultTableModel model;
	private JLabel lblNewLabel;

	public Resultados() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 123, 400, 99);
		contentPane.add(scrollPane);
		
		tableu = new JTable();
		tableu.setFont(new Font("Times New Roman", Font.BOLD, 11));
		
		model = new DefaultTableModel();

		model.addColumn("Iteraciones");
		model.addColumn("xi");
		model.addColumn("Ea%");
		tableu.setModel(model);
		
		scrollPane.setViewportView(tableu);
		
		lblNewLabel = new JLabel("Resultados");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(43, 51, 400, 35);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Regresar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewtonRaphson nr = new NewtonRaphson();
				nr.setVisible(true);
				nr.setSize(800,430);
				nr.setLocationRelativeTo(null);
				nr.setResizable(false);
				nr.setTitle("Newton-Raphson");
				ImageIcon icono = new ImageIcon("Icono/logo.png");
				nr.setIconImage(icono.getImage());
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(329, 240, 114, 23);
		contentPane.add(btnNewButton);
		

	}
	
	public void llenarTablaF1(ArrayList<Double> j, String funcion) {
		ArrayList<Double> raices = new ArrayList<Double>();
		ArrayList<Double> errores = new ArrayList<Double>();
		ArrayList<Integer> iteraciones = new ArrayList<Integer>();
		
		for(int i = 0; i< j.size(); i++) {
			if(i != ((j.size()/2))) {
				raices.add(j.get(i));
			}else {
				break;
			}
		}
		
		for(int i = 0; i< j.size(); i++) {
			if(((j.size()/2)) <= i) {
				errores.add(j.get(i));
			}
		}
		
		for(int i = 0; i< (j.size()/2); i++) {
			iteraciones.add(i);
		}

		Object[][] data = new Object[raices.size()][3];
        for (int i = 0; i < raices.size(); i++) {
            data[i][0] = iteraciones.get(i);
            data[i][1] = raices.get(i);
            data[i][2] = errores.get(i);
        }
        
        for (Object[] row : data) {
            model.addRow(row);
        }
		
        Grafica g = new Grafica();
		g.graficar(raices, funcion);
	
	}
	
	
	public void llenarTablaF2(ArrayList<Double> j, String funcion) {
		ArrayList<Double> raices = new ArrayList<Double>();
		ArrayList<Double> errores = new ArrayList<Double>();
		ArrayList<Integer> iteraciones = new ArrayList<Integer>();
		
		
		for(int i = 0; i< j.size(); i++) {
			if(i != ((j.size()/2))) {
				raices.add(j.get(i));
			}else {
				break;
			}
		}
		
		for(int i = 0; i< j.size(); i++) {
			if(((j.size()/2)) <= i) {
				errores.add(j.get(i));
			}
		}
		
		for(int i = 0; i< (j.size()/2); i++) {
			iteraciones.add(i);
		}

		Object[][] data = new Object[raices.size()][3];
        for (int i = 0; i < raices.size(); i++) {
            data[i][0] = iteraciones.get(i);
            data[i][1] = raices.get(i);
            data[i][2] = errores.get(i);
        }
        
        for (Object[] row : data) {
            model.addRow(row);
        }
		
		Grafica g = new Grafica();
		g.graficar(raices, funcion);

	}
}

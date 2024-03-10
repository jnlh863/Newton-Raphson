package newtonraphson;

import java.awt.EventQueue;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;

public class Resultados extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableu;
	private DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Resultados frame = new Resultados();
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
	public Resultados() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 64, 400, 287);
		contentPane.add(scrollPane);
		
		tableu = new JTable();
		tableu.setFont(new Font("Times New Roman", Font.BOLD, 11));
		
		model = new DefaultTableModel();

		model.addColumn("Iteraciones");
		model.addColumn("xi");
		model.addColumn("Ea%");
		tableu.setModel(model);
		
		
		
		scrollPane.setViewportView(tableu);
	}
	
	public void llenarTablaF1(ArrayList<Double> j) {
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
		
		
		System.out.println(iteraciones);
		System.out.println(raices);
		System.out.println(errores);
	}
	
	
	public void llenarTablaF2(ArrayList<Double> j) {
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
		
		
		System.out.println(iteraciones);
		System.out.println(raices);
		System.out.println(errores);
	}


}

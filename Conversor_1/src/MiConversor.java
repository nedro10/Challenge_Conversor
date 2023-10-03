import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MiConversor {

	private JFrame frame;
	private JTextField txt;
	private JButton btn;
	private JComboBox<Moneda> cmb;
	private JLabel lbl;
	
	public enum Moneda{
		pesos_dolar,
		pesos_euro,
		pesos_libra,
		dolar_pesos,
		euro_pesos,
		libra_pesos
	}
	
	public double dolar = 380.15;
	public double euro = 390.19;
	public double libra = 270.89;
	
	public double valorInput = 0.00;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MiConversor window = new MiConversor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MiConversor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txt = new JTextField();
		txt.setBounds(10, 11, 123, 20);
		frame.getContentPane().add(txt);
		txt.setColumns(10);
		
		cmb = new JComboBox<Moneda>();
		cmb.setModel(new DefaultComboBoxModel<>(Moneda.values()));
		cmb.setBounds(10, 59, 123, 22);
		frame.getContentPane().add(cmb);
		
		// evento boton
		btn = new JButton("Convertir");
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Convertir();
			}
		});
		btn.setBounds(161, 59, 89, 23);
		frame.getContentPane().add(btn);
		
		lbl = new JLabel("00.00");
		lbl.setBounds(161, 11, 89, 20);
		frame.getContentPane().add(lbl);
	}
	
	// funcion del boton
	public void Convertir() {
		// validacion de números
		if(Validar(txt.getText())) {
			Moneda moneda = (Moneda) cmb.getSelectedItem();
			
			switch (moneda) {
			
			case pesos_dolar: 
				PesosAMoneda(dolar);
				break;
			case pesos_euro: 
				PesosAMoneda(euro);
				break;
			case pesos_libra: 
				PesosAMoneda(libra);
				break;
			case dolar_pesos: 
				MonedaAPesos(dolar);
				break;
			case euro_pesos: 
				MonedaAPesos(euro);
				break;
			case libra_pesos: 
				MonedaAPesos(libra);
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + moneda);
			
			}		
		}
	}
	
	public void PesosAMoneda(double moneda) {
		double res = valorInput / moneda;
		lbl.setText(Redondear(res));
	}
	
	public void MonedaAPesos(double moneda) {
		double res = valorInput * moneda;
		lbl.setText(Redondear(res));
	}
	
	// redondear decimales
	public String Redondear(double valor) {
		DecimalFormat df = new DecimalFormat("0.00");
		df.setRoundingMode(RoundingMode.HALF_UP);
		return df.format(valor);
	}
	
	// validar input
	public boolean Validar(String texto) {
		try {
			double x = Double.parseDouble(texto);
			if(x > 0) {
			valorInput = x;
			}
			return true;
		}catch(NumberFormatException e) {
			lbl.setText("Solamente números !!");
			return false;
		}
	}
}

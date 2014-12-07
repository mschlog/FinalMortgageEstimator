//Matthew Schlogel
//FINAL

//IMPORTS
import javax.swing.*;
import java.awt.event.*;
import java.text.*;
import java.awt.*;
import java.awt.Window.*;
import java.awt.Event.*;
import javax.swing.AbstractButton;
import javax.swing.JFrame;

public class MortgageEstimator{

//___________________________________________________
	private JPanel contentPane;
	
//Main Method	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MortgageEstimator window = new MortgageEstimator();
					window.Frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		);
	}
	
//__________________________________________________
//Protected values
	protected JFrame Frame;
	protected JTextField Income;
	protected JTextField Debt;
	protected JTextField Rate;
	protected JTextField Payment;
	private JTextField Cost;
		
//___________________________________________________
//Setting up the run method for GUI	
	private void go(){
		
//Frame______________________________________________
		Frame = new JFrame();
		Frame.setBounds(100,100,450,500);
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Frame.getContentPane().setLayout(null);
		
//JLabels____________________________________________		
		JLabel IncomeLabel = new JLabel("Total Gross Income");
		IncomeLabel.setBounds(30,50,200,15);
		
		JLabel DebtLabel = new JLabel("Total Monthly Debt");
		DebtLabel.setBounds(30,100,200,15);
		
		JLabel RateLabel = new JLabel("Mortgage Interest Rate");
		RateLabel.setBounds(30,150,200,15);
		
		JLabel TermLabel = new JLabel("Term");
		TermLabel.setBounds(30,225,200,15);
			
		JLabel PaymentLabel = new JLabel("Down Payment");
		PaymentLabel.setBounds(30,300,200,15);
		
		JLabel costLabel = new JLabel("Affordable Mortgage");
		costLabel.setBounds(20,355,200,15);
//Buttons___________________________________________		
		JRadioButton Button1 = new JRadioButton(" 10 Years ");
		Button1.setBounds(220,200,200,15);
		
		JRadioButton Button2 = new JRadioButton(" 15 Years ");
		Button2.setBounds(220,225,200,15);
		
		JRadioButton Button3 = new JRadioButton(" 30 Years ");
		Button3.setBounds(220,250,200,15);	
		
//Setting the ButtonGroup____________________________
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(Button1);
		buttonGroup.add(Button2);
		buttonGroup.add(Button3);
		Button1.setSelected(true);		
				
//JTextField_________________________________________
		Income = new JTextField();
		Income.setBounds(220,45,100,25);
		
		Debt = new JTextField();
		Debt.setBounds(220,95,100,25);
		
		Rate = new JTextField();
		Rate.setBounds(220,145,100,25);
		
		Payment = new JTextField();
		Payment.setBounds(220,295,100,25);
		Payment.setText("0");
		
		final JTextField Cost = new JTextField();
		Cost.setBounds(220,350,100,25);
		Cost.setEditable(false);
						
//Adding Frame Components____________________________
		Frame.getContentPane().add(IncomeLabel);
		Frame.getContentPane().add(DebtLabel);
		Frame.getContentPane().add(RateLabel);
		Frame.getContentPane().add(TermLabel);
		Frame.getContentPane().add(Income);
		Frame.getContentPane().add(Debt);
		Frame.getContentPane().add(Rate);
		Frame.getContentPane().add(Button1);
		Frame.getContentPane().add(Button2);
		Frame.getContentPane().add(Button3);
		Frame.getContentPane().add(Payment);
		Frame.getContentPane().add(PaymentLabel);
		Frame.getContentPane().add(costLabel);
		Frame.getContentPane().add(Cost);
		
//___________________________________________________
	}
	public MortgageEstimator(){
		go();
	}
//Estimate Engine___________________________________
	
	public  static double house(double Income){
		double payment = (Income/12)*(.18);
		return payment;
	}
	public static double debt(double Income, double debt){
		double payment = ((Income/12)*(.36))-debt;
		return payment;
	}
	public static double mortgage(double Mpayment,double Dpayment, double rate, double term){
		double yearTerm = (term*12);
		double monthRate = (rate/12);
		double monthRate2 = Math.pow(1+monthRate, yearTerm);
		double Pv = ((Mpayment)*(((1-(1/monthRate2)/monthRate)))+Dpayment);
		return Pv;		
	}
//__________________________________________________	

	public void Calculate(ActionEvent arg){	
		String income = Income.getText();
		String debt = Debt.getText();
		String rate = Rate.getText();
		String payment = Payment.getText();
		Double incomevalue = Double.parseDouble(income);
		Double debtvalue = Double.parseDouble(debt);
		Double ratevalue = Double.parseDouble(rate);
		Double paymentvalue = Double.parseDouble(payment);
		
		
		double low = house(incomevalue);
		double high = debt(incomevalue,debtvalue);
		double money;
		if (low>high){
			money = low;
		}
		else{
			money = high;
		}
		double mortgageValue = mortgage(money,paymentvalue,ratevalue,10);
		DecimalFormat format = new DecimalFormat("#0.00");
		String finalmortgage = format.format(mortgageValue);
		Cost.setText(finalmortgage);	
	}
//___________________________________________________	
	/*
	 * Was having trouble trying to implement a listener to both 
	 * my RadioButtons as well as my Textfields; however, I have
	 * the right idea as far as setting up the panel and the code 
	 * to estimate an affordable mortgage.
	 */
}	
	

	
	


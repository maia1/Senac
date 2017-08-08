package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import model.Entradas;
import model.Pedidos;
import model.Produtos;
import sun.swing.table.DefaultTableCellHeaderRenderer;

public class GuiEntrada extends JPanel{
 
    private JLabel lbCod, lbProd, lbQtd, lbDtEnt, lbFunc,lbArmazem;
    private JTextField tfCod, tfProd, tfQtd, tfDtEnt;
    private JComboBox cbFunc, cbArmazem;
    private JButton btAdicionar, btRemover, btConcluir, btCadastrar;
    private JPanel pnPrincipal, pnTabela;
    private JScrollPane spTabela;
    private JTable tbTabela;
    private SimpleDateFormat sdf;
    
    public GuiEntrada(){
        inicializarComponentes();
        definirEventos();
        sdf = new SimpleDateFormat("dd/MM/yyyy");
    }
    
    public void inicializarComponentes(){
        setLayout(null);
        lbCod = new JLabel("Codigo");
        lbProd = new JLabel("Produto");
        lbQtd = new JLabel("Quantidade");
        lbDtEnt = new JLabel("Data da Entrada");
        lbFunc = new JLabel("Funcionario");
        lbArmazem = new JLabel("Armazém");       
        tfCod = new JTextField();
        tfProd = new JTextField();
        tfQtd = new JTextField();
        tfDtEnt = new JTextField();
        cbFunc = new JComboBox();
        cbArmazem = new JComboBox();
        btCadastrar = new JButton("Cadastrar");
                
        
        btAdicionar = new JButton("Adicionar");
        btRemover = new JButton("Remover");
        btConcluir = new JButton("Concluir");
        
        lbCod.setBounds(20, 10, 100, 25);
        lbProd.setBounds(20, 50, 100, 25);
        lbQtd.setBounds(230, 10, 100, 25);
        lbDtEnt.setBounds(20, 90, 100, 25);
        lbFunc.setBounds(20, 130, 100, 25);
        lbArmazem.setBounds(20, 175, 100, 25);
        
        tfCod.setEditable(false);
        tfCod.setBounds(150, 10, 50, 25);
        tfProd.setBounds(150, 50, 250, 25);
        tfQtd.setBounds(320, 10, 80, 25);
        tfDtEnt.setBounds(150, 90, 250, 25);
        
        cbFunc.setBounds(150, 130, 250, 25);
        cbArmazem.setBounds(150, 175, 250, 25);
        
        btAdicionar.setBounds(20,210,100,25);
        btRemover.setBounds(130,210,90,25);
        btConcluir.setBounds(230,210,100,25);
        btCadastrar.setBounds(400,10,90,25);
        
        pnPrincipal = new JPanel();
        pnPrincipal.setLayout(null);
        pnPrincipal.setBounds(0, 0, 500, 400);
        
        pnPrincipal.add(lbCod);
        pnPrincipal.add(lbProd);
        pnPrincipal.add(lbQtd);
        pnPrincipal.add(lbDtEnt);
        pnPrincipal.add(lbFunc);
        pnPrincipal.add(tfCod);
        pnPrincipal.add(tfProd);
        pnPrincipal.add(tfQtd);
        pnPrincipal.add(tfDtEnt);
        pnPrincipal.add(cbFunc);
        pnPrincipal.add(btAdicionar);
        pnPrincipal.add(btRemover);
        pnPrincipal.add(btConcluir);
        pnPrincipal.add(cbArmazem);
        pnPrincipal.add(lbArmazem);
        pnPrincipal.add(btCadastrar);
        
        pnTabela = new JPanel(new BorderLayout());
        pnTabela.setBorder(new TitledBorder("Itens do pedido"));
        spTabela = new JScrollPane();
        DefaultTableModel tableModel = new DefaultTableModel(
            new String[]{"CODIGO","PRODUTO", "QTD","DTENTD"},0){
                public boolean iscellEditable(int row, int col){
                    if(col == 3){
                        return false;
                    }
                    return true;
                }
            };
        
        tbTabela = new JTable(tableModel);
        
        DefaultTableCellHeaderRenderer alinharDireita = new DefaultTableCellHeaderRenderer();
        alinharDireita.setHorizontalAlignment(SwingConstants.RIGHT);
        
        tbTabela.getColumnModel().getColumn(0).setPreferredWidth(100);
        tbTabela.getColumnModel().getColumn(0).setResizable(false);
        tbTabela.getColumnModel().getColumn(1).setResizable(false);
        tbTabela.getColumnModel().getColumn(1).setPreferredWidth(170);
        tbTabela.getColumnModel().getColumn(2).setResizable(false);
        tbTabela.getColumnModel().getColumn(2).setPreferredWidth(70);
        tbTabela.getColumnModel().getColumn(2).setCellRenderer(alinharDireita);
        tbTabela.getColumnModel().getColumn(3).setResizable(false);
        tbTabela.getColumnModel().getColumn(3).setPreferredWidth(95);
        tbTabela.getColumnModel().getColumn(3).setCellRenderer(alinharDireita);
        
        tbTabela.getTableHeader().setReorderingAllowed(false);
        tbTabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        spTabela.setViewportView(tbTabela);
        pnTabela.add(spTabela);
        pnTabela.setBounds(25, 250, 450, 300);
        pnPrincipal.add(pnTabela);
        
        add(pnPrincipal);
    }
    
    public void definirEventos(){
        btAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(tfProd.equals("")||tfQtd.equals("")||tfDtEnt.equals("")){
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos");
                    return;
                }
                DefaultTableModel dtm = (DefaultTableModel)tbTabela.getModel();
                dtm.addRow(new Object[]{
                    tfCod.getText(),
                    tfProd.getText(),
                    tfQtd.getText(),
                    getPegaDataAtual()
               });
               limpar();
               return;
            }
            
            public String getPegaDataAtual() {
		Calendar calendar = new GregorianCalendar();
		Date date = new Date();
		calendar.setTime(date);
		return sdf.format(calendar.getTime());
            }
        });
        
        btCadastrar.addActionListener(new ActionListener() {
            @Override
            
            public void actionPerformed(ActionEvent ae) {
                
                ArrayList<Entradas> entradas = new ArrayList<Entradas>();
                for (int linha = 0; linha <tbTabela.getRowCount(); linha++){
                    Entradas entrada = new Entradas();
                    for(int coluna = 0; coluna< tbTabela.getColumnCount(); coluna++){  
                       
                        switch(coluna){
                            case 1: 
                            
                                entrada.setProduto("" +tbTabela.getValueAt(linha, coluna));
                                break;
                            
                            case 2:
                                
                                int qtd = Integer.parseInt(""+tbTabela.getValueAt(linha, coluna));
                                entrada.setQuantidade(qtd);
                                break;
                                
                            case 3:
                                
                                 String data = ""+ tbTabela.getValueAt(linha, coluna);
                                 Date dataEnt = null;
                                 try {
                                    dataEnt = sdf.parse(data);
                                 } catch (ParseException ex) {
                                    JOptionPane.showMessageDialog(null, "Erro no parse");
                                 }
                                entrada.setDataEntrada(dataEnt);
                                break;
                                
                            default:
                                JOptionPane.showMessageDialog(null, "Não há produtos na lista.");
                        }
                    }
                    entradas.add(entrada);
                } 
                JOptionPane.showMessageDialog(null,entradas);
                limpar();
            }
        });
       
       
        btRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int[] linhas = tbTabela.getSelectedRows();
                DefaultTableModel dtm = (DefaultTableModel) tbTabela.getModel();
                for(int i = (linhas.length-1);i>=0;i--){
                    dtm.removeRow(linhas[i]);
                }
            }
        });
    }
        
        public void limpar(){
           tfCod.setText("");
           tfDtEnt.setText("");
           tfProd.setText("");
           tfQtd.setText("");
        }

      
}
   

package gui;

import contoller.ControleSaida;
import excecoes.ProdutoNaoEncontrado;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import jdbc.Conectar;
import model.Saidas;
import sun.swing.table.DefaultTableCellHeaderRenderer;

public class GuiSaida extends JPanel{
    private JLabel lbProduto, lbQtd, lbDtSaida;
    private JTextField tfProd, tfQtd, tfDtSaida, tfCodPro, tfCodEst;
    private JButton btAdicionar, btRemover, btRegistrar, btExcluir, btLimpar;
    private JComboBox cbProdutos;
    
    private JPanel pnPrincipal, pnTabela;
    private JScrollPane spTabela;
    private JTable tbTabela;
    private ControleSaida contSaida;
    private SimpleDateFormat sdf;
    private Conectar conectar;
    private Connection connection;
    private PreparedStatement st;
    private ResultSet result;
    private String sql, resultado;
    private ArrayList<String> produtos;
  
   
    
    public GuiSaida(){
        contSaida = new ControleSaida();
        inicializarComponentes();
        definirEventos();
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        
  
    }
    
    public void inicializarComponentes(){
        setLayout(null);
        String[] produtos = listarProdutos();
        
        lbProduto = new JLabel("Produtos");
        lbQtd = new JLabel("Qtd");
        lbDtSaida = new JLabel("Data Saída");
        
        //cbProdutos = new JComboBox(buscarProdutos(produtos));
        cbProdutos = new JComboBox(produtos);
        tfProd = new JTextField();
        tfQtd = new JTextField();
        tfDtSaida = new JTextField();
        tfCodPro = new JTextField();
          
        
        btAdicionar = new JButton("Adicionar");
        btRemover = new JButton("Remover");
        btRegistrar = new JButton("Registrar");
        btExcluir = new JButton("Excluir");
        btLimpar = new JButton("Limpar");
                             //X   Y COMP  ALT
        lbProduto.setBounds(105, 10, 60, 25);
        cbProdutos.setBounds(55, 30, 270, 25);
        tfProd.setBounds(90, 30, 200, 25);
        tfCodPro.setBounds(10,30,35,25);
        lbQtd.setBounds(350, 10, 30, 25);
        tfQtd.setBounds(345, 30, 45, 25);
        
        btAdicionar.setBounds(100,80,100,25);
        btRemover.setBounds(210,80,90,25);
        
        btRegistrar.setBounds(100, 365, 100, 25);
        //btExcluir.setBounds(210,365,80,25);
        btLimpar.setBounds(210,365,80,25);
        
        pnPrincipal = new JPanel();
        pnPrincipal.setLayout(null);
        pnPrincipal.setBounds(0, 0, 500, 400);
        
        pnPrincipal.add(lbProduto);
        pnPrincipal.add(cbProdutos);
        pnPrincipal.add(tfCodPro);
        pnPrincipal.add(lbQtd);
        pnPrincipal.add(tfQtd);
        pnPrincipal.add(lbDtSaida);
        pnPrincipal.add(tfDtSaida);
        pnPrincipal.add(btAdicionar);
        pnPrincipal.add(btRemover);
        pnPrincipal.add(btRegistrar);
        //pnPrincipal.add(btExcluir);
        pnPrincipal.add(btLimpar);
        
        pnTabela = new JPanel(new BorderLayout());
        pnTabela.setBorder(new TitledBorder("Itens de Saída"));
        spTabela = new JScrollPane();
        DefaultTableModel tableModel = new DefaultTableModel(
            new String[]{"CÓD.PROD","PRODUTOS","QTD","DT.SAÍDA"},0){
                public boolean iscellEditable(int row, int col){
                    if(col <3){
                        return false;
                    }
                    return true;
                }
            };
        
        tbTabela = new JTable(tableModel);
        
        DefaultTableCellHeaderRenderer alinharDireita = new DefaultTableCellHeaderRenderer();
        alinharDireita.setHorizontalAlignment(SwingConstants.RIGHT);
        
        tbTabela.getColumnModel().getColumn(0).setPreferredWidth(40);
        tbTabela.getColumnModel().getColumn(0).setResizable(false);
        tbTabela.getColumnModel().getColumn(1).setResizable(false);
        tbTabela.getColumnModel().getColumn(1).setPreferredWidth(230);
        tbTabela.getColumnModel().getColumn(2).setResizable(false);
        tbTabela.getColumnModel().getColumn(2).setPreferredWidth(45);
        tbTabela.getColumnModel().getColumn(2).setCellRenderer(alinharDireita);
        tbTabela.getColumnModel().getColumn(3).setResizable(false);
        tbTabela.getColumnModel().getColumn(3).setPreferredWidth(70);
        tbTabela.getColumnModel().getColumn(3).setCellRenderer(alinharDireita);


        tbTabela.getTableHeader().setReorderingAllowed(false);
        tbTabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        spTabela.setViewportView(tbTabela);
        pnTabela.add(spTabela);
        pnTabela.setBounds(20, 125, 400, 200);
        pnPrincipal.add(pnTabela);
        
        add(pnPrincipal);
    }
    
    public void definirEventos(){
        
        btAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //cbProdutos = new JComboBox(buscarProdutos());
                cbProdutos.requestFocus();
                if(cbProdutos.getSelectedItem().equals("") || tfQtd.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos");
                    return;
                }
                DefaultTableModel dtm = (DefaultTableModel)tbTabela.getModel();
                dtm.addRow(new Object[]{
                    tfCodPro.getSelectedText(),
                    cbProdutos.getSelectedItem(),
                    tfQtd.getText(),
                    sdf.format(getDataAtual()),

               });
               limpar();
               
               return ;
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
        btLimpar.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent e) {
                    
                    limpar();
                }
            });
        
        btRegistrar.addActionListener(new ActionListener() {
            @Override
            
            public void actionPerformed(ActionEvent ae) {
                
                ArrayList<Saidas> saidas = new ArrayList<Saidas>();
                
                for (int linha = 0; linha <tbTabela.getRowCount(); linha++){
                    Saidas saida = new Saidas();
                    for(int coluna = 1; coluna< tbTabela.getColumnCount(); coluna++){  
                       
                        switch(coluna){
                            case 1: 
                                
                                int qtd = Integer.parseInt(""+tbTabela.getValueAt(linha, coluna));
                                saida.setQuantidadeSai(qtd);
                                break;
                            
                            case 2:
                              
                                saida.setDataSaida(getDataAtual());
                                
                                
                                break;
                                
                                
                            default:
                                JOptionPane.showMessageDialog(null, "Não há produtos na lista.");
                        }
                        
                    }
                    saidas.add(saida);
                } 
                try {
                    //JOptionPane.showMessageDialog(null, saidas);
                    contSaida.cadastrar(saidas);
                } catch (SQLException ex) {
                    Logger.getLogger(GuiSaida.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ProdutoNaoEncontrado ex) {
                    Logger.getLogger(GuiSaida.class.getName()).log(Level.SEVERE, null, ex);
                }
                limpar();
            }

            
        });
        
    }
    private void limpar(){
       cbProdutos.setSelectedItem("");
       tfDtSaida.setText("");
       tfProd.setText("");
       tfQtd.setText("");
    }

    public static Date getDataAtual(){   
        Calendar c = Calendar.getInstance();    
        return c.getTime(); 
    }
    
    private String[] listarProdutos(){
        String[] prod = contSaida.listarProdutos();
        return prod;
    }
   
     
}

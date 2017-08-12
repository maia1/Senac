package gui;

import contoller.ControleEntrada;
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
import model.Categorias;
import model.Entradas;
import model.Pedidos;
import model.Produtos;
import sun.swing.table.DefaultTableCellHeaderRenderer;

public class GuiEntrada extends JPanel{
 
    private JLabel lbCod, lbProd, lbQtd, lbDtEnt, lbdtVal,lbDescricao;
    private JTextField tfCod, tfProd, tfQtd, tfDtVal, tfDescricao;
    private JComboBox cbCategorias;
    private JButton btAdicionar, btRemover, btConcluir, btCadastrar;
    private JPanel pnPrincipal, pnTabela;
    private JScrollPane spTabela;
    private JTable tbTabela;
    private SimpleDateFormat sdf;
    private ControleEntrada contEntrada;

    
    public GuiEntrada(){
        contEntrada = new ControleEntrada();
        inicializarComponentes();
        definirEventos();
        sdf = new SimpleDateFormat("dd/MM/yyyy");
    }
    
    public void inicializarComponentes(){
        setLayout(null);
        String[] categorias = listarCategorias();
        lbCod = new JLabel("Codigo");
        lbProd = new JLabel("Produto");
        lbQtd = new JLabel("Quantidade");
        lbDtEnt = new JLabel("Categorias");
        lbdtVal = new JLabel("Data de Val.");
        lbDescricao = new JLabel("Descrição");       
        tfCod = new JTextField();
        tfProd = new JTextField();
        tfQtd = new JTextField();
        cbCategorias = new JComboBox(categorias);
        tfDtVal = new JTextField();
        tfDescricao = new JTextField();
        btCadastrar = new JButton("Cadastrar");
                
        
        btAdicionar = new JButton("Adicionar");
        btRemover = new JButton("Remover");
        btConcluir = new JButton("Concluir");
        
        lbCod.setBounds(20, 10, 100, 25);
        lbProd.setBounds(20, 50, 100, 25);
        lbQtd.setBounds(230, 10, 100, 25);
        lbDtEnt.setBounds(20, 90, 100, 25);
        lbdtVal.setBounds(20, 130, 100, 25);
        lbDescricao.setBounds(20, 175, 100, 25);
        
        tfCod.setEditable(false);
        tfCod.setBounds(150, 10, 50, 25);
        tfProd.setBounds(150, 50, 250, 25);
        tfQtd.setBounds(320, 10, 80, 25);
        cbCategorias.setBounds(150, 90, 250, 25);
        
        tfDtVal.setBounds(150, 130, 250, 25);
        tfDescricao.setBounds(150, 175, 250, 25);
        
        btAdicionar.setBounds(20,210,100,25);
        btRemover.setBounds(130,210,90,25);
        btConcluir.setBounds(230,210,100,25);
        btCadastrar.setBounds(400,10,90,25);
        
        pnPrincipal = new JPanel();
        pnPrincipal.setLayout(null);
        pnPrincipal.setBounds(0, 0, 600, 550);
        
        pnPrincipal.add(lbCod);
        pnPrincipal.add(lbProd);
        pnPrincipal.add(lbQtd);
        pnPrincipal.add(lbDtEnt);
        pnPrincipal.add(lbdtVal);
        pnPrincipal.add(tfCod);
        pnPrincipal.add(tfProd);
        pnPrincipal.add(tfQtd);
        pnPrincipal.add(cbCategorias);
        pnPrincipal.add(tfDtVal);
        pnPrincipal.add(btAdicionar);
        pnPrincipal.add(btRemover);
        pnPrincipal.add(btConcluir);
        pnPrincipal.add(tfDescricao);
        pnPrincipal.add(lbDescricao);
        pnPrincipal.add(btCadastrar);
        
        pnTabela = new JPanel(new BorderLayout());
        pnTabela.setBorder(new TitledBorder("Itens do pedido"));
        spTabela = new JScrollPane();
        DefaultTableModel tableModel = new DefaultTableModel(
            new String[]{"PRODUTO", "QTD","DTVAL","CAT","DESC"},0){
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
        tbTabela.getColumnModel().getColumn(4).setResizable(false);
        tbTabela.getColumnModel().getColumn(4).setPreferredWidth(95);
        tbTabela.getColumnModel().getColumn(4).setCellRenderer(alinharDireita);
       
       
        
        tbTabela.getTableHeader().setReorderingAllowed(false);
        tbTabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        spTabela.setViewportView(tbTabela);
        pnTabela.add(spTabela);
        pnTabela.setBounds(15, 290, 550, 250);
        pnPrincipal.add(pnTabela);
        
        add(pnPrincipal);
    }
    
    public Date getPegaDataAtual() {
	Calendar calendar = new GregorianCalendar();
	Date date = new Date();
	calendar.setTime(date);
	return calendar.getTime();
    }
    
    public void definirEventos(){
        
        btAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(tfProd.equals("")||tfQtd.equals("")||cbCategorias.equals("")){
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos");
                    return;
                }
                DefaultTableModel dtm = (DefaultTableModel)tbTabela.getModel();
                dtm.addRow(new Object[]{
                    tfProd.getText(),
                    tfQtd.getText(),
                    tfDtVal.getText(),
                    cbCategorias.getSelectedItem(),
                    tfDescricao.getText()
               });
               limpar();
               return;
            }
            
            
        });
        
        btCadastrar.addActionListener(new ActionListener() {
            @Override
            
            public void actionPerformed(ActionEvent ae) {
                
                ArrayList<Entradas> entradas = new ArrayList<Entradas>();
                
                for (int linha = 0; linha <tbTabela.getRowCount(); linha++){
                    Categorias categoria = new Categorias();
                    Produtos produto = new Produtos();
                    Entradas entrada = new Entradas();
                    for(int coluna = 0; coluna< tbTabela.getColumnCount(); coluna++){  
                        
                        switch(coluna){
                            case 0: 
                                produto.setNome("" +tbTabela.getValueAt(linha, coluna));
                                break;
                            
                            case 1:
                                int qtd = Integer.parseInt(""+tbTabela.getValueAt(linha, coluna));
                                entrada.setQuantidade(qtd);                                
                                break;
                                
                            case 2:
                                 String data = ""+ tbTabela.getValueAt(linha, coluna);
                                 Date dataEnt = null;
                                 try {
                                    dataEnt = sdf.parse(data);
                                    
                                 } catch (ParseException ex) {
                                    JOptionPane.showMessageDialog(null, "Erro no parse");
                                 }
                                entrada.setDataVal(dataEnt);
                                break;
                            
                            case 3:
                                categoria.setCategoria("" +tbTabela.getValueAt(linha, coluna));
                                produto.setCategoria(categoria);
                                break;
                                
                            case 4:
                                produto.setDescricao(""+tbTabela.getValueAt(linha, coluna));
                                break;
                                
                            default:
                                JOptionPane.showMessageDialog(null, "Não há produtos na lista.");
                                break;
                        }
                    }
                    entrada.setProduto(produto);
                    entradas.add(entrada);
                }

                String data = sdf.format(getPegaDataAtual());
                Date data1 = null;
                try {
                    data1 = sdf.parse(data);
                } catch (ParseException ex) {
                    Logger.getLogger(GuiEntrada.class.getName()).log(Level.SEVERE, null, ex);
                }
                contEntrada.cadastrar(entradas, data1);
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
        
        public String[] listarCategorias() {   
        String[] categor =  contEntrada.listarCategorias();
        return categor;        
    }
    
        public void limpar(){
           tfCod.setText("");
           tfDtVal.setText("");
           cbCategorias.setSelectedIndex(0);
           tfProd.setText("");
           tfQtd.setText("");
        }

      
}
   

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
import javax.swing.JTabbedPane;
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
 
    private JLabel lbCod, lbProd, lbQtd, lbDtEnt, lbdtVal,lbDescricao, lbDataBus;
    private JTextField tfCod, tfProd, tfQtd, tfDtVal, tfDescricao, tfDataBus;
    private JComboBox cbCategorias, cbBcodEnt;
    private JButton btAdicionar, btRemover, btConcluir, btCadastrar, btBBuscar, btBMostrar;
    private JPanel pnPrincipal, pnTabela, pnTabela1;
    private JScrollPane spTabela, spTabela1;
    private JTable tbTabela, tbTabela1;
    private SimpleDateFormat sdf;
    private ControleEntrada contEntrada;
    private JPanel pn1, pn2;
    private JTabbedPane tpAba;
    
    public GuiEntrada(){
        contEntrada = new ControleEntrada();
        inicializarComponentes();
        definirEventos();
        sdf = new SimpleDateFormat("dd/MM/yyyy");
    }
    
    public void inicializarComponentes(){
        setLayout(null);
        String[] categorias = listarCategorias();
        //Restante
        
        lbCod = new JLabel("Codigo");
        lbProd = new JLabel("Produto");
        lbQtd = new JLabel("Quantidade");
        lbDtEnt = new JLabel("Categorias");
        lbdtVal = new JLabel("Data de Val.");
        lbDataBus = new JLabel("Data da Entrada");
        lbDescricao = new JLabel("Descrição");       
        tfCod = new JTextField();
        tfProd = new JTextField();
        tfQtd = new JTextField();
        cbCategorias = new JComboBox(categorias);
        tfDtVal = new JTextField();
        tfDescricao = new JTextField();
        tfDataBus = new JTextField();
        
        cbBcodEnt = new JComboBox();
        btBMostrar = new JButton("Mostrar");
        
        cbBcodEnt.setBounds(20, 100, 100, 25);
        btBMostrar.setBounds(140, 100, 100, 25);
        
        btCadastrar = new JButton("Cadastrar");
        btBBuscar = new JButton("Buscar");
        btAdicionar = new JButton("Adicionar");
        btRemover = new JButton("Remover");
        btConcluir = new JButton("Concluir");
        
        lbCod.setBounds(20, 10, 100, 25);
        lbDataBus.setBounds(220, 10, 120, 25);
        lbProd.setBounds(20, 50, 100, 25);
        lbQtd.setBounds(230, 10, 100, 25);
        lbDtEnt.setBounds(20, 90, 100, 25);
        lbdtVal.setBounds(20, 130, 100, 25);
        lbDescricao.setBounds(20, 175, 100, 25);
        
        tfDataBus.setBounds(360, 10, 100, 25);
        tfCod.setBounds(150, 10, 50, 25);
        tfProd.setBounds(150, 50, 250, 25);
        tfQtd.setBounds(320, 10, 80, 25);
        cbCategorias.setBounds(150, 90, 250, 25);
        
        tfDtVal.setBounds(150, 130, 250, 25);
        tfDescricao.setBounds(150, 175, 250, 25);
        
        btAdicionar.setBounds(20,210,100,25);
        btRemover.setBounds(130,210,90,25);
        btConcluir.setBounds(230,210,100,25);
        btCadastrar.setBounds(400,10,100,25);
        btBBuscar.setBounds(400, 45, 100, 25);
        
        //Abas
        
        pn1 = new JPanel(getLayout()); 
        pn2 = new JPanel(getLayout());
       
            
        //pn1 add
            
        pn1.add(lbProd);
        pn1.add(lbQtd);
        pn1.add(lbDtEnt);
        pn1.add(lbdtVal);
        pn1.add(tfProd);
        pn1.add(tfQtd);
        pn1.add(cbCategorias);
        pn1.add(tfDtVal);
        pn1.add(btAdicionar);
        pn1.add(btRemover);
        pn1.add(btConcluir);
        pn1.add(tfDescricao);
        pn1.add(lbDescricao);
        pn1.add(btCadastrar);
        
        //pn2 add
        pn2.add(lbCod);
        pn2.add(tfCod);
        pn2.add(lbDataBus);
        pn2.add(tfDataBus);
        pn2.add(btBBuscar);
        pn2.add(btBMostrar);
        pn2.add(cbBcodEnt);
            
        //Abas
            
        tpAba = new JTabbedPane();
          
        tpAba.setBounds(0, 0, 600, 500);
          
        tpAba.add("Cadastro", pn1);
        tpAba.add("Buscar", pn2);
         
        
        pnPrincipal = new JPanel();
        pnPrincipal.setLayout(null);
        pnPrincipal.setBounds(0, 0, 570, 650);
        pnPrincipal.add(tpAba);
        
        //tabela 
        
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
        
        pnTabela1 = new JPanel(new BorderLayout());
        pnTabela1.setBorder(new TitledBorder("Itens da Entrada"));
        spTabela1 = new JScrollPane();
        DefaultTableModel tableModel1 = new DefaultTableModel(
            new String[]{"PRODUTO", "QTD"},0){
                public boolean iscellEditable(int row, int col){
                    if(col == 3){
                        return false;
                    }
                    return true;
                }
            };
        
        tbTabela1 = new JTable(tableModel1);
        
        DefaultTableCellHeaderRenderer alinharDireita1 = new DefaultTableCellHeaderRenderer();
        alinharDireita.setHorizontalAlignment(SwingConstants.RIGHT);
        
        tbTabela1.getColumnModel().getColumn(0).setPreferredWidth(100);
        tbTabela1.getColumnModel().getColumn(0).setResizable(false);
        tbTabela1.getColumnModel().getColumn(1).setResizable(false);
        tbTabela1.getColumnModel().getColumn(1).setPreferredWidth(170);
        
        tbTabela1.getTableHeader().setReorderingAllowed(false);
        tbTabela1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        spTabela1.setViewportView(tbTabela1);
        pnTabela1.add(spTabela1);
        pnTabela1.setBounds(30, 250, 550, 250);
        
        
        pn1.add(pnTabela);
        pn2.add(pnTabela1);
        
        pnPrincipal.add(tpAba);
      
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
        
        btBBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ArrayList<String> codigos = new ArrayList<String>();
                if(tfCod.getText().equals("")){
                    if(tfDataBus.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "Informe Código ou Data");
                    }else{
                        Date data = null;
                        String dataBus = tfDataBus.getText();
                        try {
                            data = sdf.parse(dataBus);
                        } catch (ParseException ex) {
                            Logger.getLogger(GuiEntrada.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        codigos = contEntrada.buscarCodigos(data);
                        String cod1 = "";
                        
                        cbBcodEnt.addItem(cod1);
                       
                        for(String cod : codigos){
                           cbBcodEnt.addItem(cod);
                        }

                    }
                }
            }   
        });
        
        btBMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Entradas> entradas = new ArrayList<Entradas>();
                
                int codBus = Integer.parseInt(""+cbBcodEnt.getSelectedItem());
                Produtos produto = new Produtos();
                entradas = contEntrada.buscarProdutos(codBus);
                
                
                for(Entradas entrada : entradas){
                    produto = entrada.getProduto();
                    DefaultTableModel dtm1 = (DefaultTableModel)tbTabela1.getModel();
                    dtm1.addRow(new Object[]{
                    
                    produto.getNome(),
                    entrada.getQuantidade()

                    });
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
   

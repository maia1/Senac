package gui;

import contoller.ControlePedido;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
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
import model.Pedidos;
import sun.swing.table.DefaultTableCellHeaderRenderer;

public class GuiPedido extends JPanel{
    private JLabel lbCod, lbQtd, lbProd, lbPed,lbDTPed;
    private JTextField tfCod, tfQtd, tfDTPed;
    private JComboBox cbProd, cbPed;
    private JButton btAdicionar, btRemover, btRegistrar, btMostrar, btBaixar, btBuscar;
    private JPanel pnPrincipal, pnTabela, pnTabela1, pnTabela2;
    private JScrollPane spTabela, spTabela1, spTabela2;
    private JTable tbTabela, tbTabela1, tbTabela2;
    private ControlePedido contPedidos;
    private JPanel pn1, pn2,pn3;
    private JTabbedPane tpAba;
    private SimpleDateFormat sdf;
    
    public GuiPedido(){
        contPedidos = new ControlePedido();
        inicializarComponentes();
        definirEventos();
        sdf = new SimpleDateFormat("dd/MM/yyyy");
    }
    
    public void inicializarComponentes(){
        setLayout(null);
        String[] produtos = listarProdutos();
        String[] pedidos = listarPedidos();
        
        lbCod = new JLabel("Código: ");
        lbQtd = new JLabel("Quantidade: ");
        lbProd = new JLabel("Produtos: ");
        lbPed = new JLabel("Pedidos Solicitados");
        lbDTPed = new JLabel("Data Pedido");
        
        tfCod = new JTextField();
        tfCod.setEditable(false);
        tfQtd = new JTextField();
        tfDTPed = new JTextField();
        
        cbProd = new JComboBox(produtos);
        cbPed = new JComboBox(pedidos);
        
        btAdicionar = new JButton("Adicionar");
        btRemover = new JButton("Remover");
        btRegistrar = new JButton("Registrar");
        btMostrar = new JButton("Mostrar");
        btBaixar = new JButton("Baixar");
        btBuscar = new JButton("Buscar");

        lbCod.setBounds(20, 10, 100, 25);
        lbQtd.setBounds(220, 10, 100, 25);
        lbProd.setBounds(20, 50, 100, 25);
        lbPed.setBounds(20, 10, 150, 25);
        lbDTPed.setBounds(20, 50, 100, 25);
        
        tfCod.setBounds(130, 10, 50, 25);
        tfQtd.setBounds(300, 10, 120, 25);
        tfDTPed.setBounds(130, 50, 100, 25);
        
        cbProd.setBounds(130, 50, 290, 25);
        cbPed.setBounds(130, 50, 290, 25);
        
        btBaixar.setBounds(150, 90, 120, 25);
        btMostrar.setBounds(20, 90, 120, 25);
        btAdicionar.setBounds(20, 100, 120, 25);
        btRemover.setBounds(160, 100, 120, 25);
        btRegistrar.setBounds(300, 100, 120, 25);
        btBuscar.setBounds(20, 90, 120, 25);
        
        //Abas
                
        pnPrincipal = new JPanel();
        pnPrincipal.setLayout(null);
        pnPrincipal.setBounds(0, 0, 500, 500);
        
        //abas
        
        pn1 = new JPanel(getLayout()); 
        pn2 = new JPanel(getLayout());
        pn3 = new JPanel(getLayout());
      
        //Aba: adicionar

        pn1.add(lbCod);
        pn1.add(lbQtd);
        pn1.add(lbProd);
        pn1.add(tfCod);
        pn1.add(tfQtd);
        pn1.add(cbProd);
        pn1.add(cbProd);
        pn1.add(btAdicionar);
        pn1.add(btRemover);
        pn1.add(btRegistrar);
        
        pn2.add(cbPed);
        pn2.add(btMostrar);
        pn2.add(btBaixar);
        pn2.add(lbPed);
        
        pn3.add(lbDTPed);
        pn3.add(tfDTPed);
        pn3.add(btBuscar);
        
        //Aba: criação tabela
       
        tpAba = new JTabbedPane();
        
        
        
        tpAba.setBounds(0, 0, 600, 500);
        
        tpAba.add("Solicitar", pn1);
        tpAba.add("Verificar", pn2);
        tpAba.add("Buscar",pn3);
        
        //tabela cadastro
        pnTabela = new JPanel(new BorderLayout());
        pnTabela.setBorder(new TitledBorder("Itens do pedido:"));
        spTabela = new JScrollPane();
        
        DefaultTableModel tableModel = new DefaultTableModel(
                new String[]{"Codigo","Produto","Quantidade","Data_Pedido"},0){
                    public boolean isCellEditable(int row, int col){
                        if(col == 3){
                            return false;
                        }
                        return true;
                    }
        };
        
        tbTabela = new JTable(tableModel);
        
        DefaultTableCellHeaderRenderer alinhaDireita = new DefaultTableCellHeaderRenderer();
        alinhaDireita.setHorizontalAlignment(SwingConstants.RIGHT);
        
        tbTabela.getColumnModel().getColumn(0).setPreferredWidth(50);
        tbTabela.getColumnModel().getColumn(0).setResizable(false);
        tbTabela.getColumnModel().getColumn(1).setResizable(false);
        tbTabela.getColumnModel().getColumn(1).setPreferredWidth(156);
        tbTabela.getColumnModel().getColumn(1).setCellRenderer(alinhaDireita);
        tbTabela.getColumnModel().getColumn(2).setResizable(false);
        tbTabela.getColumnModel().getColumn(2).setPreferredWidth(80);
        tbTabela.getColumnModel().getColumn(2).setCellRenderer(alinhaDireita);
        tbTabela.getColumnModel().getColumn(3).setResizable(false);
        tbTabela.getColumnModel().getColumn(3).setPreferredWidth(100);
        tbTabela.getColumnModel().getColumn(3).setCellRenderer(alinhaDireita);
        
        tbTabela.getTableHeader().setReorderingAllowed(false);
        tbTabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        spTabela.setViewportView(tbTabela);
        pnTabela.add(spTabela);
        pnTabela.setBounds(20, 140, 400, 300);
        
        //tabela busca
        
        pnTabela1 = new JPanel(new BorderLayout());
        pnTabela1.setBorder(new TitledBorder("Itens do pedido:"));
        spTabela1 = new JScrollPane();
        
        DefaultTableModel tableModel1 = new DefaultTableModel(
                new String[]{"Produto","Quantidade"},0){
                    public boolean isCellEditable1(int row, int col){
                        if(col == 1){
                            return false;
                        }
                        return true;
                    }
        };
        
        tbTabela1 = new JTable(tableModel1);
        
        DefaultTableCellHeaderRenderer alinhaDireita1 = new DefaultTableCellHeaderRenderer();
        alinhaDireita1.setHorizontalAlignment(SwingConstants.RIGHT);
        
        tbTabela1.getColumnModel().getColumn(0).setPreferredWidth(190);
        tbTabela1.getColumnModel().getColumn(0).setResizable(false);
        tbTabela1.getColumnModel().getColumn(1).setResizable(false);
        tbTabela1.getColumnModel().getColumn(1).setPreferredWidth(90);
        tbTabela1.getColumnModel().getColumn(1).setCellRenderer(alinhaDireita);
        
        tbTabela1.getTableHeader().setReorderingAllowed(false);
        tbTabela1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        spTabela1.setViewportView(tbTabela1);
        pnTabela1.add(spTabela1);
        pnTabela1.setBounds(150, 120, 300, 300);
        
        //Pn Tabela 2
        
        pnTabela2 = new JPanel(new BorderLayout());
        pnTabela2.setBorder(new TitledBorder("Itens do pedido:"));
        spTabela2 = new JScrollPane();
        
        DefaultTableModel tableModel2 = new DefaultTableModel(
                new String[]{"Produto","Quantidade"},0){
                    public boolean isCellEditable1(int row, int col){
                        if(col == 1){
                            return false;
                        }
                        return true;
                    }
        };
        
        tbTabela2 = new JTable(tableModel2);
        
        DefaultTableCellHeaderRenderer alinhaDireita2 = new DefaultTableCellHeaderRenderer();
        alinhaDireita2.setHorizontalAlignment(SwingConstants.RIGHT);
        
        tbTabela2.getColumnModel().getColumn(0).setPreferredWidth(190);
        tbTabela2.getColumnModel().getColumn(0).setResizable(false);
        tbTabela2.getColumnModel().getColumn(1).setResizable(false);
        tbTabela2.getColumnModel().getColumn(1).setPreferredWidth(90);
        tbTabela2.getColumnModel().getColumn(1).setCellRenderer(alinhaDireita);
        
        tbTabela2.getTableHeader().setReorderingAllowed(false);
        tbTabela2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        spTabela2.setViewportView(tbTabela2);
        pnTabela2.add(spTabela2);
        pnTabela2.setBounds(150, 120, 300, 300);
       
        //add
       
        pn1.add(pnTabela);
        pn2.add(pnTabela1);
        pn3.add(pnTabela2);
        
        
        pnPrincipal.add(tpAba);
        
        add(pnPrincipal);
    }
        
    public void definirEventos(){
        cbProd.requestFocus();
        
        btAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cbProd.equals("")||tfQtd.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Preenche todos os campos.");
                    return;
                }
                String prod = ""+cbProd.getSelectedItem();
                int cod = contPedidos.buscarCod(prod);
                
                DefaultTableModel dtm = (DefaultTableModel) tbTabela.getModel();
                dtm.addRow(new Object[]{
                    cod,
                    cbProd.getSelectedItem(),
                    tfQtd.getText(),
                    getDataAtual()
                });
                limpar();
                return;
            }
            
            private Object getDataAtual() {
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                Calendar c = Calendar.getInstance();
                return df.format(c.getTime());
            }
        });
        
        btRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] linhas = tbTabela.getSelectedRows();
                DefaultTableModel dtm = (DefaultTableModel) tbTabela.getModel();
                for(int i = (linhas.length - 1);i>=0;i--){
                    dtm.removeRow(linhas[i]);
                }
            }
        });
        
        btRegistrar.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                ArrayList<Pedidos> pedidos = new ArrayList<Pedidos>();
                
                for(int linha = 0; linha < tbTabela.getRowCount(); linha++){
                    Pedidos pedido = new Pedidos();
                    for(int coluna = 0; coluna < tbTabela.getColumnCount(); coluna++){
                        
                        switch(coluna){
                            case 0:
                                pedido.setCodigo(Integer.parseInt(""+tbTabela.getValueAt(linha,coluna)));
                                break;
                            case 1:
                                String nome = ""+tbTabela.getValueAt(linha, coluna);
                                pedido.setNomePro(nome);
                                break;
                            case 2:
                                String qtdS = "" + tbTabela.getValueAt(linha, coluna) ;
                                int qtd = Integer.parseInt(qtdS);
                                pedido.setQuantidade(qtd);
                                break;
                            case 3:
                                pedido.setDtPedido(getDataAtual());
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Não há pedidos na lista");
                                break;
                        }
                        
                    }
                    pedidos.add(pedido);
                }
                contPedidos.cadastrar(pedidos);
                limpar();
            }
        });
        
        btMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                ArrayList<Pedidos> pedidos = new ArrayList<Pedidos>();
                
                int codBus = Integer.parseInt(""+cbPed.getSelectedItem());
                pedidos = contPedidos.buscarPedidos(codBus);

                for(Pedidos pedido : pedidos ){
                    DefaultTableModel dtm1 = (DefaultTableModel)tbTabela1.getModel();
                    dtm1.addRow(new Object[]{
                        pedido.getNomePro(),
                        pedido.getQuantidade()
                    });
                }
            }
        });
//        
//        btBaixar.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int codPedido  = Integer.parseInt((String)cbPed.getSelectedItem());
//                contPedidos.darBaixaPedido(codPedido);
//                limparTudo();
//            }
//        });
        
        btBaixar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //ArrayList<ItemPedido> itensPedido = new ArrayList<ItemPedido>();
                //ItemPedido item;
                //int[] linhas = tbTabela1.getSelectedRows();
                
                //DefaultTableModel dtm = (DefaultTableModel) tbTabela1.getModel();
                /*for(int linha = 0; linha < tbTabela1.getRowCount(); linha++){
                    item = new ItensPedidos();
                    item.setNome(""+tbTabela1.getValueAt(linha, 0));
                    item.setQtd((int) tbTabela1.getValueAt(linha, 1));
                    itensPedido.add(item);
                }*/
                int codPedido = Integer.parseInt((String) cbPed.getSelectedItem());
                contPedidos.darBaixaPedido(codPedido);
                cbPed.removeAllItems();
            }
        });
        
        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                limparTudo();
                ArrayList<Pedidos> pedidos = new ArrayList<Pedidos>();
                
                String dataPedido = ""+tfDTPed.getText();
                
                Date data = null;
                
                
                try {
                    data = sdf.parse(dataPedido);
                } catch (ParseException ex) {
                    java.util.logging.Logger.getLogger(GuiPedido.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                 pedidos = contPedidos.buscarPedidosProdutos(data);
                 
                 for(Pedidos pedido : pedidos){
                     DefaultTableModel dtm = (DefaultTableModel)tbTabela2.getModel();
                     dtm.addRow(new Object[]{
                         pedido.getNomePro(),
                         pedido.getQuantidade(),
                         dataPedido
                     });
                     
                 }
            }

            
        });
    }
    
    private void limparTudo(){
  
        int linhas = tbTabela2.getRowCount();
        //JOptionPane.showMessageDialog(null, "Limpar Tabelas");
        DefaultTableModel dtm = (DefaultTableModel) tbTabela2.getModel();
        for(int i = 0; linhas>0; linhas--){
            dtm.removeRow(i);
                }
        int linhasB = tbTabela2.getRowCount();
        
        DefaultTableModel dtmB = (DefaultTableModel) tbTabela2.getModel();
        for(int i = 0; linhasB>0; linhasB--){
            dtmB.removeRow(i);
                }
    }
    
    private void limpar(){
        tfCod.setText("");
        cbProd.setToolTipText("");
        tfQtd.setText("");
    }
    
    public static Date getDataAtual(){
        Calendar c = Calendar.getInstance();
        return c.getTime();
    }
   
    private String[] listarProdutos(){
        String[] prod = contPedidos.listarProdutos();
        return prod;
    }
    
    private String[] listarPedidos(){
        String[] ped = contPedidos.listarPedidos();
        return ped;
    }
}
    
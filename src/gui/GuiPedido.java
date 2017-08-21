package gui;

import com.sun.istack.internal.logging.Logger;
import contoller.ControlePedido;
import excecoes.ProdutoNaoEncontrado;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
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
import model.Produtos;
import sun.swing.table.DefaultTableCellHeaderRenderer;

public class GuiPedido extends JPanel{
    private JLabel lbCod, lbQtd, lbProd;
    private JTextField tfCod, tfQtd;
    private JComboBox cbProd, cbPed;
    private JButton btAdicionar, btRemover, btRegistrar, btMostrar;
    private JPanel pnPrincipal, pnTabela, pnTabela1;
    private JScrollPane spTabela, spTabela1;
    private JTable tbTabela, tbTabela1;
    private ControlePedido contPedidos;
    private JPanel pn1, pn2;
    private JTabbedPane tpAba;
    
    public GuiPedido(){
        contPedidos = new ControlePedido();
        inicializarComponentes();
        definirEventos(); 
    }
    
    public void inicializarComponentes(){
        setLayout(null);
        String[] produtos = listarProdutos();
        String[] pedidos = listarPedidos();
        
        lbCod = new JLabel("Código: ");
        lbQtd = new JLabel("Quantidade: ");
        lbProd = new JLabel("Produtos: ");
        
        tfCod = new JTextField();
        tfCod.setEditable(false);
        tfQtd = new JTextField();
        
        cbProd = new JComboBox(produtos);
        cbPed = new JComboBox(pedidos);
        
        btAdicionar = new JButton("Adicionar");
        btRemover = new JButton("Remover");
        btRegistrar = new JButton("Registrar");
        btMostrar = new JButton("Mostrar");

        lbCod.setBounds(20, 10, 100, 25);
        lbQtd.setBounds(220, 10, 100, 25);
        lbProd.setBounds(20, 50, 100, 25);
        
        tfCod.setBounds(130, 10, 50, 25);
        tfQtd.setBounds(300, 10, 120, 25);
        
        cbProd.setBounds(130, 50, 290, 25);
        cbPed.setBounds(130, 50, 290, 25);
        
        btMostrar.setBounds(20, 100, 120, 25);
        btAdicionar.setBounds(20, 100, 120, 25);
        btRemover.setBounds(160, 100, 120, 25);
        btRegistrar.setBounds(300, 100, 120, 25);
        
        //Abas
                
        pnPrincipal = new JPanel();
        pnPrincipal.setLayout(null);
        pnPrincipal.setBounds(0, 0, 500, 500);
        
        //abas
        
        pn1 = new JPanel(getLayout()); 
        pn2 = new JPanel(getLayout());
      
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
        
        //Aba: criação tabela
       
        tpAba = new JTabbedPane();
        
        
        
        tpAba.setBounds(0, 0, 600, 500);
        
        tpAba.add("Cadastro", pn1);
        tpAba.add("Buscar", pn2);
        
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
        
        tbTabela1.getColumnModel().getColumn(0).setPreferredWidth(156);
        tbTabela1.getColumnModel().getColumn(0).setResizable(false);
        tbTabela1.getColumnModel().getColumn(1).setResizable(false);
        tbTabela1.getColumnModel().getColumn(1).setPreferredWidth(100);
        tbTabela1.getColumnModel().getColumn(1).setCellRenderer(alinhaDireita);

        
        tbTabela1.getTableHeader().setReorderingAllowed(false);
        tbTabela1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        spTabela1.setViewportView(tbTabela1);
        pnTabela1.add(spTabela1);
        pnTabela1.setBounds(150, 120, 300, 300);
       
        //add
       
        pn1.add(pnTabela);
        pn2.add(pnTabela1);
        
        
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
    
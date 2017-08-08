package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import sun.swing.table.DefaultTableCellHeaderRenderer;

public class GuiPedido extends JPanel{
    private JLabel lbCod, lbProd, lbQtd, lbDtPed, lbFunc;
    private JTextField tfCod, tfProd, tfQtd, tfDtPed;
    private JComboBox cbFunc;
    private JButton btAdicionar, btRemover;
    private JPanel pnPrincipal, pnTabela;
    private JScrollPane spTabela;
    private JTable tbTabela;
    
    public GuiPedido(){
        inicializarComponentes();
        definirEventos();
    }
    
    public void inicializarComponentes(){
        setLayout(null);
        lbCod = new JLabel("Codigo");
        lbProd = new JLabel("Produto");
        lbQtd = new JLabel("Quantidade");
        lbDtPed = new JLabel("Data do pedido");
        lbFunc = new JLabel("Funcionario");
        
        tfCod = new JTextField();
        tfProd = new JTextField();
        tfQtd = new JTextField();
        tfDtPed = new JTextField();
        cbFunc = new JComboBox();
        
        btAdicionar = new JButton("Adicionar");
        btRemover = new JButton("Remover");
        
        lbCod.setBounds(20, 30, 100, 25);
        lbProd.setBounds(20, 70, 100, 25);
        lbQtd.setBounds(230, 30, 100, 25);
        lbDtPed.setBounds(20, 110, 100, 25);
        lbFunc.setBounds(20, 150, 100, 25);
        
        tfCod.setBounds(150, 30, 50, 25);
        tfProd.setBounds(150, 70, 250, 25);
        tfQtd.setBounds(320, 30, 80, 25);
        tfDtPed.setBounds(150, 110, 250, 25);
        
        cbFunc.setBounds(150, 150, 250, 25);
        
        btAdicionar.setBounds(20,200,100,25);
        btRemover.setBounds(130,200,90,25);
        
        pnPrincipal = new JPanel();
        pnPrincipal.setLayout(null);
        pnPrincipal.setBounds(0, 0, 500, 400);
        
        pnPrincipal.add(lbCod);
        pnPrincipal.add(lbProd);
        pnPrincipal.add(lbQtd);
        pnPrincipal.add(lbDtPed);
        pnPrincipal.add(lbFunc);
        pnPrincipal.add(tfCod);
        pnPrincipal.add(tfProd);
        pnPrincipal.add(tfQtd);
        pnPrincipal.add(tfDtPed);
        pnPrincipal.add(cbFunc);
        pnPrincipal.add(btAdicionar);
        pnPrincipal.add(btRemover);
        
        pnTabela = new JPanel(new BorderLayout());
        pnTabela.setBorder(new TitledBorder("Itens do pedido"));
        spTabela = new JScrollPane();
        DefaultTableModel tableModel = new DefaultTableModel(
            new String[]{"CODIGO","PRODUTO", "QTD","DT.PED"},0){
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
        
        tbTabela.getColumnModel().getColumn(0).setPreferredWidth(70);
        tbTabela.getColumnModel().getColumn(0).setResizable(false);
        tbTabela.getColumnModel().getColumn(1).setResizable(false);
        tbTabela.getColumnModel().getColumn(1).setPreferredWidth(170);
        tbTabela.getColumnModel().getColumn(2).setResizable(false);
        tbTabela.getColumnModel().getColumn(2).setPreferredWidth(50);
        tbTabela.getColumnModel().getColumn(2).setCellRenderer(alinharDireita);
        tbTabela.getColumnModel().getColumn(3).setResizable(false);
        tbTabela.getColumnModel().getColumn(3).setPreferredWidth(95);
        tbTabela.getColumnModel().getColumn(3).setCellRenderer(alinharDireita);
        
        tbTabela.getTableHeader().setReorderingAllowed(false);
        tbTabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        spTabela.setViewportView(tbTabela);
        pnTabela.add(spTabela);
        pnTabela.setBounds(20, 250, 400, 200);
        pnPrincipal.add(pnTabela);
        
        add(pnPrincipal);
    }
    
    public void definirEventos(){
        btAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(tfProd.equals("")||tfQtd.equals("")||tfDtPed.equals("")){
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos");
                    return;
                }
                DefaultTableModel dtm = (DefaultTableModel)tbTabela.getModel();
                dtm.addRow(new Object[]{
                    tfCod.getText(),
                    tfProd.getText(),
                    tfQtd.getText(),
                    tfDtPed.getText()
               });
               limpar();
               return;
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
       private void limpar(){
           tfCod.setText("");
           tfDtPed.setText("");
           tfProd.setText("");
           tfQtd.setText("");
       }
}
    
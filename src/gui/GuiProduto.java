package gui;

import contoller.ControleProduto;
import excecoes.ProdutoNaoEncontrado;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.Categorias;
import model.Produtos;

public class GuiProduto extends JPanel {
    private JLabel lbNome, lbFabricacao, lbDataValidade, lbCategorias, lbArmazem, lbPmrn, lbCodigo;
    private JTextField tfNome, tfFabricacao, tfDataValidade, tfCodigo;
    private JComboBox cbCategorias, cbArmazem;
    private JButton btCadastrar, btBuscar, btAtualizar, btExcluir, btLimpar ;
    private SimpleDateFormat sdf; 
    private ControleProduto contProduto;
    private int contador, numCombo;
    
    public GuiProduto(){
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        contProduto = new ControleProduto();
        iniciarComponentes();
        definirEventos();
    }

   
        
   
    
    private void iniciarComponentes() {
        setLayout(null);
        setBackground(Color.white);
        String[] categorias = listarCategorias();
        
        btCadastrar = new JButton("Cadastrar");
        btBuscar = new JButton("Buscar");
        btAtualizar = new JButton("Atualizar");
        btExcluir = new JButton("Excluir");
        btLimpar = new JButton("Limpar");
        
        lbPmrn = new JLabel(new ImageIcon("C://img/PMRN.jpg"));
        lbNome = new JLabel("Produto");
        lbDataValidade = new JLabel("Data de Validade");
        lbFabricacao = new JLabel("Data de Fabricação");
        lbCategorias = new JLabel("Categorias");
        //lbArmazem = new JLabel("Armazem");
        lbCodigo = new JLabel("Código");
        tfNome = new JTextField();
        tfCodigo = new JTextField();
        tfFabricacao = new JTextField();
        tfDataValidade = new JTextField();
        cbCategorias = new JComboBox(categorias);
        //cbArmazem = new JComboBox();
        
        
        btCadastrar.setBounds     (20, 270, 100,25);
        btBuscar.setBounds        (140, 270, 80,25);
        btAtualizar.setBounds     (240, 270, 100,25);
        btExcluir.setBounds       (360, 270, 80, 25);
        btLimpar.setBounds        (450, 270, 80, 25);
        lbCodigo.setBounds        (20, 10, 100, 25);
        tfCodigo.setEditable(false);
        lbPmrn.setBounds          (350,10, 200, 200);
        lbNome.setBounds          (20,  60, 100, 25);
        lbDataValidade.setBounds  (20, 110, 100, 25);
        lbFabricacao.setBounds    (20, 160, 130, 25);
        lbCategorias.setBounds    (20, 195, 100, 25);
        //lbArmazem.setBounds       (20, 290, 100, 25);
        tfNome.setBounds          (140, 60, 200, 25);
        tfCodigo.setBounds        (140, 10, 200, 25);
        tfDataValidade.setBounds  (140,110, 200, 25);
        tfFabricacao.setBounds    (140,160, 200, 25);
        cbCategorias.setBounds    (20, 225, 200, 25);
        //cbArmazem.setBounds       (20, 320, 200, 25);
       
        add(lbNome);
        add(btCadastrar);
        add(btBuscar);
        add(btAtualizar);
        add(btExcluir);
        add(btLimpar);
        add(lbCategorias);
       
        //add(lbArmazem);
        add(lbDataValidade);
        add(lbFabricacao);
        add(cbCategorias);
        
        //add(cbArmazem);
        add(tfNome);
        add(tfFabricacao);
        add(tfCodigo);
        add(tfDataValidade);
        add(lbPmrn);
        add(lbCodigo);
    }
    
    

    private void definirEventos() {
            
        btCadastrar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                String nome = tfNome.getText();
                String data1 = tfFabricacao.getText();
                String data2 = tfDataValidade.getText();
                Date dataFab = null;
                Date dataVal = null;
                try {
                    dataFab = sdf.parse(data1);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Erro na Formatação!");
                }
                try {
                    dataVal = sdf.parse(data2);
                } catch (ParseException ex) {
                    Logger.getLogger(GuiProduto.class.getName()).log(Level.SEVERE, null, ex);
                }
                tfNome.requestFocus();
                String categoria = ""+cbCategorias.getSelectedItem();
                contProduto.cadastrar(nome, dataFab, dataVal, categoria);
                limpar();
            }
        });
        
        btBuscar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(tfNome.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Digite o nome do Produto");
                }else{
                Produtos pro = null;
                try {
                    pro = contProduto.buscar(tfNome.getText());
                } catch (ProdutoNaoEncontrado ex) {
                    Logger.getLogger(GuiProduto.class.getName()).log(Level.SEVERE, null, ex);
                }
                String nome1 = "";
                try{
                            
                    String dataFab = sdf.format(pro.getDataFabricacao());
                    String dataVali = sdf.format(pro.getDataValidade());
                    tfFabricacao.setText((dataFab));
                    tfDataValidade.setText((dataVali));
                    tfCodigo.setText(""+pro.getCodigo());
                    Categorias categor = pro.getCategoria();
                    String finalc = categor.getCategoria();
                    for(contador = 0 ; contador <= cbCategorias.getSize().height; contador++){
                        nome1 = String.valueOf(cbCategorias.getItemAt(contador));
                    if(nome1.equals(finalc)){
                           numCombo = contador;
                        }
                    }
                    cbCategorias.setSelectedIndex(numCombo);
                    tfNome.requestFocus();
                }catch(NullPointerException nu){
                   JOptionPane.showMessageDialog(null, "Produto não encontrado");
                }
                }
            }
        });
        
        btAtualizar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = tfNome.getText();
                String data1 = tfFabricacao.getText();
                String data2 = tfDataValidade.getText();
                String categoria = (String) cbCategorias.getSelectedItem();
                Date dataFab = null;
                Date dataVal = null;
                int codigo = Integer.parseInt(tfCodigo.getText());
                try {
                    dataFab = sdf.parse(data1);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Erro na Formatação!");
                }
                try {
                    dataVal = sdf.parse(data2);
                } catch (ParseException ex) {
                    Logger.getLogger(GuiProduto.class.getName()).log(Level.SEVERE, null, ex);
                }
                tfNome.requestFocus();
                contProduto.editar(nome, dataFab, dataVal, codigo, categoria);
                JOptionPane.showMessageDialog(null, "Produto Atualizado com Sucesso");
            }
        });
        
       btExcluir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String cod = tfCodigo.getText();
                String data = tfDataValidade.getText();
                if(cod.equalsIgnoreCase("")|| data.equalsIgnoreCase("")){
                    
                    JOptionPane.showMessageDialog(null, "Digite o nome do Produto \nLogo após faça a busca para excluir");
                    return;
                
                }else{
                    int codigo = Integer.parseInt(cod);
                    contProduto.excluir(codigo);
                    limpar();
                    tfNome.requestFocus();
                }
            }
        });
       
        btLimpar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                limpar();
            }
        });
    }
   
    private String[] listarCategorias() {   
        String[] categor =  contProduto.listarCategorias();
        return categor;        
    }
   
    private void limpar (){
        tfCodigo.setText("");
        tfDataValidade.setText("");
        tfFabricacao.setText("");
        tfNome.setText("");
        cbCategorias.setSelectedIndex(0);
        JOptionPane.showMessageDialog(null, "Ação Realizada com Sucesso!");
    }
}

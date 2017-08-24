
package gui;

import contoller.ControleCategoria;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GuiCategoria extends JPanel {
    private JTextField tfCategoria;
    private JComboBox cbCategoria;
    private JLabel lbCategoria;
    private JButton btCadastrar, btEditar, btExcluir;
    private ControleCategoria contCategoria;
    
    public GuiCategoria() {
        contCategoria = new ControleCategoria();
        iniciarComponentes();
        definirEventos();
    }
    
    public void iniciarComponentes(){
        setLayout(null);
       
        String[] categorias = listarCategorias();
        
        lbCategoria = new JLabel("Categoria");
        cbCategoria = new JComboBox(categorias);
        btCadastrar = new JButton("Cadastrar");
        btEditar = new JButton("Editar");
        btExcluir = new JButton("Excluir");
        tfCategoria = new JTextField();
        
        lbCategoria.setBounds(20, 10, 100, 25);
        tfCategoria.setBounds(150, 10, 80, 25);
        cbCategoria.setBounds(20,50, 100, 25);
        btCadastrar.setBounds(20, 80, 100, 25);
        btEditar.setBounds(140,80, 100, 25);
        btExcluir.setBounds(260,80, 100, 25);
        
        add(lbCategoria);
        add(tfCategoria);
        add(cbCategoria);
        add(btCadastrar);
        add(btEditar);
        add(btExcluir);
    }
    
    
    public void definirEventos(){
        btCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               String nome = tfCategoria.getText();
               contCategoria.cadastrar(nome);
               JOptionPane.showMessageDialog(null, "Cadastro Realizado.");
               tfCategoria.setText("");
            }
        });
        
        btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String nome = ""+cbCategoria.getSelectedItem();
                contCategoria.excluir(nome);
                cbCategoria.setSelectedIndex(0);
                tfCategoria.setText("");        
            }
        });
        
        btEditar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = ""+cbCategoria.getSelectedItem();
                String novoNome = tfCategoria.getText();
                contCategoria.editar(nome, novoNome);
                cbCategoria.setSelectedItem(0);
            }
        });
    
    }
    
    public String[] listarCategorias() {   
        String[] categor =  contCategoria.listarCategorias();
        return categor;        
    }

}

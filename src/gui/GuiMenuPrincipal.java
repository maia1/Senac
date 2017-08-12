
package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import model.Saidas;


public class GuiMenuPrincipal extends JFrame{
    private JMenuBar mnBarra;
    private JMenu mnArquivo, mnEstoque;
    private JMenuItem mtSair, mtPedido, mtEntrada, mtSaida;
    private Container container;

    public GuiMenuPrincipal() {
        iniciarComponentes();
        definirEventos();
    }

    public void iniciarComponentes() {
        container = getContentPane(); 
        setTitle("Estoque");
        setBounds(0, 0, 600, 500);
        
        //inicializar componentes
        mnBarra = new JMenuBar();
        mnArquivo = new JMenu("Arquivo");
        mnEstoque = new JMenu("Estoque");
        mtPedido = new JMenuItem("Pedido");
        mtSair = new JMenuItem("Sair");
        mtEntrada = new JMenuItem("Entrada");
        mtSaida = new JMenuItem("Saída");
        
        mnBarra.add(mnArquivo);
        mnBarra.add(mnEstoque);
        mnArquivo.add(mtSair);
        mnEstoque.add(mtPedido);
        mnEstoque.add(mtEntrada);
        mnEstoque.add(mtSaida);
        setJMenuBar(mnBarra);
       
    }

    public void definirEventos() {
        mtSair.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        mtPedido.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                GuiPedido pedido = new GuiPedido();
                container.removeAll();
                container.add(pedido);
                container.validate();
            }
        });
        
        mtEntrada.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                GuiEntrada entrada = new GuiEntrada();
                container.removeAll();
                container.add(entrada);
                container.validate();
            }
        });
        
        mtSaida.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                GuiSaida  saida = new GuiSaida();
                container.removeAll();
                container.add(saida);
                container.validate();
            }
        });
    }
    
     
    public static void main() {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                GuiMenuPrincipal menuPrincipal = new GuiMenuPrincipal();
                menuPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                menuPrincipal.setResizable(false);
                Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
                menuPrincipal.setLocation((tela.width-menuPrincipal.getSize().width)/2, (tela.height-menuPrincipal.getSize().height)/2);
                menuPrincipal.setVisible(true);
            }
        });
        
        
    }
    
}

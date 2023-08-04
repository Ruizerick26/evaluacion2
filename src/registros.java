import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class registros {
    private JPanel root;
    private JTextField codigotxt;
    private JTextField cedulatxt;
    private JTextField nombretxt;
    private JTextField fechatxt;
    private JComboBox signotxt;
    private JButton buscarCBt;
    private JButton buscarNbt;
    private JButton buscarSbt;
    private JButton borrarbt;
    private JButton actualizarbt;
    private JButton ingresarbt;
    private JButton limpiarButton;

    static final String db_URL="jdbc:mysql://localhost/POO3";
    static final String usser="root";
    static final String pass="root_bas3";
    static final String query="SELECT * from REGISTROS";
    private boolean data = false;

    registrosC persona = new registrosC();
    public registros(){
        ingresarbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                persona.setCodigo(codigotxt.getText());
                persona.setCedula(cedulatxt.getText());
                persona.setNombre(nombretxt.getText());
                persona.setFecha(fechatxt.getText());
                persona.setSigno((String) signotxt.getSelectedItem());

                try(
                        Connection conn = DriverManager.getConnection(db_URL,usser,pass);
                        Statement stan= conn.createStatement();
                        ){
                            String queryI = "Insert into REGISTROS VALUES ('" + persona.getCodigo() + "','"+ persona.getCedula() + "','" + persona.getNombre() + "','" + persona.getFecha() + "','"+persona.getSigno() + "')";
                            System.out.println(queryI);
                            stan.executeUpdate(queryI);
                            JOptionPane.showMessageDialog(null,"USUARIO REGISTRADO","Regitros",JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                codigotxt.setText("");
                cedulatxt.setText("");
                nombretxt.setText("");
                fechatxt.setText("");
                signotxt.setSelectedItem("");
            }
        });
        actualizarbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                persona.setCodigo(codigotxt.getText());
                persona.setCedula(cedulatxt.getText());
                persona.setNombre(nombretxt.getText());
                persona.setFecha(fechatxt.getText());
                persona.setSigno((String) signotxt.getSelectedItem());
                try(
                        Connection conn = DriverManager.getConnection(db_URL,usser,pass);
                        Statement stat = conn.createStatement();
                        ){
                            String queryI = "UPDATE REGISTROS SET cedula = '"+ persona.getCedula() + "', nombre = '" + persona.getNombre() + "', fecha = '" + persona.getFecha() + "', signo = '"+persona.getSigno() + "' " +
                                    "WHERE codigo = '"+ persona.getCodigo() + "'";
                            stat.executeUpdate(queryI);
                            System.out.println(queryI);
                            JOptionPane.showMessageDialog(null,"USUARIO ACTUALIZADO","Regitros",JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        borrarbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                persona.setCodigo(codigotxt.getText());
                try(
                        Connection conn = DriverManager.getConnection(db_URL,usser,pass);
                        Statement stat = conn.createStatement();
                        ){
                            String queriI = "DELETE FROM REGISTROS WHERE codigo = '" + persona.getCodigo() + "'";
                            stat.executeUpdate(queriI);
                            System.out.println(queriI);
                            JOptionPane.showMessageDialog(null,"USUARIO ELIMINADO","Regitros",JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        buscarCBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                persona.setCodigo(codigotxt.getText());
                try(
                        Connection conn = DriverManager.getConnection(db_URL,usser,pass);
                        Statement stat = conn.createStatement();
                        ResultSet rc = stat.executeQuery(query);
                        ){
                            while(rc.next()) {
                                if (persona.getCodigo().equals(rc.getString("codigo"))) {
                                    nombretxt.setText(rc.getString("nombre"));
                                    cedulatxt.setText(rc.getString("cedula"));
                                    fechatxt.setText(rc.getString("fecha"));
                                    signotxt.setSelectedItem(rc.getString("signo"));
                                    data = false;
                                    break;
                                }
                                else{
                                    data = true;
                                }
                            }
                            if(data == true){
                                JOptionPane.showMessageDialog(null,"USUARIO NO ENCONTRADO","Regitros",JOptionPane.ERROR_MESSAGE);
                            }

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        buscarNbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                persona.setNombre(nombretxt.getText());
                try(
                        Connection conn = DriverManager.getConnection(db_URL,usser,pass);
                        Statement stat = conn.createStatement();
                        ResultSet rc = stat.executeQuery(query);
                ){
                    while(rc.next()) {
                        if (persona.getNombre().equals(rc.getString("nombre"))) {
                            codigotxt.setText(rc.getString("codigo"));
                            cedulatxt.setText(rc.getString("cedula"));
                            fechatxt.setText(rc.getString("fecha"));
                            signotxt.setSelectedItem(rc.getString("signo"));
                            data = false;
                            break;
                        }
                        else{
                            data = true;
                        }
                    }
                    if(data == true){
                        JOptionPane.showMessageDialog(null,"USUARIO NO ENCONTRADO","Regitros",JOptionPane.ERROR_MESSAGE);
                    }

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        buscarSbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                persona.setSigno((String) signotxt.getSelectedItem());
                try(
                        Connection conn = DriverManager.getConnection(db_URL,usser,pass);
                        Statement stat = conn.createStatement();
                        ResultSet rc = stat.executeQuery(query);
                ){
                    while(rc.next()) {
                        if (persona.getSigno().equals(rc.getString("signo"))) {
                            codigotxt.setText(rc.getString("codigo"));
                            cedulatxt.setText(rc.getString("cedula"));
                            fechatxt.setText(rc.getString("fecha"));
                            nombretxt.setText(rc.getString("nombre"));
                            data = false;
                            break;
                        }
                        else{
                            data = true;
                        }
                    }
                    if(data == true){
                        JOptionPane.showMessageDialog(null,"USUARIO NO ENCONTRADO","Regitros",JOptionPane.ERROR_MESSAGE);
                    }

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("registros");
        frame.setContentPane(new registros().root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

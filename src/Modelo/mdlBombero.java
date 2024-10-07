/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Vista.Bombero;
import Vista.NewJFrame;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import jnafilechooser.api.JnaFileChooser;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.json.JSONObject;

/**
 *
 * @author gerst
 */
public class mdlBombero {

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombreB() {
        return NombreB;
    }

    public void setNombreB(String NombreB) {
        this.NombreB = NombreB;
    }

    public int getEdadB() {
        return EdadB;
    }

    public void setEdadB(int EdadB) {
        this.EdadB = EdadB;
    }

    public Double getPesoB() {
        return PesoB;
    }

    public void setPesoB(Double PespB) {
        this.PesoB = PespB;
    }

    public String getCorreoB() {
        return CorreoB;
    }

    public void setCorreoB(String CorreoB) {
        this.CorreoB = CorreoB;
    }
    
    private int ID;
    private String NombreB;
    private int EdadB;
    private Double PesoB;
    private String CorreoB;
    
    public void GuardarBombero() {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        try {
            //Variable que contiene la Query a ejecutar
            String sql = "INSERT INTO tbBombero (nombre_bombero, edad_bombero, peso_bombero, correo_bombero) values (?, ?, ?, ?)";
            //Creamos el PreparedStatement que ejecutará la Query
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            //Establecer valores de la consulta SQL
            pstmt.setString(1, getNombreB());
            pstmt.setInt(2, getEdadB());
            pstmt.setDouble(3, getPesoB());
            pstmt.setString(4, getCorreoB());

            //Ejecutar la consulta
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo:metodo guardar " + ex);
        }
    }
    
    public void Actualizar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();

        if (filaSeleccionada != -1) {
            //Obtenemos el id de la fila seleccionada
            int ID = (int)tabla.getValueAt(filaSeleccionada, 0);

            try {
                //Ejecutamos la Query
                String sql = "update tbBombero set nombre_bombero = ?,edad_bombero = ?, peso_bombero = ?, correo_bombero = ? where UUID = ?";
                PreparedStatement updateUser = conexion.prepareStatement(sql);

                updateUser.setString(1, getNombreB());
                updateUser.setInt(2, getEdadB());
                updateUser.setDouble(3, getPesoB());
                updateUser.setString(4, getCorreoB());
                updateUser.setInt(5, ID);
                updateUser.executeUpdate();

            } catch (Exception e) {
                System.out.println("este es el error en el metodo de actualizar" + e);
            }
        } else {
            System.out.println("no");
        }
    }
    
     public void cargarDatosTabla(Bombero vista) {
        // Obtén la fila seleccionada 
        int filaSeleccionada = vista.jtbBombero.getSelectedRow();

        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            int ID = (int)vista.jtbBombero.getValueAt(filaSeleccionada, 0);
            String NomBombero = vista.jtbBombero.getValueAt(filaSeleccionada, 1).toString();
            String EdadBombero = vista.jtbBombero.getValueAt(filaSeleccionada, 2).toString();
            String PesoBombero = vista.jtbBombero.getValueAt(filaSeleccionada, 3).toString();
            String CorreoBombero = vista.jtbBombero.getValueAt(filaSeleccionada, 4).toString();
            

            // Establece los valores en los campos de texto
            vista.txtNombre.setText(NomBombero);
            vista.txtEdad.setText(EdadBombero);
            vista.txtPeso.setText(PesoBombero);
            vista.txtCorreo.setText(CorreoBombero);
            
        }
    }
     
    public int Eliminar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada != -1) {
            //obtenemos que fila seleccionó el usuario
            int ID = (int)tabla.getValueAt(filaSeleccionada, 0);
            //Obtenemos el id de la fila seleccionada


            //borramos 
            try {
                String sql = "delete from tbBombero where UUID = ?";
                PreparedStatement deleteEstudiante = conexion.prepareStatement(sql);
                deleteEstudiante.setInt(1, ID);
                int respuesta = deleteEstudiante.executeUpdate();
                return respuesta;
            } catch (Exception e) {
                System.out.println("este es el error metodo de eliminar" + e);
                return 0;
            }
        }
        else {
            System.out.println("no");
            return -1;
        }
    } 
    
    public void Mostrar(JTable tabla){
        Connection conexion = ClaseConexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"ID", "Nombre Bombero", "Edad Bombero", "Peso Bombero", "Correo Bombero", "Foto"});
        
        try
        {
            String query = "Select * from tbbombero";
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modelo.addRow(new Object[]{
                    rs.getInt(1), //DUI
                    rs.getString(2), //Nombre
                    rs.getInt(3), //ApellidoPaterno
                    rs.getDouble(4), //ApellidoMaterno
                    rs.getString(5), //Emil
                    rs.getString(6)
                }   
                );
                
            }
            //tabla.getColumnModel().getColumn(10).setCellRenderer(new TableActionCellRender());
            tabla.setModel(modelo);
            tabla.getColumnModel().getColumn(0).setMinWidth(0);
            tabla.getColumnModel().getColumn(0).setMaxWidth(0);
            tabla.getColumnModel().getColumn(0).setWidth(0);
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            
        }
        
    }
    public void limpiar(Bombero vista) {
        vista.txtNombre.setText("");
        vista.txtEdad.setText("");
        vista.txtPeso.setText("");
        vista.txtCorreo.setText("");
    }
    
    // Método para seleccionar y cargar la imagen desde la base de datos
public void cargarImagenDesdeBD(NewJFrame vista) {
    Connection conexion = ClaseConexion.getConexion();
    try {
        String sql = "SELECT FOTO_EMPLEADO FROM tbbombero WHERE UUID = ?";
        PreparedStatement pstmt = conexion.prepareStatement(sql);
        pstmt.setInt(1, 1); // Cambia esto para usar el UUID real que necesites
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            String urlFoto = rs.getString("FOTO_EMPLEADO");
            if (urlFoto != null && !urlFoto.isEmpty()) {
                // Usar HttpURLConnection para cargar la imagen
                URL imageUrl = new URL(urlFoto);
                HttpURLConnection connection = (HttpURLConnection) imageUrl.openConnection();
                connection.setRequestMethod("POST");
                connection.connect();

                // Comprobar si la respuesta es OK
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    // Leer la imagen
                    BufferedImage image = ImageIO.read(connection.getInputStream());
                    if (image != null) {
                        ImageIcon imageIcon = new ImageIcon(image);
                        vista.imageLabel.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(270, 250, Image.SCALE_SMOOTH)));
                    } else {
                        System.out.println("No se pudo cargar la imagen desde la URL: " + urlFoto);
                    }
                } else {
                    System.out.println("Error al acceder a la URL: " + connection.getResponseMessage());
                }

                connection.disconnect(); // Desconectar
            } else {
                System.out.println("No se encontró ninguna URL de imagen en la base de datos.");
            }
        }
    } catch (SQLException | IOException e) {
        e.printStackTrace();
    } finally {
        try {
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

    
    private String subirImagenImgbb(File imageFile) throws IOException, ParseException {
    // Cargar la imagen y convertirla en Base64
    byte[] fileContent = Files.readAllBytes(imageFile.toPath());
    String encodedImage = Base64.getEncoder().encodeToString(fileContent);

    // URL de la API de imgBB
    String uploadUrl = "https://api.imgbb.com/1/upload";

    // Reemplaza "YOUR_API_KEY" con tu clave de API de imgBB
    String apiKey = "b2441058fa326d319187dc5545aa9aff";

    // Crear un cliente HTTP
    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpPost uploadFile = new HttpPost(uploadUrl + "?key=" + apiKey);

    // Crear un cuerpo multipart con la imagen codificada
    MultipartEntityBuilder builder = MultipartEntityBuilder.create();
    builder.addTextBody("image", encodedImage, ContentType.TEXT_PLAIN);

    // Establecer el cuerpo multipart en la solicitud
    HttpEntity multipart = builder.build();
    uploadFile.setEntity(multipart);

    // Ejecutar la solicitud de subida
    CloseableHttpResponse response = httpClient.execute(uploadFile);
    String jsonResponse = EntityUtils.toString(response.getEntity());

    // Imprimir la respuesta completa para verificar su contenido
    System.out.println("Respuesta de la API: " + jsonResponse);

    // Analizar la respuesta JSON para obtener la URL de la imagen
    JSONObject responseObject = new JSONObject(jsonResponse);
    String uploadedUrl = responseObject.getJSONObject("data").getString("url");

    response.close();
    return uploadedUrl;
}

    // Método para seleccionar y cargar la imagen desde la base de datos
    /*public void cargarImagenDesdeBD(NewJFrame vista) throws IOException {
        Connection conexion = ClaseConexion.getConexion();
        try {
            String sql = "SELECT FOTO_EMPLEADO FROM EMPLEADO WHERE UUID = 1";
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String foto = rs.getString("FOTO_EMPLEADO");
                if (foto != null) {
                    InputStream is = foto.getBinaryStream();
                    BufferedImage image = ImageIO.read(is);
                    ImageIcon imageIcon = new ImageIcon(image);
                    vista.imageLabel.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(270, 250, Image.SCALE_SMOOTH)));
                } else {
                    System.out.println("No se encontró ninguna imagen en la base de datos.");
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }*/

    // Método para seleccionar una nueva imagen y guardarla
    public void seleccionarImagenYGuardar(NewJFrame vista) {
        JnaFileChooser fileChooser = new JnaFileChooser();
                // Configurar el filtro de archivos para aceptar solo imágenes .jpg y .png
                fileChooser.addFilter("Imágenes (.jpg, .png)", "jpg", "png");
                boolean action = fileChooser.showOpenDialog(null);
                if (action) {
            File selectedFile = fileChooser.getSelectedFile();

            try {
                Connection conexion = ClaseConexion.getConexion();
                // Subir la imagen a imgBB
                String urlSubida = subirImagenImgbb(selectedFile);
                System.out.println(urlSubida);

                try{
                    String sql = "update tbbombero set foto_empleado = ? where uuid = 1";
                    PreparedStatement pstmt = conexion.prepareStatement(sql);
                    pstmt.setString(1, urlSubida);
                    pstmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Imagen actualizada en base de datos");
                    cargarImagenDesdeBD(vista);
                }
                catch(Exception ex)
                {
                    System.out.println("no se pudo actualizar imagen en base de datos" + ex);
                }
                // Mostrar URL en un JOptionPane
                JOptionPane.showMessageDialog(null, "Imagen subida a: " + urlSubida);
                
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error subiendo la imagen: " + ex.getMessage());
            }
        }
    }

    // Método para guardar la imagen en la base de datos
    /*public void guardarFotoEnBaseDeDatos(byte[] imageBytes, ProfilePanel vista) {
        Connection conexion = ClaseConexion.getConexion();
        try {
            String sql = "UPDATE EMPLEADO SET FOTO_EMPLEADO = ? WHERE DUI = ?";
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            pstmt.setBytes(1, imageBytes);
            pstmt.setString(2, SessionVar.getDui());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Foto actualizada en la base de datos.");
                cargarImagenDesdeBD(vista);
            } else {
                System.out.println("No se encontró ningún registro con el DUI proporcionado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }*/

    // Método para obtener la extensión del archivo
    public String getFileExtension(File file) {
        String name = file.getName();
        try {
            return name.substring(name.lastIndexOf("."));
        } catch (Exception e) {
            return ""; // En caso de que no tenga extensión
        }
    }

    
}


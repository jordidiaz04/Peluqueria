package Controlador;

import Datos.ClienteDA;
import Datos.MultitablaDA;
import Entidad.ClienteBE;
import Entidad.MultitablaBE;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ClienteController", urlPatterns = {"/ClienteController.do"})
public class ClienteController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Gson gson = new Gson();
        String result = "[\"error\", \"No se encontró la función en el servidor.\"]";
        
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        try{
            String clienteJSON = "";
            String listaClientesJSON = "";
            ArrayList<ClienteBE> lstClienteBE;
            ClienteBE oClienteBE;
            int IdCliente;
            boolean rspt;
            
            String Consulta = request.getParameter("Consulta");
            switch(Consulta){
                case "LISTAR":
                    String listaDocumentosJSON;                    
                    lstClienteBE = new ClienteDA().listar();
                    ArrayList<MultitablaBE> lstDocumentos = new MultitablaDA().listar(1);                    
                    listaClientesJSON = gson.toJson(lstClienteBE);
                    listaDocumentosJSON = gson.toJson(lstDocumentos);
                    result = "[\"ok\"," + listaClientesJSON + "," + listaDocumentosJSON + "]";
                    break;
                case "OBTENER":
                    IdCliente = Integer.parseInt(request.getParameter("IdCliente"));
                    oClienteBE = new ClienteDA().obtener(IdCliente);
                    clienteJSON = gson.toJson(oClienteBE);
                    result = "[\"ok\"," + clienteJSON + "]";
                    break;
                case "INSERT":
                case "UPDATE":
                    IdCliente = Integer.parseInt(request.getParameter("IdCliente"));
                    String IdTipoDocIdentidad = request.getParameter("IdTipoDocIdentidad");
                    String DocIdentidad = request.getParameter("DocIdentidad");
                    String Nombres = request.getParameter("Nombres");
                    String Apellidos = request.getParameter("Apellidos");
                    String Email = request.getParameter("Email");
                    String Telefono = request.getParameter("Telefono");
                    oClienteBE = new ClienteBE();
                    oClienteBE.setConsulta(Consulta.equals("INSERT") ? "I" : "U");
                    oClienteBE.setIdCliente(IdCliente);
                    oClienteBE.setIdTipoDocIdentidad(IdTipoDocIdentidad);
                    oClienteBE.setDocIdentidad(DocIdentidad);
                    oClienteBE.setNombres(Nombres);
                    oClienteBE.setApellidos(Apellidos);
                    oClienteBE.setEmail(Email);
                    oClienteBE.setTelefono(Telefono);
                    rspt = new ClienteDA().insertUpdate(oClienteBE);
                    if(rspt){
                        lstClienteBE = new ClienteDA().listar();
                        listaClientesJSON = gson.toJson(lstClienteBE);
                    }
                    result = "[\"ok\"," + listaClientesJSON + "]";
                    break;
            }
        }
        catch(Exception ex){
            String error = gson.toJson(ex.getMessage());
            result = "[\"error\" ," + error + "]";
        }
        
        out.print(result);
        out.flush();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
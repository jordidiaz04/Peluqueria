package Controlador;

import Datos.MultitablaDA;
import Datos.PerfilDA;
import Datos.PersonalDA;
import Datos.UbigeoDA;
import Entidad.MenuBE;
import Entidad.MultitablaBE;
import Entidad.PerfilBE;
import Entidad.PersonalBE;
import Entidad.UbigeoBE;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "PersonalController", urlPatterns = {"/PersonalController.do"})
public class PersonalController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Gson gson = new Gson();
        String result = "[\"error\", \"No se encontró la función en el servidor.\"]";
        
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        try{
            String personalJSON = "";
            String listaPersonalJSON = "";
            ArrayList<PersonalBE> lstPersonalBE;
            PersonalBE oPersonalBE;
            int IdPersonal;
            boolean rspt;
            
            String Consulta = request.getParameter("Consulta");
            switch(Consulta){
                case "LISTAR":
                    String listaDocumentosJSON;
                    String listaPerfilesJSON;
                    String listaDepartamentosJSON;
                    String listaProvinciasJSON;
                    String listaDistritosJSON;
                    
                    lstPersonalBE = new PersonalDA().listar();
                    ArrayList<PerfilBE> lstPerfiles = new PerfilDA().listar();
                    ArrayList<MultitablaBE> lstDocumentos = new MultitablaDA().listar(1);
                    ArrayList<UbigeoBE> lstDepartamentos = new UbigeoDA().listarDepartamentos();
                    ArrayList<UbigeoBE> lstProvincias = new UbigeoDA().listarProvincias();
                    ArrayList<UbigeoBE> lstDistritos = new UbigeoDA().listarDistritos();
                    
                    listaPersonalJSON = gson.toJson(lstPersonalBE);
                    listaPerfilesJSON = gson.toJson(lstPerfiles);
                    listaDocumentosJSON = gson.toJson(lstDocumentos);
                    listaDepartamentosJSON = gson.toJson(lstDepartamentos);
                    listaProvinciasJSON = gson.toJson(lstProvincias);
                    listaDistritosJSON = gson.toJson(lstDistritos);
                    
                    result = "[\"ok\"," + listaPersonalJSON + "," + listaPerfilesJSON + "," + listaDocumentosJSON + "," + listaDepartamentosJSON
                            + "," + listaProvinciasJSON + "," + listaDistritosJSON + "]";
                    break;
                case "OBTENER":
                    IdPersonal = Integer.parseInt(request.getParameter("IdPersonal"));
                    oPersonalBE = new PersonalDA().obtener(IdPersonal);
                    personalJSON = gson.toJson(oPersonalBE);
                    result = "[\"ok\"," + personalJSON + "]";
                    break;
                case "INSERT":
                case "UPDATE":
                    IdPersonal = Integer.parseInt(request.getParameter("IdPersonal"));
                    String Sexo = request.getParameter("Sexo");
                    String IdTipoDocIdentidad = request.getParameter("IdTipoDocIdentidad");
                    String DocIdentidad = request.getParameter("DocIdentidad");
                    String Nombres = request.getParameter("Nombres");
                    String Apellidos = request.getParameter("Apellidos");
                    String IdUbigeo = request.getParameter("IdUbigeo");
                    String Domicilio = request.getParameter("Domicilio");
                    String Email = request.getParameter("Email");
                    String Telefono = request.getParameter("Telefono");
                    String Contraseña = request.getParameter("Contraseña");
                    int IdPerfil = Integer.parseInt(request.getParameter("IdPerfil"));                    
                    oPersonalBE = new PersonalBE();
                    oPersonalBE.setConsulta(Consulta.equals("INSERT") ? "I" : "U");
                    oPersonalBE.setIdPersonal(IdPersonal);
                    oPersonalBE.setSexo(Sexo);
                    oPersonalBE.setIdTipoDocIdentidad(IdTipoDocIdentidad);
                    oPersonalBE.setDocIdentidad(DocIdentidad);
                    oPersonalBE.setNombres(Nombres);
                    oPersonalBE.setApellidos(Apellidos);
                    oPersonalBE.setIdUbigeo(IdUbigeo);
                    oPersonalBE.setDomicilio(Domicilio);
                    oPersonalBE.setEmail(Email);
                    oPersonalBE.setTelefono(Telefono);
                    oPersonalBE.setContraseña(Contraseña);
                    oPersonalBE.setIdPerfil(IdPerfil);
                    rspt = new PersonalDA().insertUpdate(oPersonalBE);
                    if(rspt){
                        lstPersonalBE = new PersonalDA().listar();
                        listaPersonalJSON = gson.toJson(lstPersonalBE);
                    }
                    result = "[" + rspt + "," + listaPersonalJSON + "]";
                    break;
                case "ENABLE":
                case "DISABLE":
                    IdPersonal = Integer.parseInt(request.getParameter("IdPersonal"));
                    boolean Estado = Boolean.parseBoolean(request.getParameter("Estado"));
                    oPersonalBE = new PersonalBE();
                    oPersonalBE.setIdPersonal(IdPersonal);
                    oPersonalBE.setEstado(Estado);
                    rspt = new PersonalDA().enableDisable(oPersonalBE);
                    if(rspt){
                        lstPersonalBE = new PersonalDA().listar();
                        listaPersonalJSON = gson.toJson(lstPersonalBE);
                    }
                    result = "[" + rspt + "," + listaPersonalJSON + "]";
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
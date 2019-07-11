package Controlador;

import Datos.ReservaDA;
import Entidad.ReservaBE;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ReservaAdministrarController", urlPatterns = {"/ReservaAdministrarController.do"})
public class ReservaAdministrarController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Gson gson = new Gson();
        String result = "[\"error\", \"No se encontró la función en el servidor.\"]";

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            String reservaJSON = "";
            String listaReservasJSON = "";
            ArrayList<ReservaBE> lstReservaBE;
            ReservaBE oReservaBE;
            int IdServicio;
            boolean rspt;
            LocalDateTime fecha = LocalDateTime.now();
            String año = fecha.getYear() + "";
            String mes = (fecha.getMonthValue() + "").length() == 1 ? "0" + fecha.getMonthValue() : (fecha.getMonthValue() + "");
            String dia = (fecha.getDayOfMonth() + "").length() == 1 ? "0" + fecha.getDayOfMonth() : (fecha.getDayOfMonth() + "");

            String fechaInicial = año + mes + "01";
            String fechaFinal = año + mes + dia;

            String Consulta = request.getParameter("Consulta");
            switch (Consulta) {
                case "LISTAR":
                    lstReservaBE = new ReservaDA().listar(fechaInicial, fechaFinal);
                    listaReservasJSON = gson.toJson(lstReservaBE);
                    result = "[\"ok\"," + listaReservasJSON + "]";
                    break;
            }
        } catch (Exception ex) {
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

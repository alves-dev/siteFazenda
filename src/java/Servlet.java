
import Classes.conexao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.*;

@WebServlet(urlPatterns = {"/Servlet"}, initParams = {
    @WebInitParam(name = "paramentro", value = "fazenda")})
public class Servlet extends HttpServlet {

    @SuppressWarnings("empty-statement")
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String senha;
            senha = request.getParameter("campotexto");

            System.out.println(senha);

            PrintWriter resposta = response.getWriter();

            if (senha.equals("igorlindo")) {

                consultaAnimais(response);

            }

            if (senha.equals("igor")) {
                resposta.println("<html> <boby>");
                resposta.println("<title>Fazenda</title>");
                resposta.println("<div> <h1> Relatórios </h1> </div>");
                resposta.println("<h3>"
                        + "<p><form name=\"formulario\" action=\"Servlet\">"
                        + "Seu nome:"
                        + "<input type=\"text\" name=\"campotexto\" value=\"10\\01\\2017\" size=\"8\" />"
                        + "<input type=\"submit\" value=\"imprimir\" name=\"botao\" />"
                        + " </h3>");

                int tt = 0;
                while (tt < 5) {
                    resposta.println("<table border=\"1\">\n"
                            + "            <thead>\n"
                            + "                <tr>\n"
                            + "                    <th>Código</th>\n"
                            + "                    <th>Nome:</th>\n"
                            + "                </tr>\n"
                            + "            </thead>\n"
                            + "            <tbody>\n"
                            + "                <tr>\n"
                            + "                    <td>" + tt + "</td>\n"
                            + "                    <td>Igor</td>\n"
                            + "                </tr>\n"
                            + "            </tbody>\n"
                            + "        </table>");
                    tt++;

                }

                resposta.println("</html> </boby>");

            } else {
                resposta.println("<html> <boby>");
                resposta.println("<h1> Senha errada OTARIO KK </h1>");
                resposta.println("<h1> Tente outra! </h1>");
                resposta.println("<div>Para ter acesso:</div>\n"
                        + "\n"
                        + "<form name=\"formularioLogin\" action=\"Servlet\">\n"
                        + "Senha:\n"
                        + "<input type=\"password\" name=\"campotexto\" value=\"\" size=\"13\" />\n"
                        + "<input type=\"submit\" value=\"Login\" name=\"botao\" />\n"
                        + "\n"
                        + "</form>");
                resposta.println("</html> </boby>");
            }

        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    public void consultaAnimais(HttpServletResponse response) throws SQLException, IOException {

        try (PrintWriter out = response.getWriter()) {

            conexao conexao = new conexao();
            conexao.getConexao();
            String selectSQL = "SELECT * FROM animais A ";

            Statement pstmtCon;
            pstmtCon = conexao.getConexao().createStatement();

            System.out.println("blabla");
            ResultSet rs = pstmtCon.executeQuery(selectSQL);
            while (rs.next()) {

                System.out.println("outout");
                out.println("<html> <boby>");
                out.println("<table border=\"1\">\n"
                        + "            <thead>\n"
                        + "                <tr>\n"
                        + "                    <th>Identificação:</th>\n"
                        + "                    <th>Tipo animal:</th>\n"
                        + "                </tr>\n"
                        + "            </thead>\n"
                        + "            <tbody>\n"
                        + "                <tr>\n"
                        + "                    <td>" + rs.getString("identificacao") + "</td>\n"
                        + "                    <td>" + rs.getString("tipoAnimal") + "</td>\n"
                        + "                </tr>\n"
                        + "            </tbody>\n"
                        + "        </table>");
                out.println("</html> </boby>");

            }

            rs.close();
            pstmtCon.close();
        }
    }
}

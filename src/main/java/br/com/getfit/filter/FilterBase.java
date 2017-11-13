package br.com.getfit.filter;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Francisco
 */
public class FilterBase {

    protected void doLogin(ServletRequest request, ServletResponse res, HttpServletRequest req) throws ServletException, IOException {
//        RequestDispatcher dispatcher = req.getRequestDispatcher("/login");
//        dispatcher.forward(request, response);;
        HttpServletResponse response = (HttpServletResponse) res;
        response.sendRedirect("/login");
    }

    protected void accessDenied(ServletRequest request, ServletResponse response, HttpServletRequest req) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("acessoNegado");
        dispatcher.forward(request, response);
    }
}

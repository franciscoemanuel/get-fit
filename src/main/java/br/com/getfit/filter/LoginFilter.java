package br.com.getfit.filter;

import br.com.getfit.model.Usuario;
import java.io.IOException;
import java.util.Arrays;
import javax.faces.application.ResourceHandler;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Francisco
 */
@WebFilter(urlPatterns = "/*")
public class LoginFilter extends AbstractFilter implements Filter {

    @Override
    public void init(FilterConfig fc) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest sreq, ServletResponse sres, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) sreq;
        HttpServletResponse response = (HttpServletResponse) sres;

        HttpSession sessao = request.getSession();
        Usuario usuario = (Usuario) sessao.getAttribute("usuario");

        if (isUrlRedirecionavel(request.getRequestURI()) && (sessao.isNew() || usuario == null)) {
            doLogin(sreq, sres, request);
        } else {
            fc.doFilter(sreq, sres);
        }
    }
    
    private boolean isUrlRedirecionavel(String url) {
        String[] urlsAutenticacao = {"login", "cadastro", "logout"};
        String[] urlsResource = {ResourceHandler.RESOURCE_IDENTIFIER, "resources"};
        boolean isUrlAutenticacao = Arrays.stream(urlsAutenticacao).anyMatch(urlAutenticacao -> url.endsWith(urlAutenticacao));
        boolean isResource = Arrays.stream(urlsResource).anyMatch(resource -> url.contains(resource));
        return (!isUrlAutenticacao && !isResource);
    }

    @Override
    public void destroy() {
        // throw new UnsupportedOperationException("Not supported yet.");
    }

}

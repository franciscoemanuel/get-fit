package br.com.getfit.filter;

import br.com.getfit.model.Usuario;
import java.io.IOException;
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
public class LoginFilter extends FilterBase implements Filter {

    @Override
    public void init(FilterConfig fc) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest sreq, ServletResponse sres, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) sreq;
        HttpServletResponse response = (HttpServletResponse) sres;

        HttpSession sessao = request.getSession();
        Usuario usuario = (Usuario) sessao.getAttribute("usuario");

        if (isUrlValida(request.getRequestURI()) && (sessao.isNew() || usuario == null)) {
            doLogin(sreq, sres, request);
        } else {
            fc.doFilter(sreq, sres);
        }
    }
    
    private boolean isUrlValida(String url) {
        if (url.endsWith("login")) {
            return false;
        }
        return true;
    }

    @Override
    public void destroy() {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

}

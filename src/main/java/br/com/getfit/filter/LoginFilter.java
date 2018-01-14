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
        String url = request.getRequestURI();
        boolean isUrlRedirecionavel = !isUrlAutenticacao(url) && !isResource(url);
        
        if (isUrlRedirecionavel && (sessao.isNew() || usuario == null)) {
            doLogin(sreq, sres, request);
        } else if (!isResource(url) && usuario != null && !usuarioPossuiPermissaoDeAcesso(url, usuario.getTipoUsuario())) {
            redirecionaParaHome(sreq, sres, request, usuario.getTipoUsuario());
        }
        else {
            fc.doFilter(sreq, sres);
        }
    }
    
    private boolean isResource(String url) {
        String[] urlsResource = {ResourceHandler.RESOURCE_IDENTIFIER, "resources"};
        boolean isResource = Arrays.stream(urlsResource).anyMatch(resource -> url.contains(resource));
        return isResource;
    }
    
    private boolean isUrlAutenticacao(String url) {
        String[] urlsAutenticacao = {"login", "cadastro", "logout"};
        boolean isUrlAutenticacao = Arrays.stream(urlsAutenticacao).anyMatch(urlAutenticacao -> url.endsWith(urlAutenticacao));
        return isUrlAutenticacao;
    }
    
    private boolean usuarioPossuiPermissaoDeAcesso(String url, String tipoUsuario) {
        return tipoUsuario.equals("pessoa") ? url.contains("/pessoa") : url.contains("/centroEsportivo");
    }
     
    @Override
    public void destroy() {
        // throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public static void main(String[] args) {
        System.out.println("br.com.getfit.filter.LoginFilter.main()");
    }

}

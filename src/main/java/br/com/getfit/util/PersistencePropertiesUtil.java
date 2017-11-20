package br.com.getfit.util;

import java.util.Properties;

/**
 *
 * @author Francisco
 */
public class PersistencePropertiesUtil {

    private static final String JDBC_URL = "JDBC_URL";

    private static final String JDBC_USER = "JDBC_USER";

    private static final String JDBC_PASSWORD = "JDBC_PASSWORD";

    /**
     * Procura por propriedades que irão sobrescrever o persistence.xml. A
     * procura é feita na pasta home do usuário, em variáveis de ambiente e em
     * propriedades passadas na inicialização do processo Java.
     *
     * @return Properties propriedades que irão sobrescrever o persistence.xml
     * @throws Exception
     */
    public static Properties get() {
        try {
            Properties props = new Properties();

            // Heroku
            props.putAll(systemEnv());

            // Parâmetros java
            props.putAll(javaProperties());

            return props;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Verifica a existência de variáveis de ambiente que serão usadas para
     * sobrescrever as propriedades do persistence.xml.
     *
     * A variável de ambiente para url, usuário e senha devem ter o mesmo nome
     * dos valores das propriedades estáticas
     * {@link #JDBC_URL}, {@link #JDBC_USER} e {@link #JDBC_PASSWORD}
     * respectivamente.
     *
     * @return Properties propriedades que irão sobrescrever o persistence.xml
     * @throws Exception
     */
    private static Properties systemEnv() {
        Properties props = new Properties();

        if (System.getenv().containsKey(JDBC_URL)) {
            props.put("javax.persistence.jdbc.url", System.getenv(JDBC_URL));
        }

        if (System.getenv().containsKey(JDBC_USER)) {
            props.put("javax.persistence.jdbc.user", System.getenv(JDBC_USER));
        }

        if (System.getenv().containsKey(JDBC_PASSWORD)) {
            props.put("javax.persistence.jdbc.password", System.getenv(JDBC_PASSWORD));
        }

        return props;
    }

    /**
     * Verifica a existência de propriedades Java que serão usadas para
     * sobrescrever as propriedades do persistence.xml.
     *
     * A propriedade Java para url, usuário e senha devem ter o mesmo nome dos
     * valores das propriedades estáticas {@link #JDBC_URL}, {@link #JDBC_USER}
     * e {@link #JDBC_PASSWORD} respectivamente.
     *
     * Caso queira sobrescrever a url, por exemplo, a propriedade a ser passada
     * será "-DJDBC_URL=jdbc:mysql://url:porta/banco"
     *
     * @return Properties propriedades que irão sobrescrever o persistence.xml
     * @throws Exception
     */
    private static Properties javaProperties() {
        Properties props = new Properties();

        if (System.getProperties().containsKey(JDBC_URL)) {
            props.put("javax.persistence.jdbc.url", System.getProperty(JDBC_URL));
        }

        if (System.getProperties().containsKey(JDBC_USER)) {
            props.put("javax.persistence.jdbc.user", System.getProperty(JDBC_USER));
        }

        if (System.getProperties().containsKey(JDBC_PASSWORD)) {
            props.put("javax.persistence.jdbc.password", System.getProperty(JDBC_PASSWORD));
        }

        return props;
    }
}

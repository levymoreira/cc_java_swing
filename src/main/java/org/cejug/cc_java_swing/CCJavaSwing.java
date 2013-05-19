package org.cejug.cc_java_swing;

import org.cejug.cc_java_swing.jdbc.CCJavaSwingJDBC;


/**
 * Classe principal para inciar o sistema.
 * 
 * @author helio frota https://www.heliofrota.com
 * 
 */
public class CCJavaSwing {

    /**
     * Método main.
     * 
     * @param String... args
     */
    public static void main(String... args) {

        
        CCJavaSwingJDBC ccJavaSwingJDBC = new CCJavaSwingJDBC();
        System.out.println(ccJavaSwingJDBC.listarPersonagens());
  
    }

}

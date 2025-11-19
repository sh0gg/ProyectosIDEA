/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exemploJDialog;

/**
 *
 * @author DAM2
 */
public class XestorDeXanelas {
    static boolean iFrmListadoUsuariosAberta=false;
    
    public static int podeseAbrirIFrmListadoUsuarios()
    {
        if(iFrmListadoUsuariosAberta)
        {
            return 1;
        }
        else
        {
            if(AlmacenDeUsuarios.isBaleiroAlmacenDeDatos())
            {
                return 2;
            }
            else
            {
                return 0;
            }
        }
    }
    
    public static void abrirIFrmListadoUsuarios()
    {
       iFrmListadoUsuariosAberta=true; 
    }
    
    public static void cerrarIFrmListadoUsuarios()
    {
       iFrmListadoUsuariosAberta=false; 
    }    
}

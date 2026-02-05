package LOGICA;

import PERSITENCIA.EmpresaHBDAO;
import POJOS.Empregado;
import POJOS.Familiar;
import POJOS.Fase;
import POJOS.Proxecto;

import java.util.List;


public class GestorEmpresaHB {
    public static void comprobarConexion() {
        int resultado = EmpresaHBDAO.conectarHibernate();

        if (resultado == 0) {
            System.out.println("Conexión correcta");

        } else {
            System.out.println("Error de conexión ");

        }
    }

    public static void visualizarProxecto(int proxecto) {
        try {
            Proxecto p = EmpresaHBDAO.buscarProxecto(proxecto);

            if (p == null) {
                System.out.println("No existe el proyecto con código " + proxecto);
            } else {
                System.out.println("Proyecto encontrado:");
                System.out.println("Número: " + p.getNumProxecto());
                System.out.println("Nombre: " + p.getNomeProxecto());
            }

        } catch (RuntimeException e) {
            System.out.println("Error de acceso a la base de datos: " + e.getMessage());
        }
    }

    public static void añadirFuncionDepartamento(int i, String funcion) {
        int resultado = EmpresaHBDAO.añadirFuncionDep(i, funcion);
        if (resultado != 1) {
            if (resultado == 0) {
                System.out.println("Ha fallado la insercion de la funcion " + funcion + " en el departamento " + i);
            } else if (resultado == -1) {
                System.out.println("No se ha encontrado el departamento número " + i + ".");
            }
        } else {
            System.out.println("Se ha añadido la funcion " + funcion + " en el departamento " + i);
        }
    }

    public static void eliminarFuncionDepartamento(int i, String funcion) {
        int resultado = EmpresaHBDAO.eliminarFuncionDep(i, funcion);
        if (resultado != 1) {
            if (resultado == 0) {
                System.out.println("Ha fallado el borrado de la funcion " + funcion + " en el departamento " + i);
            } else if (resultado == -1) {
                System.out.println("No se ha encontrado el departamento numero " + i + ".");
            }
        } else {
            System.out.println("Se ha eliminado la funcion " + funcion + " en el departamento " + i);
        }
    }

    public static void altaEmpregado(Empregado novoEmpregado) {
        EmpresaHBDAO.crearEmpregado(novoEmpregado);
    }

    public static void engadirFaseProxecto(int i, Fase fase) {
        int resultado = EmpresaHBDAO.añadirFaseProxecto(i, fase);
        if (resultado != 1) {
            if (resultado == 0) {
                System.out.println("Ha fallado la insercion de la fase " + fase + " en el proyecto " + i);
            } else if (resultado == -1) {
                System.out.println("No se ha encontrado el proyecto numero " + i + ".");
            }
        } else {
            System.out.println("Se ha insertado la fase " + fase + " en el proyecto " + i);
        }
    }

    public static void engadirOuActualizarTelefono(String nss, String numero, String info) {
        int resultado = EmpresaHBDAO.addOrUpdateTlf(nss, numero, info);
        if (resultado != 1) {
            if (resultado == 0) {
                System.out.println("Ha fallado la insercion/actualizacion del telefono " + info + " en el empleado con NSS " + nss + ".");
            } else if (resultado == -1) {
                System.out.println("No se ha encontrado el empleado con NSS " + nss + ".");
            }
        } else {
            System.out.println("Se ha insertado/actualizado el telefono " + info + "(" + numero + ") en el empleado con NSS " + nss + ".");
        }
    }

    public static void borrarTelefono(String nss, String numero) {
        int resultado = EmpresaHBDAO.deleteTlf(nss, numero);
        if (resultado != 1) {
            if (resultado == 0) {
                System.out.println("Ha fallado el borrado del numero " + numero + " del empleado " + nss);
            } else if (resultado == -1) {
                System.out.println("No se ha encontrado el empleado con NSS " + nss + ".");
            }
        } else {
            System.out.println("Se ha eliminado el teléfono " + numero + " en el empleado con NSS " + nss + ".");
        }
    }

    public static void crearFamiliar(String nss, Familiar familiar) {
        int resultado = EmpresaHBDAO.insertFamiliar(nss, familiar);
        if (resultado != 1) {
            if (resultado == 0) {
                System.out.println("Ha fallado la insercion del familiar " + familiar.toString() + " del empleado con NSS " + nss + ".");
            } else if (resultado == -1) {
                System.out.println("No se ha encontrado el empleado con NSS " + nss + ".");
            }
        } else {
            System.out.println("Se ha añadido el familiar " + familiar.toString() + " del empleado con NSS " + nss + ".");
        }
    }

    public static void mostrarEmpregadosPorLocalidade(String localidade) {
        List<Empregado> empregados = EmpresaHBDAO.getEmpregadosLocalidade(localidade);
        System.out.println("Empregados da localidade de " +  localidade + ":");
        for (Empregado e: empregados) {
            System.out.println(e.toString());
        }
    }

    public static void crearEmpregadoConDepartamento(Empregado novoEmpregado, String nomeDepartamento) {
    }

    public static void cambiarDepartamentoEmpregado(String nss, int idDepartamentoNuevo) {
    }
}


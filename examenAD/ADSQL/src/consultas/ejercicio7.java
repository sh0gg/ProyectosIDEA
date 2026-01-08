package consultas;

public class ejercicio7 {

    """
    SELECT d.NumDepartamento, d.NomeDepartamento, COUNT(e.NSS) AS NumEmpregados
    FROM DEPARTAMENTO d
    LEFT JOIN EMPREGADO e
        ON e.NumDepartamentoPertenece = d.NumDepartamento
    GROUP BY d.NumDepartamento, d.NomeDepartamento
    HAVING COUNT(e.NSS) > ?
    ORDER BY NumEmpregados DESC
"""
}

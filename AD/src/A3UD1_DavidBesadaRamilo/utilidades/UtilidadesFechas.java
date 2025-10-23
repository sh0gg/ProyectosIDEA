package A3UD1_DavidBesadaRamilo.utilidades;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class UtilidadesFechas {
    private UtilidadesFechas(){}
    private static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static LocalDate parse(String s){ return LocalDate.parse(s, DF); }
    public static String fmt(LocalDate d){ return d.format(DF); }
}

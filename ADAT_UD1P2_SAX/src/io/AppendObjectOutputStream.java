package io;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/** Evita escribir cabecera cuando se hace append a un .dat serializado */
public class AppendObjectOutputStream extends ObjectOutputStream {
    public AppendObjectOutputStream(OutputStream out) throws IOException { super(out); }
    @Override protected void writeStreamHeader() throws IOException { reset(); }
}

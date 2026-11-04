package vulnlab.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

/** Redéfinit resolveClass, mais ne refuse rien : ce n'est PAS une protection. */
public class LoggingObjectInputStream extends ObjectInputStream {

    public LoggingObjectInputStream(InputStream in) throws IOException {
        super(in);
    }

    @Override
    protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
        System.out.println("lecture de " + desc.getName());
        return super.resolveClass(desc);
    }
}

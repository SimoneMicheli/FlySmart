package db;

import com.google.code.morphia.logging.Logr;
import com.google.code.morphia.logging.LogrFactory;
import com.google.code.morphia.logging.SilentLogger;

/**
 */
public class SilentLogrFactory implements LogrFactory {

    /**
     * Method get.
     * @param c Class<?>
     * @return Logr
     * @see com.google.code.morphia.logging.LogrFactory#get(Class<?>)
     */
    @Override
    public Logr get(Class<?> c) {
        return new SilentLogger();
    }

}
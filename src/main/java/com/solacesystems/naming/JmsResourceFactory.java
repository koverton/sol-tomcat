package com.solacesystems.naming;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.naming.*;
import javax.naming.spi.ObjectFactory;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;

/**
 * Custom resource factory to create Solace JMS resources.
 */
public class JmsResourceFactory implements ObjectFactory {
    private Log log = LogFactory.getLog(JmsResourceFactory.class);

    @Override
    public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) throws Exception {

        Reference reference = (Reference) obj;

        Properties properties = new Properties();
        String factoryName = null;

        Enumeration addresses = reference.getAll();
        while (addresses.hasMoreElements()) {
            RefAddr address = (RefAddr) addresses.nextElement();
            String type = address.getType();
            String content = (String) address.getContent();

            if (type != null) {
                if (type.equalsIgnoreCase("factory.name")) {
                    factoryName = content;
                }
                else if (type.startsWith("java.naming.")) {
                    properties.put(type, content);
                }
            }
        }

        if (properties.size() < 1) {
            throw new IllegalArgumentException("InitialContext properties not specified!");
        }

        if (factoryName == null) {
            throw new IllegalArgumentException("Resource name to lookup not specified (" + factoryName + ")!");
        }

        Context context = new InitialContext(properties);
        return context.lookup(factoryName);
    }
}

package se.curity.examples.spark;

import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class EmbeddedSparkOpaqueFilterConfig implements FilterConfig
{
    private final Map<String,String> _params;

    public EmbeddedSparkOpaqueFilterConfig(String oauthHost, String oauthPort,
                                        String introspectionPath, String clientId, String clientSecret,
                                        String scope)
    {
        _params = new HashMap<>();
        _params.put("oauthHost", oauthHost);
        _params.put("oauthPort", oauthPort);
        _params.put("introspectionPath", introspectionPath);
        _params.put("clientId", clientId);
        _params.put("clientSecret", clientSecret);
        _params.put("scope", scope);
    }

    @Override
    public String getFilterName()
    {
        return "EmbeddedSparkOpaqueFilterConfig";
    }

    @Override
    public ServletContext getServletContext()
    {
        return null;
    }

    @Override
    public String getInitParameter(String s)
    {
        return _params.get(s);
    }

    @Override
    public Enumeration<String> getInitParameterNames()
    {
        return new EmbeddedSparkOpaqueFilterConfig.EnumerationFromIteration(_params.keySet().iterator());
    }

    public class EnumerationFromIteration implements Enumeration {

        private Iterator iterator;

        EnumerationFromIteration(Iterator iterator){
            this.iterator = iterator;
        }


        public EnumerationFromIteration(Map map) {
            iterator = map.keySet().iterator();
        }


        @Override
        public boolean hasMoreElements() {
            return iterator.hasNext();
        }


        @Override
        public Object nextElement() {
            return iterator.next();
        }

    }
}

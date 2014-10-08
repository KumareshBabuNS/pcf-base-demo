package io.pivotal.pcf.demo;

/**
 * Created by sgupta on 10/1/14.
 */
public class QueryResponse {
    private long count;
    private PropertyObject[] results;

    public QueryResponse() {
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public PropertyObject[] getResults() {
        return results;
    }

    public void setResults(PropertyObject[] results) {
        this.results = results;
    }
}

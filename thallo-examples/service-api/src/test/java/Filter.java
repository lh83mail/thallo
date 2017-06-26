/**
 * Created by lihong on 17-5-19.
 */
public class Filter {
    String prefix = "and";
    StringBuffer buf = new StringBuffer();
    Filter(String name, String op, Object val) {
        buf.append(prefix)
                .append(name)
                .append(op)
                .append(val);
    }

    public Filter AND(Filter... filters) {
        String prefix = "and ";
        for (Filter f: filters) {

        }

        return this;
    }

    public void addFilter(Filter f) {

    }
}

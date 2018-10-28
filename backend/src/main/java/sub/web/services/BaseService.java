package sub.web.services;

import java.util.List;

public interface BaseService<X> {
    public X save(X x);
    public X update(X x);
    public void delete(X x);
    public X get(Long x);
    public List<X>getAll();
}

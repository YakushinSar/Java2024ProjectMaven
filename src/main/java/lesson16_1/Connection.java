package lesson16_1;

import java.util.Map;

public interface Connection {

//    тут создаются абстрактные методы которые будут использоваться в будущем
    public String connect(String connectionString);
//    public Map<Long, Employee> getData(String sql);
    public void closeConnection();
}

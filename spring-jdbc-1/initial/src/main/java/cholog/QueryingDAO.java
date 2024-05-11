package cholog;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QueryingDAO {
    private JdbcTemplate jdbcTemplate;

    public QueryingDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /* RowMapper: 각 행의 결과를 객체로 매핑하는 로직을 유연하게 구현
    resultSet: 데이터베이스로부터 가져온 결과 집합을 나타내는 객체
               요청한 쿼리의 실행 결과가 포함되어 있음
               RowMapper는 ResultSet의 각 행을 매핑하여 Java 객체로 변환하는 역할을 수행
               resultSet 매개변수는 각 행의 데이터를 읽어오기 위해 사용됨
    rowNum:    현재 행의 인덱스를 나타내는 변수 */

    private final RowMapper<Customer> actorRowMapper = (resultSet, rowNum) -> {
        Customer customer = new Customer(
                resultSet.getLong("id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name")
        );
        return customer;
    };



    /**
     * public <T> T queryForObject(String sql, Class<T> requiredType)
     */

    public int count() {
        //TODO : customers 디비에 포함되어있는 row가 몇개인지 확인하는 기능 구현
        int rowCount = jdbcTemplate.queryForObject("select count(*) from customers", Integer.class);
        return rowCount;
    }

    /**
     * public <T> T queryForObject(String sql, Class<T> requiredType, @Nullable Object... args)
     */
    public String getLastName(Long id) {
        //TODO : 주어진 Id에 해당하는 customers의 lastName을 반환
        String lastName = jdbcTemplate.queryForObject("select last_name from customers where id = ?", String.class, id);
        return lastName;
    }

    /**
     * public <T> T queryForObject(String sql, RowMapper<T> rowMapper, @Nullable Object... args)
     */
    public Customer findCustomerById(Long id) {
        String sql = "select id, first_name, last_name from customers where id = ?";
        //TODO : 주어진 Id에 해당하는 customer를 객체로 반환
        // queryForObject 메서드는 단일 결과를 반환하는 쿼리를 실행할 때 사용됨.
        // 즉, 결과 집합에 하나의 행만 있는 경우에 사용됨
        Customer customer = jdbcTemplate.queryForObject(sql, actorRowMapper, id);
        return customer;
    }

    /**
     * public <T> List<T> query(String sql, RowMapper<T> rowMapper)
     */
    public List<Customer> findAllCustomers() {
        String sql = "select id, first_name, last_name from customers";
        //TODO : 저장된 모든 Customers를 list형태로 반환
        // query 메서드는 다중 결과를 반환하는 쿼리를 실행할 때 사용됨.
        // 즉, 결과 집합에 여러 행이 있는 경우에 사용됨 (따라서 != queryForObject)
        List<Customer> customer = jdbcTemplate.query(sql, actorRowMapper);
        return customer;
    }

    /**
     * public <T> List<T> query(String sql, RowMapper<T> rowMapper, @Nullable Object... args)
     */
    public List<Customer> findCustomerByFirstName(String firstName) {
        String sql = "select id, first_name, last_name from customers where first_name = ?";
        //TODO : firstName을 기준으로 customer를 list형태로 반환
        return null;
    }
}

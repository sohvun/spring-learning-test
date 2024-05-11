package cholog;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository
public class UpdatingDAO {
    private JdbcTemplate jdbcTemplate;

    public UpdatingDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /*
    private final RowMapper<Customer> actorRowMapper = (resultSet, rowNum) -> {
        Customer customer = new Customer(
                resultSet.getLong("id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name")
        );
        return customer;
    };
    추후 rowMapper에 대해 학습해보고 이용해보기
    */

    /**
     * public int update(String sql, @Nullable Object... args)
     */
    public void insert(Customer customer) {
        //todo: customer를 디비에 저장하기
        jdbcTemplate.update("insert into customers (first_name, last_name) values (?, ?)",
                customer.getFirstName(), customer.getLastName());

    }
    /**
     * public int update(String sql, @Nullable Object... args)
     */
    public int delete(Long id) {
        //todo: id에 해당하는 customer를 지우고, 해당 쿼리에 영향받는 row 수반환하기
        int rowNum = jdbcTemplate.update("delete from customers where id = ?", Long.valueOf(id));
        return rowNum;
    }

    /**
     * public int update(final PreparedStatementCreator psc, final KeyHolder generatedKeyHolder)
     */
    public Long insertWithKeyHolder(Customer customer) {
        //
        String sql = "insert into customers (first_name, last_name) values (?, ?)";
        // KeyHolder 인터페이스를 구현한 GeneratedKeyHolder 객체를 생성
        // -- 이 객체는 데이터베이스 삽입 작업으로 생성된 키를 유지하기 위해 사용됨
        KeyHolder keyHolder = new GeneratedKeyHolder();

        //todo : keyHolder에 대해 학습하고, Customer를 저장후 저장된 Customer의 id를 반환하기

        // update 메서드 호출
        // 1st parameter: connection 람다식 -- 삽입 작업을 정의하고 실행하는 데 사용
        jdbcTemplate.update(connection -> { // Connection 객체를 사용하여 삽입 작업을 수행하기 위해 PreparedStatement를 생성
            PreparedStatement ps = connection.prepareStatement(
                    sql, new String[]{"id"}); // id 열은 데이터베이스에서 생성되는 자동 증가 기본 키!
            ps.setString(1, customer.getFirstName()); // PreparedStatement에 동적으로 값을 바인딩
            ps.setString(2, customer.getLastName());
            return ps;
        }, keyHolder); // 2nd parameter: KeyHolder 객체 -- 삽입 작업으로 생성된 키를 보관하는 객체

        // KeyHolder에서 삽입 작업으로 생성된 키를 가져옴: 이 키는 자동 증가 기본 키로 설정된 열의 값
        Long id = keyHolder.getKey().longValue();

        return id;
    }
}

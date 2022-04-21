package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class MemberJdbcTemplateRepository implements MemberRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MemberJdbcTemplateRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", member.getName());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        member.createId(key.longValue());
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = null;
        try {
            member = jdbcTemplate.queryForObject("select * from member where id = ?", memberRowMapper(), id);
        } catch (DataAccessException e) {
            log.error("findById 조회 실패");
        }
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        Member member = null;
        try {
            member = jdbcTemplate.queryForObject("select * from member where name = ?", memberRowMapper(), name);
        } catch (DataAccessException e) {
            log.error("findByName 조회 실패");
        }
        return Optional.ofNullable(member);
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * from member", memberRowMapper());
    }

    private RowMapper<Member> memberRowMapper() {
        return new RowMapper<Member>() {
            @Override
            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                Member member = new Member(rs.getLong("id"), rs.getString("name"));
                return member;
            }
        };
    }
}

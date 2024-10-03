package my.scheduler.repository;

import lombok.RequiredArgsConstructor;
import my.scheduler.model.Schedule;
import my.scheduler.util.ScheduleRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    // 일정 생성
    public void save(Schedule schedule) {
        String sql = "INSERT INTO schedule (title, author, password, description, date, created_at, updated_at) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, schedule.getTitle(), schedule.getAuthor(), schedule.getPassword(),
                schedule.getDescription(), schedule.getDate(), schedule.getCreatedAt(), schedule.getUpdatedAt());
    }

    // 전체 일정 조회
    public List<Schedule> findAll(String author, String updatedAt) {
        String sql = "SELECT * FROM schedule WHERE author = ? OR updated_at = ? ORDER BY updated_at DESC";
        return jdbcTemplate.query(sql, new ScheduleRowMapper(), author, updatedAt);
    }

    // 단일 일정 조회
    public Optional<Schedule> findById(Long id) {
        String sql = "SELECT * FROM schedule WHERE id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new ScheduleRowMapper(), id));
    }

    // 일정 수정
    public void update(Schedule schedule) {
        String sql = "UPDATE schedule SET title = ?, author = ?, description = ?, date = ?, updated_at = ? WHERE id = ?";
        jdbcTemplate.update(sql, schedule.getTitle(), schedule.getAuthor(), schedule.getDescription(),
                schedule.getDate(), schedule.getUpdatedAt(), schedule.getId());
    }

    // 일정 삭제
    public void delete(Long id) {
        String sql = "DELETE FROM schedule WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}

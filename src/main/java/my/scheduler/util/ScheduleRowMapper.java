package my.scheduler.util;

import my.scheduler.model.Schedule;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ScheduleRowMapper implements RowMapper<Schedule> {
    @Override
    public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
        Schedule schedule = new Schedule();
        schedule.setId(rs.getLong("id"));
        schedule.setTitle(rs.getString("title"));
        schedule.setAuthor(rs.getString("author"));
        schedule.setPassword(rs.getString("password"));
        schedule.setDescription(rs.getString("description"));
        schedule.setDate(rs.getTimestamp("date").toLocalDateTime());
        schedule.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        schedule.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
        return schedule;
    }
}

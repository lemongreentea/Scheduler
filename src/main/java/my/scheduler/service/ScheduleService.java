package my.scheduler.service;

import lombok.RequiredArgsConstructor;
import my.scheduler.exception.InvalidPasswordException;
import my.scheduler.exception.ResourceNotFoundException;
import my.scheduler.model.Schedule;
import my.scheduler.model.ScheduleDTO;
import my.scheduler.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    // 일정 생성
    public Schedule createSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        schedule.setTitle(scheduleDTO.getTitle());
        schedule.setAuthor(scheduleDTO.getAuthor());
        schedule.setPassword(scheduleDTO.getPassword());
        schedule.setDescription(scheduleDTO.getDescription());
        schedule.setDate(scheduleDTO.getDate());
        LocalDateTime now = LocalDateTime.now();
        schedule.setCreatedAt(now);
        schedule.setUpdatedAt(now);

        scheduleRepository.save(schedule);
        return schedule;
    }

    // 전체 일정 조회
    public List<Schedule> getAllSchedules(String author, String updatedAt) {
        return scheduleRepository.findAll(author, updatedAt);
    }

    // 단일 일정 조회
    public Schedule getScheduleById(Long id) {
        return scheduleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("일정을 찾을 수 없습니다."));
    }

    // 일정 수정
    public void updateSchedule(Long id, ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("일정을 찾을 수 없습니다."));

        // 비밀번호 검증
        if (!schedule.getPassword().equals(scheduleDTO.getPassword())) {
            throw new InvalidPasswordException("비밀번호가 일치하지 않습니다.");
        }

        schedule.setTitle(scheduleDTO.getTitle());
        schedule.setAuthor(scheduleDTO.getAuthor());
        schedule.setDescription(scheduleDTO.getDescription());
        schedule.setDate(scheduleDTO.getDate());
        schedule.setUpdatedAt(LocalDateTime.now());

        scheduleRepository.update(schedule);
    }

    // 일정 삭제
    public void deleteSchedule(Long id, String password) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("일정을 찾을 수 없습니다."));

        if (!schedule.getPassword().equals(password)) {
            throw new InvalidPasswordException("비밀번호가 일치하지 않습니다.");
        }

        scheduleRepository.delete(id);
    }
}

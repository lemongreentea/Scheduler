package my.scheduler.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import my.scheduler.model.Schedule;
import my.scheduler.model.ScheduleDTO;
import my.scheduler.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 일정 생성
    @PostMapping
    public ResponseEntity<Schedule> createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        return ResponseEntity.ok(scheduleService.createSchedule(scheduleDTO));
    }

    // 전체 일정 조회
    @GetMapping
    public ResponseEntity<List<Schedule>> getSchedules(@RequestParam(required = false) String author,
                                                       @RequestParam(required = false) String updated_at) {
        return ResponseEntity.ok(scheduleService.getAllSchedules(author, updated_at));
    }

    // 단일 일정 조회
    @GetMapping("/{id}")
    public ResponseEntity<Schedule> getScheduleById(@PathVariable Long id) {
        return ResponseEntity.ok(scheduleService.getScheduleById(id));
    }

    // 일정 수정
    @PutMapping("/{id}")
    public ResponseEntity<String> updateSchedule(@PathVariable Long id, @RequestBody ScheduleDTO scheduleDTO) {
        scheduleService.updateSchedule(id, scheduleDTO);
        return ResponseEntity.ok("일정이 성공적으로 수정되었습니다.");
    }

    // 일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSchedule(@PathVariable Long id, @RequestBody String password) {
        scheduleService.deleteSchedule(id, password);
        return ResponseEntity.ok("일정이 성공적으로 삭제되었습니다.");
    }
}

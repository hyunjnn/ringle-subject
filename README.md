## 링글 1:1 튜터링 수강 신청 서비스

학생과 튜터 간 1:1 수업을 위한 수강 신청 서비스입니다. 수업은 30분 또는 60분 단위로 정각 혹은 30분에 시작되며, 튜터는 자신이 수업 가능한 시간대를 등록하고, 학생은 해당 시간과 수업 길이에 맞는 튜터를 선택하여 수업을 신청할 수 있습니다.

### 튜터 기능
- 수업 가능한 시간대 생성
- 수업 가능한 시간대 삭제

### 학생 기능
- 특정 기간 & 수업 길이로 예약 가능한 수업 조회
- 특정 시간대 & 수업 길이로 예약 가능한 튜터 조회
- 수업 신청 및 내역 조회

### 설계 참고 사항
- 수업 시작 시간은 정각 또는 30분만 허용 (TimeSlot)
- 60분 수업은 연속된 2개의 TimeSlot이 모두 가능한 튜터만 조회됨
- 예약 시 수업권 잔여 횟수 감소
- 예약된 스케줄은 튜터가 삭제할 수 없음

### 흐름
```
학생 A는 30분 수업 5회권을 구매 → StudentLessonPackage 생성

튜터 B는 6/1 10:00, 10:30에 수업 가능하도록 TutorSchedule 등록

학생 A가 6/1 10:00에 30분 수업을 예약함

    Booking 생성
    
    tutorSchedule.isAvailable = false
    
    StudentLessonPackage.remainingCount--
```
### 기술 스택
- Java 21, Spring Boot, Spring Data JPA, Swagger
- MySQL
- Gradle

## 테스트 방법
### 1. 도커 실행(Mysql, Adminer)
```bash
docker-compose up
```
### 2. 애플리케이션 서버 실행
### 3. localhost:8081 접속(Adminer)
- 아래 정보로 로그인 후 기본 테스트 데이터 삽입
```
서버 (host): ringle-mysql   
사용자 이름: ringle  
비밀번호: password  
데이터베이스: ringle_db 
```

### 기본 테스트 데이터 

```sql
-- time_slots (10:00, 10:30, 11:00)
INSERT INTO time_slots (start_time, created_at, updated_at) VALUES
('2025-05-27T10:00:00', NOW(), NOW()),
('2025-05-27T10:30:00', NOW(), NOW()),
('2025-05-27T11:00:00', NOW(), NOW());

-- 튜터
INSERT INTO tutors (tutor_name, tutor_email, tutor_phone_number, tutor_university, tutor_major, tutor_time_zone, tutor_status, created_at, updated_at)
VALUES ('튜터1', 'tutor@example.com', '010-0000-0000', '서울대', '영어영문과', 'Asia/Seoul', 0, NOW(), NOW());

-- 학생
INSERT INTO students (student_name, student_email, student_phone_number, student_status, student_time_zone, created_at, updated_at)
VALUES ('학생1', 'student@example.com', '010-1234-5678', 'ACTIVE', 'Asia/Seoul', NOW(), NOW());

-- 수업 패키지 & 수강권
INSERT INTO lesson_packages
(package_name, lesson_duration, total_count, valid_days, price, created_at, updated_at)
VALUES
  ('30분 수업 5회권', 'MINUTES_30', 5, 30, 15000, NOW(), NOW());


INSERT INTO student_lesson_packages (student_id, lesson_package_id, remaining_count, start_date, end_date, created_at, updated_at)
VALUES (1, 1, 5, NOW(), DATE_ADD(NOW(), INTERVAL 30 DAY), NOW(), NOW())
```

### 4. 로컬에서 API 테스트 실행
- http://localhost:8080/swagger-ui/index.html

# Scheduler

# 일정 관리 API

### 1. 일정 생성 (POST /schedule)

- **URL**: `/schedule`
- **Method**: `POST`
- **Description**: 새로운 일정을 생성하는 API. 일정에는 고유한 ID가 자동으로 부여되며, 작성일과 수정일은 동일하게 설정됨.
- **Request Body**:
  ```json
  {
    "title": "string",        // 할 일 제목 (필수)
    "author": "string",       // 작성자명 (필수)
    "password": "string",     // 비밀번호 (필수)
    "description": "string",  // 할 일 설명 (선택)
    "date": "yyyy-MM-dd HH:mm:ss"  // 일정 날짜와 시간 (필수, 형식: YYYY-MM-DD HH:mm:ss)
  }
  ```
- **Response**:
  ```json
  {
    "id": "int",              // 생성된 일정의 고유 ID
    "title": "string",        // 할 일 제목
    "author": "string",       // 작성자명
    "description": "string",  // 할 일 설명
    "date": "yyyy-MM-dd HH:mm:ss", // 일정 날짜와 시간
    "created_at": "yyyy-MM-dd HH:mm:ss", // 작성일 (날짜와 시간 포함)
    "updated_at": "yyyy-MM-dd HH:mm:ss"  // 수정일 (최초엔 작성일과 동일)
  }
  ```

---

### 2. 전체 일정 조회 (GET /schedule)

- **URL**: `/schedule`
- **Method**: `GET`
- **Description**: 등록된 일정 목록을 조회하는 API. 작성자명 또는 수정일을 기준으로 조회 가능하며, 수정일은 내림차순으로 정렬됨.
- **Query Parameters**:
  - `author` (선택): 작성자명으로 필터링.
  - `updated_at` (선택): 수정일(YYYY-MM-DD)로 필터링.
  - 두 필터 중 하나 또는 모두를 사용할 수 있으며, 조건 없이도 조회 가능.
- **Response**:
  ```json
  [
    {
      "id": "int",              // 일정의 고유 ID
      "title": "string",        // 할 일 제목
      "author": "string",       // 작성자명
      "description": "string",  // 할 일 설명
      "date": "yyyy-MM-dd HH:mm:ss", // 일정 날짜와 시간
      "created_at": "yyyy-MM-dd HH:mm:ss", // 작성일
      "updated_at": "yyyy-MM-dd HH:mm:ss"  // 수정일
    }
  ]
  ```

---

### 3. 선택 일정 조회 (GET /schedule/{id})

- **URL**: `/schedule/{id}`
- **Method**: `GET`
- **Description**: 고유 ID로 단일 일정을 조회하는 API. 비밀번호는 반환되지 않음.
- **Response**:
  ```json
  {
    "id": "int",              // 일정의 고유 ID
    "title": "string",        // 할 일 제목
    "author": "string",       // 작성자명
    "description": "string",  // 할 일 설명
    "date": "yyyy-MM-dd HH:mm:ss", // 일정 날짜와 시간
    "created_at": "yyyy-MM-dd HH:mm:ss", // 작성일
    "updated_at": "yyyy-MM-dd HH:mm:ss"  // 수정일
  }
  ```

---

### 4. 일정 수정 (PUT /schedule/{id})

- **URL**: `/schedule/{id}`
- **Method**: `PUT`
- **Description**: 고유 ID로 일정을 수정하는 API. 일정 수정 시 비밀번호가 필요하며, `할일`과 `작성자명`만 수정 가능. 비밀번호가 일치해야 수정 가능하며, 수정일은 수정한 시점으로 갱신됨.
- **Request Body**:
  ```json
  {
    "title": "string",        // 할 일 제목 (필수)
    "author": "string",       // 작성자명 (필수)
    "password": "string",     // 비밀번호 (필수, 기존 비밀번호 확인용)
    "description": "string",  // 할 일 설명 (선택)
    "date": "yyyy-MM-dd HH:mm:ss"  // 일정 날짜와 시간 (필수, 형식: YYYY-MM-DD HH:mm:ss)
  }
  ```
- **Response**:
  ```json
  {
    "message": "일정이 성공적으로 수정되었습니다."  // 성공 메시지
  }
  ```
- **Error**:
  ```json
  {
    "error": "비밀번호가 일치하지 않습니다."  // 비밀번호 오류 시
  }
  ```

---

### 5. 일정 삭제 (DELETE /schedule/{id})

- **URL**: `/schedule/{id}`
- **Method**: `DELETE`
- **Description**: 고유 ID로 일정을 삭제하는 API. 삭제 시 비밀번호가 필요하며, 비밀번호가 일치해야 삭제 가능.
- **Request Body**:
  ```json
  {
    "password": "string"      // 비밀번호 (필수, 삭제할 때 필요)
  }
  ```
- **Response**:
  ```json
  {
    "message": "일정이 성공적으로 삭제되었습니다."  // 성공 메시지
  }
  ```
- **Error**:
  ```json
  {
    "error": "비밀번호가 일치하지 않습니다."  // 비밀번호 오류 시
  }
  ```

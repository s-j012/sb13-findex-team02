# Team 200 OK

> 한국거래소 지수 정보를 수집·관리하고, 지수 데이터와 성과를 조회할 수 있는 금융 지수 관리 서비스입니다.

- 팀 협업 문서: [바로가기](https://www.notion.so/P-r-o-j-e-c-t-Findex-39cd89d755a680d7910bcc7e84a07113?source=copy_link)
- GitHub Repository: [바로가기](https://github.com/jjeong98/sb13-findex-team02)
- 배포 서비스: [바로가기](https://giving-consideration-production-4d17.up.railway.app/#/dashboard)

---

## 팀원 구성

| 팀원 | GitHub | 담당 기능 |
| --- | --- | --- |
| 김정현 | https://github.com/jjeong98 | 연동 작업 이력 조회 및 자동 배치 |
| 이소정 | https://github.com/s-j012 | 지수 정보 등록·단건 조회·수정·삭제 |
| 이예은 | https://github.com/y2ahhh | 지수 정보·지수 데이터 연동 |
| 장현서 | https://github.com/totobabo01 | 지수 차트·성과 조회 및 CSV 내보내기 |
| 조혜령 | https://github.com/gpfud09 | 지수 정보 목록·요약 조회 및 자동 연동 설정 |
| 최성웅 | https://github.com/sungwoong-svg | 지수 데이터 CRUD 및 커서 페이지네이션 |

---

## 프로젝트 소개

- 한국거래소의 지수 정보와 일별 지수 데이터를 관리하는 Spring Boot 기반 백엔드 시스템 구축
- 공공데이터포털 Open API를 통한 지수 정보·지수 데이터 연동
- 지수 정보 및 지수 데이터 CRUD 제공
- 필터링·정렬·커서 페이지네이션을 적용한 목록 조회
- 지수 차트, 이동평균선, 기간별 성과 및 순위 조회
- 수동 연동과 스케줄러 기반 자동 연동 지원
- 연동 작업의 성공·실패 이력 관리
- 프로젝트 기간: 2026.07.08 ~ 2026.07.20

---

## 기술 스택

### Backend

- Java 17
- Spring Boot 3.5.14
- Spring Web
- Spring Data JPA
- QueryDSL
- Spring Validation
- Spring Scheduler
- RestClient
- MapStruct
- Springdoc OpenAPI / Swagger
- P6Spy
- Lombok
- Gradle

### Database

- PostgreSQL

### 배포 및 협업

- Railway
- Git & GitHub
- Notion
- Discord
- ZEP

---

## 팀원별 구현 기능 상세

### 김정현

- **연동 작업 목록 조회**
  - 지수 정보 및 지수 데이터 연동 작업 이력 조회 기능 구현
  - 작업 유형, 작업자, 처리 결과, 대상 날짜를 기준으로 연동 이력 제공
- **연동 성공·실패 이력 저장**
  - 수동·자동 연동 결과를 성공 또는 실패 상태로 기록
  - 연동 작업의 처리 결과를 추적할 수 있도록 이력 관리
- **자동 배치**
  - 스케줄러를 이용해 활성화된 자동 연동 대상의 데이터 동기화
  - 정해진 주기에 맞춰 자동 연동 작업 실행

### 이소정

- **지수 정보 등록**
  - 지수 분류, 지수명, 채용 종목 수, 기준 시점, 기준 지수 입력 처리
  - 요청값 검증 및 지수 정보 저장
- **지수 정보 단건 조회**
  - 지수 정보 ID를 기준으로 상세 정보 조회
- **지수 정보 수정·삭제**
  - 등록된 지수 정보 수정 기능 구현
  - 지수 정보 및 연관 데이터 삭제 처리

### 이예은

- **지수 정보 연동**
  - 공공데이터포털 Open API에서 지수 정보를 조회해 데이터베이스에 저장
  - 외부 응답을 내부 지수 정보 형식으로 변환하고 동기화
- **지수 데이터 연동**
  - 지정한 기간의 일별 지수 데이터를 외부 API에서 조회
  - 지수별 데이터를 저장하고 연동 성공·실패 결과 기록

### 장현서

- **지수 차트 조회**
  - 기간별 지수 종가 차트 데이터 제공
  - 5일·20일 이동평균선 계산 및 조회
- **지수 성과 랭킹 조회**
  - 일간·주간·월간 기준 등락과 등락률 계산
  - 등락률을 기준으로 지수 성과 순위 제공
- **관심 지수 성과 조회**
  - 즐겨찾기로 등록한 지수의 기간별 성과 제공
- **지수 데이터 CSV 내보내기**
  - 지수와 기간 조건을 적용한 데이터를 CSV 파일로 다운로드
  - 요청한 정렬 조건을 결과 파일에 반영

### 조혜령

- **지수 정보 목록 조회**
  - 검색·필터링·정렬 조건을 적용한 지수 정보 목록 제공
  - 커서 페이지네이션을 적용해 목록 조회
- **지수 정보 요약 목록 조회**
  - 대시보드와 선택 목록에서 사용할 지수 요약 정보 제공
- **자동 연동 설정 목록 조회**
  - 지수별 자동 연동 활성화 상태 조회
  - 필터링·정렬·커서 페이지네이션 적용
- **자동 연동 설정 수정**
  - 지수별 자동 연동 활성화 여부 변경

### 최성웅

- **지수 데이터 등록·수정·삭제**
  - 지수 데이터 등록 및 기준일 중복 검증
  - 기존 데이터 수정과 삭제 기능 구현
- **지수 데이터 목록 조회**
  - 지수 ID와 기간 조건을 이용한 필터링
  - 기준일과 ID를 함께 사용하는 커서 페이지네이션 구현
  - 정렬 필드와 오름차순·내림차순 조건 지원
- **예외 처리 및 검증**
  - 존재하지 않는 지수·지수 데이터에 대한 예외 처리
  - 숫자 필드의 범위와 음수 입력 검증

---

## 주요 기능

### 1. 지수 정보 관리

- 지수 정보 등록·조회·수정·삭제
- 지수 분류와 이름 기반 검색
- 즐겨찾기 설정
- 커서 페이지네이션

### 2. 지수 데이터 관리

- 일별 지수 데이터 등록·조회·수정·삭제
- 지수와 기간에 따른 조건 조회
- 동적 정렬 및 커서 페이지네이션
- CSV 다운로드

### 3. 대시보드

- 지수 종가 차트
- 5일·20일 이동평균선
- 관심 지수 성과 조회
- 일간·주간·월간 성과 및 순위 조회

### 4. 외부 데이터 연동

- 공공데이터포털 Open API 연동
- 지수 정보 수동 동기화
- 기간별 지수 데이터 수동 동기화
- 스케줄러 기반 자동 동기화
- 성공·실패 작업 이력 저장

---

## 파일 구조

```text
src/main
├── java/com/findex/team02
│   ├── Team02Application.java
│   ├── autosync
│   │   ├── controller
│   │   ├── dto
│   │   │   ├── request
│   │   │   └── response
│   │   ├── entity
│   │   ├── mapper
│   │   ├── repository
│   │   └── service
│   ├── global
│   │   ├── config
│   │   ├── dto
│   │   ├── entity
│   │   ├── exception
│   │   └── type
│   ├── indexdata
│   │   ├── controller
│   │   ├── dto
│   │   │   ├── request
│   │   │   └── response
│   │   ├── entity
│   │   ├── mapper
│   │   ├── repository
│   │   └── service
│   ├── indexinfo
│   │   ├── controller
│   │   ├── date
│   │   ├── dto
│   │   │   ├── request
│   │   │   └── response
│   │   ├── entity
│   │   ├── mapper
│   │   ├── repository
│   │   │   └── projection
│   │   └── service
│   └── sync
│       ├── controller
│       ├── dto
│       │   ├── request
│       │   └── response
│       ├── entity
│       ├── mapper
│       ├── repository
│       ├── scheduler
│       └── service
└── resources
    ├── application.yaml
    ├── application-dev.yaml
    ├── application-prod.yaml
    └── schema.sql
build.gradle
README.md
```

---

## 구현 홈페이지

- 배포 페이지: [바로가기](https://giving-consideration-production-4d17.up.railway.app/#/dashboard)
- Swagger UI: [바로가기](https://giving-consideration-production-4d17.up.railway.app/swagger-ui/index.html)
- 시연 영상: [바로가기](https://drive.google.com/file/d/1Jr22GwO3k-kIXneT4GUGYjHD2BE-EUD7/view?usp=drive_link)

---

## 프로젝트 문서

- 팀 Notion: [바로가기](https://www.notion.so/P-r-o-j-e-c-t-Findex-39cd89d755a680d7910bcc7e84a07113?source=copy_link)
- 발표 자료: [바로가기](https://drive.google.com/file/d/1MBIGbEVO5dEzz46bo7f4oqltStoiuaf5/view?usp=drive_link)

# 프로젝트 명세서: VibeApp

최소 기능을 넘어 게시글 관리 기능을 포함한 스프링부트 애플리케이션 'vibeapp'의 프로젝트 명세서입니다.

## 프로젝트 개요
- **목적**: 고에너지 환경에서 지식의 노드(게시글)를 관리하는 커뮤니티 플랫폼
- **스택**: JDK 25, Spring Boot 4.0.1, Thymeleaf, Spring Web

## 아키텍처 개요 (기능형 구조)
프로젝트는 유지보수성을 위해 도메인 기반의 기능형 패키지 구조를 따릅니다.

### 1. Backend (`com.example.vibeapp`)
- **[home]**: 랜딩 페이지 및 공통 API 처리 (`HomeController`)
- **[post]**: 게시글 도메인 관련 전체 로직 처리
    - `Post`: 게시글 엔티티
    - `PostController`: 라우팅 및 뷰 매핑
    - `PostService`: 비즈니스 로직
    - `PostRepository`: 인메모리 데이터 저장소

### 2. Frontend (`src/main/resources/templates`)
- **home/**: `home.html` (메인 화면)
- **post/**: `posts.html`, `post_detail.html`, `post_new_form.html`, `post_edit_form.html`

## 구현된 주요 기능
1. **게시글 목록**: 5개 단위 페이징 처리
2. **상세 보기**: 조회수 증가 로직 포함
3. **게시글 생성/수정**: 실시간 서버 시간 기반 생성일/수정일 관리
4. **게시글 삭제**: 안정성을 위해 POST 방식의 삭제 프로세스 준수

## 검증 방법
- **빌드**: `./gradlew compileJava`로 정적 타입 및 컴파일 무결성 확인
- **실행**: `./gradlew bootRun` 후 `http://localhost:8080` 접속 확인

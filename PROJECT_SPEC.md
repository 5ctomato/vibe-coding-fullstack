# 프로젝트 명세서: VibeApp

최소 기능을 갖춘 스프링부트 애플리케이션 'vibeapp'의 프로젝트 명세서입니다.

## 사용자 검토 필요 사항

> [!IMPORTANT]
> 본 프로젝트는 **JDK 25** 및 **Spring Boot 4.0.1** 기반으로 설정되었습니다. 이는 매우 최신(혹은 미래 지향적인) 사양으므로, 현재 개발 환경에서 해당 버전을 지원하는지 확인이 필요합니다.

## 제안된 변경 사항

### [프로젝트 골격 생성]
스프링부트 프로젝트 구동에 필요한 최소한의 기본 파일들을 생성합니다.

#### [신규] [build.gradle](file:///c:/ai_OK/git/vibe-coding-fullstack/server/vibeapp/build.gradle)
Gradle 9.3.0 및 Groovy DSL을 사용하는 빌드 스크립트입니다.
- Spring Boot 플러그인 (`org.springframework.boot` 버전 4.0.1)
- 의존성 관리 플러그인 (`io.spring.dependency-management`)
- Java 25 호환 설정 포함

#### [신규] [VibeApp.java](file:///c:/ai_OK/git/vibe-coding-fullstack/server/vibeapp/src/main/java/com/example/vibeapp/VibeApp.java)
애플리케이션의 시작점인 메인 클래스입니다.

#### [신규] [application.yml](file:///c:/ai_OK/git/vibe-coding-fullstack/server/vibeapp/src/main/resources/application.yml)
YAML 형식의 프로젝트 설정 파일입니다.

## 검증 계획

### 자동화 테스트
- `./gradlew build`: 프로젝트 빌드 성공 및 기본 구조 검증

### 수동 검증
- 애플리케이션 실행 로그 확인: "Started VibeApp in ..." 메시지 출력 확인
- JDK 버전 확인: 터미널에서 `java -version` 결과가 25 이상인지 확인

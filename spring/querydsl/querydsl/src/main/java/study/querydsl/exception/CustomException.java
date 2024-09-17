package study.querydsl.exception;

public class CustomException extends RuntimeException {

    private final HttpStatus httpStatus;

    // HTTP 상태 코드와 사용자 정의 메시지를 받는 생성자
    public CustomException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    // HTTP 상태 코드, 사용자 정의 메시지, 원인을 받는 생성자
    public CustomException(HttpStatus httpStatus, String message, Throwable cause) {
        super(message, cause);
        this.httpStatus = httpStatus;
    }

    // HTTP 상태 코드를 반환하는 getter 메서드
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    // toString 메서드를 오버라이드하여 HTTP 상태 코드와 메시지를 포함한 문자열 반환
    @Override
    public String toString() {
        return "CustomException{" +
                "httpStatus=" + httpStatus.getCode() + " " + httpStatus.getMessage() +
                ", message='" + getMessage() + '\'' +
                '}';
    }
}
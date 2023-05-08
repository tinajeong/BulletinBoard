package world.tina.bulletinboard.exception;

public class JsonConversionException extends RuntimeException {
	private static final String DEFAULT_MESSAGE = "JSON으로 변환하는데 실패했습니다.";

	public JsonConversionException() {
		super(DEFAULT_MESSAGE);
	}

	public JsonConversionException(Throwable cause) {
		super(DEFAULT_MESSAGE, cause);
	}
}

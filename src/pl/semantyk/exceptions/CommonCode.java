package pl.semantyk.exceptions;

public enum CommonCode implements ErrorCode {

    NULL_POINTER(201),

    ILLEGAL_ARGUMENT(202),

    INTERRUPTED_THREAD(203);

    private CommonCode(int code) {
        this.code = code;
    }

    private final int code;

    @Override
    public int getErrorCode() {
        return code;
    }

    public static CommonCode enumOf(int code) {
        for (CommonCode b : CommonCode.values()) {
            if (code == b.getErrorCode()) {
                return b;
            }
        }
        return null;
    }
}

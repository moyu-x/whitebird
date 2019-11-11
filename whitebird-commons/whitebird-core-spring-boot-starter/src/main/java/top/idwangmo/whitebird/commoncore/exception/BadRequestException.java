package top.idwangmo.whitebird.commoncore.exception;

/**
 * 参数错误的异常.
 *
 * @author idwangmo
 */
public class BadRequestException extends RuntimeException{

    private static final long serialVersionUID = -5013387584213193695L;

    public BadRequestException(String message) {
        super(message);
    }
}

package top.idwangmo.whitebird.webspringbootstarter.exception;

/**
 * 业务异常.
 *
 * @author idwangmo
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -3135662995183485593L;

    public BusinessException(String message) {
        super(message);
    }
}


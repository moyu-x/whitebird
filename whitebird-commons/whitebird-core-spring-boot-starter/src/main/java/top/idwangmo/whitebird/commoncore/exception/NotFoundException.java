package top.idwangmo.whitebird.commoncore.exception;

/**
 * 未发现此资源异常.
 *
 * @author idwangmo
 */
public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = -545365354482537829L;

    public NotFoundException(String message) {
        super(message);
    }
}

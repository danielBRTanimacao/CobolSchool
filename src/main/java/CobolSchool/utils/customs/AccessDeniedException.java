package CobolSchool.utils.customs;

public class AccessDeniedException extends RuntimeException{
    public AccessDeniedException(String msg) {
        super(msg);
    }

    public AccessDeniedException(String msg, Throwable err) {
        super(msg, err);
    }
}

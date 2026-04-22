package CobolSchool.utils.customs;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String msg) {
        super(msg);
    }

    public NotFoundException(String msg, Throwable err) {
        super(msg, err);
    }
}

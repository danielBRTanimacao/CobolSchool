package CobolSchool.utils.customs;

public class TokenException extends RuntimeException{
    public TokenException(String msg) {
        super(msg);
    }

    public TokenException(String msg, Throwable err) {
        super(msg, err);
    }
}

package oncall.error;

public class DomainNotExistException extends OnCallException {
    public DomainNotExistException(String domainName) {
        super(domainName + "을 찾을 수 없습니다.");
    }
}

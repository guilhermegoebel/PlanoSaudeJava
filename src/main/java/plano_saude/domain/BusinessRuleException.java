package plano_saude.domain;

public class BusinessRuleException extends RuntimeException {
    public BusinessRuleException(String e) {
        super(e);
    }
}

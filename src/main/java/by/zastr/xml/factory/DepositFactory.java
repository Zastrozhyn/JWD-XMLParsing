package by.zastr.xml.factory;

import by.zastr.xml.builder.DepositBuilder;
import by.zastr.xml.builder.DepositDomBuilder;
import by.zastr.xml.builder.DepositSaxBuilder;
import by.zastr.xml.builder.DepositStaxBuilder;
import by.zastr.xml.exception.XmlDepositException;

public class DepositFactory {
    private DepositFactory(){}

    public enum TypeParser{
        SAX, STAX, DOM
    }

    public static DepositBuilder createDepositBuilder(TypeParser typeParser)throws XmlDepositException {
        DepositBuilder depositBuilder;
        switch (typeParser){
            case DOM:
                depositBuilder = new DepositDomBuilder();
                break;
            case SAX:
                depositBuilder = new DepositSaxBuilder();
                break;
            case STAX:
                depositBuilder = new DepositStaxBuilder();
                break;
            default: throw new XmlDepositException("Invalid parser type, can't create builder");
        }
        return depositBuilder;
    }

}

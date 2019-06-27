package util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * Esta classe é responsável por implementar a opcao de limitar o tamanho e upperCase
 * de um componente de entrada como exemplo: jtextfield, jpasswordfield.
 * @author Marcilio-Pc
 */
public class JTextFieldTamanhoMaximo extends PlainDocument {

    private final int limit;

    private boolean toUppercase = false;
    
    /**
     * Construtor da classe define o tamanho máximo de caracteres de entrada no componente.
     * @param limit 
     */
    public JTextFieldTamanhoMaximo(int limit) {
        super();
        this.limit = limit;
    }

    /**
     * Construtor da classe define o tamanho e a opcao de upperCase de
     * caracteres de entrada do componente.
     *
     * @param limit int - Seta o limite máximo de entrada de caracteres
     * @param upper Boolean - True ou False para usar upperCase
     */
    public JTextFieldTamanhoMaximo(int limit, boolean upper) {
        super();
        this.limit = limit;
        toUppercase = upper;
    }

    /**
     * Reescrita de método da classe herdada.
     * @param offset
     * @param str
     * @param attr
     * @throws BadLocationException 
     */
    @Override
    public void insertString(int offset, String str, AttributeSet attr)
            throws BadLocationException {
        if (str == null) {
            return;
        }

        if ((getLength() + str.length()) <= limit) {
            if (toUppercase) {
                str = str.toUpperCase();
            }
            super.insertString(offset, str, attr);
        }
    }

}

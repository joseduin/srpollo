package com.patricia.srpollo.utils;

/**
 * Created by Jose on 4/3/2018.
 */
import android.text.InputFilter;
import android.text.Spanned;

public class Input {

    /**
     * Input con solo numeros y decimales
     */
    public static InputFilter decimalEditText(final int decimal) {
        InputFilter filter = new InputFilter() {
            // Preguntar Maximo ponometro
            final int maxDigitsBeforeDecimalPoint = 10;
            final int maxDigitsAfterDecimalPoint = decimal;

            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                StringBuilder builder = new StringBuilder(dest);
                builder.replace(dstart, dend, source
                        .subSequence(start, end).toString());
                if (!builder.toString().matches(
                        "(([0-9]{1})([0-9]{0,"+(maxDigitsBeforeDecimalPoint-1)+"})?)?(\\.[0-9]{0,"+maxDigitsAfterDecimalPoint+"})?"

                )) {
                    if(source.length()==0)
                        return dest.subSequence(dstart, dend);
                    return "";
                }

                return null;
            }
        };
        return filter;
    }

}

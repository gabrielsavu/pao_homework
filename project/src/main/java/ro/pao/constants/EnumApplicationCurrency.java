package ro.pao.constants;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Optional;

public enum EnumApplicationCurrency {
    AED(100, "AED"),
    AUD(100, "AUD"),
    BGN(100, "BGN"),
    BRL(100, "BRL"),
    CAD(100, "CAD"),
    CHF(100, "CHF"),
    CNY(100, "CNY"),
    CZK(100, "CZK"),
    DKK(100, "DKK"),
    EGP(100, "EGP"),
    EUR(100, "EUR"),
    GBP(100, "GBP"),
    HRK(100, "HRK"),
    HUF(100, "HUF"),
    INR(100, "INR"),
    JPY(100, "JPY"),
    KRW(100, "KRW"),
    MDL(100, "MDL"),
    MXN(100, "MXN"),
    NOK(100, "NOK"),
    NZD(100, "NZD"),
    PLN(100, "PLN"),
    RSD(100, "RSD"),
    RUB(100, "RUB"),
    SEK(100, "SEK"),
    THB(100, "THB"),
    TRY(100, "TRY"),
    UAH(100, "UAH"),
    USD(100, "USD"),
    XAU(100, "XAU"),
    XDR(100, "XDR"),
    ZAR(100, "ZAR"),
    RON(100, "RON");

    private int scale;

    private String symbol;

    EnumApplicationCurrency(int scale, String symbol) {
        this.scale = scale;
        this.symbol = symbol;
    }

    public boolean isMe(String name) {
        if (StringUtils.isBlank(name)) {
            return false;
        }
        return name.contains(this.name());
    }

    /**
     * It's a slow method, don't use it when performance is required.
     *
     * @param currency the currency in string format (RON, EUR, ...)
     * @return It returns the currency in enum format or null if the string is empty or null.
     */
    public static EnumApplicationCurrency whatCurrency(String currency) {
        Optional<EnumApplicationCurrency> result = Arrays.stream(EnumApplicationCurrency.values()).filter(aa -> aa.isMe(currency)).findFirst();
        return result.orElse(null);
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}

/**
 *  This class for maintaining the various supported domestic currencies. All methods are static
 *  and this class should NEVER be instantiated.
 */

package com.randallgr.cryptocurrencytracker;

public final class DomesticCurrencyUtil {

    public static final String [] SUPPORTED_DOMESTIC_CURRENCIES = {"U.S. Dollars", "Euros",
            "British Pounds", "Canadian Dollars", "Chinese Yuan"};

    public static final String [] SUPPORTED_DOMESTIC_CURRENCY_SYMBOLS = {"$", "€", "£", "C$", "¥"};

    /**
     * Cryptocurrency objects keep the domestic currency ticker because that is whar is needed to
     * interact with the CryptoCompare API. Methods below take in that ticker and then return
     * either that or the currency's full name or symbol.
     */
    public static String getDomesticCurrencyName(String domesticTicker) {
        return SUPPORTED_DOMESTIC_CURRENCIES[ determineIndex(domesticTicker) ];
    }

    public static String getDomesticCurrencySymbol(String domesticTicker) {
        return SUPPORTED_DOMESTIC_CURRENCY_SYMBOLS[ determineIndex(domesticTicker) ];
    }


    private static int determineIndex(String domesticTicker) {
        if(domesticTicker.equals("USD"))
            return 0;
        else if(domesticTicker.equals("EUR"))
            return 1;
        else if(domesticTicker.equals("GBP"))
            return 2;
        else if(domesticTicker.equals("CAD"))
            return 3;
        else if(domesticTicker.equals("CNY"))
            return 4;
        else
            return 0;
    }

    private DomesticCurrencyUtil() {
        // empty private constructor to prevent it from being instantiated
    }
}

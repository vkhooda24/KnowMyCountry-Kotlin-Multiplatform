package com.vkhooda24.utils

/**
 * Created by Vikram Hooda on 12/25/18.
 */
object StringUtil {

    fun formatNumberWithCommas(number: String): String {
        return try {
            //            NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
            //            return numberFormat.format(number);
            String.format("%,d", Integer.valueOf(number))
        } catch (e: Exception) {
            number
        }

    }
}

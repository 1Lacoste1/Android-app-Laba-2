package com.example.safronovmmlaba2.utils

object InputUtils {

    /**
     * Преобразует строку в Int, удаляя пробелы. Если строка некорректная, возвращает значение по умолчанию.
     *
     * @param input строка для преобразования
     * @param defaultValue значение по умолчанию в случае ошибки
     * @return преобразованное число или defaultValue
     */
    fun parseInt(input: String, defaultValue: Int = 0): Int {
        return try {
            input.replace(" ", "").toInt()
        } catch (e: NumberFormatException) {
            defaultValue
        }
    }
}
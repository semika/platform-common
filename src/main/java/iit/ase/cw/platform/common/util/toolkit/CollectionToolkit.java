/*
 * ====================================================================
 * Copyright  (c) : 2021 by Kaleris. All rights reserved.
 * ====================================================================
 *
 * The copyright to the computer software herein is the property of Kaleris
 * The software may be used and/or copied only
 * with the written permission of Kaleris or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */

package iit.ase.cw.platform.common.util.toolkit;

import iit.ase.cw.platform.common.util.constant.ThaproGlobalConstant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class CollectionToolkit {

    private CollectionToolkit() {
    }

    // -- Start List Operations --

    /**
     * find list is null or empty. null handled.
     */
    public static boolean isListEmpty(List list) {
        return null == list || list.isEmpty();
    }

    /**
     * find list is null or empty. null handled.
     */
    public static boolean isListNotEmpty(List list) {
        return !isListEmpty(list);
    }

    /**
     * find set is null or empty.
     */
    public static boolean isSetEmpty(Set set) {
        return null == set || set.isEmpty();
    }

    /**
     * return entities which are exist in both list.
     *
     * @return list of matching items.
     */
    public static <T> List<T> getMatchingItems(List<T> first, List<T> second) {
        List<T> result = new ArrayList<T>();
        if (!isListEmpty(first) && !isListEmpty(second)) {
            for (T firstT : first) {
                for (T secondT : second) {
                    if (firstT.equals(secondT)) {
                        result.add(secondT);
                    }
                }
            }
        }
        return result;
    }

    /**
     * if considerNull true mean one of list null or empty and other have values it matches.
     */
    public static <T> List<T> getMatchingItems(List<T> first, List<T> second, boolean considerNull) {
        if (considerNull) {
            if (isListEmpty(first) && !isListEmpty(second)) {
                return second;
            }
            if (isListEmpty(second) && !isListEmpty(first)) {
                return first;
            }
        }
        return getMatchingItems(first, second);
    }

    /**
     * if considerNull true mean one of list null or empty and other have values it matches.
     */
    public static boolean isMatchAnyItem(List<String> firstList, List<String> secList, boolean considerNull) {
        if (considerNull) {
            if (isListEmpty(firstList) || isListEmpty(secList)) {
                return true;
            }
        }
        return isMatchAnyItem(firstList, secList);
    }

    /**
     * check is any item contain in both list.
     */
    public static boolean isMatchAnyItem(List<String> firstList, List<String> secList) {
        if (firstList == null || secList == null) {
            return false;
        }
        for (String item : firstList) {
            if (StringToolkit.isNotEmpty(item)) {
                for (String secItem : secList) {
                    if (item.equals(secItem)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * check two list are idle.
     */
    public static <E> boolean isMatchAllItem(List<E> firstList, List<E> secList) {
        return isMatchAllItem(firstList, secList, false);
    }

    /**
     * check two list are idle.
     *
     * @param emptyListConsiderAsTrue empty two list consider as same +
     */
    public static <E> boolean isMatchAllItem(List<E> firstList, List<E> secList, boolean emptyListConsiderAsTrue) {
        if (emptyListConsiderAsTrue && isListEmpty(firstList) && isListEmpty(secList)) {
            return true;
        }
        if (isListEmpty(firstList) || isListEmpty(secList)) {
            return false;
        }
        if (firstList.size() != secList.size()) {
            return false;
        }
        return firstList.containsAll(secList);
        //return true;
    }

    public static <T> String convertTypeToString(List<T> listItems, Function<T, String> function) {
        return convertTypeToString(listItems, function, ThaproGlobalConstant.Symbol.COMMA_SPACE);
    }

    public static <T> String convertTypeToString(List<T> listItems, Function<T, String> function, String paramSymbol) {
        if (isListEmpty(listItems)) {
            return ThaproGlobalConstant.Symbol.EMPTY_STR;
        }
        return listItems.stream().map(function).collect(Collectors.joining(paramSymbol));
    }

    /**
     * convert list to string seperate by symbol .
     *
     * @return list to string.
     */
    public static String convertToString(List<String> listItems, String paramSymbol) {
        if (isListEmpty(listItems)) {
            return ThaproGlobalConstant.Symbol.EMPTY_STR;
        }
        String symbol = StringToolkit.nvl(paramSymbol, ThaproGlobalConstant.Symbol.COMMA);
        StringBuilder result = new StringBuilder();
        for (String item : listItems) {
            result.append(item);
            result.append(symbol);
        }
        return StringToolkit.removeLastElement(result.toString());
    }

    /**
     * convert list to comma separate string.
     *
     * @return list to string.
     */
    public static String convertToCommaSeparatedString(List<String> listItems) {
        return convertToString(listItems, ThaproGlobalConstant.Symbol.COMMA);
    }

    /**
     * convert string which separate by symbol to list. Ex : A,B,C to list contain A,B and C
     */
    public static List<String> convertToList(String items, String paramSymbol) {
        List<String> result = new ArrayList<String>();
        String symbol = StringToolkit.nvl(paramSymbol, ThaproGlobalConstant.Symbol.COMMA);
        if (StringToolkit.isEmpty(items)) {
            return result;
        }
        String[] itemArray = items.split(symbol);
        if (itemArray != null) {
            for (String item : itemArray) {
                if (StringToolkit.isNotEmpty(item)) {
                    result.add(item.trim());
                }
            }
        }
        return result;
    }

    /**
     * call {@link #convertToList(String)} with symbol as Comma.
     */
    public static List<String> convertToList(String items) {
        return convertToList(items, ThaproGlobalConstant.Symbol.COMMA);
    }

    /**
     * Convert Array To List. Handle Null.
     */
    @SafeVarargs
    public static <T> List<T> convertToList(boolean avoidNull, T... items) {
        List<T> result = new ArrayList<>();
        if (!isArrayEmpty(items)) {
            if (avoidNull) {
                populateList(result, items);
            } else {
                result = Arrays.asList(items);
            }
        }
        return result;
    }

    /**
     * covert comma separate string to long array.
     */
    public static List<Long> convertToLongList(String longList, String symbol) {
        List<Long> result = new ArrayList();
        List<String> strList = convertToList(longList, symbol);
        for (String longStr : strList) {
            result.add(NumberToolkit.parseLong(longStr));
        }
        return result;
    }

    /**
     * covert comma separate string to long array.
     */
    public static List<Long> convertToLongList(String longList) {
        return convertToLongList(longList, ThaproGlobalConstant.Symbol.COMMA);
    }

    /**
     * covert comma separate string to float array.
     */
    public static List<Float> convertToFloatList(String longList, String symbol) {
        List<Float> result = new ArrayList();
        List<String> strList = convertToList(longList, symbol);
        for (String longStr : strList) {
            result.add(NumberToolkit.parseFloat(longStr));
        }
        return result;
    }

    /**
     * covert comma separate string to float array.
     */
    public static List<Float> convertToFloatList(String longList) {
        return convertToFloatList(longList, ThaproGlobalConstant.Symbol.COMMA);
    }

    /**
     * convert parameter list to mention size of list. if list size is 33 and sub list size is 10, return 3 list which
     * contain 10 item in each list and 3 lenght list.   * @param list
     */
    public static <E> List<List<E>> getSplitList(List<E> list, int paramSubListSize) {
        List<List<E>> result = new ArrayList<List<E>>();
        if (isListEmpty(list)) {
            return result;
        }
        int loopNo = 0;
        int subListSize = Math.max(paramSubListSize, 1);
        while ((loopNo * subListSize) < list.size()) {
            int toIndex = Math.min((loopNo * subListSize) + subListSize, list.size());
            List<E> subList = list.subList(loopNo * subListSize, toIndex);
            result.add(subList);
            loopNo++;
        }
        return result;
    }

    /**
     * get first element of list.
     */
    public static <T> T getFirstElement(List<T> list) {
        if (isListEmpty(list)) {
            return null;
        }
        return list.get(0);
    }

    /**
     * get last element of list.
     */
    public static <T> T getLastElement(List<T> list) {
        if (isListEmpty(list)) {
            return null;
        }
        return list.get(list.size() - 1);
    }

    /**
     * conver set to list.
     */
    public static <T> List<T> getListFromSet(Set<T> set) {
        if (isSetEmpty(set)) {
            return Collections.emptyList();
        }
        return new ArrayList<>(set);
    }

    /**
     * populate list. adding not null values.
     */
    @SafeVarargs
    public static <T> void populateList(List<T> list, T... items) {
        if (null != list) {
            for (T item : items) {
                if (null != item) {
                    list.add(item);
                }
            }
        }
    }

    /**
     * populate list. adding not null values.
     */
    public static <T> void populateList(boolean allowDuplicate, List<T> list, T... items) {
        if (null != list) {
            for (T item : items) {
                if (null != item && (allowDuplicate || !list.contains(item))) {
                    list.add(item);
                }
            }
        }
    }

    // -- End List Operations --

    // -- Start Array Operations --

    public static <E> boolean isArrayEmpty(E[] array) {
        return null == array || array.length == 0;
    }

    /**
     * convert string to array.
     */
    public static String[] convertToArray(String string) {
        return convertToArray(string, ThaproGlobalConstant.Symbol.COMMA);
    }

    /**
     * convert string to array.
     */
    public static String[] convertToArray(String string, String separator) {
        if (StringToolkit.isNotEmpty(string)) {
            return string.split(separator);
        }
        return new String[0];
    }

    /**
     * convert long list to array.
     */
    public static Long[] convertToLongArray(List<Long> dataList) {
        if (isListEmpty(dataList)) {
            return new Long[0];
        }
        return dataList.toArray(new Long[0]);
    }

    /**
     * convert string list to array
     */
    public static String[] convertToStringArray(List<String> dataList) {
        if (dataList == null) {
            return new String[0];
        }
        return dataList.toArray(new String[0]);
    }

    // -- End Array Operations --

    // -- Start Map Operations --

    /**
     * convert map to linked hash map. handled null.
     *
     * @param map original map to convert.
     * @return linked hash map
     */
    public static LinkedHashMap<String, String> convertMapToLinkedHashMap(Map<String, String> map) {
        if (null != map) {
            return new LinkedHashMap<String, String>(map);
        }
        return new LinkedHashMap<String, String>();
    }

    // -- End Map Operations --

}
